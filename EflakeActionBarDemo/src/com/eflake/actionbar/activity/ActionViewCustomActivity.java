package com.eflake.actionbar.activity;

import com.example.actionbarscrolltabs.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActionViewCustomActivity extends Activity implements
		 OnClickListener, TextWatcher, OnKeyListener {
	private ActionBar actionBar;
	private TextView textview;
	private View mCustomMenuView;
	private MenuItem mCustomMenu;
	private Button button;
	private EditText edittext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_action_view_activity);
		textview = (TextView) findViewById(R.id.textView1);
		actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.custom_action_view, menu);
		mCustomMenu = menu.findItem(R.id.item1);
		mCustomMenuView = menu.findItem(R.id.item1).getActionView();
		edittext = (EditText) mCustomMenuView.findViewById(R.id.edit_number);
		edittext.addTextChangedListener(this);
		edittext.setOnKeyListener(this);
		button = (Button) mCustomMenuView
				.findViewById(R.id.button_search_complete);
		button.setOnClickListener(this);
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
			break;
		case R.id.action_airplane:
			Toast.makeText(this, "action_airplane", Toast.LENGTH_SHORT).show();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_search_complete:
			Toast.makeText(this,
					"search_complete" + edittext.getText().toString(),
					Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

	// EditText输入监听回调方法
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		textview.setText(s);
	}

	@Override
	public void afterTextChanged(Editable s) {
		
	}
	//EditText监听软键盘输入
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_ENTER:
			Toast.makeText(this, keyCode+"", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return false;
	}
}
