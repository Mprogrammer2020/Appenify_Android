package com.calgiary.appenify;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appenify.adapter.Modadapter;
import com.appenify.adapter.SlideMenuFunctions;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class My_invitations extends Activity {
	ListView search_ListView;
	TextView header;
	RelativeLayout menu_button;
	SlidingMenu smenu;
	RelativeLayout bck,fwd;
	Modadapter madapter;
	ActionBar abar;
	ImageView imageFront;
	LinearLayout createEventTab, homeEventTab, notificationEventTab, favoriteEventTab, eventNearTabLay;
	Bundle b;
	int layoutHeight = 65;
	
	int[] images = { R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3, R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3 };
	String[] text = { "Love Electro", "My Own Event News", "Love Electro",
			"Love Electro", "Love Electro", "My Own Event News",
			"Love Electro", "Love Electro" };

	Button pendingTab, declinedTab;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	//	overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.act_my_inviations);
		search_ListView = (ListView) findViewById(R.id.listview);
		abar = (ActionBar) findViewById(R.id.abar1);
		header = (TextView) abar.findViewById(R.id.action_text);
		madapter = new Modadapter(My_invitations.this, text, images);
		search_ListView.setAdapter(madapter);
		header.setText("My Invitations");
		menu_button = (RelativeLayout) abar.findViewById(R.id.action_menu);
		smenu = com.appenify.adapter.commonutilities
				.setSlidingMenu(My_invitations.this);
		
		pendingTab = (Button)findViewById(R.id.pendingButton);
		declinedTab = (Button)findViewById(R.id.declinedButton);
		imageFront = (ImageView)abar.findViewById(R.id.action_edit);
		
		createEventTab = (LinearLayout)findViewById(R.id.createEventTab);
		homeEventTab = (LinearLayout)findViewById(R.id.homeEventTab);
		notificationEventTab = (LinearLayout)findViewById(R.id.notificationEventTab);
		favoriteEventTab = (LinearLayout)findViewById(R.id.favoriteEventTab);
		eventNearTabLay = (LinearLayout)findViewById(R.id.eventNearTabLay);
		
		b = new Bundle();
		
		getPixel(layoutHeight);
		eventNearTabLay.getLayoutParams().height = layoutHeight;
		
		fwd=(RelativeLayout)findViewById(R.id.action_edit_container);
		fwd.setBackground(null);
		imageFront.setBackground(null);
		new SlideMenuFunctions(smenu, My_invitations.this);
		menu_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				smenu.showMenu();
			}
		});
		
		
		search_ListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
		//	startActivity(new Intent(My_invitations.this,Event_detail.class));
			
			Intent i = new Intent(My_invitations.this, Event_detail.class);
			
			 b.putString("detail","invitation");
			 i.putExtras(b);
			startActivity(i);
		//	finish();
			
			}
		});
	}
	
	
	public void invitationLayout(View view) {

		switch (view.getId()) {

		case R.id.pendingButton:
			pendingTab.setBackgroundColor(Color.parseColor("#D84949"));
			declinedTab.setBackgroundColor(Color.WHITE);
			pendingTab.setTextColor(Color.WHITE);
			declinedTab.setTextColor(Color.BLACK);
			
			break;
			
			
			case R.id.declinedButton:
				declinedTab.setBackgroundColor(Color.parseColor("#D84949"));
				pendingTab.setBackgroundColor(Color.WHITE);
				declinedTab.setTextColor(Color.WHITE);
				pendingTab.setTextColor(Color.BLACK);
			
			break;
			
			
		}
		
	}
	
	public void tabCallFunc(View view){
		switch (view.getId()) {
		
	
			
		case R.id.createEventTab:
			Intent i = new Intent(My_invitations.this, TabAct.class);
			
			 b.putString("condition","tb1");
			 i.putExtras(b);
			startActivity(i);
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			finish();
			
			break;

		case R.id.homeEventTab:
			backMethod();
			break;
					

		case R.id.favoriteEventTab:
			Intent inifav = new Intent(My_invitations.this, TabAct.class);
			
			 b.putString("condition","tb4");
			 inifav.putExtras(b);
			startActivity(inifav);
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			finish();
			break;
			
		case R.id.notificationEventTab:
			Intent ini = new Intent(My_invitations.this, TabAct.class);
			
			 b.putString("condition","tb3");
			 ini.putExtras(b);
			startActivity(ini);
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			finish();
			break;
	
			
		default:
			break;
		}
		
		
	}
	
	public void backMethod(){
		Intent in = new Intent(My_invitations.this, TabAct.class);
		
		 b.putString("condition","tb2");
		 in.putExtras(b);
		startActivity(in);
		overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
		finish();
	}
	

	@Override
	public void onBackPressed() {
	//	super.onBackPressed();
		
		backMethod();
	}
	
	public void getPixel(int value) {
		int density = getResources().getDisplayMetrics().densityDpi;
		switch (density) {
		case DisplayMetrics.DENSITY_LOW:
			layoutHeight = (int) (value * .50);
		//	 Toast.makeText(TabAct.this, "LDPI", Toast.LENGTH_SHORT).show();
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			layoutHeight = (int) (value * .75);
		//	Toast.makeText(TabAct.this, "MDPI", Toast.LENGTH_SHORT).show();
			break;
		case DisplayMetrics.DENSITY_HIGH:
			layoutHeight = (int) (value * 1);
		//	 Toast.makeText(TabAct.this, "HDPI", Toast.LENGTH_SHORT).show();
			break;
		case DisplayMetrics.DENSITY_XHIGH:
			layoutHeight = (int) (value * 1.5) + 1;
		//	 Toast.makeText(TabAct.this, "XHDPI", Toast.LENGTH_SHORT).show();
			break;
		case DisplayMetrics.DENSITY_XXHIGH:
			layoutHeight = (int) (value * 2) + 2;
			break;
		case DisplayMetrics.DENSITY_XXXHIGH:
			layoutHeight = (int) (value * 4) + 3;
			break;
		}

	}

}
