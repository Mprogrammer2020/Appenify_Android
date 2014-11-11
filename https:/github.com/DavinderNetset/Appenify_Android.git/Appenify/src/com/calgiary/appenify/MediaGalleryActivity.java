package com.calgiary.appenify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.appenify.adapter.MediaGridVIewItemsAdapter;

public class MediaGalleryActivity extends Activity {

	GridView mediaGridView;
	MediaGridVIewItemsAdapter mediaGridValues;
	RelativeLayout fullscreenImage, gridVIewLayout, videoViewLay;
	ImageView fullScreenImage, cancelBtn, previousImage, nextImage;	
	RelativeLayout backward, forward;
	
	Button photosTab, videosTab;
	
	boolean imaageFull = false;
	
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	        setContentView(R.layout.act_media_gallery);
	    
	        mediaGridView = (GridView)findViewById(R.id.gridViewMedia);
	        fullscreenImage = (RelativeLayout)findViewById(R.id.fullscreenLay);
	        gridVIewLayout = (RelativeLayout)findViewById(R.id.gridVIewLayout);
	        videoViewLay = (RelativeLayout)findViewById(R.id.videoViewLay);
	        fullScreenImage = (ImageView)findViewById(R.id.full_image_view1);
	        cancelBtn = (ImageView)findViewById(R.id.cancelWindow1);
	        nextImage = (ImageView)findViewById(R.id.next_Btn_media);
	        previousImage = (ImageView)findViewById(R.id.previousImageBtn);
	        photosTab = (Button)findViewById(R.id.photoButton);
	        videosTab = (Button)findViewById(R.id.videoButton);
	        
	        mediaGridView.setVerticalScrollBarEnabled(false);
	        mediaGridView.setAdapter(new MediaGridVIewItemsAdapter(this));
	 
	        backward = (RelativeLayout) findViewById(R.id.back_Btn_mediaLay);
			forward = (RelativeLayout)findViewById(R.id.next_Btn_mediaLay);
			
	        mediaGridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						final int position, long id) {
					
					imaageFull = true;
					
					fullscreenImage.setVisibility(View.VISIBLE);
					mediaGridValues = new MediaGridVIewItemsAdapter(MediaGalleryActivity.this);
					
					fullScreenImage.setImageResource(mediaGridValues.gridValues[position]);
					mediaGridView.setClickable(false);
					/*System.out.println("mediaGridValues.getCount(): " +mediaGridValues.getCount());
					System.out.println("mediaGridValues.getItemId(): " +mediaGridValues.getItemId(position));
					System.out.println("mediaGridValues.gridValues: " +mediaGridView.getItemIdAtPosition(position));
					System.out.println("mediaGridValues.gridValues: " +position);
					*/
					nextImage.setVisibility(View.VISIBLE);
					
		
				if(position == mediaGridValues.getCount()){
					nextImage.setVisibility(View.GONE);
					previousImage.setVisibility(View.VISIBLE);
					System.out.println("next gone previous vesible");
				}
					                    
					if(position == 0){
						previousImage.setVisibility(View.GONE);
						nextImage.setVisibility(View.VISIBLE);
						
						System.out.println("next visible previous gone");
					}
			
					nextImage.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							if(position < mediaGridValues.getCount())
							fullScreenImage.setImageResource(mediaGridValues.gridValues[position +1]);
						}
					});
					
					previousImage.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							if(position > 0)
								if(position < mediaGridValues.getCount()){
										fullScreenImage.setImageResource(mediaGridValues.gridValues[position - 1]);
								}
						}
					});

			  }
			});
	 }
	 
	
		public void imageFunction(View view){
			
			switch(view.getId()){
		       
			
			case R.id.back_Btn_mediaLay:
			MediaGalleryActivity.this.finish();
				break;
				
				
			case R.id.next_Btn_mediaLay:
				Intent i = new Intent(MediaGalleryActivity.this, Comments.class);
				startActivity(i);
				break;
				
				
			case R.id.fullscreenLay:
				//fullscreenImage.setVisibility(View.GONE);
				mediaGridView.setClickable(true);
				break;
		
				
			case R.id.cancelWindow1:
				fullscreenImage.setVisibility(View.GONE);
				break;
		
				
			case R.id.photoButton:
				videoViewLay.setVisibility(View.GONE);
			gridVIewLayout.setVisibility(View.VISIBLE);
			photosTab.setBackgroundColor(Color.parseColor("#D84949"));
			videosTab.setBackgroundColor(Color.WHITE);
			photosTab.setTextColor(Color.WHITE);
			videosTab.setTextColor(Color.BLACK);
				break;
			
			case R.id.videoButton:
				videoViewLay.setVisibility(View.VISIBLE);
				gridVIewLayout.setVisibility(View.GONE);
				videosTab.setBackgroundColor(Color.parseColor("#D84949"));
				photosTab.setBackgroundColor(Color.WHITE);
				videosTab.setTextColor(Color.WHITE);
				photosTab.setTextColor(Color.BLACK);
				break;
				
				case R.id.back_Btn_media:
					finish();
					break;
			}
		}
		
		@Override
		public void onBackPressed() {
			if(imaageFull){
				fullscreenImage.setVisibility(View.GONE);
				imaageFull = false;
			}else {
				super.onBackPressed();
				MediaGalleryActivity.this.finish();
			}
			
		}
}




/*	LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

View popupView = layoutInflater.inflate(R.layout.media_gallery_full_screen, null);
   
  final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    popupWindow.setContentView(popupView);
    
 //   popupWindow.showAtLocation(parent, gravity, x, y)
    ImageView fullImageView = (ImageView)popupView. findViewById(R.id.full_image_view);
    mediaGridValues = new MediaGridVIewItemsAdapter(MediaGalleryActivity.this);
   fullImageView.setImageResource(mediaGridValues.gridValues[position]);
    
   popupWindow.showAtLocation(popupView, Gravity.CENTER_VERTICAL, 0, 0);
	System.out.println("Position: " +mediaGridValues.gridValues[position]);*/

