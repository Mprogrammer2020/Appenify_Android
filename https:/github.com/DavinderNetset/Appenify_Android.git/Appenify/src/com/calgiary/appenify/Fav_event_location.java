package com.calgiary.appenify;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appenify.globalpackage.SimpleGestureFilter;
import com.appenify.globalpackage.SimpleGestureFilter.SimpleGestureListener;

public class Fav_event_location extends Activity implements SimpleGestureListener{
	//ActionBar abar;
	Modaadapter custadapter;
	//SlidingMenu smenu;
	ListView lview;
	TextView header;
	RelativeLayout menu_bttn,fwd;
	ImageView right_icon;
	int[] images = { R.drawable.location1, R.drawable.location1, R.drawable.location1 };
	String[] text = { "Delhi, India", "Chandigarh, India", "Delhi, India"};
	RelativeLayout backLay, okLay;
	
	  private SimpleGestureFilter detector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_fav_event_location);
		custadapter = new Modaadapter(Fav_event_location.this, text, images);
		lview=(ListView)findViewById(R.id.fav_locns);
		lview.setAdapter(custadapter);
	
		backLay = (RelativeLayout)findViewById(R.id.backkonbar_Btn_mediaLay);
		okLay = (RelativeLayout)findViewById(R.id.okkkckLayouttt);
		
		
		 detector = new SimpleGestureFilter(this,this);
		 
		 lview.setOnTouchListener(detector);
		
	}

	@Override
    public boolean dispatchTouchEvent(MotionEvent me){
    	 this.detector.onTouchEvent(me);
       return super.dispatchTouchEvent(me);
    }
	
	@Override
	public void onSwipe(int direction) {
		 
		switch (direction) {
		 
		  case SimpleGestureFilter.SWIPE_LEFT :  
			  Toast.makeText(this, "SWIPE_LEFT", Toast.LENGTH_SHORT).show();
		                                           
		  break;
		

		  case SimpleGestureFilter.SWIPE_RIGHT:  
			  Toast.makeText(this, "SWIPE_RIGHT", Toast.LENGTH_SHORT).show();	 
            
			  break;
			  
		  }
	}
	@Override
	public void onDoubleTap() { }
	
	
	
	public void swipeAct(View view){
		switch (view.getId()) {
		case R.id.backkonbar_Btn_mediaLay:
			finish();
			break;

		case R.id.okkkckLayouttt:
			Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
			break;
			
		default:
			break;
		}
	}
	
	public class Modaadapter extends BaseAdapter implements ListAdapter, SimpleGestureListener {
		LayoutInflater mInflater;
		Context c;
//		ArrayList<HashMap<String, String>> eventlist;
		String[] titles;
		int[] draws;

		public Modaadapter(Context c,
				String[] titles,int[] draws) {
			this.c = c;
			this.titles=titles;
			this.draws=draws;
			mInflater = LayoutInflater.from(c);
		}

		@Override
		public int getCount() {
			return titles.length;
		}

		@Override
		public Object getItem(int arg0) {

			return arg0;
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {

				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.comment_inflater, null);
				holder.event_image = (ImageView) convertView.findViewById(R.id.evnt_image);
				holder.event_name = (TextView) convertView.findViewById(R.id.evnt_title);
				
				convertView.setTag(holder);
			} else {

				holder = (ViewHolder) convertView.getTag();
			}
			holder.event_image.setImageResource(draws[position]);
			holder.event_name.setText(titles[position]);
			
		
			
			
			return convertView;
		}

		public class ViewHolder {
			TextView event_name;
			ImageView event_image;
		
		}
		
		

		@Override
		public void onSwipe(int direction) {
			switch (direction) {
			 
			  case SimpleGestureFilter.SWIPE_LEFT :  
				  Toast.makeText(c, "SWIPE_LEFT", Toast.LENGTH_SHORT).show();
			                                           
			  break;
			

			  case SimpleGestureFilter.SWIPE_RIGHT:  
				  Toast.makeText(c, "SWIPE_RIGHT", Toast.LENGTH_SHORT).show();	 
	              
				  break;
				  
			  }
		}

		@Override
		public void onDoubleTap() {}
	}
}
