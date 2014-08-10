package com.eflake.actionbar.fragment;

import com.eflake.actionbar.activity.ScrollTabsActionbarActivity;
import com.eflake.actionbar.adapter.ScrollTabsSectionPagerAdapter;
import com.example.actionbarscrolltabs.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScrollTabsFragment extends Fragment {
private TextView textView1;

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.scrolltabs_action_fragment, null);
	textView1 = (TextView)v.findViewById(R.id.textView1);
	textView1.setText(getArguments().getString(ScrollTabsSectionPagerAdapter.SCROLLTABS_TAG));
	return  v;
}
}
