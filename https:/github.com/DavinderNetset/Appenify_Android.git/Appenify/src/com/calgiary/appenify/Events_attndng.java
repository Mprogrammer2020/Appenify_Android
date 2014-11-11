package com.calgiary.appenify;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appenify.adapter.Modadapter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class Events_attndng extends Activity {

	ListView lview;
	
	Modadapter custadapter;
	
	SlidingMenu smenu;
	ActionBar abar;
	RelativeLayout menu_bttn;
	int[] images = { R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3, R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3 };
	String[] text = { "Love Electro", "My Own Event News", "Love Electro",
			"Love Electro", "Love Electro", "My Own Event News",
			"Love Electro", "Love Electro" };
	RelativeLayout nextScreen;
	ImageView backImage, imageFront;
	TextView header;
	boolean isclicked = false;
	boolean isclickedNext = false;
	Bundle b;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_events_attndng);
		abar = (ActionBar) findViewById(R.id.abar1);
		lview = (ListView) findViewById(R.id.content_lv);
	
		
	
		
		menu_bttn=(RelativeLayout)abar.findViewById(R.id.action_menu);
		nextScreen = (RelativeLayout)abar.findViewById(R.id.action_edit_container);
		
		backImage=(ImageView)abar.findViewById(R.id.action_menu_icon);
		imageFront = (ImageView)abar.findViewById(R.id.action_edit);
		backImage.setBackgroundResource(R.drawable.actin_bar);
		imageFront.setBackgroundResource(R.drawable.next);
		
		
		custadapter = new Modadapter(Events_attndng.this, text, images);
		lview.setAdapter(custadapter);
		
		b = new Bundle();
		
				lview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Intent i = new Intent(Events_attndng.this, Event_detail.class);
						
						 b.putString("detail","invitationnnnnn");
						 i.putExtras(b);
						startActivity(i);
						//finish();
					}
				});
				
				nextScreen.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
					/*if(!isclickedNext){
						backImage.setBackgroundResource(R.drawable.back);
						imageFront.setBackgroundResource(R.drawable.search);
						header.setText("Events Nearby");
						lview.setVisibility(View.GONE);
						eventNearby_lv.setVisibility(View.VISIBLE);
						isclicked = true;
						isclickedNext = true;
					}else {*/
						startActivity(new Intent(Events_attndng.this,EventNearBy.class));
						finish();
				//	}
					
					}
				});
				
				
				/*menu_bttn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
					
					if (isclicked) {
						backImage.setBackgroundResource(R.drawable.actin_bar);
						imageFront.setBackgroundResource(R.drawable.next);
						header.setText("Events I'm Attending");
						lview.setVisibility(View.VISIBLE);
						eventNearby_lv.setVisibility(View.GONE);
						isclicked = false;
						isclickedNext = false;
					}else {
						Toast.makeText(getApplicationContext(), "else Clicked", Toast.LENGTH_LONG).show();
						smenu.showMenu();
					}
					
					}
				});*/
				
	}
	
	

}
