package com.calgiary.appenify;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.appenify.adapter.Dumpclass;
import com.appenify.adapter.ListViewSwipeGesture;
import com.appenify.adapter.SwipeListViewModel;

public class FavEventLocation extends Activity{

	private ListView swipe_list_view;
	private SwipeListViewModel listAdapter;
	private ArrayList<Dumpclass> listdata;
	ListViewSwipeGesture touchListener;
	RelativeLayout backkonbar_Btn_mediaLay, okkkckLayouttt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_fav_event_location);
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		swipe_list_view	=	(ListView) findViewById(R.id.fav_locns);
		backkonbar_Btn_mediaLay = (RelativeLayout)findViewById(R.id.backkonbar_Btn_mediaLay);
		okkkckLayouttt = (RelativeLayout)findViewById(R.id.okkkckLayouttt);
		
		listdata = new ArrayList<Dumpclass>();
		
		InitializeValues();
		touchListener = new ListViewSwipeGesture(swipe_list_view, swipeListener, this);
		touchListener.SwipeType	=	ListViewSwipeGesture.Single;    //Set two options at background of list item
		
		swipe_list_view.setOnTouchListener(touchListener);
		
		
	}
	
public void facEventAct(View view){
	switch (view.getId()) {
	case R.id.backkonbar_Btn_mediaLay:
		finish();
		break;

		case R.id.okkkckLayouttt:
			Toast.makeText(getApplicationContext(),"Done", Toast.LENGTH_SHORT).show();
			finish();
			break;
	default:
		break;
	}
}
	private void InitializeValues() {
		listdata.add(new Dumpclass("Delhi"));
		listdata.add(new Dumpclass("Chandigarh"));
		listdata.add(new Dumpclass("Mumbai"));
	
		listAdapter	= new SwipeListViewModel(this, listdata);
		swipe_list_view.setAdapter(listAdapter);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.appenify, menu);
		return true;
	}
	
	ListViewSwipeGesture.TouchCallbacks swipeListener = new ListViewSwipeGesture.TouchCallbacks() {

		@Override
		public void FullSwipeListView(int position) {
			//Toast.makeText(getApplicationContext(),"Action_2", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void HalfSwipeListView(int position) {
			//Toast.makeText(getApplicationContext(),"Delete", Toast.LENGTH_SHORT).show();
		//	Toast.makeText(getApplicationContext(),"position: " +position, Toast.LENGTH_SHORT).show();
		//	Toast.makeText(getApplicationContext(),"listdata.size(): " +listdata.size(), Toast.LENGTH_SHORT).show();
			
			try {
				/*if(position == 2 && listdata.size() == 3){
					Toast.makeText(getApplicationContext(),"Remove First or second Place", Toast.LENGTH_SHORT).show();
				}else if (position == 1 && listdata.size() == 2) {
					Toast.makeText(getApplicationContext(),"Remove First Place", Toast.LENGTH_SHORT).show();
				}else {*/
				
					
					listdata.remove(position);
					touchListener.ResetListItem(touchListener.old_mDownView);
					listAdapter.notifyDataSetChanged();
				
					
			//	}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
				
		}

		@Override
		public void LoadDataForScroll(int count) {}

		@Override
		public void onDismiss(ListView listView, int[] reverseSortedPositions) {
			Toast.makeText(getApplicationContext(),"Delete onDismiss", Toast.LENGTH_SHORT).show();
			for(int i:reverseSortedPositions){
				/*if(i != listdata.size())
				{
				listdata.remove(i);
				listAdapter.notifyDataSetChanged();
				}*/
				
			}
		}

		@Override
		public void OnClickListView(int position) {
			//startActivity(new Intent(getApplicationContext(),FavEventLocation.class));
			System.out.println("Clicked");
			//touchListener.SwipeType	=	ListViewSwipeGesture.Dismiss;
		}
		
	};
	
}
