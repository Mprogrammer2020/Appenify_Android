package com.calgiary.appenify;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class FavouriteEventCat extends Activity{

	RelativeLayout backLay, okLay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.popup_event_category1);
		
		backLay = (RelativeLayout)findViewById(R.id.actionbar_Btn_mediaLay);
		okLay = (RelativeLayout)findViewById(R.id.rightactionbar_Btn_mediaLay);
		
		backLay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		okLay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
			}
		});
		
		
	}
	
	 @Override
	public void onBackPressed() {
	//	super.onBackPressed();
		 overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
		 finish();
	}
}
