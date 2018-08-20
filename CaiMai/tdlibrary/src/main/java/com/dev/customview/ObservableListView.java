package com.dev.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;

/***
 * com.dev.customview.ObservableListView
 * 
 * @author LYY
 *
 */
public class ObservableListView extends ListView {
	private ScrollViewListener scrollViewListener = null;

	public ObservableListView(Context context) {
		super(context);
	}

	public ObservableListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ObservableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public interface ScrollViewListener {
		void onScrollChanged(ListView scrollView, int x, int y, int oldx, int oldy);
	}

	public void setScrollViewListener(ScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if (scrollViewListener != null) {
			scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
		}
	}
	
	
}