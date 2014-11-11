package com.calgiary.appenify;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.appenify.globalpackage.FriendPickerApplication;
import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.Session.NewPermissionsRequest;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;

public class SocialConnectActivity1 extends FragmentActivity{

	Button fb_connect_Btn, twitter_connect_Btn, linkedin_connect_Btn, googlePlus_connect_Btn, skip_Button;
	Dialog confirmSocialDialog;
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<String> userId = new ArrayList<String>();
	 String results = "", userIdStr = "";
	private static final List<String> PERMISSIONS = new ArrayList<String>() {
        {
            add("user_friends");
            add("public_profile");
        }
    };
    private static final int PICK_FRIENDS_ACTIVITY = 1;
 private UiLifecycleHelper lifecycleHelper;
    boolean pickFriendsWhenSessionOpened;
    
    private UiLifecycleHelper uiHelper;
	    private Session.StatusCallback callback = new Session.StatusCallback() {
	        @Override
	        public void call(Session session, SessionState state,
	                Exception exception) {
	            onSessionStateChange(session, state, exception);
	        }
	    };
	    private void onSessionStateChange(Session session, SessionState state,
	            Exception exception) {
	        if (state.isOpened()) {
	            // System.out.println("Logged in...");
	        } else if (state.isClosed()) {
	            // System.out.println("Logged out...");
	        }
	    }
	  
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_social_connect);
		
		fb_connect_Btn = (Button)findViewById(R.id.fb_connect_Btn);
		twitter_connect_Btn = (Button)findViewById(R.id.twitter_connect_Btn);
		linkedin_connect_Btn = (Button)findViewById(R.id.linkedin_connect_Btn);
		googlePlus_connect_Btn = (Button)findViewById(R.id.googlePlus_connect_Btn);
		skip_Button = (Button)findViewById(R.id.skipBtn_social);
		
		
		fb_connect_Btn.setBackgroundResource(R.drawable.facebook_draa);
		twitter_connect_Btn.setBackgroundResource(R.drawable.twitter_hover);
		linkedin_connect_Btn.setBackgroundResource(R.drawable.linkedin_hover);
		googlePlus_connect_Btn.setBackgroundResource(R.drawable.google_hover);
		
		
		  lifecycleHelper = new UiLifecycleHelper(this, new Session.StatusCallback() {
	            @Override
	            public void call(Session session, SessionState state, Exception exception) {
	                onSessionStateChanged(session, state, exception);
	            }
	        });
	        lifecycleHelper.onCreate(savedInstanceState);

	        ensureOpenSession();
		 
	       
		
	}
	
	 @Override
	    protected void onStart() {
	        super.onStart();

	        // Update the display every time we are started.
	        displaySelectedFriends(RESULT_OK);
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();

	        // Call the 'activateApp' method to log an app event for use in analytics and advertising reporting.  Do so in
	        // the onResume methods of the primary Activities that an app may be launched into.
	        AppEventsLogger.activateApp(this);
	    }

	    @Override
	    protected void onPause() {
	        super.onPause();

	        // Call the 'deactivateApp' method to log an app event for use in analytics and advertising
	        // reporting.  Do so in the onPause methods of the primary Activities that an app may be launched into.
	        AppEventsLogger.deactivateApp(this);
	    }

	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	
	    
	        switch (requestCode) {
	            case PICK_FRIENDS_ACTIVITY:
	                displaySelectedFriends(resultCode);
	                break;
	            default:
	                Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	                break;
	        }
	    }

	    private boolean ensureOpenSession() {
	        if (Session.getActiveSession() == null || !Session.getActiveSession().isOpened()) {
	            Session.openActiveSession(this,  true, PERMISSIONS,
	                    new Session.StatusCallback() {
	                        @Override
	                        public void call(Session session, SessionState state, Exception exception) {
	                            onSessionStateChanged(session, state, exception);
	                        }
	                    });
	            return false;
	        }
	        return true;
	    }
	    
	    private boolean sessionHasNecessaryPerms(Session session) {
	        if (session != null && session.getPermissions() != null) {
	            for (String requestedPerm : PERMISSIONS) {
	                if (!session.getPermissions().contains(requestedPerm)) {
	                    return false;
	                }
	            }
	            return true;
	        }
	        return false;
	    }
	    
	    private List<String> getMissingPermissions(Session session) {
	        List<String> missingPerms = new ArrayList<String>(PERMISSIONS);
	        if (session != null && session.getPermissions() != null) {
	            for (String requestedPerm : PERMISSIONS) {
	                if (session.getPermissions().contains(requestedPerm)) {
	                    missingPerms.remove(requestedPerm);
	                }
	            }
	        }
	        return missingPerms;
	    }

	    private void onSessionStateChanged(final Session session, SessionState state, Exception exception) {
	        if (state.isOpened() && !sessionHasNecessaryPerms(session)) {
	            AlertDialog.Builder builder = new AlertDialog.Builder(this);
	            builder.setMessage(R.string.need_perms_alert_text);
	            builder.setPositiveButton(
	                    R.string.need_perms_alert_button_ok, 
	                    new DialogInterface.OnClickListener() {
	                        @Override
	                        public void onClick(DialogInterface dialog, int which) {
	                            session.requestNewReadPermissions(
	                                    new NewPermissionsRequest(
	                                            SocialConnectActivity1.this, 
	                                            getMissingPermissions(session)));
	                        }
	                    });
	            builder.setNegativeButton(
	                    R.string.need_perms_alert_button_quit,
	                    new DialogInterface.OnClickListener() {
	                        @Override
	                        public void onClick(DialogInterface dialog, int which) {
	                            finish();
	                        }
	                    });
	            builder.show();
	        } else if (pickFriendsWhenSessionOpened && state.isOpened()) {
	            pickFriendsWhenSessionOpened = false;

	            startPickFriendsActivity();
	        }
	    }

	    private void displaySelectedFriends(int resultCode) {
	       
	        FriendPickerApplication application = (FriendPickerApplication) getApplication();

	        Collection<GraphUser> selection = application.getSelectedUsers();
	        if (selection != null && selection.size() > 0) {
	            
	            for (GraphUser user : selection) {
	                names.add(user.getName());
	                userId.add(user.getId());
	                System.out.println("user.getName():  " + user.getName());
	                System.out.println("user.getId():  " + user.getId());
	            }
	            results = TextUtils.join(", ", names);
	            userIdStr = TextUtils.join(", ",  userId);
	        } else {
	            results = "No friends selected";
	        }

	        System.out.println("Results:  " + results);
	     //   resultsTextView.setText(results);
	    }

	    private void onClickPickFriends() {
	        startPickFriendsActivity();
	    }

	    private void startPickFriendsActivity() {
	        if (ensureOpenSession()) {
	            Intent intent = new Intent(this, FbFriendListActivity.class);
	            FbFriendListActivity.populateParameters(intent, null, true, true);
	            startActivityForResult(intent, PICK_FRIENDS_ACTIVITY);
	        } else {
	            pickFriendsWhenSessionOpened = true;
	        }
	    }
	    
	
