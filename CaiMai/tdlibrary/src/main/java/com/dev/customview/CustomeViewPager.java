package com.dev.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/***
 * 
 * 我的viewpager
 * 
 * @author Lyy com.pull.list.custom.CustomeViewPager
 */
public class CustomeViewPager extends ViewPager {
	private boolean isCanScroll = false;
	private float mDownX_, mDownY_, tempX;
	private int k = 0;
	public CustomeViewPager(Context context) {
		super(context);
	}

	public CustomeViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setScanScroll(boolean isCanScroll) {
		this.isCanScroll = isCanScroll;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		try {
			if (isCanScroll == true) {
				return super.onTouchEvent(ev);
			} else {
				return false;
			}
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			if (isCanScroll == true) {
				return super.onInterceptTouchEvent(ev);
			} else {
				return false;
			}
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {

		try {

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mDownX_ = event.getX();
				mDownY_ = event.getY();
				getParent().requestDisallowInterceptTouchEvent(true);
				break;
			case MotionEvent.ACTION_MOVE:
				// 斜率
				k = (int) ((Math.abs(event.getY() - mDownY_)) / Math.abs((event.getX() - mDownX_)));
				if (k == 0) {
					getParent().requestDisallowInterceptTouchEvent(false);
				} else {
					getParent().requestDisallowInterceptTouchEvent(true);
				}
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				getParent().requestDisallowInterceptTouchEvent(false);
				break;

			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.dispatchTouchEvent(event);
	}

}
