package com.eflake.animateingdrawable.demo.main;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;

public class MainActivity extends Activity {

	 private final Handler mHandler = new Handler();
	    private ColorAnimationDrawable mActionBarBackground;

	    @SuppressLint("NewApi")
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        // Animating drawable using in actionbar
	        mActionBarBackground = new ColorAnimationDrawable();
	        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
	            mActionBarBackground.setCallback(mDrawableCallback);
	        } else {
	            getActionBar().setBackgroundDrawable(mActionBarBackground);
	        }
	        mActionBarBackground.start();
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();
	        mActionBarBackground.start();
	    }

	    @Override
	    protected void onPause() {
	        super.onPause();
	        mActionBarBackground.stop();
	    }

	    private Drawable.Callback mDrawableCallback = new Drawable.Callback() {
	        @SuppressLint("NewApi")
			@Override
	        public void invalidateDrawable(Drawable who) {
	            getActionBar().setBackgroundDrawable(who);
	        }

	        @Override
	        public void scheduleDrawable(Drawable who, Runnable what, long when) {
	            mHandler.postAtTime(what, when);
	        }

	        @Override
	        public void unscheduleDrawable(Drawable who, Runnable what) {
	            mHandler.removeCallbacks(what);
	        }
	    };
}
