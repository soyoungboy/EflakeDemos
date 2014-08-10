package com.eflake.showcase.demo.main;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnimatedActivity extends Activity implements
		OnShowcaseEventListener, OnClickListener {

	private ShowcaseView sv;
	private TextView tv_1;
	private TextView tv_2;
	private TextView tv_3;
	private int counter= 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_1 = (TextView) findViewById(R.id.textView1);
		tv_2 = (TextView) findViewById(R.id.textView2);
		tv_3 = (TextView) findViewById(R.id.textView3);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AnimatedActivity.this, ActionViewActivity.class));
			}
		});
		sv = new ShowcaseView.Builder(this)
        .setTarget(new ViewTarget(findViewById(R.id.textView1)))
        .setOnClickListener(this)
        .build();
		sv.setButtonText("Next");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onShowcaseViewHide(ShowcaseView showcaseView) {

	}

	@Override
	public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onShowcaseViewShow(ShowcaseView showcaseView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		  switch (counter) {
          case 0:
              sv.setShowcase(new ViewTarget(tv_2), true);
              break;

          case 1:
        	  sv.setShowcase(new ViewTarget(tv_3), true);
              break;

          case 2:
        	  sv.setTarget(Target.NONE);
        	  sv.setContentTitle("Check it out");
        	  sv.setContentText("You don't always need a target to showcase");
        	  sv.setButtonText("Close");
              setAlpha(0.4f, tv_1, tv_2, tv_3);
              break;

          case 3:
        	  sv.hide();
              setAlpha(1.0f, tv_1, tv_2, tv_3);
              break;
      }
      counter++;		
	}
	
	  @SuppressLint("NewApi")
	private void setAlpha(float alpha, View... views) {
	            for (View view : views) {
	                view.setAlpha(alpha);
	            }
	    }

}
