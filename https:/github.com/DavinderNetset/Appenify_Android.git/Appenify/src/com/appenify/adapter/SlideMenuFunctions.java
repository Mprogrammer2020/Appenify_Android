package com.appenify.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.calgiary.appenify.AboutAppenify;
import com.calgiary.appenify.Events_organized;
import com.calgiary.appenify.Main_search;
import com.calgiary.appenify.MySocialConnectActivity;
import com.calgiary.appenify.My_invitations;
import com.calgiary.appenify.R;
import com.calgiary.appenify.SettingActivity;
import com.calgiary.appenify.TabAct;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class SlideMenuFunctions {
	SlidingMenu menu;
	ListView slide_menu_list;
	RelativeLayout fav_count, Org_count, Pend_count;
	ImageView user_detail_Lay;
	Context c;
	Intent i;
	TextView user_name;
	RelativeLayout first, second, third, fourth, fifth, sixth, main, seventh;
	
	View p_view;
	String[] menu_items = { "My Invitations", "My Events", "Search Events",
			"Social Connect","Settings", "Terms of Service", "Privacy Policy", "FAQ",
			"About Appenify" };
	int[] icons = { R.drawable.my_invitation, R.drawable.my_events,
			R.drawable.search, R.drawable.social,R.drawable.settings, R.drawable.termsservices,
			R.drawable.privacy_policy1, R.drawable.faq, R.drawable.about };

	public SlideMenuFunctions(final SlidingMenu menu, final Context c) {

		this.menu = menu;
		this.c = c;
		slide_menu_list = (ListView) menu.findViewById(R.id.slide_menu_list);
		user_detail_Lay = (ImageView) menu.findViewById(R.id.user_pic);
		fav_count = (RelativeLayout) menu.findViewById(R.id.fav_count);
		Org_count = (RelativeLayout) menu.findViewById(R.id.Org_count);
		Pend_count = (RelativeLayout) menu.findViewById(R.id.Pend_count);
		user_name = (TextView)menu.findViewById(R.id.user_name);
		
		slide_menu_list.setAdapter(new CustAdapter(c, menu_items, icons));
		
		slide_menu_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				menu.showContent();
				switch (position) {
				case 0:
					i = new Intent(c, My_invitations.class);
					c.startActivity(i);
					((Activity) c).finish();
					
					break;
				case 1:

					i = new Intent(c, Events_organized.class);
					c.startActivity(i);
					((Activity) c).finish();
					break;
				case 2:
					i = new Intent(c, Main_search.class);
					c.startActivity(i);
					break;
				
				case 3:
					i = new Intent(c, MySocialConnectActivity.class);
					c.startActivity(i);
					((Activity) c).finish();
					break;
					
					
				case 4:
					i = new Intent(c, SettingActivity.class);
					c.startActivity(i);
					((Activity) c).finish();
					break;
				case 5:
					String url = "http://www.appenify.com/terms-of-service/";
					Intent terms = new Intent(Intent.ACTION_VIEW);
					terms.setData(Uri.parse(url));
					((Activity) c).startActivity(terms);
					break;
					
				case 6:
					String privacy = "http://www.appenify.com/privacy-policy/";
					Intent p = new Intent(Intent.ACTION_VIEW);
					p.setData(Uri.parse(privacy));
					((Activity) c).startActivity(p);
						break;
						
				case 7:
					String urll = "http://www.appenify.com/terms-of-service/";
					Intent termss = new Intent(Intent.ACTION_VIEW);
					termss.setData(Uri.parse(urll));
					((Activity) c).startActivity(termss);
						break;
						
						
				case 8:
					
				/*	AboutAppenify fragment2 = new AboutAppenify();
					 FragmentManager fragmentManager = c.getFragmentManager();
					 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					 fragmentTransaction.replace(R.id.fragment1, fragment2);
					 fragmentTransaction.commit();
*/
                        
						i = new Intent(c, AboutAppenify.class);
						c.startActivity(i);
						break;
						
				default:
					break;
				}

			}
		});
		
		user_detail_Lay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				i = new Intent(c, SettingActivity.class);
				c.startActivity(i);
				((Activity) c).finish();
			}
		});
		
		user_name.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i = new Intent(c, TabAct.class);
				Bundle bb=new Bundle();
				 bb.putString("condition","tb2");
				 i.putExtras(bb);
				c.startActivity(i);
				((Activity) c).finish();
			}
		});
		
		
		fav_count.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				i = new Intent(c, TabAct.class);
				Bundle bb=new Bundle();
				 bb.putString("condition","tb4");
				 i.putExtras(bb);
				c.startActivity(i);
				((Activity) c).finish();
			}
		});
		
		Org_count.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				i = new Intent(c, Events_organized.class);
				c.startActivity(i);
				((Activity) c).finish();
			}
		});
		
		Pend_count.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				i = new Intent(c, My_invitations.class);
				c.startActivity(i);
				((Activity) c).finish();
			}
		});
		
	}


	class CustAdapter extends BaseAdapter {
		Context mContext;
		String[] menu_items;
		LayoutInflater mInflater;
		int[] icons;

		public CustAdapter(Context c, String[] menu_items, int[] icons) {
			mInflater = LayoutInflater.from(c);
			this.mContext = c;
			this.icons = icons;
			this.menu_items = menu_items;

		}

		@Override
		public int getCount() {
			return menu_items.length;
		}

		@Override
		public Object getItem(int position) {
			return menu_items[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {

				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.slide_menu_inflater,
						null);
				holder.text = (TextView) convertView.findViewById(R.id.text);
				holder.icon = (ImageView) convertView.findViewById(R.id.icon);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();

			}
			holder.icon.setImageResource(icons[position]);
			holder.text.setText(menu_items[position]);
			return convertView;
		}
	}

	class ViewHolder {
		ImageView icon;
		TextView text;
	}

}
