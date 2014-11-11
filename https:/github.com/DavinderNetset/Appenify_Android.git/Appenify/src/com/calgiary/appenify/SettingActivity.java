package com.calgiary.appenify;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.appenify.adapter.SettingAdapter;
import com.appenify.adapter.SettingPrefAdapter;
import com.appenify.adapter.SlideMenuFunctions;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class SettingActivity extends Activity {

	RelativeLayout profileLay, preferencesLay, distanceLay, searchLay, favoriteLay, favoriteLocationLay;
	Button profileTab, preferencesTab;
	LinearLayout photoVideoLay;
	ListView prefListView;
	List<String> li;
	SettingAdapter custadapter;
	
	TextView header, distanceUnitView;
	ActionBar abar;
	SettingPrefAdapter prefAdapter;
	SlidingMenu slideMenu;
	RelativeLayout forword, next;
	Dialog changeDistance;
String checkDistance = "";
ScrollView settingPrefScroll;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// remove title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	//	overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.act_setting);
		abar = (ActionBar) findViewById(R.id.abar1);
		profileLay = (RelativeLayout) findViewById(R.id.profilelay);
		preferencesLay = (RelativeLayout) findViewById(R.id.preferencesLay);
		profileTab = (Button) findViewById(R.id.profileButton);
		preferencesTab = (Button) findViewById(R.id.preferencesButton);
		prefListView = (ListView) findViewById(R.id.prefListView);
		header = (TextView) abar.findViewById(R.id.action_text);
		forword = (RelativeLayout)findViewById(R.id.action_menu);
		next = (RelativeLayout)findViewById(R.id.action_edit_container);
		distanceLay = (RelativeLayout)findViewById(R.id.distanceLay);
		searchLay  = (RelativeLayout)findViewById(R.id.searchLay);
		favoriteLay = (RelativeLayout)findViewById(R.id.favoriteLay);
		favoriteLocationLay = (RelativeLayout)findViewById(R.id.favoriteLocationLay);
		distanceUnitView = (TextView)findViewById(R.id.distanceUnitView);
		
		
		photoVideoLay = (LinearLayout)findViewById(R.id.photoVideoLay);
		settingPrefScroll = (ScrollView)findViewById(R.id.settingPrefScroll);
		
	//	settingPrefScroll.setVisibility(View.GONE);
		next.setVisibility(View.INVISIBLE);
		
		header.setText("Settings");
		checkDistance = distanceUnitView.getText().toString();
		
		/*final String[] prefName = new String[] { "Distance Unit", "Search Distance Radius",
				"Hype my public event", "Hype when i check-in at event",
				"Notify when new invitation", "Notify new nearby events",
				"Favorite Event Categories", "Favorite Event Locations",
				"Send us feedback / comments" };*/

		final String[] prefNameSecond = new String[] { "Kilometers","10 Km",
				"Yes","Yes","Yes","Yes", "Party, Metting, Wedding", "Noida, India: Banglore, India", ""	 };
		
		/*prefAdapter = new SettingPrefAdapter(getApplicationContext(), prefName);
		prefListView.setAdapter(prefAdapter);*/

		slideMenu = com.appenify.adapter.commonutilities
				.setSlidingMenu(SettingActivity.this);
		new SlideMenuFunctions(slideMenu, SettingActivity.this);
		
		
		/*custadapter = new SettingAdapter(SettingActivity.this, prefName, prefNameSecond);
		prefListView.setAdapter(custadapter);
		*/
		forword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				slideMenu.showMenu();
			}
		});
		
		prefListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
			//	settingPrefScroll.setVisibility(View.VISIBLE);
			//	profileLay.setVisibility(View.GONE);
				
			/*	 String poString = parent.getItemAtPosition(position).toString();
				//System.out.println("List Index" + parent.getItemAtPosition(position).toString());
			//	System.out.println("List id" +view.getId());
				
				if( poString.equalsIgnoreCase("0")){
					
					
				}
				*/
				
			}
		
		
		});
		
		
	}

	public void settingLayout(View view) {

		switch (view.getId()) {

		
		case R.id.distanceLay:
			changeDistance = new Dialog(SettingActivity.this);
			changeDistance.requestWindowFeature(Window.FEATURE_NO_TITLE);
			changeDistance.setContentView(R.layout.popup_distance_unit_value);
			changeDistance.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.WHITE));
			
			RelativeLayout killometerLay = (RelativeLayout)changeDistance.findViewById(R.id.killometerLay);
			RelativeLayout milesLay = (RelativeLayout)changeDistance.findViewById(R.id.milesLay);
			final RadioButton killometerBtn = (RadioButton)changeDistance.findViewById(R.id.kilometersBtn);
			final RadioButton milesBtn = (RadioButton)changeDistance.findViewById(R.id.milesBtn);
			
			if(checkDistance.equalsIgnoreCase("kilometers")){
				killometerBtn.setChecked(true);
				milesBtn.setChecked(false);
			}else if(checkDistance.equalsIgnoreCase("Miles")) {
				milesBtn.setChecked(true);
				killometerBtn.setChecked(false);
			}
			
			killometerLay.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					killometerBtn.setChecked(true);
					milesBtn.setChecked(false);
					distanceUnitView.setText("kilometers");
					checkDistance = "kilometers";
					changeDistance.dismiss();
					
				}
			});
			
			
			milesLay.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					milesBtn.setChecked(true);
					killometerBtn.setChecked(false);
					distanceUnitView.setText("Miles");
					checkDistance = "Miles";
					changeDistance.dismiss();
				}
			});
			
		
			killometerBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					
					killometerBtn.setChecked(true);
					milesBtn.setChecked(false);
					distanceUnitView.setText("kilometers");
					checkDistance = "kilometers";
					changeDistance.dismiss();
				}
			});

			milesBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					milesBtn.setChecked(true);
					killometerBtn.setChecked(false);
					distanceUnitView.setText("Miles");
					checkDistance = "Miles";
					changeDistance.dismiss();
				}
			});
			

			changeDistance.show();
			
			break;
			
		case R.id.searchLay:
			Intent i = new Intent(SettingActivity.this, MapMultLocation.class);
			startActivity(i);
			break;
		case R.id.favoriteLay:
			Intent i1 = new Intent(SettingActivity.this, FavouriteEventCat.class);
			startActivity(i1);
			break;
		case R.id.favoriteLocationLay:
			Intent i2 = new Intent(SettingActivity.this, FavEventLocation.class);
			startActivity(i2);
			break;
	
		case R.id.preferencesButton:
			profileLay.setVisibility(View.GONE);
			preferencesLay.setVisibility(View.VISIBLE);
			photoVideoLay.setBackgroundResource(R.drawable.border_orange);
			profileTab.setBackgroundResource(R.drawable.border_orange);
			preferencesTab.setBackgroundColor(Color.parseColor("#D84949"));
			profileTab.setBackgroundColor(Color.WHITE);
			preferencesTab.setTextColor(Color.WHITE);
			profileTab.setTextColor(Color.BLACK);
			break;

		case R.id.profileButton:
			profileLay.setVisibility(View.VISIBLE);
			preferencesLay.setVisibility(View.GONE);

			profileTab.setBackgroundColor(Color.parseColor("#D84949"));
			preferencesTab.setBackgroundColor(Color.WHITE);
			profileTab.setTextColor(Color.WHITE);
			preferencesTab.setTextColor(Color.BLACK);

			break;
			
			
		}
	}
	
	@Override
	public void onBackPressed() {
	//	super.onBackPressed();
		
		Intent ii = new Intent(SettingActivity.this, TabAct.class);
		Bundle bb=new Bundle();
		 bb.putString("condition","tb2");
		 ii.putExtras(bb);
		startActivity(ii);
		finish();
	}
}



