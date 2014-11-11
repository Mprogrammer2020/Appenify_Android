package com.calgiary.appenify;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;


public class AboutAppenify extends Activity{

	RelativeLayout actionbar_Btn_mediaLay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	//	overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.act_about_appenify);
	
		actionbar_Btn_mediaLay = (RelativeLayout)findViewById(R.id.actionbar_Btn_mediaLay);
		
		actionbar_Btn_mediaLay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	
	/* @Override
	 * 
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
	        View rootView = inflater.inflate(R.layout.act_about_appenify, container, false);
	        
	        actionbar_Btn_mediaLay = (RelativeLayout)rootView.findViewById(R.id.actionbar_Btn_mediaLay);
			
			actionbar_Btn_mediaLay.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					getActivity().finish();
				}
			});
	        return rootView;
	    }*/
	 
	 @Override
	public void onBackPressed() {
	//	super.onBackPressed();
		 overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
		 finish();
	}
}