public void socialConnect(View view){
		
		switch(view.getId()){
	       
		case R.id.fb_connect_Btn:
			
			onClickPickFriends();
			/*Intent ii = new Intent(SocialConnectActivity.this, ShareWithFriendsActivity.class);
			startActivity(ii);
			finish();*/
			break;
			
		case R.id.twitter_connect_Btn:
			//confirmSocialDialog = new Dialog(SocialConnectActivity1.this);
			
			Uri imageUri = Uri.parse(Environment.getExternalStorageDirectory()+File.separator+"wel.jpg");
		    Log.e("image path",""+Environment.getExternalStorageDirectory()+File.separator+"wel.jpg");
			boolean tw_flag = false;

				Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
				shareIntent.setType("image/*");

				shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share");

				
				shareIntent.putExtra(Intent.EXTRA_SUBJECT,  "aaaaa");
				shareIntent.putExtra(Intent.EXTRA_TEXT, "bb");

				shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri); // put your image URI

				PackageManager pm = getPackageManager();

				List<ResolveInfo> activityList = pm
						.queryIntentActivities(shareIntent, 0);

				for (final ResolveInfo app : activityList) {
					if ((app.activityInfo.name).contains("twitter")) {
							tw_flag = true;
						final ActivityInfo activity = app.activityInfo;

						final ComponentName name = new ComponentName(
								activity.applicationInfo.packageName,activity.name);

						shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);

						shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
										| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
						shareIntent.setComponent(name);
						startActivity(shareIntent);

						break;
					}
				}

				if(tw_flag==false){
					
					Toast.makeText(getApplicationContext(), "Twitter Application is Not Installed!", Toast.LENGTH_SHORT).show();
				}
				
			
				
		
			
			break;
					
		case R.id.linkedin_connect_Btn:
			
			break;
			
		case R.id.googlePlus_connect_Btn:
			Intent intent = new Intent(SocialConnectActivity1.this, GooglePlusIntegration.class);
			startActivity(intent);
			
			break;
			
			case R.id.skipBtn_social:
				
				confirmSocialDialog = new Dialog(SocialConnectActivity1.this);
				confirmSocialDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				confirmSocialDialog.setContentView(R.layout.pop_up_social_connect);
				
				RelativeLayout social_layoutt = (RelativeLayout)confirmSocialDialog.findViewById(R.id.social_layoutt);
				social_layoutt.setBackgroundColor(Color.WHITE);
				
				Button yesButton = (Button)confirmSocialDialog.findViewById(R.id.socialConnectPopUpYES);
				Button connectButton = (Button)confirmSocialDialog.findViewById(R.id.socialConnectPopUpOKConnect);
				
				yesButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						Intent i = new Intent(SocialConnectActivity1.this, TabAct.class);
						Bundle b=new Bundle();
						 b.putString("condition","tb2");
						 i.putExtras(b);
						startActivity(i);
						finish();
						confirmSocialDialog.dismiss();
					}
				});
				
				connectButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						
						confirmSocialDialog.dismiss();
					}
				});
				confirmSocialDialog.show();
				
				break;
			
		}
	}

	
}
