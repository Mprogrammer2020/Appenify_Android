package com.appenify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.calgiary.appenify.R;


public class SettingAdapter extends BaseAdapter implements ListAdapter {
	LayoutInflater mInflater;
	Context c;
//	ArrayList<HashMap<String, String>> eventlist;
	String[] titles;
	String[] titless;

	public SettingAdapter(Context c,
			String[] titles,String[] titless) {
		this.c = c;
		this.titles=titles;
		this.titless=titless;
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
			convertView = mInflater.inflate(R.layout.setting_pref_list_items, null);
		
				holder.textprefName = (TextView) convertView.findViewById(R.id.prefname);
				holder.textprefDetail = (TextView) convertView.findViewById(R.id.prefDetail);
			convertView.setTag(holder);
		} else {

			holder = (ViewHolder) convertView.getTag();
		}
		holder.textprefName.setText(titles[position]);
		holder.textprefDetail.setText(titless[position]);
		return convertView;
	}

	public class ViewHolder {
		TextView textprefName, textprefDetail;
		
	}
}