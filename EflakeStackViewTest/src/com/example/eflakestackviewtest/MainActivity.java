package com.example.eflakestackviewtest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.StackView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final StackView stackView = (StackView) findViewById(R.id.mStackView);  
        
        ColorAdapter colorAdapter = new ColorAdapter(this, mColors);  
        stackView.setAdapter(colorAdapter);  
          
        final Button previousButon = (Button) findViewById(R.id.previousButton);  
        previousButon.setOnClickListener(new OnClickListener() {  
            public void onClick(View view) {  
                stackView.showPrevious();  
            }  
        });  
          
        final Button nextButton = (Button) findViewById(R.id.nextButton);  
        nextButton.setOnClickListener(new OnClickListener() {  
            public void onClick(View view) {  
                stackView.showNext();  
            }  
        });  
    }  
      
    private int [] mColors = {Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.RED};  
      

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class ColorAdapter extends BaseAdapter {  
		  
	    private Context mContext;  
	      
	    private int [] mColors;  
	      
	    public ColorAdapter(Context context, int [] colors) {  
	        mContext = context;  
	        mColors = colors;  
	    }  
	      
	    public int getCount() {  
	        return mColors == null ? 0 : mColors.length;  
	    }  
	  
	    public Object getItem(int position) {  
	        return mColors == null ? null : mColors[position];  
	    }  
	  
	    public long getItemId(int position) {  
	        return position;  
	    }  
	  
		@Override
		public View getView(int position, View cacheView, ViewGroup parent) {
			  LinearLayout.LayoutParams colorLayoutParams = new LinearLayout.LayoutParams(100, 100);  
	          
		        LinearLayout colorLayout = new LinearLayout(mContext);  
		        colorLayout.setBackgroundColor(mColors[position]);  
		        colorLayout.setLayoutParams(colorLayoutParams);  
		          
		        return colorLayout;  
		}  
	  
	}  
}
