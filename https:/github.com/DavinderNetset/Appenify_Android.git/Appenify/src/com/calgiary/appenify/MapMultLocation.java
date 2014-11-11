package com.calgiary.appenify;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MapMultLocation extends FragmentActivity implements LocationListener
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
	RelativeLayout visible, backkkkkk;
    AutoCompleteTextView ssearch;
    ImageView imageView1;int checkinput=0;
    String by="username";
    String checkss="all";
    int addmarker=0,mail=0;
   
    
     LatLng latLng;
	 GoogleMap map;
	 ArrayList<LatLng> markerPoints;
	 double latitude=0,longitude=0;
	 
	 ArrayList<String> a1  = new ArrayList<String>();
			
		
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_multlocation);
		
		
        mapdisplay();
        
        googleMap.setMyLocationEnabled(true);
		
		a1.add("Mohali Sec 58");
		a1.add("Mohali Sec 59");
		a1.add("Mohali Sec 60");
		a1.add("Mohali Sec 75");
		a1.add("Mohali Sec 76");
		a1.add("Mohali Sec 77");
		
		for(int i=0; i<a1.size(); i++)
		{
			
		 JSONObject jj1 = getLocationInfo(a1.get(i));
		 LatLng tolatlong = getLatLong(jj1);
		
		 Log.e("Latlong", tolatlong+"...");
		
		 addmarker(tolatlong);
		
		}
		
		backkkkkk = (RelativeLayout)findViewById(R.id.backkkkkk);
		backkkkkk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		
	}

	// map display
    public void mapdisplay() 
    {
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

	/*@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();	
		
		mapdisplay();
		
		a1.add("Mohali Phase 10");
		a1.add("Chandigarh Sec 40");
		a1.add("Zirakpur");
		a1.add("Mohali Phase 11");
		
		for(int i=0; i<a1.size(); i++)
		{
			
		JSONObject jj1 = getLocationInfo(a1.get(i));
		LatLng tolatlong = getLatLong(jj1);
		addmarker(tolatlong);
		
		}

	}*/
	
	@SuppressWarnings("deprecation")
	public static JSONObject getLocationInfo(String address) 
	{
	 	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			StrictMode.setThreadPolicy(policy);

			StringBuilder stringBuilder = new StringBuilder();
			try {

				// address = address.replaceAll(" ", "%20");
				System.out.println("dddddd");

				HttpPost httppost = new HttpPost(
						"http://maps.google.com/maps/api/geocode/json?address="
								+ URLEncoder.encode(address) + "&sensor=false");
				HttpClient client = new DefaultHttpClient();
				HttpResponse response;
				stringBuilder = new StringBuilder();
				response = client.execute(httppost);
				HttpEntity entity = response.getEntity();
				InputStream stream = entity.getContent();
				int b;
				while ((b = stream.read()) != -1) {
					stringBuilder.append((char) b);
				}
			} catch (ClientProtocolException e) {
				System.out.println("dddddd" +e);
			} catch (IOException e) {
				System.out.println("dddddd" +e);

			}

			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject = new JSONObject(stringBuilder.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				System.out.println("dddddd" +e);

				e.printStackTrace();
			}

			return jsonObject;
		}
	  
	public LatLng getLatLong(JSONObject jsonObject) {
		LatLng latlong = null;
		try {

			double longitute11 = ((JSONArray) jsonObject.get("results"))
					.getJSONObject(0).getJSONObject("geometry")
					.getJSONObject("location").getDouble("lng");

			double latitude11 = ((JSONArray) jsonObject.get("results"))
					.getJSONObject(0).getJSONObject("geometry")
					.getJSONObject("location").getDouble("lat");

			System.out
					.println("pppppppppppppp" + latitude11 + "" + longitute11);
			latlong = new LatLng(latitude11, longitute11);
		} catch (JSONException e) {

		}

		return latlong;
	}
	
	void addmarker(LatLng lg) {

		try {
			
			// Creating a marker
			MarkerOptions markerOptions = new MarkerOptions();

			// Setting the position for the marker
			markerOptions.position(lg);

			// Setting the title for the marker. // This will be displayed on
			//markerOptions.title(dd);

			/*markerOptions.icon(BitmapDescriptorFactory
					.fromResource(R.drawable.man_location));*/
			
			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					lg.latitude, lg.longitude), 13));
			// Animating to the touched position
			System.out.println("here is lat and lon" + lg);
			// Placing a marker on the touched position
			googleMap.addMarker(markerOptions);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			Log.e("Exception", e+"....");
			
		}
		
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		    latitude = location.getLatitude();       
	        longitude = location.getLongitude();  
	        
	        Log.e("latitude", latitude+".");
	        Log.e("longitude", longitude+".");
	        
	        latLng = new LatLng(latitude, longitude);   
	        
		addmarker(latLng);
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}