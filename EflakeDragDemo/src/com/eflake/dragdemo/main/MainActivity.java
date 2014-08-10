package com.eflake.dragdemo.main;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private DraggableImageView img;
	private DraggableImageView img_target;
	private TextView report_tv;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img = (DraggableImageView)findViewById(R.id.draggableImageView_source);
		img_target = (DraggableImageView)findViewById(R.id.draggableImageView_target);
		img.setTag("0");
		img_target.setTag("1");
		report_tv = (TextView)findViewById(R.id.textView1);
		img.setReportView(report_tv);
		img_target.setReportView(report_tv);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
