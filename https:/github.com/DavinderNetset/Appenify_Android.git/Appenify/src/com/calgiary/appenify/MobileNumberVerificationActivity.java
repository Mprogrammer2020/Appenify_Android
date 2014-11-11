package com.calgiary.appenify;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MobileNumberVerificationActivity extends Activity {

	
	ImageView termsConditions, privatePolicy, agreeContinue;
	int backgroundImages[]={R.drawable.splash_1,R.drawable.splash_2 , 
			R.drawable.splash_3,R.drawable.splash_4, R.drawable.splash_1 };
	 Handler handler = new Handler();
	int flag;
	RelativeLayout BackkgroundLay1;
	//SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_mob_no_veri);
		
		
		termsConditions = (ImageView)findViewById(R.id.terms_and_continue);
		privatePolicy = (ImageView)findViewById(R.id.privacyPolicy);
		agreeContinue = (ImageView)findViewById(R.id.agree_and_continue);
		BackkgroundLay1 = (RelativeLayout)findViewById(R.id.BackkgroundLay1);
		
		/*prefs = this.getSharedPreferences("FirstTime", Context.MODE_PRIVATE);
		boolean l = prefs.getBoolean("isFirstTime", false); 

		if(l==false)
		{
		prefs.edit().putBoolean("isFirstTime",true).apply();
	
		String url = "http://www.appenify.com/terms-of-service/";
		Intent terms = new Intent(Intent.ACTION_VIEW);
		terms.setData(Uri.parse(url));
		startActivity(terms);

		}*/
		
		
		//saveScore = getSharedPreferences("score",Context.MODE_PRIVATE);
		
	//	termsConditions.setImageResource(R.drawable.terms_and_condition);
		agreeContinue.setImageResource(R.drawable.agree_and_continue);
		
		 handler.postDelayed(changeImage, 2000);
   }
		
	
	public void agreeContinue(View view){
		
		switch(view.getId()){
	       
		case R.id.agree_and_continue:
		//	Toast.makeText(getApplicationContext(), "agree_and_continue", Toast.LENGTH_LONG).show();
			Intent i = new Intent(MobileNumberVerificationActivity.this, VerifyPhoneNumberActivity.class);
			startActivity(i);
			finish();
			break;
		
		case R.id.terms_and_continue:
			String url = "http://www.appenify.com/terms-of-service/";
			Intent terms = new Intent(Intent.ACTION_VIEW);
			terms.setData(Uri.parse(url));
			startActivity(terms);
			//Toast.makeText(getApplicationContext(), "terms_and_continue", Toast.LENGTH_LONG).show();
//			Intent ii = new Intent(MobileNumberVerificationActivity.this, TermsAndConditions.class);
//			startActivity(ii);
			//finish();
			break;
			
		case R.id.privacyPolicy:
			String privacy = "http://www.appenify.com/privacy-policy/";
			Intent p = new Intent(Intent.ACTION_VIEW);
			p.setData(Uri.parse(privacy));
			startActivity(p);
			//Toast.makeText(getApplicationContext(), "privacyPolicy", Toast.LENGTH_LONG).show();
			//Intent in = new Intent(MobileNumberVerificationActivity.this, PrivacyPolicy.class);
			//startActivity(in);
		//	finish();
			break;
			
		
		}
	}

	  public Runnable changeImage = new Runnable(){

		    @Override
		    public void run(){
		    	   if(flag >= 0){
		        	    handler.removeCallbacks(changeImage);
		        		if( flag == backgroundImages.length -1){
		        			flag = 0;
		        		}
				        BackkgroundLay1.setBackgroundResource(backgroundImages[flag++]);
				        handler.postDelayed(changeImage, 2000);
		        	 }else{
		        	BackkgroundLay1.setBackgroundResource(backgroundImages[flag++]);
		           handler.postDelayed(changeImage, 2000);
		        }
		    }

		};
		
		 @Override
		    protected void onDestroy() {
		        super.onDestroy();
		    } 
		
}
