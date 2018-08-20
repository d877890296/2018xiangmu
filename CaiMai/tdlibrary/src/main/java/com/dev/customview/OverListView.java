package com.dev.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ListView;


/***
 * 
 * 两种一种是固定高度
 *  
 * 第二种是计算高度 
 * 
 * @author Lyy
 *com.pull.list.custom.OverListView
 */

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class OverListView extends ListView {
	private static final int MAX_Y_OVERSCROLL_DISTANCE = 100;
	private int mMaxYOverscrollDistance;
	private Context mContext;

	private int mDownY;

	public OverListView(Context context) {
		super(context);
		mContext = context;
		initBounceListView();
	}

	public OverListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initBounceListView();
	}

	public OverListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initBounceListView();
	}

	private void initBounceListView() {
		// get the density of the screen and do some maths with it on the max
		// overscroll distance
		// variable so that you get similar behaviors no matter what the screen
		// size
		final DisplayMetrics metrics = mContext.getResources()
				.getDisplayMetrics();
		final float density = metrics.density;
		mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);

	}

	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
			int scrollY, int scrollRangeX, int scrollRangeY,
			int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
		// This is where the magic happens, we have replaced the incoming
		// maxOverScrollY with our own custom variable mMaxYOverscrollDistance;

		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
				scrollRangeX, scrollRangeY, maxOverScrollX,
				mMaxYOverscrollDistance, isTouchEvent);

	}
	float mDownX_, mDownY_, tempX;
	int k = 0;

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownX_ = event.getX();
			mDownY_ = event.getY();
			getParent().requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:
			// 斜率
			k = (int) ((Math.abs(event.getY() - mDownY_)) / Math.abs((event
					.getX() - mDownX_)));
			if (k == 0) {
				getParent().requestDisallowInterceptTouchEvent(false);
			} else {
				getParent().requestDisallowInterceptTouchEvent(true);
			}

			// if (Math.abs(event.getX() - mDownX) > Math.abs(event.getY()
			// - mDownY)) {
			// getParent().requestDisallowInterceptTouchEvent(true);
			// } else {
			// getParent().requestDisallowInterceptTouchEvent(false);
			// }
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(false);
			break;

		}
		return super.dispatchTouchEvent(event);

	}


}