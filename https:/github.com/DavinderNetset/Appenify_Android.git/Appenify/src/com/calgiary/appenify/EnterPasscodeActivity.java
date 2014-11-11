package com.calgiary.appenify;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class EnterPasscodeActivity extends Activity{

	Button verify_passcode_Btn, resend_passcode_Btn;
	Dialog sendDialog;
	EditText passcode_edit1, passcode_edit2, passcode_edit3, passcode_edit4, passcode_edit5, passcode_edit6;
	String oneTimePasscode;
	TextView error_messsage;
	RelativeLayout mainLayyy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_enter_passcode);
		
		verify_passcode_Btn = (Button)findViewById(R.id.verify_passcode_Btn);
		resend_passcode_Btn = (Button)findViewById(R.id.resend_passcode_Btn);
		
		passcode_edit1 = (EditText)findViewById(R.id.passcode_edit1);
		passcode_edit2 = (EditText)findViewById(R.id.passcode_edit2);
		passcode_edit3= (EditText)findViewById(R.id.passcode_edit3);
		passcode_edit4 = (EditText)findViewById(R.id.passcode_edit4);
		passcode_edit5 = (EditText)findViewById(R.id.passcode_edit5);
		passcode_edit6 = (EditText)findViewById(R.id.passcode_edit6);
		error_messsage = (TextView)findViewById(R.id.error_messsage);
		mainLayyy = (RelativeLayout)findViewById(R.id.mainLayyy);
		
		Intent i = getIntent();
		oneTimePasscode = i.getStringExtra("phoneNumberr");
		passcode_edit2.setCursorVisible(false);
		passcode_edit1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				passcode_edit1.setCursorVisible(true);
				if(passcode_edit1.getText().toString().length()==1)    
		        {
					
					passcode_edit2.requestFocus();
		        }
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
	
		passcode_edit2.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				passcode_edit2.setCursorVisible(true);
				if(passcode_edit2.getText().toString().length()==1)    
		        {
					passcode_edit3.requestFocus();
		        }
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				passcode_edit1.setCursorVisible(true);
				if(passcode_edit2.getText().toString().isEmpty())    
		        {
					passcode_edit1.requestFocus();
		        }
			}
		});
		
		
		passcode_edit3.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				passcode_edit3.setCursorVisible(true);
				if(passcode_edit3.getText().toString().length()==1)    
		        {
					passcode_edit4.requestFocus();
		        }
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			@Override
			public void afterTextChanged(Editable s) {
				passcode_edit2.setCursorVisible(true);
				if(passcode_edit3.getText().toString().isEmpty())    
		        {
					passcode_edit2.requestFocus();
		        }
			}
		});
		
		
		passcode_edit4.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				passcode_edit4.setCursorVisible(true);
				if(passcode_edit4.getText().toString().length()==1)    
		        {
					passcode_edit5.requestFocus();
		        }
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			@Override
			public void afterTextChanged(Editable s) {
				passcode_edit3.setCursorVisible(true);
				if(passcode_edit4.getText().toString().isEmpty())    
		        {
					passcode_edit3.requestFocus();
		        }
				
			}
		});
		
		
		
		passcode_edit5.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						passcode_edit5.setCursorVisible(true);
						if(passcode_edit5.getText().toString().length()==1)    
				        {
							passcode_edit6.requestFocus();
				        }
					}
						@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {}
					@Override
					public void afterTextChanged(Editable s) {
						passcode_edit4.setCursorVisible(true);
						if(passcode_edit5.getText().toString().isEmpty())    
				        {
							passcode_edit4.requestFocus();
				        }
						
					}
				});
		
		passcode_edit6.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
				@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			@Override
			public void afterTextChanged(Editable s) {
				passcode_edit5.setCursorVisible(true);
				if(passcode_edit6.getText().toString().isEmpty())    
		        {
					passcode_edit5.requestFocus();
		        }
				
			}
		});
		
		passcode_edit6.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				passcode_edit6.setCursorVisible(false);
				return false;
			}
			
		});
		
		
		
		
		/*passcode_edit6.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_DEL) {
	                if(passcode_edit6.getText().toString().isEmpty()){
	                	passcode_edit5.requestFocus();
	                }
	                Log.e("IME_TEST", "DEL KEY");
	            }
				
				return false;
			}
		});*/

				
	}
	
	   public void verifyResendFunction(View view){
			switch(view.getId()){
		       case R.id.mainLayyy:
		    	   InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(passcode_edit1.getWindowToken(), 0);
						imm.hideSoftInputFromWindow(passcode_edit2.getWindowToken(), 0);
						imm.hideSoftInputFromWindow(passcode_edit3.getWindowToken(), 0);
						imm.hideSoftInputFromWindow(passcode_edit4.getWindowToken(), 0);
						imm.hideSoftInputFromWindow(passcode_edit5.getWindowToken(), 0);
						imm.hideSoftInputFromWindow(passcode_edit6.getWindowToken(), 0);
						passcode_edit1.setCursorVisible(false);
						passcode_edit2.setCursorVisible(false);
						passcode_edit3.setCursorVisible(false);
						passcode_edit4.setCursorVisible(false);
						passcode_edit5.setCursorVisible(false);
						passcode_edit6.setCursorVisible(false);
						
		    	   break;
		    	   
		    	   
					case R.id.verify_passcode_Btn:
					
						
						if(!passcode_edit1.getText().toString().isEmpty() && !passcode_edit2.getText().toString().isEmpty() && !passcode_edit3.getText().toString().isEmpty()
								&& !passcode_edit4.getText().toString().isEmpty() && !passcode_edit5.getText().toString().isEmpty() && !passcode_edit6.getText().toString().isEmpty()){
							Intent i = new Intent(EnterPasscodeActivity.this, SocialConnectActivity.class);
							startActivity(i);
							finish();
						}else {
							error_messsage.setVisibility(View.VISIBLE);
							Toast.makeText(getApplicationContext(), "Please check your passcode fields", Toast.LENGTH_LONG).show();
						}
						/*sendDialog = new Dialog(EnterPasscodeActivity.this);
						sendDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						sendDialog.setContentView(R.layout.popup_confirm_phone_no);
						
						RelativeLayout confirem_layyy = (RelativeLayout)sendDialog.findViewById(R.id.confirem_layyy);
						confirem_layyy.setBackgroundColor(Color.WHITE);
						
						Button editButton = (Button)sendDialog.findViewById(R.id.viewPhoneNumberPopUpEditButton);
						Button continueButton = (Button)sendDialog.findViewById(R.id.viewPhoneNumberPopUpContinueButton);
						
						TextView viewPhoneNumberText3 = (TextView)sendDialog.findViewById(R.id.viewPhoneNumberText3);
						viewPhoneNumberText3.setText(oneTimePasscode);
						
						oneTimePasscode = passcode_edit1.getText().toString() + passcode_edit2.getText().toString() + passcode_edit3.getText().toString() +
								passcode_edit2.getText().toString() +passcode_edit5.getText().toString() +passcode_edit6.getText().toString();
						
						editButton.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								
								sendDialog.dismiss();
								
							}
						});
						
						
						continueButton.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								
								Intent i = new Intent(EnterPasscodeActivity.this, SocialConnectActivity.class);
								startActivity(i);
								finish();
								sendDialog.dismiss();
							}
						});
						sendDialog.show();*/
						
						break;	
						
					case R.id.resend_passcode_Btn:
						
						break;	
						
						
			}
	   }
	   
	 
	 

}
