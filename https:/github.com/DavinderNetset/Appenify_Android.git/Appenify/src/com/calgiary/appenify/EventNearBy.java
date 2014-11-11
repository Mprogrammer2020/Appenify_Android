package com.calgiary.appenify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.appenify.adapter.Modadapter;

public class EventNearBy extends Activity{

	int layoutHeight = 65;
	LinearLayout eventNearTabLay;
	LinearLayout createEventTab, homeEventTab, notificationEventTab, favoriteEventTab;
	RelativeLayout backEventNearBy, searchEventNearBy;
	Bundle b;
	
	ListView eventNearby_lv;
	Modadapter custadapter;
	
	int[] images = { R.drawable.pic3, R.drawable.pic2, R.drawable.pic1,
			R.drawable.pic3, R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic };
	String[] text = { "My Own Event News", "Love Electro", "My Own Event News", "Love Electro",
			"Love Electro", "Love Electro", "My Own Event News",
			"Love Electro" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.act_event_naerby);
		
		eventNearTabLay = (LinearLayout)findViewById(R.id.eventNearTabLay);
		createEventTab = (LinearLayout)findViewById(R.id.createEventTab);
		homeEventTab = (LinearLayout)findViewById(R.id.homeEventTab);
		notificationEventTab = (LinearLayout)findViewById(R.id.notificationEventTab);
		favoriteEventTab = (LinearLayout)findViewById(R.id.favoriteEventTab);
		
		backEventNearBy = (RelativeLayout)findViewById(R.id.backEventNearBy);
		searchEventNearBy = (RelativeLayout)findViewById(R.id.searchEventNearBy);
		eventNearby_lv = (ListView) findViewById(R.id.eventNearby_lv);
		
		b = new Bundle();
		getPixel(layoutHeight);
		eventNearTabLay.getLayoutParams().height = layoutHeight;
		
		custadapter = new Modadapter(EventNearBy.this, text, images);
		eventNearby_lv.setAdapter(custadapter);
		
	}
	
	
	public void tabCallFunc(View view){
		switch (view.getId()) {
		
		case R.id.backEventNearBy:
			backMethod();
			break;
		
		case R.id.searchEventNearBy:
			
			Intent iii = new Intent(EventNearBy.this,Main_search.class);
			startActivity(iii);
			break;
			
			
		case R.id.createEventTab:
			Intent i = new Intent(EventNearBy.this, TabAct.class);
			
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
			Intent inifav = new Intent(EventNearBy.this, TabAct.class);
			
			 b.putString("condition","tb3");
			 inifav.putExtras(b);
			startActivity(inifav);
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			finish();
			break;
			
		case R.id.notificationEventTab:
			Intent ini = new Intent(EventNearBy.this, TabAct.class);
			
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
		Intent in = new Intent(EventNearBy.this, TabAct.class);
		
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
	
	@Override
	public void onBackPressed() {
	//	super.onBackPressed();
		
		backMethod();
	}
}
