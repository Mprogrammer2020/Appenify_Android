package com.appenify.adapter;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Display;
import android.widget.RelativeLayout;

import com.calgiary.appenify.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class commonutilities {
	static Context ctx;
	public static SlidingMenu menu;
	static boolean isConnected = false;
	static RelativeLayout rl;

	public static SlidingMenu setSlidingMenu(Context c) {
		menu = new SlidingMenu(c);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setFadeDegree(0.35f);
		menu.setBehindWidth(setWidth(c));
		menu.attachToActivity((Activity) c, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.slide_menu);
		
		menu.addIgnoredView(menu);

		return menu;
	}

	public static int getSuitableWidth(Activity active) {

		Display display = active.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();

		width = (width / 2) + 80;

		return width;
	}

	public static int getHeight(Context c) {
		Display display = ((Activity) c).getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();

		return height;
	}

	public static int setWidth(Context c) {
		Display display = ((Activity) c).getWindowManager().getDefaultDisplay();
		int w = display.getWidth();
		int h = display.getHeight();
		int width;
		if (h < 500) {
			width = (w - 60);
		} else {
			width = (w - 100);
		}
		return width;

	}

	public static Bitmap getCircularBitmap(Bitmap bitmap) {
		Bitmap output;

		if (bitmap.getWidth() > bitmap.getHeight()) {
			output = Bitmap.createBitmap(bitmap.getHeight(),
					bitmap.getHeight(), Config.ARGB_8888);
		} else {
			output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(),
					Config.ARGB_8888);
		}

		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		float r = 0;

		if (bitmap.getWidth() > bitmap.getHeight()) {
			r = bitmap.getHeight() / 2;
		} else {
			r = bitmap.getWidth() / 2;
		}

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawCircle(r, r, r, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public static boolean getConnectivityStatus(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (null != activeNetwork) {
			if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)

				isConnected = true;
			// return TYPE_WIFI;

			if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
				// return TYPE_MOBILE;
				isConnected = true;
		} else {
			isConnected = false;
		}
		// return TYPE_NOT_CONNECTED;
		return isConnected;
	}

	public static void openInternetDialog(Context c) {
		ctx = c;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
		alertDialogBuilder.setTitle("Internet Alert!");
		alertDialogBuilder
				.setMessage(
						"You are not connected to Internet..Please Enable Internet!")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();

								final Intent intent = new Intent(
										Intent.ACTION_MAIN, null);
								intent.addCategory(Intent.CATEGORY_LAUNCHER);
								final ComponentName cn = new ComponentName(
										"com.android.settings",
										"com.android.settings.wifi.WifiSettings");
								intent.setComponent(cn);
								intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								ctx.startActivity(intent);
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	public static void buildAlertMessageNoGps() {
		  final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		    builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
		           .setCancelable(false)
		           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		               public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
		                   ctx.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
		               }
		           })
		           .setNegativeButton("No", new DialogInterface.OnClickListener() {
		               public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
		                    dialog.cancel();
		               }
		           });
		    final AlertDialog alert = builder.create();
		    alert.show();
		
	}
}
