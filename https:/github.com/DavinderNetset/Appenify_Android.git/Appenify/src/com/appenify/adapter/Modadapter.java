package com.appenify.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.calgiary.appenify.R;



public class Modadapter extends BaseAdapter implements ListAdapter {
		LayoutInflater mInflater;
		Context c;
//		ArrayList<HashMap<String, String>> eventlist;
		String[] titles;
		int[] draws;

		public Modadapter(Context c,
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
				holder.event_desc = (TextView) convertView
						.findViewById(R.id.desc);

				convertView.setTag(holder);
			} else {

				holder = (ViewHolder) convertView.getTag();
			}
			holder.event_image.setImageResource(draws[position]);
			holder.event_name.setText(titles[position]);
			return convertView;
		}

		public class ViewHolder {
			TextView event_name, event_desc;
			ImageView event_image;
		}
	}