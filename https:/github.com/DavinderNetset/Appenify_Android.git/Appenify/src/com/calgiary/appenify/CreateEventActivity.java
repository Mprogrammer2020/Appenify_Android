package com.calgiary.appenify;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.squareup.picasso.Picasso;

public class CreateEventActivity extends Activity {

	RelativeLayout cancel_Btn_media_Lay, done_event_Lay;
	RelativeLayout spinnerEventCat,  spinnerInviteGuest;
	Spinner spinnerEentType, spinnerEventTheme;
	Dialog picker, inviteGuestDialog, eventTypeNullDialog, eventVenue;
	LinearLayout endEventLay, startEventLay, inviteGuestt, eventVenueLay;
	Button set;
	TimePicker timePicker;
	DatePicker datePicker;
	Integer hour,minute,month,day,year;
	Dialog eventCategorydialog, eventCategoryType;
	LinearLayout sportsBtnLay, partyLay2, meetingLay3;
	LinearLayout musicartLay1, weddingLay2, festivalLay3;
	LinearLayout meetupLay1, outdoorLay2, otherLay3;
	LinearLayout getMediaFiles, mygallery;
	private Uri mImageCaptureUri;
	String imagePath = "", dateYearStr = "";
	ImageView locationImageView;
	
	EditText titleEventEdit;
	TextView eventCatText, startEventText, endEventText, bottomTextView, eventVenueText;
	String value = "", catVAlue = "", selectedImagePath = "", totalFiles = "";
	private static final int PICK_FROM_FILE = 1;
	ScrollView scrollView1;
	
