package com.eflake.actionbar.activity;

import com.eflake.actionbar.adapter.ScrollTabsSectionPagerAdapter;
import com.eflake.actionbar.effect.DepthPageTransformer;
import com.example.actionbarscrolltabs.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * 可滑动TAB样式ActionBar
 * 
 * @author Eflake-pc
 * 
 */
public class ScrollTabsActionbarActivity extends FragmentActivity implements
		TabListener, OnPageChangeListener {
	private ViewPager pager;
	private ScrollTabsSectionPagerAdapter adapter;
	private ActionBar actionBar;
	public static int current_index = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrolltabs_action_activity);
		pager = (ViewPager) findViewById(R.id.pager);
		// ViewPager适配器
		adapter = new ScrollTabsSectionPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
		// 设置ViewPager切换动画
		// pager.setPageTransformer(true, new ZoomOutPageTransformer());
		pager.setPageTransformer(true, new DepthPageTransformer());
		pager.setOnPageChangeListener(this);
		actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (int i = 0; i < 8; i++) {
			actionBar.addTab(actionBar.newTab().setText("Tab" + i)
					.setTabListener(this).setTag(i));
		}
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

	// ActionBar中的TabListener
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// 第二个参数控制是否有流畅切换动画效果
		pager.setCurrentItem((Integer) tab.getTag(), true);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		Log.d("eflake", "tab " + tab.getTag() + "onTabUnselected");

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		Log.d("eflake", "tab " + tab.getTag() + "onTabReselected");

	}
	
	// ViewPager中的OnPageChangeListener
	@Override
	public void onPageScrollStateChanged(int state) {
		// State 0：动作完毕 1： 按下 2：松开，动作开始
		Log.d("eflake", "state=" + state);
	}

	@Override
	public void onPageScrolled(int index, float offsetRange, int offetPix) {
		// 当前滑动的index,起点范围（0.，1），起点位置像素
		Log.d("eflake", "index=" + index + ",offsetRange=" + offsetRange
				+ ",offetPix=" + offetPix);
	}

	@Override
	public void onPageSelected(int position) {
		ScrollTabsActionbarActivity.current_index = position;
		actionBar.setSelectedNavigationItem(position);
		// 重新刷新菜单，用于改变ActionBar
        invalidateOptionsMenu();
	}
}
