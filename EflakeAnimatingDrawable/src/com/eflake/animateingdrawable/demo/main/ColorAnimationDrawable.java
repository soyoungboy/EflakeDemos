package com.eflake.animateingdrawable.demo.main;

import java.util.Random;

import android.R.menu;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.animation.AnimationUtils;

public class ColorAnimationDrawable extends Drawable implements Animatable {
	private static final long FRAME_DURATION = 1000 / 60;
	private static final long ANIMATION_DURATION = 1500;
	private static final int ACCCENT_COLOR = 0x33FFFFFF;
	private static final int DIM_COLOR = 0x33000000;
	private static final Random mRandom = new Random();
	private final Paint mPaint = new Paint();
	private final Paint mEflakePaint = new Paint();
	private boolean mIsRunning;
	private int mStartColor;
	private int mEndColor;
	private int mCurrentColor;
	private long mStartTime;
	private String mEflakeText = "Eflake";
	private float radius = 50;
	private float cx = 0;
	private float cy = 0;

	public ColorAnimationDrawable() {
		mEflakePaint.setColor(0x990099cc);
		mEflakePaint.setStrokeWidth(2);
		mEflakePaint.setTextSize(25);
		mEflakePaint.setAntiAlias(true);
	}

	@Override
	public void draw(Canvas canvas) {
		final Rect bounds = getBounds();
		// mPaint.setColor(mCurrentColor);
		// canvas.drawRect(bounds, mPaint);
		//
		// mPaint.setColor(ACCCENT_COLOR);
		// canvas.drawRect(bounds.left, bounds.top, bounds.right, bounds.top +
		// 1, mPaint);
		//
		// mPaint.setColor(DIM_COLOR);
		// canvas.drawRect(bounds.left, bounds.bottom - 2, bounds.right,
		// bounds.bottom, mPaint);
//		canvas.drawText(mEflakeText, 100, 100, mEflakePaint);
		canvas.drawCircle(cx, cy, radius, mEflakePaint);

	}

	@Override
	public void setAlpha(int alpha) {
		oops("setAlpha(int)");
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		oops("setColorFilter(ColorFilter)");
	}

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSPARENT;
	}

	@Override
	public void start() {
		if (!isRunning()) {
			mIsRunning = true;
			mStartTime = AnimationUtils.currentAnimationTimeMillis();
			mStartColor = randomColor();
			mEndColor = randomColor();
			scheduleSelf(mUpdater, SystemClock.uptimeMillis() + FRAME_DURATION);
			invalidateSelf();
		}
	}

	@Override
	public void stop() {
		if (isRunning()) {
			unscheduleSelf(mUpdater);
			mIsRunning = false;
		}
	}

	@Override
	public boolean isRunning() {
		return mIsRunning;
	}

	private void oops(String message) {
		throw new UnsupportedOperationException(
				"ColorAnimationDrawable doesn't support " + message);
	}

	private static int randomColor() {
		return mRandom.nextInt() & 0x00FFFFFF;
	}

	private static int evaluate(float fraction, int startValue, int endValue) {
		return (int) (startValue + fraction * (endValue - startValue));
	}

	private final Runnable mUpdater = new Runnable() {
		@Override
		public void run() {
			long now = AnimationUtils.currentAnimationTimeMillis();
			long duration = now - mStartTime;
			mEflakeText += mEndColor;
			if (duration >= 50) {
				if (radius < 500) {
					cx += 10;
					cy += 10;
					radius += 10;
				} else {
					radius = 500;
				}
			}

			if (duration >= ANIMATION_DURATION) {
				mStartColor = mEndColor;
				mEndColor = randomColor();
				mStartTime = now;
				mCurrentColor = mStartColor;

			} else {
				float fraction = duration / (float) ANIMATION_DURATION;
				// @formatter:off
				mCurrentColor = Color.rgb(
						evaluate(fraction, Color.red(mStartColor),
								Color.red(mEndColor)), // red
						evaluate(fraction, Color.green(mStartColor),
								Color.green(mEndColor)), // green
						evaluate(fraction, Color.blue(mStartColor),
								Color.blue(mEndColor))); // blue
				// @formatter:on
			}
			scheduleSelf(mUpdater, SystemClock.uptimeMillis() + FRAME_DURATION);
			invalidateSelf();
		}
	};

}
