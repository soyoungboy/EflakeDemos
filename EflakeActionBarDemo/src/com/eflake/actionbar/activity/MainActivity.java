package com.eflake.actionbar.activity;

import com.example.actionbarscrolltabs.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 针对4.0以上系统版本，ActionBar所有使用方法示例</br>
 * 主列表Activity
 * 
 * @author Eflake-pc
 *
 */
public class MainActivity extends Activity implements OnItemClickListener {
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				new String[] { "ScrollTabs", "Spinner","Action Button","Custom Action View","Spilt ActionBar"});
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:
			Intent intent_scroll = new Intent(this, ScrollTabsActionbarActivity.class);
			startActivity(intent_scroll);
			break;
		case 1:
			Intent intent_spinner = new Intent(this,SpinnerActionbarActivity.class);
			startActivity(intent_spinner);
			break;
		case 2:
			Intent intent_actionbtn = new Intent(this, ActionViewActivity.class);
			startActivity(intent_actionbtn);
			break;
		case 3:
			Intent intent_action_custom_view = new Intent(this, ActionViewCustomActivity.class);
			startActivity(intent_action_custom_view);
			break;
		case 4:
			Intent intent_spilt_actionbar = new Intent(this, SpiltActionBarActivity.class);
			startActivity(intent_spilt_actionbar);
			break;
		default:
			break;
		}
	}

}
