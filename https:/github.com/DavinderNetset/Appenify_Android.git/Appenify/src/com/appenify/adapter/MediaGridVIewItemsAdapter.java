package com.appenify.adapter;


import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.calgiary.appenify.R;

public class MediaGridVIewItemsAdapter extends BaseAdapter{

	private Context context; 
	// Keep all Images in array
		public  Integer[] gridValues = {
				R.drawable.media1, R.drawable.media10,
				R.drawable.media11, R.drawable.media12,
				R.drawable.media3, R.drawable.media4,
				R.drawable.media5, R.drawable.media6,
				R.drawable.media7, R.drawable.media4,
				R.drawable.media9, R.drawable.media1,
				R.drawable.media3, R.drawable.media10,
				R.drawable.media6
		};
		
	public MediaGridVIewItemsAdapter(Context context){
		
		this.context = context;
		
	}
	
	
	@Override
	public int getCount() {
		return gridValues.length;
	}

	@Override
	public Object getItem(int position) {
		return gridValues[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(context);
        imageView.setImageResource(gridValues[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
       
        return imageView;
	}

	
	public int GetDipsFromPixel(float pixels)
		{
		  // Get the screen's density scale
		  final float scale = context.getResources().getDisplayMetrics().density;
		  // Convert the dps to pixels, based on density scale
		  return (int) (pixels * scale + 0.5f);
		}
}
