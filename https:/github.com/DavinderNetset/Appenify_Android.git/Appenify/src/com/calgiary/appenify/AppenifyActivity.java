package com.calgiary.appenify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupWindow;

public class AppenifyActivity extends Activity implements OnClickListener{

	Button pop_up_Phone_no,pop_up_social_connect,edit,contnue,ok,connect;
	PopupWindow viewPhoneNoPopUp; //,viewSocialConnect;
	View popUpViewPhone_no ;//,popUpView_socialConnect;
	LayoutInflater popUpInflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_appenify);
		
		pop_up_Phone_no=(Button) findViewById(R.id.pop_up);
		pop_up_social_connect=(Button) findViewById(R.id.pop_up_socialconnect);
		
		popUpInflater=(LayoutInflater) this
        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		//PopUp for ViewPhoneNo
		
		popUpViewPhone_no=popUpInflater.inflate(R.layout.popup_confirm_phone_no, null);
		
		edit=(Button) popUpViewPhone_no.findViewById(R.id.viewPhoneNumberPopUpEditButton);
		contnue=(Button) popUpViewPhone_no.findViewById(R.id.viewPhoneNumberPopUpContinueButton);
		
		viewPhoneNoPopUp=new PopupWindow(popUpViewPhone_no, LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		
		viewPhoneNoPopUp.setContentView(popUpViewPhone_no);
		
		pop_up_Phone_no.setOnClickListener(this);
		
		//Pop Up for Social Connect
		
	//	popUpView_socialConnect=popUpInflater.inflate(R.layout.pop_up_social_connect, null);
		
	//	ok=(Button) popUpView_socialConnect.findViewById(R.id.socialConnectPopUpYES);
		//connect=(Button) popUpView_socialConnect.findViewById(R.id.socialConnectPopUpOKConnect);
		
		//viewSocialConnect=new PopupWindow(popUpView_socialConnect, LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT );
		//viewSocialConnect.setContentView(popUpView_socialConnect);
		pop_up_social_connect.setOnClickListener(this);

}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		viewPhoneNoPopUp.dismiss();
	//	viewSocialConnect.dismiss();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		
		case R.id.pop_up:{
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					viewPhoneNoPopUp.showAtLocation(popUpViewPhone_no, Gravity.CENTER, 0, 0);
				}
			}, 200);
			
			Intent i=new Intent(getApplicationContext(), ShareWithFriendsActivity.class);
			startActivity(i);
			
			break;
		}
		
		case R.id.pop_up_socialconnect:{
			
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
				//	viewSocialConnect.showAtLocation(popUpView_socialConnect, Gravity.CENTER, 0, 0);
				}
			}, 200);
		break;	
		}
		
		
		}
		
	}
	

}