package com.calgiary.appenify;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

@SuppressLint("SetJavaScriptEnabled")
public class PrivacyPolicy extends Activity {
	
	WebView privacyWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_terms_and_conditions);
		
		privacyWebView = (WebView)findViewById(R.id.privacyView);
		
		privacyWebView.getSettings().setJavaScriptEnabled(true);
		privacyWebView.getSettings().setLoadWithOverviewMode(true);
		privacyWebView.getSettings().setUseWideViewPort(true);
		privacyWebView.loadUrl("http://www.bluehost.com/privacy-policy");
		
	}


}
