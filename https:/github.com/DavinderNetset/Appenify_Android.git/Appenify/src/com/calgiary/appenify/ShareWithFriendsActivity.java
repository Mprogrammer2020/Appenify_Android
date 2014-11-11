package com.calgiary.appenify;

import java.util.ArrayList;
import java.util.List;

import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShareWithFriendsActivity extends Activity {

	private SocialAuthAdapter socialAuthAd;
	 Button selectButton;
	private ListView shareWithFriends_lv;
	private List<ShareWithFriendsListRowData> rowData;
	private String[] textName={"Afro Jack","Alex Metric","A Track","Afro Jack","Alex Metric","A Track"};
	private int[] imgages={R.drawable.appnify_logo,R.drawable.pic3,R.drawable.pic,
			R.drawable.pic1,R.drawable.pic2,R.drawable.pic3};
	Button shareWithFriendsSkipBotton, shareWithFriendsSharecontBotton;
	Dialog linkedInloginDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_share_with_friends);
		shareWithFriends_lv= (ListView) findViewById(R.id.shareWithFriendsListView);
		rowData=new ArrayList<ShareWithFriendsListRowData>();
		
		shareWithFriendsSkipBotton = (Button)findViewById(R.id.shareWithFriendsSkipBotton);
		shareWithFriendsSharecontBotton = (Button)findViewById(R.id.shareWithFriendsSharecontBotton);
		
				
		for(int i=0; i<textName.length;i++){
			ShareWithFriendsListRowData data=new ShareWithFriendsListRowData(imgages[i],textName[i]);
			rowData.add(data);
		}
		
		//CustomAdapter ad=new CustomAdapter(this, rowData);
		//shareWithFriends_lv.setAdapter(ad);

		
		
		socialAuthAd = new SocialAuthAdapter(new ResponseDataListener());
		socialAuthAd.addProvider(Provider.LINKEDIN, R.drawable.linkd_sq);
		/*socialAuthAd.addProvider(Provider.FACEBOOK, R.drawable.facebook);*/
		socialAuthAd.addCallBack(Provider.LINKEDIN, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");
		/*socialAuthAd.addCallBack(Provider.FACEBOOK, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");*/
		
		 socialAuthAd.enable(shareWithFriendsSharecontBotton);
		
		/*linkedInloginDialog = new Dialog(this);
		linkedInloginDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.WHITE));
		linkedInloginDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		linkedInloginDialog.setContentView(R.layout.popup_linkedin_login);
		
		 selectButton = (Button)linkedInloginDialog.findViewById(R.id.linkedLoginBtn);
		 socialAuthAd.enable(selectButton);
		 
		 selectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 socialAuthAd.enable(selectButton);
				linkedInloginDialog.dismiss();
			}
			
		 });
			
		 linkedInloginDialog.show();
*/
		
	}
	
	
	class ResponseDataListener implements DialogListener{		
		
		@Override
		public void onBack() {
			
		}

		@Override
		public void onCancel() {
		
		}

		@Override
		public void onComplete(Bundle arg0) {
			//After Authentication UserProfile Data
			socialAuthAd.getUserProfileAsync(new SocialAuthListener<Profile>() {
				
				@Override
				public void onExecute(String s, Profile p) {
				
					String pUrl;
					pUrl=p.getProfileImageURL();
					Log.i("String s", ""+s);
					
					Log.i("ProfileImageURL", ""+pUrl);
					Log.i("DOB", ""+p.getDob());
				
				}
				
				@Override
				public void onError(SocialAuthError arg0) {
					
				}
			});
			
			//After Authentication User Contact Data
			socialAuthAd.getContactListAsync(new SocialAuthListener<List<Contact>>() {
				
				@Override
				public void onExecute(String string, List<Contact> userContacts) {
					 Log.i("name", ""+userContacts.get(0).getFirstName());
				        Log.i("lName", ""+userContacts.get(0).getLastName());
				        Log.i("ID", ""+userContacts.get(0).getId());
				        Log.i("PimgURL", ""+userContacts.get(0).getProfileImageURL());
				        Log.i("Purl", ""+userContacts.get(0).getProfileUrl());
				        
				     shareWithFriends_lv.setAdapter(new LinkedInContactAdapter(ShareWithFriendsActivity.this, userContacts));
					
					
				}
				
				@Override
				public void onError(SocialAuthError arg0) {
					
				}
			});
			
		}

		@Override
		public void onError(SocialAuthError arg0) {
			
		}
	
	}
	
	
	
    public void shareSkipFunction(View view){
		switch(view.getId()){
	       
				case R.id.shareWithFriendsSkipBotton:
			Intent i = new Intent(ShareWithFriendsActivity.this, TabAct.class);
			Bundle b=new Bundle();
			 b.putString("condition","tb2");
			 i.putExtras(b);
			startActivity(i);
			finish();
			break;
			
		/*case R.id.shareWithFriendsSharecontBotton:
			 socialAuthAd.enable(shareWithFriendsSharecontBotton);
			Intent ii = new Intent(ShareWithFriendsActivity.this, TabAct.class);
			Bundle bb=new Bundle();
			 bb.putString("condition","tb2");
			 ii.putExtras(bb);
			startActivity(ii);
			finish();
			break;*/
			
		}
}

	class CustomAdapter extends BaseAdapter{
		
		private Activity context;
		private List<ShareWithFriendsListRowData> listData;
		private LayoutInflater inflater;
		
		public CustomAdapter(Activity c,List<ShareWithFriendsListRowData> listData) {
			super();
			context=c;
			this.listData=listData;
			
		}

		@Override
		public int getCount() {
			return listData.size();
		}

		@Override
		public Object getItem(int arg0) {
			return listData.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			
			ViewHolder holder=new ViewHolder();
			ShareWithFriendsListRowData rowItem = (ShareWithFriendsListRowData) getItem(position);
			 
	         inflater = context.getLayoutInflater();
	        if (convertView == null) {
	            convertView = inflater.inflate(R.layout.share_with_friends_lvrow_view, null);
	            holder = new ViewHolder();
	           
	            holder.txtV = (TextView) convertView.findViewById(R.id.shareWithFriendsLVRowText);
	            holder.imgV = (ImageView) convertView.findViewById(R.id.shareWithFriendsLVRowimage);
	          
	            convertView.setTag(holder);
	        } else
	            holder = (ViewHolder) convertView.getTag();
			
	        holder.txtV.setText(rowItem.getName());
	        holder.imgV.setImageResource(rowItem.getImgId());
			
			return convertView;
		}
		private class ViewHolder{
			ImageView imgV;
			TextView txtV;
		}
	}
	
}