package com.eflake.actionbar.fragment;

import android.support.v4.app.Fragment;

public class SpinnerFragmentFactory {
	public final static int SPINNER_FRAGMENT_ONE_INDEX = 0;
	public final static int SPINNER_FRAGMENT_TWO_INDEX = 1;
	public final static int SPINNER_FRAGMENT_THREE_INDEX = 2;

	public static Fragment product(int index) {
		if (index == SPINNER_FRAGMENT_ONE_INDEX) {
			return new SpinnerFragmentOne();
		} else if (index == SPINNER_FRAGMENT_TWO_INDEX) {
			return new SpinnerFragmentTwo();
		} else if (index == SPINNER_FRAGMENT_THREE_INDEX) {
			return new SpinnerFragmentThree();
		}
		return null;
	}
}
