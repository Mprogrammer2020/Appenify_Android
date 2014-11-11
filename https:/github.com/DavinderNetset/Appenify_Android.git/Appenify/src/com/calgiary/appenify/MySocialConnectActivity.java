package com.calgiary.appenify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appenify.adapter.SlideMenuFunctions;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MySocialConnectActivity extends Activity{

	TextView header;
	ActionBar abar;
	
	SlidingMenu slideMenu;
	RelativeLayout forword, next;
	RelativeLayout facebookLay, twitterLay, linkedInLay, googlePlusLay;
	ImageView fb_sq_rightArrow, twitter_sq_rightArrow, linked_sq_rightArrow, google_plus_sq_rightArrow;
	
	LinearLayout createEventTab, homeEventTab, notificationEventTab, favoriteEventTab, eventNearTabLay;
	Bundle b;
	int layoutHeight = 65;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// remove title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	//	overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.act_my_social_connect);
		
		abar = (ActionBar) findViewById(R.id.abar1);
		header = (TextView) abar.findViewById(R.id.action_text);
		forword = (RelativeLayout)findViewById(R.id.action_menu);
		next = (RelativeLayout)findViewById(R.id.action_edit_container);
		header.setText("My Social Networks");
		next.setVisibility(View.INVISIBLE);
		
		facebookLay = (RelativeLayout)findViewById(R.id.fb_sq_Lay);
		twitterLay = (RelativeLayout)findViewById(R.id.twitter_sq_Lay);
		linkedInLay = (RelativeLayout)findViewById(R.id.linkedin_sq_Lay);
		googlePlusLay = (RelativeLayout)findViewById(R.id.google_plus_sq_Lay);
		
		fb_sq_rightArrow = (ImageView)findViewById(R.id.fb_sq_rightArrow);
		twitter_sq_rightArrow = (ImageView)findViewById(R.id.twitter_sq_rightArrow);
		linked_sq_rightArrow = (ImageView)findViewById(R.id.linked_sq_rightArrow);
		google_plus_sq_rightArrow = (ImageView)findViewById(R.id.google_plus_sq_rightArrow);
		
		createEventTab = (LinearLayout)findViewById(R.id.createEventTab);
		homeEventTab = (LinearLayout)findViewById(R.id.homeEventTab);
		notificationEventTab = (LinearLayout)findViewById(R.id.notificationEventTab);
		favoriteEventTab = (LinearLayout)findViewById(R.id.favoriteEventTab);
		eventNearTabLay = (LinearLayout)findViewById(R.id.eventNearTabLay);
		
		b = new Bundle();
		getPixel(layoutHeight);
		eventNearTabLay.getLayoutParams().height = layoutHeight;
		
		slideMenu = com.appenify.adapter.commonutilities
				.setSlidingMenu(MySocialConnectActivity.this);
		new SlideMenuFunctions(slideMenu, MySocialConnectActivity.this);
		
		
		forword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				slideMenu.showMenu();
			}
		});
		
		
	}
	
	
	
		public void socialConnect(View view){
				switch(view.getId()){
			       
				case R.id.fb_sq_Lay:
					
					break;
			
				
			case R.id.twitter_sq_Lay:
				
				break;
			
			
			case R.id.linked_sq:
				
				break;
			
			case R.id.google_plus_sq_Lay:
				
				break;
			}
		}
		
		@Override
		public void onBackPressed() {
		//	super.onBackPressed();
			
			backMethod();
		}
		
		public void tabCallFunc(View view){
			switch (view.getId()) {
			
		
				
			case R.id.createEventTab:
				Intent i = new Intent(MySocialConnectActivity.this, TabAct.class);
				
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
				Intent inifav = new Intent(MySocialConnectActivity.this, TabAct.class);
				
				 b.putString("condition","tb3");
				 inifav.putExtras(b);
				startActivity(inifav);
				overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
				finish();
				break;
				
			case R.id.notificationEventTab:
				Intent ini = new Intent(MySocialConnectActivity.this, TabAct.class);
				
				 b.putString("condition","tb4");
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
			Intent in = new Intent(MySocialConnectActivity.this, TabAct.class);
			
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