	RadioGroup eventTypeRadioGroup;
	RadioButton julyparty_radio, after_party_radio, anniversary_party_radio, baby_shower_radio, bachelor_party_radio;
	RadioButton bachelorette_radio, barbeque_radio, birthday_party_adults_radio, birthday_party_kids_radio, Block_party_radio;
	LinearLayout my_connet_fb_Lay, my_connet_twitter_Lay, my_connet_linked_Lay, my_connet_google_plus_Lay, my_connet_profile_Lay;
	Date date;
	SimpleDateFormat sdf,formatter;
	String dateFormat = "", dayNameStr = "", hoursStr = "", eventEndStr = "", locationStr = "", eventtype = "";
	StringBuilder s;
	char c;
	ArrayAdapter<String> dataAdapter2, eventThemeSpin;
	List<String> themeList, list2;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.act_create_event);

			spinnerEventCat = (RelativeLayout) findViewById(R.id.eventCategoryLay);
			spinnerEentType = (Spinner) findViewById(R.id.eventTypeLay);
			spinnerEventTheme = (Spinner) findViewById(R.id.eventThemeLay);
			spinnerInviteGuest = (RelativeLayout) findViewById(R.id.inviteGuestLay);
			inviteGuestt  = (LinearLayout) findViewById(R.id.inviteGuestt);
			eventVenueLay = (LinearLayout)findViewById(R.id.eventVenueLay);
			
			eventVenueText = (TextView)findViewById(R.id.eventVenueText);
			cancel_Btn_media_Lay = (RelativeLayout)findViewById(R.id.cancel_Btn_media_Lay);
			done_event_Lay = (RelativeLayout)findViewById(R.id.done_event_Lay);
			startEventLay = (LinearLayout)findViewById(R.id.startEventLay);
			endEventLay = (LinearLayout)findViewById(R.id.endEventLay);
			titleEventEdit = (EditText)findViewById(R.id.titleEventEdit);
			eventCatText = (TextView)findViewById(R.id.eventCatText);
			startEventText = (TextView)findViewById(R.id.startEventText);
			endEventText = (TextView)findViewById(R.id.endEventText);
			bottomTextView = (TextView)findViewById(R.id.bottomTextView);
			bottomTextView.setVisibility(View.GONE);
			getMediaFiles = (LinearLayout)findViewById(R.id.getMediaFiles);
			mygallery = (LinearLayout)findViewById(R.id.mygallery);
			scrollView1 = (ScrollView)findViewById(R.id.scrollView1);
			locationImageView = (ImageView)findViewById(R.id.locationImageView);
			
			//scrollView1.fullScroll(View.FOCUS_UP);
			//scrollView1.scrollTo(0, 0);
		//	titleEventEdit.setFocusableInTouchMode(true);
		//	titleEventEdit.requestFocus();
			this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			
			scrollView1.post(new Runnable() {
				  @Override public void run() {
					  scrollView1.fullScroll(ScrollView.FOCUS_UP);
					  scrollView1.scrollTo(0, 0);
				  }
				});
			
			
			list2 = new ArrayList<String>();
			list2.add("Choose your Event Type");
			
			themeList = new ArrayList<String>();
			themeList.add("Event Theme");
			
					
			dataAdapter2 = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item,list2);
			dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerEentType.setAdapter(dataAdapter2);
			
			eventThemeSpin = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,themeList);
			eventThemeSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerEventTheme.setAdapter(eventThemeSpin);
		
			
			
			
			
		
			/*
			titleEventEdit.setText("set text" + eventData.getEventTitleStr());
			startEventText.setText(eventData.getDayNameStr()+ ", " +eventData.getDateYearStr()+ " " +eventData.getHoursStr());
			endEventText.setText(eventData.getEventEndStr());
			eventVenueText.setText(eventData.getLocationStr());
			*/
		}
		
		
		public void eventLayoutFunction(View view){
			
			switch(view.getId()){
		       
			
			case R.id.eventVenueLay:
				
				eventVenue = new Dialog(CreateEventActivity.this);
				eventVenue.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.WHITE));
				eventVenue.requestWindowFeature(Window.FEATURE_NO_TITLE);
				eventVenue.setContentView(R.layout.popup_create_event_locatopn);
				
				LinearLayout locationPopupLay = (LinearLayout)eventVenue.findViewById(R.id.locationPopupLay);
				
				locationPopupLay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						eventVenue.dismiss();
					}
				});
				
				eventVenue.show();
				
				
				break;
				
			case R.id.getMediaFiles:
			/*	Intent intent = new Intent();
				intent.setType("video/*, images/*");
				intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_FROM_FILE);
				*/
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "WeddingBetting"
						+ String.valueOf(System.currentTimeMillis())+ ".jpg"));
				try {
					
					intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,mImageCaptureUri);
					intent.putExtra("return-data", true);
					startActivityForResult(intent, PICK_FROM_FILE);
					
				} catch (ActivityNotFoundException e) {
					e.printStackTrace();
				}catch (NullPointerException e) {
					e.printStackTrace();
				}catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
				
				break;
				
				case R.id.locationImageView:
				Intent i =new Intent(CreateEventActivity.this, MapMainActivity.class);
				startActivity(i);
					
					break;
					
					
			case R.id.inviteGuestLay:
				bottomTextView.setVisibility(View.VISIBLE);
				scrollView1.fullScroll(View.FOCUS_DOWN);
				scrollView1.scrollTo(0, 100);
				inviteGuestDialog = new Dialog(CreateEventActivity.this);
				inviteGuestDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.WHITE));
				inviteGuestDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				inviteGuestDialog.setContentView(R.layout.invite_guest_social);
				
				Drawable d = new ColorDrawable(Color.BLACK);
				d.setAlpha(100);
				inviteGuestDialog.getWindow().setBackgroundDrawable(d);
				
				Window window = inviteGuestDialog.getWindow();
				WindowManager.LayoutParams wlp = window.getAttributes();
				wlp.gravity = Gravity.BOTTOM;
				window.setAttributes(wlp);
				
				my_connet_fb_Lay = (LinearLayout)inviteGuestDialog.findViewById(R.id.my_connet_fb_Lay);
				my_connet_twitter_Lay = (LinearLayout)inviteGuestDialog.findViewById(R.id.my_connet_twitter_Lay);
				my_connet_linked_Lay = (LinearLayout)inviteGuestDialog.findViewById(R.id.my_connet_linked_Lay);
				
				my_connet_google_plus_Lay = (LinearLayout)inviteGuestDialog.findViewById(R.id.my_connet_google_plus_Lay);
				my_connet_profile_Lay = (LinearLayout)inviteGuestDialog.findViewById(R.id.my_connet_profile_Lay);
				
				my_connet_fb_Lay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						bottomTextView.setVisibility(View.GONE);
						inviteGuestDialog.dismiss();
					}
				});
				
				
				
				my_connet_twitter_Lay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						bottomTextView.setVisibility(View.GONE);
						inviteGuestDialog.dismiss();
					}
				});
				
				
				my_connet_linked_Lay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						bottomTextView.setVisibility(View.GONE);
						inviteGuestDialog.dismiss();
					}
				});
				
				my_connet_google_plus_Lay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						bottomTextView.setVisibility(View.GONE);
						inviteGuestDialog.dismiss();
					}
				});
				
				
				my_connet_profile_Lay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						bottomTextView.setVisibility(View.GONE);
						inviteGuestDialog.dismiss();
					}
				});
				
				inviteGuestDialog.show();
				break;
					
				
			case R.id.startEventLay:
				picker = new Dialog(CreateEventActivity.this);
				picker.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.WHITE));
				picker.requestWindowFeature(Window.FEATURE_NO_TITLE);
				picker.setContentView(R.layout.popup_datepicker);
				//picker.setTitle("Select Date and Time");
				
				 timePicker = (TimePicker)picker.findViewById(R.id.timePicker1);
				 datePicker=(DatePicker)picker.findViewById(R.id.datePicker1);
				 Button selectButton = (Button)picker.findViewById(R.id.setTime);
				 
				 selectButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int hour=timePicker.getCurrentHour();
						int min=timePicker.getCurrentMinute();
						int day=datePicker.getDayOfMonth();
						int year=datePicker.getYear();
						int mnth=datePicker.getMonth()+1;
						
						String mins=String.valueOf(min);
						if(mins.length()==1){
							mins="0"+mins;
						}
						
						String d=String.valueOf(day)+"-"+String.valueOf(mnth)+"-"+String.valueOf(year);
						Log.i("StringDate",d);
						 sdf=new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
						 formatter = new SimpleDateFormat("E, dd MMM yyyy",Locale.ENGLISH);
						try {
							 date=sdf.parse(d);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
						 dateFormat=formatter.format(date);
						 s=new StringBuilder(dateFormat);
						
						for(int i=0;i<s.length();i++){
							
							 c=s.charAt(i);
							if(Character.isLowerCase(c)){
								s.setCharAt(i, Character.toUpperCase(c));
							}
						}
					
						startEventText.setText(s.toString()+" "+String.valueOf(hour)+":"+mins);
						Log.i("date",formatter.format(date));
						Log.i("DATE",""+date);
						picker.dismiss();
					}
				});
				
				picker.show();
				
				
				break;
		 
			case R.id.endEventLay:
				
				picker = new Dialog(CreateEventActivity.this);
				picker.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.WHITE));
				picker.requestWindowFeature(Window.FEATURE_NO_TITLE);
				picker.setContentView(R.layout.popup_datepicker);
				//picker.setTitle("Select Date and Time");
				
				 timePicker = (TimePicker)picker.findViewById(R.id.timePicker1);
				 datePicker=(DatePicker)picker.findViewById(R.id.datePicker1);
				 Button selectButton1 = (Button)picker.findViewById(R.id.setTime);
				 
				 selectButton1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int hour=timePicker.getCurrentHour();
						int min=timePicker.getCurrentMinute();
						int day=datePicker.getDayOfMonth();
						int year=datePicker.getYear();
						int mnth=datePicker.getMonth()+1;
						
						String mins=String.valueOf(min);
						if(mins.length()==1){
							mins="0"+mins;
						}
						
						String d=String.valueOf(day)+"-"+String.valueOf(mnth)+"-"+String.valueOf(year);
						Log.i("StringDate",d);
						 sdf=new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
						 formatter = new SimpleDateFormat("E, dd MMM yyyy",Locale.ENGLISH);
						try {
							 date=sdf.parse(d);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
						 dateFormat=formatter.format(date);
						 s=new StringBuilder(dateFormat);
						
						for(int i=0;i<s.length();i++){
							
							 c=s.charAt(i);
							if(Character.isLowerCase(c)){
								s.setCharAt(i, Character.toUpperCase(c));
							}
						}
					
						endEventText.setText(s.toString()+" "+String.valueOf(hour)+":"+mins);
						Log.i("date",formatter.format(date));
						Log.i("DATE",""+date);
						picker.dismiss();
					}
				});
				
				picker.show();
				
				break;
				
			case R.id.cancel_Btn_media_Lay:
				
				TabAct.tabHost.setCurrentTab(1);
			
				break;
				
				case R.id.inviteGuestt:
					
					bottomTextView.setVisibility(View.GONE);
					break;
			

					
			case R.id.done_event_Lay:
				TabAct.tabHost.setCurrentTab(1);
				break;
				
				
			case R.id.eventCategoryLay:
				
				eventCategorydialog = new Dialog(CreateEventActivity.this);
				
				eventCategorydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				eventCategorydialog.setContentView(R.layout.popup_event_category);
				eventCategorydialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
				/*Drawable draw = new ColorDrawable(Color.BLACK);
				draw.setAlpha(100);
				eventCategorydialog.getWindow().setBackgroundDrawable(draw);*/
			
				Window win = eventCategorydialog.getWindow();
				WindowManager.LayoutParams wlpmn = win.getAttributes();
				wlpmn.gravity = Gravity.BOTTOM;
				win.setAttributes(wlpmn);
				
				
				RelativeLayout pop_event_caregory_lay = (RelativeLayout)eventCategorydialog.findViewById(R.id.pop_event_caregory_lay);
				pop_event_caregory_lay.setBackgroundColor(Color.WHITE);
				
				sportsBtnLay = (LinearLayout)eventCategorydialog.findViewById(R.id.sportsBtnLay);
				partyLay2 = (LinearLayout)eventCategorydialog.findViewById(R.id.partyLay2);
				meetingLay3 = (LinearLayout)eventCategorydialog.findViewById(R.id.meetingLay3);
				final ImageView sportsBtn = (ImageView)eventCategorydialog.findViewById(R.id.sportsBtn);
				final ImageView partyBtn = (ImageView)eventCategorydialog.findViewById(R.id.partyBtn);
				final ImageView mettingBtn = (ImageView)eventCategorydialog.findViewById(R.id.mettingBtn);
				
				musicartLay1 = (LinearLayout)eventCategorydialog.findViewById(R.id.musicartLay1);
				weddingLay2 = (LinearLayout)eventCategorydialog.findViewById(R.id.weddingLay2);
				festivalLay3 = (LinearLayout)eventCategorydialog.findViewById(R.id.festivalLay3);
				final ImageView musicArtBtn = (ImageView)eventCategorydialog.findViewById(R.id.musicArtBtn);
				final ImageView weddingBtn = (ImageView)eventCategorydialog.findViewById(R.id.weddingBtn);
				final ImageView festivalBtn = (ImageView)eventCategorydialog.findViewById(R.id.festivalBtn);
				
				meetupLay1 = (LinearLayout)eventCategorydialog.findViewById(R.id.meetupLay1);
				outdoorLay2 = (LinearLayout)eventCategorydialog.findViewById(R.id.outdoorLay2);
				otherLay3 = (LinearLayout)eventCategorydialog.findViewById(R.id.otherLay3);
				final ImageView meetupBtn = (ImageView)eventCategorydialog.findViewById(R.id.meetupBtn);
				final ImageView outdoorBtn = (ImageView)eventCategorydialog.findViewById(R.id.outdoorBtn);
				final ImageView otherlBtn = (ImageView)eventCategorydialog.findViewById(R.id.otherlBtn);
				
				
				if(value.equalsIgnoreCase("Sports")){
					sportsBtn.setBackgroundResource(R.drawable.sport_selected_3x);
					partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
					mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
					musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
					weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
					festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
					meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
					outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
					otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
				}else if (value.equalsIgnoreCase("Party")) {
					sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
					partyBtn.setBackgroundResource(R.drawable.party_selected_3x);
					mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
					musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
					weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
					festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
					meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
					outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
					otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
				}else if (value.equalsIgnoreCase("Meeting")) {
					sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
					partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
					mettingBtn.setBackgroundResource(R.drawable.meeting_selected_3x);
					musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
					weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
					festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
					meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
					outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
					otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
				}else if (value.equalsIgnoreCase("Music / Art")) { 
					sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
					partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
					mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
					musicArtBtn.setBackgroundResource(R.drawable.music_selected_3x);
					weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
					festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
					meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
					outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
					otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
				}else if (value.equalsIgnoreCase("Wedding")) {
					sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
					partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
					mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
					musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
					weddingBtn.setBackgroundResource(R.drawable.wedding_selected_3x);
					festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
					meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
					outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
					otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
				}else if (value.equalsIgnoreCase("Festival")) {
					sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
					partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
					mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
					musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
					weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
					festivalBtn.setBackgroundResource(R.drawable.festival_selected_3x);
					meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
					outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
					otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
				}else if (value.equalsIgnoreCase("Meet-up")) {
					sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
					partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
					mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
					musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
					weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
					festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
					meetupBtn.setBackgroundResource(R.drawable.meet_up_selected_3x);
					outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
					otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
				}else if (value.equalsIgnoreCase("Outdoors")) {
					sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
					partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
					mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
					musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
					weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
					festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
					meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
					outdoorBtn.setBackgroundResource(R.drawable.outdoor_selected_3x);
					otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
				}else if (value.equalsIgnoreCase("Others")) {
					sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
					partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
					mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
					musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
					weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
					festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
					meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
					outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
					otherlBtn.setBackgroundResource(R.drawable.other_selected_3x);
				}
					
					
				sportsBtnLay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						eventCatText.setText("Sports");
						sportsBtn.setBackgroundResource(R.drawable.sport_selected_3x);
						partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
						mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
						musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
						weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
						festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
						meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
						outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
						otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
						value = "Sports";
						eventCategorydialog.dismiss();
						eventTypeValues();
					}
				});
				
				partyLay2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
						partyBtn.setBackgroundResource(R.drawable.party_selected_3x);
						mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
						musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
						weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
						festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
						meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
						outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
						otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
						eventCatText.setText("Party");
						value = "Party";
						
						eventCategorydialog.dismiss();
						eventTypeValues();
						
					}
				});
				
				meetingLay3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
						partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
						mettingBtn.setBackgroundResource(R.drawable.meeting_selected_3x);
						musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
						weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
						festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
						meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
						outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
						otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
						eventCatText.setText("Meeting");
						value = "Meeting";
						
						eventCategorydialog.dismiss();
						eventTypeValues();
						
					}
				});
				
				
				musicartLay1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
						partyBtn.setBackgroundResource(R.drawable.party_selected_3x);
						mettingBtn.setBackgroundResource(R.drawable.meeting_selected_3x);
						musicArtBtn.setBackgroundResource(R.drawable.music_selected_3x);
						weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
						festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
						meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
						outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
						otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
						eventCatText.setText("Music / Art");
						value = "Music / Art";
						
						eventCategorydialog.dismiss();
						eventTypeValues();
						
					}
				});
				
				weddingLay2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
						partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
						mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
						musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
						weddingBtn.setBackgroundResource(R.drawable.wedding_selected_3x);
						festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
						meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
						outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
						otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
						eventCatText.setText("Wedding");
						value = "Wedding";
						
						eventCategorydialog.dismiss();
						eventTypeValues();
						
					}
				});
				
				festivalLay3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
						partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
						mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
						musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
						weddingBtn.setBackgroundResource(R.drawable.wedding_selected_3x);
						festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
						meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
						outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
						otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
						eventCatText.setText("Festival");
						value = "Festival";
						
						eventCategorydialog.dismiss();
						eventTypeValues();
					}
				});
				
				
				
				meetupLay1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
						partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
						mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
						musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
						weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
						festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
						meetupBtn.setBackgroundResource(R.drawable.meet_up_selected_3x);
						outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
						otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
						eventCatText.setText("Meet-up");
						value = "Meet-up";
						eventTypeValues();
						eventCategorydialog.dismiss();
						eventTypeValues();
					}
				});
				
				
				outdoorLay2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
						partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
						mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
						musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
						weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
						festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
						meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
						outdoorBtn.setBackgroundResource(R.drawable.outdoor_selected_3x);
						otherlBtn.setBackgroundResource(R.drawable.other_unselected_3x);
						eventCatText.setText("Outdoors");
						value = "Outdoors";
						eventCategorydialog.dismiss();
						eventTypeValues();
					}
				});
				
				otherLay3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						otherlBtn.setBackgroundResource(R.drawable.other_selected_3x);
						sportsBtn.setBackgroundResource(R.drawable.sport_unselected_3x);
						partyBtn.setBackgroundResource(R.drawable.party_unselected_3x);
						mettingBtn.setBackgroundResource(R.drawable.meeting_unselected_3x);
						musicArtBtn.setBackgroundResource(R.drawable.music_unselected_3x);
						weddingBtn.setBackgroundResource(R.drawable.wedding_unselected_3x);
						festivalBtn.setBackgroundResource(R.drawable.festival_unselected_3x);
						meetupBtn.setBackgroundResource(R.drawable.meet_up_unselected_3x);
						outdoorBtn.setBackgroundResource(R.drawable.outdoor_unselected_3x);
						eventCatText.setText("Others");
						value = "Others";
					eventCategorydialog.dismiss();
					eventTypeValues();
					}
				});
				
			
				eventCategorydialog.show();
				
				break;
				
							
				
			}
		}
		
		
		 @Override
			protected void onActivityResult(int requestCode, int resultCode, Intent data) {
				super.onActivityResult(requestCode, resultCode, data);
				
					
				if (requestCode == PICK_FROM_FILE) {
					   if (resultCode == RESULT_OK) {
						   
						   BitmapFactory.Options options = new BitmapFactory.Options();
						   
				            // downsizing image as it throws OutOfMemory Exception for larger
				            // images
				            options.inSampleSize = 8;
				          //  Uri selectedImageUri = data.getData();
			              //  selectedImagePath = getPath(selectedImageUri);
			                
				            if(mImageCaptureUri != null){
				          imagePath = mImageCaptureUri.getPath();
				            System.out.println("imagePath: " +imagePath);
				            mygallery.addView(insertPhoto(imagePath));
				            }
				            //final Bitmap bitmap = BitmapFactory.decodeFile(mImageCaptureUri.getPath(), options);
				            //imgPreview.setImageBitmap(bitmap);	
				            
					   }
					}
						//	mImageCaptureUri = data.getData();
				/* if (requestCode == PICK_FROM_FILE) {
							   if (resultCode == RESULT_OK) {
						           
						                Uri selectedImageUri = data.getData();
						                selectedImagePath = getPath(selectedImageUri);
						                System.out.println("Image Path : " + selectedImagePath);
						              //  selectImage.setImageURI(selectedImageUri);
						                totalFiles = totalFiles + "," +selectedImagePath;
						                System.out.println("totalFiles: " +totalFiles);
						                File targetDirector = new File(selectedImagePath);
						                
						                
						            if(targetDirector.exists())  
						            {
						              	  String subString = selectedImagePath.substring(selectedImagePath.length() - 3);
						             // 	  System.out.println("orientation:===" +orientation);
						             
						              	  
						              	if(subString.equalsIgnoreCase("mp4") || subString.equalsIgnoreCase("MP4") ||
						       			  subString.equalsIgnoreCase("3gp") || subString.equalsIgnoreCase("3GP") || subString.equalsIgnoreCase("AVI") || subString.equalsIgnoreCase("avi")) { // 
						       		
						              		Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(selectedImagePath,  Thumbnails.MICRO_KIND);
						              		System.out.println("BitMapToString(bitmap)+ " +BitMapToString(bitmap));
						              		mygallery.addView(insertPhoto(BitMapToString(bitmap)));
						              		
						              	}else {
						              		mygallery.addView(insertPhoto(selectedImagePath));
										}
						           }
						               
						                
						                File[] files = targetDirector.listFiles();
						                for (File file : files){
						                 mygallery.addView(insertPhoto(file.getAbsolutePath()));
						                } 
						                
						            }
						        }*/
			
					
				}
				
		  // change bitmap to string
		    public String BitMapToString(Bitmap bitmap){
		         ByteArrayOutputStream baos=new  ByteArrayOutputStream();
		         bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
		         byte [] b=baos.toByteArray();
		         String temp=Base64.encodeToString(b, Base64.DEFAULT);
		         return temp;
		    }
		    
				 public String getPath(Uri uri) {
				        String[] projection = { MediaStore.Images.Media.DATA };
				        Cursor cursor = managedQuery(uri, projection, null, null, null);
				        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				        cursor.moveToFirst();
				        return cursor.getString(column_index);
				    }
		    
		  
		    View insertPhoto(String path){
		     Bitmap bm = decodeSampledBitmapFromUri(path, 220, 150);
		     
		     LinearLayout layout = new LinearLayout(getApplicationContext());
		     layout.setLayoutParams(new LayoutParams(250, 180));
		     layout.setGravity(Gravity.CENTER);
		     
		     ImageView imageView = new ImageView(getApplicationContext());
		     imageView.setLayoutParams(new LayoutParams(220, 150));
		     imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		     Picasso.with(getApplicationContext()).load(new File(imagePath)).resize(160, 120).centerCrop().into(imageView);
		     imageView.setImageBitmap(bm);
		     
		     layout.addView(imageView);
		     return layout;
		    }
		    
		    
		   
		    
		    public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
		     Bitmap bm = null;
		     
		     // First decode with inJustDecodeBounds=true to check dimensions
		     final BitmapFactory.Options options = new BitmapFactory.Options();
		     options.inJustDecodeBounds = true;
		     BitmapFactory.decodeFile(path, options);
		     
		     // Calculate inSampleSize
		     options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		     
		     // Decode bitmap with inSampleSize set
		     options.inJustDecodeBounds = false;
		     bm = BitmapFactory.decodeFile(path, options); 
		     
		     return bm;  
		    }
		    
		    public int calculateInSampleSize(
		      
		     BitmapFactory.Options options, int reqWidth, int reqHeight) {
		     // Raw height and width of image
		     final int height = options.outHeight;
		     final int width = options.outWidth;
		     int inSampleSize = 1;
		        
		     if (height > reqHeight || width > reqWidth) {
		      if (width > height) {
		       inSampleSize = Math.round((float)height / (float)reqHeight);   
		      } else {
		       inSampleSize = Math.round((float)width / (float)reqWidth);   
		      }   
		     }
		     
		     return inSampleSize;   
		    }
		    
		    
		@Override
		protected void onResume() {
			super.onResume();
			catVAlue = value;
			//scrollView1.scrollTo(0, 0);
		}

		public void dismissnullEvent(){
			eventTypeNullDialog = new Dialog(CreateEventActivity.this);
			eventTypeNullDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.WHITE));
			eventTypeNullDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			eventTypeNullDialog.setContentView(R.layout.popup_no_event);
			
			//eventThemeSpin = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,themeList);
			eventThemeSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerEventTheme.setAdapter(eventThemeSpin);
			
			Button evenTypeNullBtn = (Button)eventTypeNullDialog.findViewById(R.id.evenTypeNullBtn);
			evenTypeNullBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					eventTypeNullDialog.dismiss();
				}
			});
			eventTypeNullDialog.show();
		}
		
		
		
		public void eventTypeValues(){
			eventtype = value;
			if(eventtype.equalsIgnoreCase("Sports")){
				System.out.println("aaa Sports");
				dismissnullEvent();
			}else if (eventtype.equalsIgnoreCase("Party")) {
				System.out.println("aaa Party");
				dataAdapter2 = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.party_event_type));
				dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerEentType.setAdapter(dataAdapter2);
				
				eventThemeSpin = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.party_event_theme));
				eventThemeSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerEventTheme.setAdapter(eventThemeSpin);
				
				
			}else if (eventtype.equalsIgnoreCase("Meeting")) {
				System.out.println("aaa Meeting");
				dataAdapter2 = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.meeting_event_type));
				dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerEentType.setAdapter(dataAdapter2);
				
				eventThemeSpin = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.meeting_event_theme));
				eventThemeSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerEventTheme.setAdapter(eventThemeSpin);
				
				
			}else if (eventtype.equalsIgnoreCase("Wedding")) {
				System.out.println("aaa Wedding");
				dataAdapter2 = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.wedding_event_type));
				dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerEentType.setAdapter(dataAdapter2);
				
				eventThemeSpin = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.wedding_event_theme));
				eventThemeSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerEventTheme.setAdapter(eventThemeSpin);
				
				
			}else {
				dismissnullEvent();
				// Music / Art,Meet-up,Festival,Outdoors,Others
			}
			
		}
	}


