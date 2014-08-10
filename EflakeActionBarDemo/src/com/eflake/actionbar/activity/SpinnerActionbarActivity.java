package com.eflake.actionbar.activity;

import com.eflake.actionbar.fragment.SpinnerFragmentFactory;
import com.example.actionbarscrolltabs.R;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

/**
 * 下拉框导航样式ActionBar
 * 
 * @author Eflake-pc
 * 
 */
public class SpinnerActionbarActivity extends FragmentActivity {

	private ActionBar actionbBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner_action_activity);
		actionbBar = getActionBar();
		actionbBar.setHomeButtonEnabled(true);
		actionbBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionbBar.setDisplayHomeAsUpEnabled(true);
		SpinnerAdapter adapter = ArrayAdapter.createFromResource(this,
				R.array.drop_list, R.layout.spinner_layout);
		actionbBar.setListNavigationCallbacks(adapter,
				new OnNavigationListener() {
					@Override
					public boolean onNavigationItemSelected(int itemPosition,
							long itemId) {
						Log.d("eflake", "navigation clicked!");
						switchToCorrespondFragment(itemPosition);
						return false;
					}
				});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			// 这个标识确保在用户返回主页或上级页面时，新的Activity不会被添加到当前的任务中，
			// 而是在属于你自己的应用程序的任务中启动
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 切换FrameLayout中的Fragment
	 * 
	 * @param fragmentIndex
	 *            Fragment对应Index
	 */
	protected void switchToCorrespondFragment(int fragmentIndex) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
		transaction.replace(R.id.content,
				SpinnerFragmentFactory.product(fragmentIndex));
		transaction.commit();
	}

}
