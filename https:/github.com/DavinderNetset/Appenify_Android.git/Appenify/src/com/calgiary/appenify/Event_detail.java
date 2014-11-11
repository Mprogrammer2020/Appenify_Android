package com.calgiary.appenify;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Event_detail extends Activity {

	LinearLayout l1;
	ActionBar abar;
	TextView header, dayNAmeView, eventDetailTitle, dateYearView, hoursView;
	TextView  eventsEndsView, locationAddressView, descTextView, descTextt11, descTextt22;
	LinearLayout share, editEventView;
	ImageView back;
	ImageView drp_icon, event_banner_pic;
	RelativeLayout mapiconLay, drp_iconLay;
	boolean layout = false;
	RelativeLayout nextScreen, backScreen;
	int layoutHeight = 280;
	String eventTitleStr = "", dayNameStr = "", dateYearStr = "", hoursStr = "", eventEndStr = "", locationStr = "", descStr = "";
	String con = "";
	LinearLayout discardEventLay, joinEventView;
	Dialog joinEventDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_my_event_details);
		l1 = (LinearLayout) findViewById(R.id.agree);
		abar = (ActionBar) findViewById(R.id.abar1);
		share = (LinearLayout) findViewById(R.id.share);
		header = (TextView) findViewById(R.id.action_text);
		dayNAmeView = (TextView)findViewById(R.id.dayNAmeView);
		eventDetailTitle = (TextView)findViewById(R.id.eventDetailTitle);
		dateYearView = (TextView)findViewById(R.id.dateYearView);
		hoursView = (TextView)findViewById(R.id.hoursView);
		eventsEndsView = (TextView)findViewById(R.id.eventsEndsView);
		locationAddressView = (TextView)findViewById(R.id.locationAddressView);
		descTextView = (TextView)findViewById(R.id.descTextView);
		descTextt11 = (TextView)findViewById(R.id.descTextt11);
		descTextt22 = (TextView)findViewById(R.id.descTextt22);
		discardEventLay = (LinearLayout)findViewById(R.id.discardEventLay);
		joinEventView = (LinearLayout)findViewById(R.id.joinEventView);
		
		
		editEventView = (LinearLayout)findViewById(R.id.editEventView);
		drp_iconLay = (RelativeLayout)findViewById(R.id.drp_iconLay);
		
		drp_icon = (ImageView)findViewById(R.id.drp_icon);
		drp_icon.setBackgroundResource(R.drawable.down_arrow);
		
		//share.setVisibility(View.GONE);
		back=(ImageView)abar.findViewById(R.id.action_menu_icon);
		mapiconLay = (RelativeLayout)findViewById(R.id.mapiconLay);
		event_banner_pic = (ImageView)findViewById(R.id.event_banner_pic);
		
		getPixel(layoutHeight);
		event_banner_pic.getLayoutParams().height = layoutHeight;
		
		
		Bundle bundle = getIntent().getExtras();
		con = bundle.getString("detail");
		
		if (con.equals("invitation")) {
			joinEventView.setVisibility(View.VISIBLE);
			discardEventLay.setVisibility(View.VISIBLE);
			editEventView.setVisibility(View.GONE);
		}else if (con.equals("attending")) {
			joinEventView.setVisibility(View.GONE);
			discardEventLay.setVisibility(View.GONE);
			editEventView.setVisibility(View.VISIBLE);
		}else if (con.equalsIgnoreCase("invitationnnnnn")) {
			joinEventView.setVisibility(View.GONE);
			discardEventLay.setVisibility(View.GONE);
			editEventView.setVisibility(View.GONE);
		}
		
		back.setBackgroundResource(R.drawable.back);
		header.setText("Event Details");
		
		nextScreen = (RelativeLayout)abar.findViewById(R.id.action_edit_container);
		backScreen = (RelativeLayout)abar.findViewById(R.id.action_menu);
		
		nextScreen.setOnClickListener(new OnClickListener() {
				@Override
			public void onClick(View v) {
					startActivity(new Intent(Event_detail.this,People_Attending.class));
			}
		});
		
		
		backScreen.setOnClickListener(new OnClickListener() {
			@Override
		public void onClick(View v) {
			finish();
		}
	});
		
		mapiconLay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Event_detail.this,MapMainActivity.class));
			}
		});
		
		
		/*drop.setOnClickListener(new OnClickListener() {
				@Override
			public void onClick(View v) {
				if(!layout){
				share.setVisibility(View.VISIBLE);
				layout = true;
				}else{
					share.setVisibility(View.GONE);
					layout=false;
				}
			}
		});*/

				
		drp_iconLay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!layout){
					//share.setVisibility(View.VISIBLE);
					drp_icon.setBackgroundResource(R.drawable.top_red_arrow);
					layout = true;
					descTextt22.setVisibility(View.VISIBLE);
					descTextt11.setVisibility(View.VISIBLE);
					}else{
						drp_icon.setBackgroundResource(R.drawable.down_arrow);
						//share.setVisibility(View.GONE);
						layout=false;
						descTextt22.setVisibility(View.GONE);
						descTextt11.setVisibility(View.GONE);
					}
			}
		});

	}
	
	@Override
	public void onBackPressed() {
	//	super.onBackPressed();
		
		Intent ii = new Intent(Event_detail.this, TabAct.class);
		Bundle bb=new Bundle();
		 bb.putString("condition","tb2");
		 ii.putExtras(bb);
		startActivity(ii);
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
	
	
	public void editEvent(){
		eventTitleStr = eventDetailTitle.getText().toString();
		dayNameStr = dayNAmeView.getText().toString();
		dateYearStr = dateYearView.getText().toString();
		hoursStr = hoursView.getText().toString();
		eventEndStr = eventsEndsView.getText().toString();
		locationStr = locationAddressView.getText().toString();
		descStr = descTextView.getText().toString();
		
		System.out.println("dateYearStr: " +dateYearStr);
		dateYearStr = dateYearStr.replaceAll("\n", " ");
		System.out.println("dateYearStr: " +dateYearStr);
		/*
		EditEventDetailValues eventData = new EditEventDetailValues(); 
		
		eventData.setEventTitleStr(eventTitleStr);
		eventData.setDayNameStr(dayNameStr);
		eventData.setDateYearStr(dateYearStr);
		eventData.setHoursStr(hoursStr);
		eventData.setEventEndStr(eventEndStr);
		eventData.setLocationStr(locationStr);
		*/
		
		Intent in = new Intent(Event_detail.this, CreateEventEdit.class);
		
		Bundle b= new Bundle();
		 
		 b.putString("eventTitleStr", eventTitleStr);
		 b.putString("dayNameStr", dayNameStr);
		 b.putString("dateYearStr", dateYearStr);
		 b.putString("hoursStr", hoursStr);
		 b.putString("eventEndStr", eventEndStr);
		 b.putString("locationStr", locationStr);
		b.putString("descStr", descStr);
		
		 in .putExtras(b);
		 startActivity(in);
		 
		 
		
	}
	
	public void getClickMethod(View view){
		switch (view.getId()) {
		case R.id.editEventView:
			editEvent();
			break;

			case R.id.joinEventView:
				joinEventDialog = new Dialog(Event_detail.this);
				joinEventDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.WHITE));
				joinEventDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				joinEventDialog.setContentView(R.layout.popup_join_event);
				
				ImageView eventDetailCancel = (ImageView)joinEventDialog.findViewById(R.id.eventDetailCancel);
				ImageView eventDetailCheckin = (ImageView)joinEventDialog.findViewById(R.id.eventDetailCheckin);
				RelativeLayout mainLAyevent = (RelativeLayout)joinEventDialog.findViewById(R.id.mainLAyevent);
				mainLAyevent.setBackgroundColor(Color.TRANSPARENT);
				TextView eventTitleCheckin = (TextView)joinEventDialog.findViewById(R.id.eventTitleCheckin);
				
				eventDetailCancel.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						
						joinEventDialog.dismiss();
					}
				});
				
				eventDetailCheckin.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						joinEventDialog.dismiss();
					}
				});
		
				joinEventDialog.show();
				break;
		default:
			break;
		}
	}
}

