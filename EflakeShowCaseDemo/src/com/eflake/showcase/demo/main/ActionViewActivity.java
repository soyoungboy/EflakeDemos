package com.eflake.showcase.demo.main;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;
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

public class ActionViewActivity extends Activity implements
		OnShowcaseEventListener, OnClickListener {

	private ShowcaseView sv;
	private TextView tv_1;
	private TextView tv_2;
	private TextView tv_3;
	private int counter = 0;

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

			}
		});
		sv = new ShowcaseView.Builder(this)
				.setTarget(new ViewTarget(findViewById(R.id.textView1)))
				.setOnClickListener(this).build();
		sv.setButtonText("Next");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		ActionViewTarget target = new ActionViewTarget(this,
				ActionViewTarget.Type.OVERFLOW);
		sv = new ShowcaseView.Builder(this).setTarget(target)
				.setContentTitle("Actionview Test")
				.setContentText("Testing here").doNotBlockTouches().build();
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			break;

		default:
			break;
		}
	}

	@Override
	public void onShowcaseViewHide(ShowcaseView showcaseView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShowcaseViewShow(ShowcaseView showcaseView) {
		// TODO Auto-generated method stub
		
	}

}
