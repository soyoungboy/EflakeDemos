package com.eflake.showcase.demo.main;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnShowcaseEventListener {

	private ShowcaseView sv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, AnimatedActivity.class));
				
			}
		});
		RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		lps.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		int margin = ((Number) (getResources().getDisplayMetrics().density * 12))
				.intValue();
		lps.setMargins(margin, margin, margin, margin);

		ViewTarget target = new ViewTarget(R.id.button1, this);
		sv = new ShowcaseView.Builder(this, true).setTarget(target)
				.setContentTitle("ƒ„∫√").setContentText("Œ“ «ShowCase")
				.setStyle(R.style.CustomShowcaseTheme2)
//				.singleShot(42)
				.setShowcaseEventListener(this).build();
		sv.setButtonPosition(lps);
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

}
