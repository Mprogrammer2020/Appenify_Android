package com.calgiary.appenify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity{

	RelativeLayout BackkgroundLay;
	int backgroundImages[]={R.drawable.splash_1,R.drawable.splash_2, 
			 R.drawable.splash_3,R.drawable.splash_4 };
	 Handler handler = new Handler();
	int flag;
	 //    String images[]={};
	     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_splash_screen);
		
		/*Display display = getWindowManager().getDefaultDisplay(); 
		int screenWidth = display.getWidth();
		int screenHeight = display.getHeight();
		
		Log.i("", "Width screenWidth: " +screenWidth);
		Log.i("", "Height screenHeight: " +screenHeight);*/
				
		
		 BackkgroundLay = (RelativeLayout)findViewById(R.id.BackkgroundLay);
		 
		 
		 handler.postDelayed(changeImage, 1000);
		 
// METHOD 1    
       
        /****** Create Thread that will sleep for 5 seconds *************/        
      Thread background = new Thread() {
           public void run() {
                
               try {
                   // Thread will sleep for 3 seconds
                   sleep(3*1000);
                   Intent i=new Intent(getBaseContext(),MobileNumberVerificationActivity.class);
                   startActivity(i);
                   finish();
                    
               } catch (Exception e) {
                
               }
           }
       };
        
       // start thread
       background.start();
        
//METHOD 2 
        
       /*
       new Handler().postDelayed(new Runnable() {
             
           // Using handler with postDelayed called runnable run method
 
           @Override
           public void run() {
               Intent i = new Intent(MainSplashScreen.this, FirstScreen.class);
               startActivity(i);
 
               // close this activity
               finish();
           }
       }, 5*1000); // wait for 5 seconds
       */
	}




	public Runnable changeImage = new Runnable(){

	    @Override
	    public void run(){
	    	//System.out.println("flag: " +flag);
	    //	System.out.println(" backgroundImages length: " + backgroundImages.length);
	    	
	        if(flag >= 0){
	        	    handler.removeCallbacks(changeImage);
	        		if( flag == backgroundImages.length -1){
	        			flag = 0;
	        		}
			        BackkgroundLay.setBackgroundResource(backgroundImages[flag++]);
			        handler.postDelayed(changeImage, 1000);
	        	 }else{
	        	BackkgroundLay.setBackgroundResource(backgroundImages[flag++]);
	           handler.postDelayed(changeImage, 1000);
	        }
	    }

	};
	
	
	 @Override
	    protected void onDestroy() {
	         
	        super.onDestroy();
	         
	    }
}
