package com.calgiary.appenify;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;

public class SocialConnectActivity extends Activity{

	Button fb_connect_Btn, twitter_connect_Btn, linkedin_connect_Btn, googlePlus_connect_Btn, skip_Button;
	Dialog confirmSocialDialog;
	

	  String message = "Hello there!";
	    private UiLifecycleHelper uiHelper;
	   /* private Session.StatusCallback callback = new Session.StatusCallback() {
	        @Override
	        public void call(Session session, SessionState state,
	                Exception exception) {
	            onSessionStateChange(session, state, exception);
	        }
	    };
	    private void onSessionStateChange(Session session, SessionState state,
	            Exception exception) {
	        if (state.isOpened()) {
	            // System.out.println("Logged in...");
	        } else if (state.isClosed()) {
	            // System.out.println("Logged out...");
	        }
	    }*/
	/*    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        uiHelper.onActivityResult(requestCode, resultCode, data,
	                new FacebookDialog.Callback() {
	                    @Override
	                    public void onError(FacebookDialog.PendingCall pendingCall,
	                            Exception error, Bundle data) {
	                        Log.e("Activity",
	                                String.format("Error: %s", error.toString()));
	                    }
	                    @Override
	                    public void onComplete(
	                            FacebookDialog.PendingCall pendingCall, Bundle data) {
	                        Log.i("Activity", "Success!");
	                    }
	                });
	    }*/
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_social_connect);
		
		fb_connect_Btn = (Button)findViewById(R.id.fb_connect_Btn);
		twitter_connect_Btn = (Button)findViewById(R.id.twitter_connect_Btn);
		linkedin_connect_Btn = (Button)findViewById(R.id.linkedin_connect_Btn);
		googlePlus_connect_Btn = (Button)findViewById(R.id.googlePlus_connect_Btn);
		skip_Button = (Button)findViewById(R.id.skipBtn_social);
		
		
		fb_connect_Btn.setBackgroundResource(R.drawable.facebook_draa);
		twitter_connect_Btn.setBackgroundResource(R.drawable.twitter_hover);
		linkedin_connect_Btn.setBackgroundResource(R.drawable.linkedin_hover);
		googlePlus_connect_Btn.setBackgroundResource(R.drawable.google_hover);
		
		
		 /* uiHelper = new UiLifecycleHelper(this, callback);
	        uiHelper.onCreate(savedInstanceState);
	        
	        
	        //Code to get KeyHash value.
	        try {
	     PackageInfo info = getPackageManager().getPackageInfo(
	     "com.netset.appenify",
	     PackageManager.GET_SIGNATURES);
	     for (Signature signature : info.signatures) {
	     MessageDigest md = MessageDigest.getInstance("SHA");
	     md.update(signature.toByteArray());
	     System.out.println("KeyHash : "+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
	     }
	     } catch (NameNotFoundException e) {
	     } catch (NoSuchAlgorithmException e) {
	     }
	        
	       */
		
	}
	
	
	public void socialConnect(View view){
		
		switch(view.getId()){
	       
		case R.id.fb_connect_Btn:
			
			 //facebook();
			 
			Intent ii = new Intent(SocialConnectActivity.this, ShareWithFriendsActivity.class);
			startActivity(ii);
			finish();
			break;
			
		case R.id.twitter_connect_Btn:
			Intent i2 = new Intent(SocialConnectActivity.this, ShareWithFriendsActivity.class);
			startActivity(i2);
			finish();
			
			break;
					
		case R.id.linkedin_connect_Btn:
			Intent i3 = new Intent(SocialConnectActivity.this, ShareWithFriendsActivity.class);
			startActivity(i3);
			finish();
			
			
			break;
			
		case R.id.googlePlus_connect_Btn:
			Intent i4 = new Intent(SocialConnectActivity.this, GooglePlusIntegration.class);
			startActivity(i4);
			finish();
			
			
			break;
			
			case R.id.skipBtn_social:
				
				confirmSocialDialog = new Dialog(SocialConnectActivity.this);
				confirmSocialDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				confirmSocialDialog.setContentView(R.layout.pop_up_social_connect);
				
				RelativeLayout social_layoutt = (RelativeLayout)confirmSocialDialog.findViewById(R.id.social_layoutt);
				social_layoutt.setBackgroundColor(Color.WHITE);
				
				Button yesButton = (Button)confirmSocialDialog.findViewById(R.id.socialConnectPopUpYES);
				Button connectButton = (Button)confirmSocialDialog.findViewById(R.id.socialConnectPopUpOKConnect);
				
				yesButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						Intent i = new Intent(SocialConnectActivity.this, TabAct.class);
						Bundle b=new Bundle();
						 b.putString("condition","tb2");
						 i.putExtras(b);
						startActivity(i);
						finish();
						confirmSocialDialog.dismiss();
					}
				});
				
				connectButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						
						confirmSocialDialog.dismiss();
					}
				});
				confirmSocialDialog.show();
				
				break;
			
		}
	}
	
	/*@Override
    protected void onDestroy() {
        uiHelper.onDestroy();
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        uiHelper.onResume();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }
    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }*/
    public void facebook() {
        if (!checkNetwork()) {
            Toast.makeText(getApplicationContext(),
                    "No active internet connection ...", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (!checkFbInstalled()) {
            Toast.makeText(getApplicationContext(),
                    "Facebook app not installed!..", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_SHORT).show();
        if (FacebookDialog.canPresentShareDialog(this,
                FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
            FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(
                    this).setName("The Buddha Quotes")
                    .setLink("http://code2care.org").setDescription(message)
                    .setPicture("http://code2care.org/buddha.jpg").build();
            uiHelper.trackPendingDialogCall(shareDialog.present());
        } else {
            // System.out.println("Fail Success!");
        }
    }
    private boolean checkNetwork() {
        boolean wifiAvailable = false;
        boolean mobileAvailable = false;
        ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = conManager.getAllNetworkInfo();
        for (NetworkInfo netInfo : networkInfo) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    wifiAvailable = true;
            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    mobileAvailable = true;
        }
        return wifiAvailable || mobileAvailable;
    }
    public Boolean checkFbInstalled() {
        PackageManager pm = getPackageManager();
        boolean flag = false;
        try {
            pm.getPackageInfo("com.facebook.katana",
                    PackageManager.GET_ACTIVITIES);
            flag = true;
        } catch (PackageManager.NameNotFoundException e) {
            flag = false;
        }
        return flag;
    }
    
    
}

