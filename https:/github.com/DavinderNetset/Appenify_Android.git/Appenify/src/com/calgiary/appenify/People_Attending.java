package com.calgiary.appenify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appenify.adapter.PeopleAttendingAdapter;
import com.appenify.adapter.PeopleAttendingGridViewApapter;

public class People_Attending extends Activity {
	ListView lview;
	GridView gridView_lv;
	PeopleAttendingGridViewApapter custadapterGridview;
	PeopleAttendingAdapter custadapter;
	ActionBar abar;
	ImageView bck_bttn;
	TextView header;
	RelativeLayout bck,fwd;
	LinearLayout viewLook;
	int[] images = { R.drawable.media1, R.drawable.media10, R.drawable.media11,
			R.drawable.media12, R.drawable.media1, R.drawable.media4, R.drawable.media5,
			R.drawable.media6 };
	String[] text = { "Jhoony Smith", "Devender Singh", "Ann  Sarah Parker",
			"Will Smith", "Rajan Sandhu", "Jhoony Smith", "Devender Singh", "Jaz Gill"};
	Button listViewBtn, gridViewBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.act_people_attending);
		abar = (ActionBar) findViewById(R.id.abar1);
		lview = (ListView) findViewById(R.id.content_lv);
		bck = (RelativeLayout) abar.findViewById(R.id.action_menu);
		fwd = (RelativeLayout)findViewById(R.id.action_edit_container);
		custadapter = new PeopleAttendingAdapter(People_Attending.this, text, images);
		header=(TextView)findViewById(R.id.action_text);
		header.setText("People Attending");
		bck_bttn=(ImageView)abar.findViewById(R.id.action_menu_icon);
		bck_bttn.setBackgroundResource(R.drawable.back);  //  getResources().getDrawable(R.drawable.back)
		lview.setAdapter(custadapter);
		bck_bttn.setPadding(4, 0, 0, 0);
		
		viewLook = (LinearLayout)findViewById(R.id.viewLook);
		gridView_lv = (GridView) findViewById(R.id.eventNearby_lv);
		
		listViewBtn = (Button)findViewById(R.id.listViewBtn);
		gridViewBtn = (Button)findViewById(R.id.gridViewBtn);
		
		listViewBtn.setBackgroundResource(R.drawable.list_view_onpressed);
		gridViewBtn.setBackgroundResource(R.drawable.gridview2x);
		
		custadapterGridview = new PeopleAttendingGridViewApapter(People_Attending.this, text, images);
		gridView_lv.setAdapter(custadapterGridview);

		gridView_lv.setVisibility(View.GONE);
		lview.setVisibility(View.VISIBLE);
		
		bck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//startActivity(new Intent(People_Attending.this,Event_detail.class));
				//finish();
				
			/*	Intent i = new Intent(People_Attending.this, TabAct.class);
				Bundle b=new Bundle();
				 b.putString("condition","tb2");
				 i.putExtras(b);
				startActivity(i);*/
				finish();
			}
		});
		
		fwd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(People_Attending.this,MediaGalleryActivity.class));
			}
		});
	}
	
	@Override
	public void onBackPressed() {
	//	super.onBackPressed();
		
		/*Intent ii = new Intent(People_Attending.this, TabAct.class);
		Bundle bb=new Bundle();
		 bb.putString("condition","tb2");
		 ii.putExtras(bb);
		startActivity(ii);*/
		finish();
	}

public void layoutView(View view){
		
		switch (view.getId()) {
		case R.id.listViewBtn:
			gridView_lv.setVisibility(View.GONE);
			lview.setVisibility(View.VISIBLE); 
			
			listViewBtn.setBackgroundResource(R.drawable.list_view_onpressed);
			gridViewBtn.setBackgroundResource(R.drawable.gridview2x);
			
			break;

		case R.id.gridViewBtn:
			
			listViewBtn.setBackgroundResource(R.drawable.list_active2x);
			gridViewBtn.setBackgroundResource(R.drawable.grid_onpressed);
			
			gridView_lv.setVisibility(View.VISIBLE);
			lview.setVisibility(View.GONE);
			break;
			
		default:
			break;
		}
	}

}
