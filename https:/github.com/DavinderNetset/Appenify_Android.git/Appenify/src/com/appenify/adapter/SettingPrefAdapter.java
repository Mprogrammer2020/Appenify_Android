package com.appenify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.calgiary.appenify.R;

public class SettingPrefAdapter extends ArrayAdapter<String> {

	
	private final Context context;
	private final String[] values;
 
	public SettingPrefAdapter(Context context, String[] values) {
		super(context, R.layout.setting_pref_list_items, values);
		this.context = context;
		this.values = values;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.setting_pref_list_items, parent, false);
		TextView textprefName = (TextView) rowView.findViewById(R.id.prefname);
		TextView textprefDetail = (TextView) rowView.findViewById(R.id.prefDetail);
		textprefName.setText(values[position]);
		textprefDetail.setText(values[position]);
		// Change icon based on name
		String pos = values[position];
 
		if(pos.equals("Distance Unit")){
			textprefDetail.setText("Kilometers");
		}else if (pos.equalsIgnoreCase("Search Distance Radius")) {
			textprefDetail.setText("10 Km");
			/*Intent i = new Intent(context, MapMultLocation.class);
			context.startActivity(i);*/
		}
		else if (pos.equals("Hype my public event")) {
			textprefDetail.setText("Yes");
		}
		else if (pos.equals("Hype when i check-in at event")) {
			textprefDetail.setText("Yes");
		}
		else if (pos.equals("Notify when new invitation")) {
			textprefDetail.setText("Yes");
		}
		else if (pos.equals("Notify new nearby events")) {
			textprefDetail.setText("Yes");
		}
		else if (pos.equals("Favorite Event Categories")) {
			textprefDetail.setText("Party, Metting, Wedding");
		}
		else if (pos.equals("Favorite Event Locations")) {
			textprefDetail.setText("Delhi, India : Chandigarh, India : Delhi, India");
		
		}else if (pos.equals("Send us feedback / comments")) {
			textprefDetail.setText(" ");
		}
		
 
		return rowView;
	}
	
}
