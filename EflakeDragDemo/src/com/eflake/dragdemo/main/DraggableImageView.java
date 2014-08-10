package com.eflake.dragdemo.main;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DraggableImageView extends ImageView {

	private float event_x;
	private float event_y;
	private boolean acceptDrag;
	private boolean hovering;
	private boolean dragging;
	private Context context;
	private TextView reportView;
	private int view_width;
	private int view_height;
	private Paint paint;

	public DraggableImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		paint = new Paint();
		paint.setColor(0x660099cc);
		paint.setStrokeWidth(2);
		setFocusable(true);
		setClickable(true);
		setOnLongClickListener(new View.OnLongClickListener() {
			@SuppressLint("NewApi")
			public boolean onLongClick(View v) {
				ClipData data = ClipData.newPlainText("dot",
						"Dot : " + v.toString());
				// Send the drag event ACTION_DRAG_STARTED to all of views
				// View who want to deal it should return true on it's
				// ACTION_DRAG_STARTED
				// callback deal method
				v.startDrag(data, new ANRShadowBuilder(v, false), (Object) v, 0);
				return true;
			}
		});
	}

	// Shadow builder that can ANR if desired
	@SuppressLint("NewApi")
	class ANRShadowBuilder extends DragShadowBuilder {
		boolean mDoAnr;

		public ANRShadowBuilder(View view, boolean doAnr) {
			super(view);
			mDoAnr = doAnr;
		}

		@Override
		public void onDrawShadow(Canvas canvas) {
			if (mDoAnr) {
				sleepSixSeconds();
			}
			super.onDrawShadow(canvas);
		}

		private void sleepSixSeconds() {
			// hang forever; good for producing ANRs
			long start = SystemClock.uptimeMillis();
			do {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			} while (SystemClock.uptimeMillis() < start + 6000);
		}
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onDragEvent(DragEvent event) {
		boolean result = false;
		event_x = event.getX();
		event_y = event.getY();
		switch (event.getAction()) {
		case DragEvent.ACTION_DRAG_STARTED: {
			Log.d("eflake", "ACTION_DRAG_STARTED");
			// all views will received this callback when
			// one of them called startDrag,
			// return true to accept consequent event
			// otherwise return false ,we do nothing
			dragging = true;
			acceptDrag = result = true;
			invalidate();
		}
			break;

		case DragEvent.ACTION_DRAG_ENDED: {
			// all views will received this callback when
			// one of them haved called startDrag and it's over
			Log.d("eflake", "ACTION_DRAG_ENDED");
			dragging = false;
			hovering = false;
			invalidate();
		}
			break;

		case DragEvent.ACTION_DRAG_LOCATION: {
			// Log.d("eflake", "ACTION_DRAG_LOCATION");
			result = true;
		}
			break;

		case DragEvent.ACTION_DROP: {
			Log.d("eflake", "ACTION_DROP");
			processDrop(event);
			result = true;
		}
			break;

		case DragEvent.ACTION_DRAG_ENTERED: {
			Log.d("eflake", "ACTION_DRAG_ENTERED");
			hovering = true;
			invalidate();
		}
			break;

		case DragEvent.ACTION_DRAG_EXITED: {
			Log.d("eflake", "ACTION_DRAG_EXITED");
			hovering = false;
			invalidate();
		}
			break;

		default:
			result = acceptDrag;
			break;
		}

		return result;
	}

	@SuppressLint("NewApi")
	private void processDrop(DragEvent event) {
		 final ClipData data = event.getClipData();
	        final int N = data.getItemCount();
	        for (int i = 0; i < N; i++) {
	            ClipData.Item item = data.getItemAt(i);
//	            Log.i("eflake", "Dropped item " + i + " : " + item);
//	            if (reportView != null) {
//	                String text = item.coerceToText(getContext()).toString();
	                if (event.getLocalState() == (Object) this) {
	            		Toast.makeText(context, "Failed! Drop self", Toast.LENGTH_SHORT).show();
	                }else {
	            		Toast.makeText(context, "Success! Drop complete", Toast.LENGTH_SHORT).show();

					}
//	                reportView.setText(text);
//	            }
	        }
	}

	public void setReportView(TextView report) {
		this.reportView = report;
	}
	
	@SuppressLint("NewApi")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (hovering) {
			canvas.drawRect(0, 0, view_width, view_height, paint);
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		view_width = MeasureSpec.getSize(widthMeasureSpec);
		view_height = MeasureSpec.getSize(heightMeasureSpec);
	}
}
