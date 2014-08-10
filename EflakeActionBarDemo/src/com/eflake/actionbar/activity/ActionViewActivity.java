package com.eflake.actionbar.activity;

import com.example.actionbarscrolltabs.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class ActionViewActivity extends Activity implements
		OnQueryTextListener, Callback {
	private ActionBar actionBar;
	private TextView textview;
	private boolean isDefault = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.action_button_activity);
		textview = (TextView) findViewById(R.id.textView1);
		actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (isDefault) {
			getMenuInflater().inflate(R.menu.action_view, menu);
			SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
					.getActionView();
			searchView.setOnQueryTextListener(this);
		} else {
			getMenuInflater().inflate(R.menu.custom_action_view, menu);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(this, "action_settings", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_access_accounts:
			Toast.makeText(this, "action_access_accounts", Toast.LENGTH_SHORT)
					.show();
			// 通过Activity的startActionMode（CallBack）方法，显示这个ActionMode控件
			// 注意：若使用ListView或GridView之类继承自AbsListView的控件
			// 可以使用setMultiChoiceModeListener方式并实现接口来使用ActionMode
			// 比如要多项删除，当长按某一项时，ActionMode会出现，并且被长按的那项会被选中
			// 接着点击其他项，触发onActionItemClicked方法
			startActionMode(this);
			break;
		case R.id.action_airplane:
			Toast.makeText(this, "action_airplane", Toast.LENGTH_SHORT).show();
			changeActionBarStyle();
			break;
		case R.id.action_rotation:
			Toast.makeText(this, "action_rotation", Toast.LENGTH_SHORT).show();
			break;
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

	private void changeActionBarStyle() {
		isDefault = !isDefault;
		invalidateOptionsMenu();
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		Toast.makeText(this, "Query Text = " + query, Toast.LENGTH_SHORT)
				.show();
		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		textview.setText(newText);
		return true;
	}

	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		MenuInflater inflater = mode.getMenuInflater();
		inflater.inflate(R.menu.custom_action_view, menu);
		return true;
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		return false;
	}

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		return false;
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {

	}
}
