package com.eflake.actionbar.fragment;

import com.example.actionbarscrolltabs.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SpinnerFragmentThree extends Fragment {
	private ProgressBar progressBar;
	private TextView textView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.spinner_fragment_three, null);
		progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
		textView = (TextView)v.findViewById(R.id.textView1);
		beginLoading();
		return v;
	}
	private void init() {
		textView.setText("Spinner Three");
	}
	
	private void beginLoading() {
		progressBar.setVisibility(View.VISIBLE);
		FakeLoadingAsyncTask task = new FakeLoadingAsyncTask();
		task.execute();
	}

	public class FakeLoadingAsyncTask extends
			AsyncTask<String, Integer, String> {

		public FakeLoadingAsyncTask() {
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			progressBar.setVisibility(View.GONE);
			init();
		}
	}
}
