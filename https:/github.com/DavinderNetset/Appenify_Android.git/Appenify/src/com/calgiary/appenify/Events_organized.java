package com.calgiary.appenify;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appenify.adapter.Modadapter;
import com.appenify.adapter.SlideMenuFunctions;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class Events_organized extends Activity {
	ActionBar abar;
	Modadapter custadapter;
	SlidingMenu smenu;
	ListView lview;
	TextView header;
	RelativeLayout menu_bttn,fwd;
	LinearLayout createEventTab, homeEventTab, notificationEventTab, favoriteEventTab, eventNearTabLay;
	Bundle b;
	int layoutHeight = 65;
	
	int[] images = { R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3, R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3 };
	String[] text = { "Love Electro", "My Own Event News", "Love Electro",
			"My Own Event News", "Love Electro", "My Own Event News",
			"Love Electro", "Love Electro" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.act_event_organised);
		abar = (ActionBar) findViewById(R.id.abar1);
		menu_bttn = (RelativeLayout) abar.findViewById(R.id.action_menu);
		custadapter = new Modadapter(Events_organized.this, text, images);
		lview=(ListView)findViewById(R.id.content_lv);
		lview.setAdapter(custadapter);
		header=(TextView)findViewById(R.id.action_text);
		header.setText("Events I Organized");
		fwd=(RelativeLayout)findViewById(R.id.action_edit_container);
		
		createEventTab = (LinearLayout)findViewById(R.id.createEventTab);
		homeEventTab = (LinearLayout)findViewById(R.id.homeEventTab);
		notificationEventTab = (LinearLayout)findViewById(R.id.notificationEventTab);
		favoriteEventTab = (LinearLayout)findViewById(R.id.favoriteEventTab);
		eventNearTabLay = (LinearLayout)findViewById(R.id.eventNearTabLay);
		
		b = new Bundle();
		getPixel(layoutHeight);
		eventNearTabLay.getLayoutParams().height = layoutHeight;
		
		smenu = com.appenify.adapter.commonutilities
				.setSlidingMenu(Events_organized.this);
		new SlideMenuFunctions(smenu, Events_organized.this);
		
		fwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Events_organized.this,My_favourite.class));
			}
		});
		lview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//startActivity(new Intent(Events_organized.this,Event_detail.class));
				Intent i = new Intent(Events_organized.this, Event_detail.class);
				
				 b.putString("detail","attending");
				 i.putExtras(b);
				startActivity(i);
			}
			
		});
		menu_bttn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				smenu.showMenu();
			}
		});
	}
	

	public void tabCallFunc(View view){
		switch (view.getId()) {
		
	
			
		case R.id.createEventTab:
			Intent i = new Intent(Events_organized.this, TabAct.class);
			
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
			Intent inifav = new Intent(Events_organized.this, TabAct.class);
			
			 b.putString("condition","tb4");
			 inifav.putExtras(b);
			startActivity(inifav);
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			finish();
			break;
			
		case R.id.notificationEventTab:
			Intent ini = new Intent(Events_organized.this, TabAct.class);
			
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
	
	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		
		backMethod();
	}
	public void backMethod(){
		Intent in = new Intent(Events_organized.this, TabAct.class);
		
		 b.putString("condition","tb2");
		 in.putExtras(b);
		startActivity(in);
		overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
		finish();
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
