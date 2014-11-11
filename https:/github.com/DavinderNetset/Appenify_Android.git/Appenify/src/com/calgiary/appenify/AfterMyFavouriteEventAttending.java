package com.calgiary.appenify;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appenify.adapter.Modadapter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class AfterMyFavouriteEventAttending extends Activity{

	
	ListView lview;
	Modadapter custadapter;
	SlidingMenu smenu;
	ActionBar abar;
	TextView header;
	RelativeLayout menu_bttn;
	int[] images = { R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3, R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3 };
	String[] text = { "Love Electro", "My Own Event News", "Love Electro",
			"Love Electro", "Love Electro", "My Own Event News",
			"Love Electro", "Love Electro" };
	
 RelativeLayout nextScreen;
	ImageView imageFront, backImage;
	Bundle b;
	int layoutHeight = 65;
	LinearLayout eventNearTabLay;
	LinearLayout createEventTab, homeEventTab, notificationEventTab, favoriteEventTab;
	
 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.act_event_i_attended);
		abar = (ActionBar) findViewById(R.id.abar1);
		lview = (ListView) findViewById(R.id.content_lv);
		menu_bttn=(RelativeLayout)abar.findViewById(R.id.action_menu);
		nextScreen = (RelativeLayout)abar.findViewById(R.id.action_edit_container);
		imageFront = (ImageView)abar.findViewById(R.id.action_edit);
		eventNearTabLay = (LinearLayout)findViewById(R.id.eventNearTabLay);
		createEventTab = (LinearLayout)findViewById(R.id.createEventTab);
		homeEventTab = (LinearLayout)findViewById(R.id.homeEventTab);
		notificationEventTab = (LinearLayout)findViewById(R.id.notificationEventTab);
		favoriteEventTab = (LinearLayout)findViewById(R.id.favoriteEventTab);
		
		
		imageFront.setBackground(null);
		nextScreen.setBackground(null);
		
		backImage=(ImageView)abar.findViewById(R.id.action_menu_icon);
		backImage.setBackgroundResource(R.drawable.back);
		header = (TextView) findViewById(R.id.action_text);
		header.setText("Events I Attended");
	
		custadapter = new Modadapter(AfterMyFavouriteEventAttending.this, text, images);
		lview.setAdapter(custadapter);
		lview.setVisibility(View.VISIBLE);

		menu_bttn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				backMethod();
			}
		});
				
				lview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					//startActivity(new Intent(AfterMyFavouriteEventAttending.this,Event_detail.class));
						Intent i = new Intent(AfterMyFavouriteEventAttending.this, Event_detail.class);
						
						 b.putString("detail","invitationnnnnn");
						 i.putExtras(b);
						startActivity(i);
					
					}
				});
				
				b = new Bundle();
				getPixel(layoutHeight);
				eventNearTabLay.getLayoutParams().height = layoutHeight;
				
	}
 
 public void tabCallFunc1(View view){
	 switch (view.getId()) {
		
					
		case R.id.createEventTab:
			Intent i = new Intent(AfterMyFavouriteEventAttending.this, TabAct.class);
			
			 b.putString("condition","tb1");
			 i.putExtras(b);
			startActivity(i);
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			finish();
			
			break;

		case R.id.homeEventTab:
			Intent iin = new Intent(AfterMyFavouriteEventAttending.this, TabAct.class);
			
			 b.putString("condition","tb2");
			 iin.putExtras(b);
			startActivity(iin);
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			finish();
			break;
					

		case R.id.favoriteEventTab:
			Intent ini = new Intent(AfterMyFavouriteEventAttending.this, TabAct.class);
			
			 b.putString("condition","tb4");
			 ini.putExtras(b);
			startActivity(ini);
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			finish();
			break;
			
		case R.id.notificationEventTab:
			Intent i4 = new Intent(AfterMyFavouriteEventAttending.this, TabAct.class);
			
			 b.putString("condition","tb3");
			 i4.putExtras(b);
			startActivity(i4);
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			finish();
			break;
	
			
		default:
			break;
		}
 }
 

 public void backMethod(){
	// Intent inifav = new Intent(AfterMyFavouriteEventAttending.this, My_favourite.class);
	
	//startActivity(inifav);
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
	
		 @Override
		public void onBackPressed() {
		//	super.onBackPressed();
			 backMethod();
			 
		}
}
