package com.calgiary.appenify;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appenify.adapter.MobileVeriSpinnerModel;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class VerifyPhoneNumberActivity extends Activity {

	Spinner spinner;
	TextView countryCodeTextView, testCountryCode;
	EditText numberEditField;
	ImageView okTextView;
	Dialog confirmDialog;
	String[] countryCode;
	RelativeLayout mainverifyLay;

	public ArrayList<MobileVeriSpinnerModel> CustomListViewValuesArr = new ArrayList<MobileVeriSpinnerModel>();
	TextView output = null;
	CustomAdapter adapter;
	VerifyPhoneNumberActivity activity = null;
	String phoneNumber;
	String isSpin = "false";
	int check = 0;
	ArrayAdapter<String> countryCodespinner;
	List<String> cointryList;
	String[] data; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.act_verify_phone_number);
	
	
		spinner = (Spinner) findViewById(R.id.spinnerCountryCode);
		numberEditField = (EditText) findViewById(R.id.numberEditField);
		countryCodeTextView = (TextView) findViewById(R.id.countryCodeTextView);
		okTextView = (ImageView) findViewById(R.id.okTextView);
		testCountryCode = (TextView) findViewById(R.id.testCountryCode);
		mainverifyLay = (RelativeLayout) findViewById(R.id.mainverifyLay);

		countryCode = getResources().getStringArray(R.array.CountryCodes);
		testCountryCode.setText("Choose your country");
		data = getResources().getStringArray(R.array.country_arrays);

		cointryList = new ArrayList<String>();
		for(int i=0; i<data.length;i++){
			cointryList.add(data[i]);
			
			
		}
		
		countryCodespinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item , cointryList){
			@Override
			public View getDropDownView(int position, View convertView,
					ViewGroup parent) {
				
				View v=null;
				if(position==0){
					TextView tv=new TextView(getContext());
					tv.setHeight(0);
					v=tv;
					
					
					if(position==0 && isSpin.equalsIgnoreCase("false")) {
						isSpin = "true";
						countryCodeTextView.setText("");
					
					}
					//countryCodeTextView.setText("");
				}
				else {
					v=super.getDropDownView(position, null, parent);
				}
				return v;
			}
		};
		countryCodespinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(countryCodespinner);
		
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View v,
					int position, long id) {

				if (position == 0 ) {
				countryCodeTextView.setText("");
				}else {

				String items = "";
				int indexNumber = 0;

				indexNumber = (int) parentView.getItemIdAtPosition(position);

				System.out.println("indexNumber: " +indexNumber);
				
				items = countryCode[indexNumber];
				items = items.replaceAll("[,A-Z]", "");
				System.out.println("items: " +items);
				
				phoneNumber = "+" + items + " - ";
				numberEditField.setPadding(10, 0, 0, 0);
				
				countryCodeTextView.setText("+" + items);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {

			}

		});

		
		okTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				confirmDialog = new Dialog(VerifyPhoneNumberActivity.this);
				confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				confirmDialog.setContentView(R.layout.popup_confirm_phone_no);

				if (numberEditField.getText().toString().length() < 7) {
					numberEditField.setError("Invalid Phone Number");
				} else if (countryCodeTextView.getText().toString().length() < 1) {
					Toast.makeText(getApplicationContext(),
							"Please select country Code", Toast.LENGTH_LONG)
							.show();
				} else {
					RelativeLayout confirem_layyy = (RelativeLayout) confirmDialog
							.findViewById(R.id.confirem_layyy);
					confirem_layyy.setBackgroundColor(Color.WHITE);
					Button editButton = (Button) confirmDialog
							.findViewById(R.id.viewPhoneNumberPopUpEditButton);
					Button continueButton = (Button) confirmDialog
							.findViewById(R.id.viewPhoneNumberPopUpContinueButton);
					TextView viewPhoneNumberText3 = (TextView) confirmDialog.findViewById(R.id.viewPhoneNumberText3);
					
					String checkPhoneNumber = numberEditField.getText().toString();
					String firstChar = String.valueOf(checkPhoneNumber.charAt(0));
					
					if(firstChar.equalsIgnoreCase("0")){
						checkPhoneNumber = checkPhoneNumber.substring(1);
					}
					System.out.println("value : " +checkPhoneNumber.charAt(0));
					System.out.println("value trim : " +checkPhoneNumber.substring(1));
					
					viewPhoneNumberText3.setText(phoneNumber + checkPhoneNumber);

					editButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							numberEditField.requestFocus();
							confirmDialog.dismiss();
							numberEditField.setPadding(10, 0, 0, 0);
						}
					});

					continueButton
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {

									Intent i = new Intent(
											VerifyPhoneNumberActivity.this,
											EnterPasscodeActivity.class);
									/*
									 * Bundle b=new Bundle();
									 * b.putString("condition","tb2");
									 * i.putExtras(b);
									 */
									i.putExtra("phoneNumberr", phoneNumber
											+ numberEditField.getText());
									startActivity(i);
									finish();
									confirmDialog.dismiss();
								}
							});
					confirmDialog.show();

				}
			}
		});

		mainverifyLay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(numberEditField.getWindowToken(), 0);
			}
		});

	}

	/*
	 * public void setSelection(int position) { boolean sameSelected = position
	 * == getSelectedItemPosition(); super.setSelection(position); if
	 * (sameSelected) { // Spinner does not call the OnItemSelectedListener if
	 * the same item is selected, so do it manually now
	 * getOnItemSelectedListener().onItemSelected(this, getSelectedView(),
	 * position, getSelectedItemId()); }
	 */

	public void setSpinnerData() {

		for (int i = 0; i < 11; i++) {

			final MobileVeriSpinnerModel sched = new MobileVeriSpinnerModel();

			/******* Firstly take data in model object ******/
			sched.setCountryName("" + i);

			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(sched);
		}

	}

	// Adapter class fr spinner values

	private class CustomAdapter extends ArrayAdapter<String> {

		private Activity activity;
		private ArrayList data;
		public Resources res;
		MobileVeriSpinnerModel tempValues = null;
		LayoutInflater inflater;

		/************* CustomAdapter Constructor *****************/
		public CustomAdapter(VerifyPhoneNumberActivity activitySpinner,
				int textViewResourceId, ArrayList objects, Resources resLocal) {
			super(activitySpinner, textViewResourceId, objects);

			/********** Take passed values **********/
			activity = activitySpinner;
			data = objects;
			res = resLocal;

			/*********** Layout inflator to call external xml layout () **********************/
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		public View getCustomView(int position, View convertView,
				ViewGroup parent) {

			/********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
			View row = inflater.inflate(R.layout.spinner_textview, parent,
					false);

			/***** Get each Model object from Arraylist ********/
			tempValues = null;
			tempValues = (MobileVeriSpinnerModel) data.get(position);

			TextView label = (TextView) row.findViewById(R.id.countryName);

			if (position == 0) {

				// Default selected Spinner item
				label.setText("Choose your Country");
				label.setPadding(15, 0, 0, 0);
			} else {
				// Set values for spinner each row
				label.setText(tempValues.getCountryName());
				label.setPadding(15, 0, 0, 0);

			}

			return row;
		}
	}

}
