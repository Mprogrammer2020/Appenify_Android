package com.calgiary.appenify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.appenify.adapter.Modadapter;

public class FavoriteTab extends Activity{

	
	Modadapter custadapter;
	Bundle b;
	ListView lview;
	
	
	RelativeLayout menu_bttn,fwd, back;
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
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.act_favouritetab);
	
		custadapter = new Modadapter(FavoriteTab.this, text, images);
		lview = (ListView) findViewById(R.id.content_lv_fav);
		lview.setAdapter(custadapter);
	
		fwd=(RelativeLayout)findViewById(R.id.done_event_favLay);
		back = (RelativeLayout)findViewById(R.id.cancel_Btn_fav_Lay);
		b = new Bundle();
	/*	smenu = com.netset.appenify.adapter.commonutilities
				.setSlidingMenu(My_favourite.this);
		new SlideMenuFunctions(smenu, My_favourite.this);*/
		fwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(FavoriteTab.this,AfterMyFavouriteEventAttending.class));
				finish();
			}
		});
	
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TabAct.tabHost.setCurrentTab(1);
			}
		});
	
		lview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(FavoriteTab.this, Event_detail.class);
				
				 b.putString("detail","invitation");
				 i.putExtras(b);
				startActivity(i);
			}
			
		});
	}
	
	@Override
	public void onBackPressed() {
	//	super.onBackPressed();
		overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
		TabAct.tabHost.setCurrentTab(1);
	}
	
}
