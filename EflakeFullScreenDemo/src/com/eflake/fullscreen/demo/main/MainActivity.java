package com.eflake.fullscreen.demo.main;

import java.lang.reflect.Field;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class MainActivity extends ActionBarActivity {

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// Full screen mode in Android 4.4 +
			Window window = getWindow();
			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			// create our manager instance after the content view is set
			SystemBarTintManager tintManager = new SystemBarTintManager(this);
			// enable status bar tint
			tintManager.setStatusBarTintEnabled(true);
			// enable navigation bar tint
			tintManager.setNavigationBarTintEnabled(true);
			// set a custom tint color for all system bars
			tintManager.setTintColor(Color.parseColor("#ff0099cc"));
			// set a custom navigation bar resource
			// tintManager.setNavigationBarTintResource(R.drawable.my_tint);
			// set a custom status bar drawable
			// tintManager.setStatusBarTintDrawable(MyDrawable);
			// Because this mode layout will start from top(including status
			// bar),so
			// We set a padding to do this
			RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layout);
			relativeLayout.setPadding(0, getActionBarHeight()
					+ getStatusBarHeight(), 0, 0);
		}
	}

	private int getStatusBarHeight() {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	public int getActionBarHeight() {
		TypedValue tv = new TypedValue();
		int actionBarHeight = 0;
		if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))// 如果资源是存在的、有效的
		{
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
					getResources().getDisplayMetrics());
		}
		return actionBarHeight;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
