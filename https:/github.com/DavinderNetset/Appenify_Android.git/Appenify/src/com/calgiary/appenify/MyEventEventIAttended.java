package com.calgiary.appenify;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appenify.adapter.Modadapter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MyEventEventIAttended extends Activity{

	
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
 ImageView backImage;
 TextView header;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_events_attndng);
		abar = (ActionBar) findViewById(R.id.abar1);
		lview = (ListView) findViewById(R.id.content_lv);
		menu_bttn=(RelativeLayout)abar.findViewById(R.id.action_menu);
		nextScreen = (RelativeLayout)abar.findViewById(R.id.action_edit_container);
		nextScreen.setBackground(null);
		
		custadapter = new Modadapter(MyEventEventIAttended.this, text, images);
		lview.setAdapter(custadapter);
	
		backImage=(ImageView)abar.findViewById(R.id.action_menu_icon);
		backImage.setBackgroundResource(R.drawable.back);
		header = (TextView) abar.findViewById(R.id.action_text);
		header.setText("Events I Attended");
		
backImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});

				/*lview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
					startActivity(new Intent(MyEventEventIAttended.this,Event_detail.class));
					}
				});
				
				nextScreen.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(MyEventEventIAttended.this,Main_search.class));
					}
				});*/
				
	}
	
	
}
