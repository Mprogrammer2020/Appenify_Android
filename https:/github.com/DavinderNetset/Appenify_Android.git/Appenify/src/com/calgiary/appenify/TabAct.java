package com.calgiary.appenify;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

import com.appenify.adapter.SlideMenuFunctions;
import com.appenify.adapter.commonutilities;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

@SuppressWarnings("deprecation")
public class TabAct extends TabActivity {
	static TabHost tabHost;
	static TabWidget tabWidget;
	Intent i;
	SlidingMenu slidingmenu;
	ImageView bttn;
	String con = "";
	int layoutHeight = 65;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.tab);
		tabHost = getTabHost();
		tabWidget = (TabWidget) this.findViewById(android.R.id.tabs);
		
		bttn=(ImageView)findViewById(R.id.imageView1);
		slidingmenu=commonutilities.setSlidingMenu(TabAct.this);
		new SlideMenuFunctions(slidingmenu, TabAct.this);
		
		getPixel(layoutHeight);
		
		bttn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				slidingmenu.showMenu();
			}
		});
		tabHost.getTabWidget().setStripEnabled(false);
	
		
		Bundle bundle = getIntent().getExtras();
		con = bundle.getString("condition");
		
		TabSpec f = tabHost.newTabSpec("tb1");
		f.setIndicator("", getResources().getDrawable(R.drawable.edit_3x));
		i = new Intent(TabAct.this, CreateEventActivity.class);
		f.setContent(i);
		

		TabSpec e = tabHost.newTabSpec("tb2");
		e.setIndicator("", getResources().getDrawable(R.drawable.home_3x));
		i = new Intent(TabAct.this,  Events_attndng.class);
		e.setContent(i);

		TabSpec s = tabHost.newTabSpec("tb3");
		s.setIndicator("", getResources().getDrawable(R.drawable.notification_3x));
		i = new Intent(TabAct.this,  Notifications.class);
		s.setContent(i);

		TabSpec fav = tabHost.newTabSpec("tb4");
		fav.setIndicator("", getResources().getDrawable(R.drawable.favorite));
		i = new Intent(TabAct.this,  FavoriteTab.class);
		fav.setContent(i);

		

		// Adding all TabSpec to TabHost
		tabHost.addTab(f);
		tabHost.addTab(e);
		tabHost.addTab(fav);
		tabHost.addTab(s);
		
		if (con.equals("tb1")) {
			tabHost.setCurrentTab(0);
		}
		else if(con.equals("tb2")){
			tabHost.setCurrentTab(1);
			
		}else if (con.equals("tb3")) {
			tabHost.setCurrentTab(3);
			
		}
		else if (con.equalsIgnoreCase("tb4")) {
			tabHost.setCurrentTab(2);
		}
		
		tabHost.getTabWidget().setPadding(0, 5, 0, 5);
		for(int i = 0 ;i<tabHost.getTabWidget().getChildCount();i++)
		{
			tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#d94949"));
			tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#d94949"));
			tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#d94949"));
			tabHost.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor("#d94949"));
			
			tabHost.getTabWidget().getChildAt(0).getLayoutParams().width = layoutHeight-12;
			tabHost.getTabWidget().getChildAt(0).getLayoutParams().height = layoutHeight-12;
			
			tabHost.getTabWidget().getChildAt(1).getLayoutParams().width = layoutHeight-12;
			tabHost.getTabWidget().getChildAt(1).getLayoutParams().height = layoutHeight-12;
			
			tabHost.getTabWidget().getChildAt(2).getLayoutParams().width = layoutHeight-12;
			tabHost.getTabWidget().getChildAt(2).getLayoutParams().height = layoutHeight-12;
			
			tabHost.getTabWidget().getChildAt(3).getLayoutParams().width = layoutHeight-12;
			tabHost.getTabWidget().getChildAt(3).getLayoutParams().height = layoutHeight-12;
			
		}
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(String arg0) {
            	int index = tabHost.getCurrentTab();
            //	tabHost.getTabWidget().setBackgroundColor(Color.parseColor("#D84A49"));
            	for(int i = 0;i<tabHost.getTabWidget().getChildCount();i++)
            	{
			if(index==i)
			{
				tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#d94949"));
				
			}
			else
			{
				tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#D84A49"));
			}
            	}
                
            }       
        });  
		
	}


	
	
	public void getPixel(int value) {
		int density = getResources().getDisplayMetrics().densityDpi;
		switch (density) {
		case DisplayMetrics.DENSITY_LOW:
			layoutHeight = (int) (value * .50);
		//	 Toast.makeText(TabAct.this, "LDPI", Toast.LENGTH_SHORT).show();
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			layoutHeight = (int) (value * .75);
		//	Toast.makeText(TabAct.this, "MDPI", Toast.LENGTH_SHORT).show();
			break;
		case DisplayMetrics.DENSITY_HIGH:
			layoutHeight = (int) (value * 1);
		//	 Toast.makeText(TabAct.this, "HDPI", Toast.LENGTH_SHORT).show();
			break;
		case DisplayMetrics.DENSITY_XHIGH:
			layoutHeight = (int) (value * 1.5) + 1;
		//	 Toast.makeText(TabAct.this, "XHDPI", Toast.LENGTH_SHORT).show();
			break;
		case DisplayMetrics.DENSITY_XXHIGH:
			layoutHeight = (int) (value * 2) + 2;
			break;
		case DisplayMetrics.DENSITY_XXXHIGH:
			layoutHeight = (int) (value * 4) + 3;
			break;
		}

	}
	
	/*@Override
	protected void onResume() {
		super.onResume();
		overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
	}*/
	
}