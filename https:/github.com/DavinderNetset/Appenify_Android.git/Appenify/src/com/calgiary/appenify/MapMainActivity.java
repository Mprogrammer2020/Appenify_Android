package com.calgiary.appenify;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapMainActivity extends FragmentActivity implements LocationListener
{
	
	SupportMapFragment mapFragment;
	GoogleMap googleMap;
	Double latitudee, longitudee;
	TextView phospital, ghospital, lab, pharmacy, check;
	String Bookingid1;
	static String checkaccepreject = "0";
	String lat, lon;
	TextView customernametext;
	ProgressDialog pd;
	String type,che="";
	ArrayAdapter<String> adapter;
	
	ImageView click;
	RelativeLayout visible, back_Layout;
    AutoCompleteTextView ssearch;
    ImageView imageView1;int checkinput=0;
    String by="username";
    String checkss="all";
    int addmarker=0,mail=0;
    ArrayList<String>a1;
    LatLng latLng;
    double latitude=0,longitude=0;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_map_main);

		back_Layout = (RelativeLayout)findViewById(R.id.back_Layout);
		
		back_Layout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mapdisplay();
	}
	
	
	// map display
    public void mapdisplay() 
    {
		// TODO Auto-generated method stub
		FragmentManager fragmentManager = getSupportFragmentManager();
		mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
		googleMap = mapFragment.getMap();

		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

		// Find ZoomControl view
		View zoomControls = mapFragment.getView().findViewById(0x1);

	   if (zoomControls != null && zoomControls.getLayoutParams() instanceof RelativeLayout.LayoutParams) 
	   {
	   // ZoomControl is inside of RelativeLayout
	   RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) zoomControls.getLayoutParams();

	   // Align it to - parent top|left
	   param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	   param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		// Update margins, set to 10dp
			final int margi = (int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 60, getResources()
							.getDisplayMetrics());
			param.setMargins(margi, margi, 35, 140);
	    }
	 }	


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		/* googleMap.clear();
		
		 latitude = location.getLatitude();       
	     longitude = location.getLongitude();       
	     latLng = new LatLng(latitude, longitude);*/  
		
	}


	@Override
	public void onProviderDisabled(String arg0) {
		
	}


	@Override
	public void onProviderEnabled(String arg0) {
		
	}


	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		
	}
	
	

	
public void eventLayoutFunction(View view){
		
		switch(view.getId()){
	       
		
		case R.id.eventVenueLayout:
			
			
			break;
			
			
		}
}



}