package com.eflake.actionbar.adapter;

import com.eflake.actionbar.fragment.ScrollTabsFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ScrollTabsSectionPagerAdapter extends FragmentStatePagerAdapter {

	public static final String SCROLLTABS_TAG = "scrolltabs_tag";

	public ScrollTabsSectionPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		ScrollTabsFragment fragment = new ScrollTabsFragment();
		Bundle args = new Bundle();
		args.putString(ScrollTabsSectionPagerAdapter.SCROLLTABS_TAG, "Fragment:"+ position);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 8;
	}

}
