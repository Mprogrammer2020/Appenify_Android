package com.appenify.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.calgiary.appenify.R;

public class SwipeListViewModel extends BaseAdapter{

	private Activity activity;
	private ArrayList<Dumpclass> data;
	public SwipeListViewModel(Activity a, ArrayList<Dumpclass> basicList){
		activity	=	a;
		data		=	basicList;
	}
	
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
        if(convertView == null)
        {holder				=	new ViewHolder();
		 convertView		=	LayoutInflater.from(activity).inflate(R.layout.test_layout, null);
		 holder.text		=	(TextView) convertView.findViewById(R.id.evnt_title);
		 convertView.setTag(holder);
        }else
        	holder			=	(ViewHolder) convertView.getTag();
        
        holder.text.setText(data.get(position).sampletext);
        
        
        return convertView;
	}

	
	public static class ViewHolder{
		TextView text;
		
	}

	/*private String list_item;
	private boolean isVisible;
	
	public String getList_item() {
		return list_item;
	}
	public void setList_item(String list_item) {
		this.list_item = list_item;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}*/
}
