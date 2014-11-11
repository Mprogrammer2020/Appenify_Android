package com.calgiary.appenify;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class Comments extends Activity {
	ActionBar abar;
	Modaadapter custadapter;
	SlidingMenu smenu;
	ImageView bck_bttn;
	ListView lview;
	TextView header;
	RelativeLayout menu_bttn,fwd, back;
	ImageView right_icon;
	int[] images = { R.drawable.img1, R.drawable.img2, R.drawable.img3,
			R.drawable.img4, R.drawable.img1, R.drawable.img2, R.drawable.img3,
			R.drawable.img4 };
	String[] text = { "Jhonny Smith", "Reena", "Will Smith",
			"Ted Lauren","Jhonny Smith", "Reena", "Will Smith",
			"Ted Lauren"};
	Dialog confirmDialog, confirmDialog1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.act_comments);
		abar = (ActionBar) findViewById(R.id.abar1);
		menu_bttn = (RelativeLayout) abar.findViewById(R.id.action_menu);
		menu_bttn.setPadding(8, 8, 8, 8);
		custadapter = new Modaadapter(Comments.this, text, images);
		lview=(ListView)findViewById(R.id.content_lv);
		lview.setAdapter(custadapter);
		header=(TextView)findViewById(R.id.action_text);
		header.setText("Comments");
		fwd = (RelativeLayout)findViewById(R.id.action_edit_container);
		back = (RelativeLayout)findViewById(R.id.action_menu);
		right_icon=(ImageView)findViewById(R.id.action_edit);
		right_icon.setBackgroundResource(R.drawable.add_white);
		right_icon.getLayoutParams().width = 40;
		bck_bttn=(ImageView)abar.findViewById(R.id.action_menu_icon);
		bck_bttn.setBackgroundResource(R.drawable.back); 
		back.setPadding(8, 3, 3, 3);
		
		
		lview.setOnItemClickListener(new OnItemClickListener() {
				@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
					confirmDialog1 = new Dialog(Comments.this);
					confirmDialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
					confirmDialog1.setContentView(R.layout.popup_comment_detail);
					confirmDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
					
					RelativeLayout pop_message_lay = (RelativeLayout)confirmDialog1.findViewById(R.id.userImage_commenL);
					RelativeLayout userImage_commenLayy = (RelativeLayout)confirmDialog1.findViewById(R.id.popUpFramee);
					
					pop_message_lay.setBackgroundColor(Color.WHITE);
					userImage_commenLayy.setBackgroundColor(Color.TRANSPARENT);
					//userImage_commenLayy.setBackgroundDrawable(getResources().getDrawable(R.drawable.transparent_drawable));
					
					ImageView cancelImage = (ImageView)confirmDialog1.findViewById(R.id.cancelImage);
					cancelImage.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							confirmDialog1.dismiss();
						}
					});
					confirmDialog1.show();
			}
		
		});
		back.setPadding(7, 0, 0, 0);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		//fwd.setBackgroundResource(R.drawable.right_arrow);
		
		fwd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
				confirmDialog = new Dialog(Comments.this);
				confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				confirmDialog.setContentView(R.layout.popup_create_comment);
				
				confirmDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
				
				RelativeLayout confirem_layy = (RelativeLayout)confirmDialog.findViewById(R.id.opppppppp);
				confirem_layy.setBackgroundColor(Color.WHITE);
				
				
				RelativeLayout closeButton = (RelativeLayout)confirmDialog.findViewById(R.id.closeCommentLayout);
				RelativeLayout addButton = (RelativeLayout)confirmDialog.findViewById(R.id.addCommentLayout);
				ImageView cancelPop = (ImageView)confirmDialog.findViewById(R.id.cancelPop);
				
				addButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
						confirmDialog.dismiss();
					}
				});
				
				
				
				cancelPop.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						//Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
						confirmDialog.dismiss();
					}
				});
				
				
				closeButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
					
						confirmDialog.dismiss();
					}
				});
				confirmDialog.show();
				
			}
		});
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
				convertView = mInflater.inflate(R.layout.popup_comments_list, null);
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
}

