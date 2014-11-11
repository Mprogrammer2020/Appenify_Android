package com.calgiary.appenify;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appenify.adapter.Modadapter;
import com.appenify.adapter.SlideMenuFunctions;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class Search_events extends Activity {

	ListView search_ListView;
	TextView header;
	Modadapter madapter;
	RelativeLayout menu_button;
	ActionBar abar;
	SlidingMenu smenu;
	TextView location, nearby, favs;
	int[] images = { R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3, R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3 };
	String[] text = { "Love Electro", "My Own Event News", "Love Electro",
			"Love Electro", "Love Electro", "My Own Event News",
			"Love Electro", "Love Electro" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_events);
		search_ListView = (ListView) findViewById(R.id.listview);
		abar = (ActionBar) findViewById(R.id.abar1);
		location = (TextView) findViewById(R.id.location);
		favs = (TextView) findViewById(R.id.favs);
		nearby = (TextView) findViewById(R.id.nearby);
		header = (TextView) abar.findViewById(R.id.action_text);
		madapter = new Modadapter(Search_events.this, text, images);
		search_ListView.setAdapter(madapter);
		menu_button = (RelativeLayout) abar.findViewById(R.id.action_menu);

		header.setText("Search");
		smenu = com.appenify.adapter.commonutilities
				.setSlidingMenu(Search_events.this);
		new SlideMenuFunctions(smenu, Search_events.this);
		menu_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				smenu.showMenu();
			}
		});

		location.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				location.setBackgroundColor(Color.parseColor("#d94949"));
				location.setTextColor(Color.parseColor("#ffffff"));
				nearby.setBackgroundColor(Color.parseColor("#ffffff"));
				nearby.setTextColor(Color.parseColor("#d94949"));
				favs.setBackgroundColor(Color.parseColor("#ffffff"));
				favs.setTextColor(Color.parseColor("#d94949"));
			}
		});
		favs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				favs.setBackgroundColor(Color.parseColor("#d94949"));
				favs.setTextColor(Color.parseColor("#ffffff"));
				nearby.setBackgroundColor(Color.parseColor("#ffffff"));
				nearby.setTextColor(Color.parseColor("#d94949"));
				location.setBackgroundColor(Color.parseColor("#ffffff"));
				location.setTextColor(Color.parseColor("#d94949"));
			}
		});
		nearby.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nearby.setBackgroundColor(Color.parseColor("#d94949"));
				nearby.setTextColor(Color.parseColor("#ffffff"));
				favs.setBackgroundColor(Color.parseColor("#ffffff"));
				favs.setTextColor(Color.parseColor("#d94949"));
				location.setBackgroundColor(Color.parseColor("#ffffff"));
				location.setTextColor(Color.parseColor("#d94949"));
			}
		});

	}

}
