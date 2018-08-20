package com.dev.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/***
 * 
 * �ҵ�listview
 * 
 * @author Lyy
 * 
 */
public class MListView extends ListView {

	public MListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mGestureDetector = new GestureDetector(new YScrollDetector());

		setFadingEdgeLength(0);
	}

	private GestureDetector mGestureDetector;

	OnTouchListener mGestureListener;

	public MListView(Context context) {

		super(context);
	}

	public MListView(Context context, AttributeSet attrs) {

		super(context, attrs);

		mGestureDetector = new GestureDetector(new YScrollDetector());

		setFadingEdgeLength(0);

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		return super.onInterceptTouchEvent(ev)
				&& mGestureDetector.onTouchEvent(ev);

	}

	class YScrollDetector extends SimpleOnGestureListener {

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			Log.e("-----fff-----------------" + distanceX, "-----------------"
					+ distanceY);
			if (distanceY != 0 && distanceX != 0) {

			}
			if (Math.abs(distanceY) >= Math.abs(distanceX)) {

				return true;

			}

			return false;

		}
	}

}
