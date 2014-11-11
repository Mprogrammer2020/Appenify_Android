package com.calgiary.appenify;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ActionBar extends RelativeLayout {
	private Context mContext;
	RelativeLayout bttn,fwd;
	private ImageView right;
	public ActionBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		LayoutInflater mInflater =  (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout rl = (RelativeLayout) mInflater.inflate(R.layout.action_bar, null); 
		addView(rl);
		bttn=(RelativeLayout)rl.findViewById(R.id.action_menu);
		fwd=(RelativeLayout)rl.findViewById(R.id.action_edit_container);
	}
	public void RightOnClick(OnClickListener rightclick)
	{
		this.right.setOnClickListener(rightclick);
	}
	
}
