package com.dev.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/***
 * 
 * 我的viewpager
 * 
 * @author Lyy
 *  com.pull.list.custom.MyViewPager
 */
public class MyViewPager extends ViewPager {
	// 控制滑动的boolean锁
	public boolean scrollable = false;

	public MyViewPager(Context context) {
		super(context);

	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public void setScrollable(boolean enable) {
		this.scrollable = enable;

	}

	float mDownX, mDownY;

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownX = event.getX();
			mDownY = event.getY();
			getParent().requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:
			if (Math.abs(event.getX() - mDownX) > Math.abs(event.getY()
					- mDownY)) {
				getParent().requestDisallowInterceptTouchEvent(true);
			} else {
				getParent().requestDisallowInterceptTouchEvent(false);
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(false);
			break;

		}

		return super.dispatchTouchEvent(event);

	}
}
