package com.calgiary.appenify;

import java.util.List;

import org.brickred.socialauth.Contact;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

class LinkedInContactAdapter extends BaseAdapter {
	
	LayoutInflater inflater;
	List<Contact> conList;
	Context c;
	
	public LinkedInContactAdapter(Context c,List<Contact> contacts) {
		inflater=LayoutInflater.from(c);
		conList=contacts;
		this.c=c;
	}
	

	@Override
	public int getCount() {
		return conList.size();
	}

	@Override
	public Object getItem(int position) {
		return conList.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Log.i("getViewContact", ""+position);

		ViewHolder holder;
		Contact contactItem = (Contact) getItem(position);
		 
         
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.share_with_friends_lvrow_view,parent,false);
            holder = new ViewHolder();
           
            holder.nameText = (TextView) convertView.findViewById(R.id.shareWithFriendsLVRowText);
            holder.profileImage=(ImageView) convertView.findViewById(R.id.shareWithFriendsLVRowimage);
          
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
		
        holder.nameText.setText(contactItem.getFirstName());
        Picasso.with(c).load(contactItem.getProfileImageURL()).into(holder.profileImage);
        
        Log.e("name"+":"+position, ""+contactItem.getFirstName());
        Log.e("email"+":"+position, ""+contactItem.getEmail());
        Log.e("pImgURL"+":"+position, ""+contactItem.getProfileImageURL());
        Log.e("ID"+":"+position, ""+contactItem.getId());
		
		return convertView;
		
	}

	private class ViewHolder{
		TextView nameText;
		ImageView profileImage;
	}
	
	
}
