package com.calgiary.appenify;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class Notifications extends Activity {
	ActionBar abar;
	Modaadapter custadapter;
	SlidingMenu smenu;
	ListView lview;
	TextView header;
	RelativeLayout menu_bttn, fwd;
	ImageView imageFront;
	
	int[] images = { R.drawable.img1, R.drawable.img2, R.drawable.img3,
			R.drawable.img4, R.drawable.img1, R.drawable.img2, R.drawable.img3,
			R.drawable.img4 };
	String[] text = { "Jhonny Smith", "Reena", "Will Smith",
			"Ted Lauren","Jhonny Smith", "Reena", "Will Smith",
			"Ted Lauren"};
	LinearLayout viewLook;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_events_attndng);
		abar = (ActionBar) findViewById(R.id.abar1);
//		menu_bttn = (RelativeLayout) abar.findViewById(R.id.action_menu);
		custadapter = new Modaadapter(Notifications.this, text, images);
		lview=(ListView)findViewById(R.id.content_lv);
		lview.setAdapter(custadapter);
		header=(TextView)findViewById(R.id.action_text);
		header.setText("Notifications");
		fwd=(RelativeLayout)abar.findViewById(R.id.action_edit_container);
		fwd.setBackground(null);
		imageFront = (ImageView)abar.findViewById(R.id.action_edit);
		imageFront.setBackground(null);
		
		viewLook = (LinearLayout)findViewById(R.id.viewLook);
		viewLook.setVisibility(View.INVISIBLE);
		viewLook.getLayoutParams().height = 1;
		lview.setVisibility(View.VISIBLE);
		
		/*menu_bttn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				smenu.showMenu();
			}
		});*/
	}
	public class Modaadapter extends BaseAdapter implements ListAdapter {
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
				convertView = mInflater.inflate(R.layout.event_inflater, null);
				holder.event_image = (ImageView) convertView
						.findViewById(R.id.evnt_image);
				holder.event_name = (TextView) convertView
						.findViewById(R.id.evnt_title);
				holder.event_desc = (TextView) convertView.findViewById(R.id.desc);
				holder.mor_desc = (TextView)convertView.findViewById(R.id.mor_desc);
				
				holder.event_desc.setText("Just Now");
				holder.mor_desc.setVisibility(View.GONE);
				convertView.setTag(holder);
			} else {

				holder = (ViewHolder) convertView.getTag();
			}
			holder.event_desc.setText("Just Now");
			
			holder.event_image.setBackgroundResource(draws[position]);
			holder.event_name.setText(titles[position]);
			return convertView;
		}

		public class ViewHolder {
			TextView event_name, event_desc, mor_desc;
			ImageView event_image;
		}
	}
}
