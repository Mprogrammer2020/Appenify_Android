package com.calgiary.appenify;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

@SuppressLint("SetJavaScriptEnabled")
public class TermsAndConditions extends Activity{
	
	WebView termsCondWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_terms_and_conditions);
		
		termsCondWebView = (WebView)findViewById(R.id.termsCondView);
		

		termsCondWebView.getSettings().setJavaScriptEnabled(true);
		termsCondWebView.getSettings().setLoadWithOverviewMode(true);
		termsCondWebView.getSettings().setUseWideViewPort(true);
		termsCondWebView.loadUrl("http://www.bluehost.com/cgi/info/terms.html");
		
	}

}
