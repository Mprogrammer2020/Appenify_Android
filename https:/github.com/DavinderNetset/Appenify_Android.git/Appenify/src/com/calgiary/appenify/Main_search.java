package com.calgiary.appenify;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.appenify.adapter.Modadapter;

public class Main_search extends Activity {
	ListView lview;
	Modadapter custadapter;
	ActionBar abar;
	ImageView bck_bttn;
	RelativeLayout bck,fwd;
	TextView header;
	LinearLayout bestLay, favsLay;
	
	int[] images = { R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3, R.drawable.pic, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3 };
	String[] text = { "Love Electro", "My Own Event News", "Love Electro",
			"Love Electro", "Love Electro", "My Own Event News",
			"Love Electro", "Love Electro" };
	TextView bestText, favText;
	Spinner spinnerDay;
	
	String[] dayList = {"Today", "Next 7 Days", "Next 30 Days", "Next 60 Days", "Next 3 Months",  "Next 6 Months"   };
  

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	//	overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		/*InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		*/
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		setContentView(R.layout.act_main_search);
		
		
		abar = (ActionBar) findViewById(R.id.abar1);
		lview = (ListView) findViewById(R.id.content_lv);
		bck_bttn = (ImageView) abar.findViewById(R.id.action_menu_icon);
		bck_bttn.setBackgroundResource(R.drawable.back);
		bck=(RelativeLayout)findViewById(R.id.action_menu);
		fwd=(RelativeLayout)findViewById(R.id.action_edit_container);
		fwd.setBackgroundResource(R.drawable.add);
		bestText = (TextView)findViewById(R.id.best);
		favText = (TextView)findViewById(R.id.favs);
		spinnerDay = (Spinner)findViewById(R.id.spinnerDay);
		bestLay = (LinearLayout)findViewById(R.id.bestLay);
		favsLay = (LinearLayout)findViewById(R.id.favsLay);
		
		custadapter = new Modadapter(Main_search.this, text, images);
		lview.setAdapter(custadapter);
		header=(TextView)findViewById(R.id.action_text);
		header.setText("Search");
		fwd.setVisibility(View.GONE);
		
		bck_bttn.setPadding(6 , 0 , 0 , 0);
		
	
		
		  ArrayAdapter<String> adapter = new ArrayAdapter<String>(
			        this, android.R.layout.simple_spinner_item, dayList);
		   spinnerDay.setAdapter(adapter);
			   
		spinnerDay.setOnItemSelectedListener(
			              new AdapterView.OnItemSelectedListener() {
			                  @Override
			                  public void onItemSelected(AdapterView<?> arg0, View arg1,
			                          int arg2, long arg3) {
			                    int position = spinnerDay.getSelectedItemPosition();
			                 //   Toast.makeText(getApplicationContext(),"Selected "+dayList[+position],Toast.LENGTH_LONG).show();
			                    
			                  }
			                  @Override
			                  public void onNothingSelected(AdapterView<?> arg0) {
			                     
			                  }
			              }
			          );
			    
		bck.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//startActivity(new Intent(Main_search.this,Events_attndng.class));
				finish();
			}
		});
		lview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//startActivity(new Intent(Main_search.this,Search_events.class));
			}
		});

	}
	
	

	public void eventSearchFunction(View view){
		
		switch(view.getId()){
	       
		case R.id.bestLay:
			bestText.setBackgroundColor(Color.parseColor("#D84949"));
			favText.setBackgroundColor(Color.WHITE);
			bestText.setTextColor(Color.WHITE);
			favText.setTextColor(Color.BLACK);
			
			break;
			
		case R.id.favsLay:
			
			//profileLay.setVisibility(View.VISIBLE);
			//preferencesLay.setVisibility(View.GONE);
			System.out.println("Favorite clicked");
			favText.setBackgroundColor(Color.parseColor("#D84949"));
			bestText.setBackgroundColor(Color.WHITE);
			favText.setTextColor(Color.WHITE);
			bestText.setTextColor(Color.BLACK);
			
			break;
			
			default:
				break;
		}
	}
	
	@Override
	public void onBackPressed() {
	//	super.onBackPressed();
		overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
		finish();
	}
}
