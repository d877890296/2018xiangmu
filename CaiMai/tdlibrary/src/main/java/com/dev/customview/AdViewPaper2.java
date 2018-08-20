package com.dev.customview;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/****
 * 广告 视图的viewpager
 * 
 * @author lyy
 *com.dev.customview.AdViewPaper
 */
public class AdViewPaper2 extends ViewPager {
	/** 触摸时按下的点 **/
	PointF downP = new PointF();
	/** 触摸时当前的点 **/
	PointF curP = new PointF();
	OnSingleTouchListener onSingleTouchListener;

	public AdViewPaper2(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public AdViewPaper2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
//		// 当拦截触摸事件到达此位置的时候，返回true，
//		// 说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
//
//		return true;
//	}

	private int down_y = 0;// 按下时y的值
	private int move_y = 0;// 滑动时Y的值

	//@Override
//	public boolean onTouchEvent(MotionEvent event) {
//
//		// TODO Auto-generated method stub
//		// 每次进行onTouch事件都记录当前的按下的坐标
//		curP.x = event.getX();
//		curP.y = event.getY();
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			// 记录按下时候的坐标
//			// 切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
//			downP.x = event.getX();
//			downP.y = event.getY();
//			down_y = (int) event.getY();
//			// 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
//			getParent().requestDisallowInterceptTouchEvent(true);
//		}
//		if (event.getAction() == MotionEvent.ACTION_MOVE) {
//			// 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
//			move_y = (int) event.getY();
//			// Log.e("------dd---------", down_y + "----------" + move_y);
//			// if (move_y - down_y > 60) {
//			// getParent().requestDisallowInterceptTouchEvent(false);
//			// } else {
//			// getParent().requestDisallowInterceptTouchEvent(true);
//			// }
//			float dx = Math.abs(curP.x - downP.x);
//			float dy = Math.abs(curP.y - downP.y);
//			if (dx < dy) {
//				getParent().requestDisallowInterceptTouchEvent(false);
//			} else {
//				getParent().requestDisallowInterceptTouchEvent(true);
//			}
//
//		}
//		if (event.getAction() == MotionEvent.ACTION_UP) {
//			// 在up时判断是否按下和松手的坐标为一个点
//			// 如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onclick
//			if (downP.x == curP.x && downP.y == curP.y) {
//	
//				onSingleTouch();
//				move_y = 0;
//				down_y = 0;
//				//return true;
//			}
//		}
//		return super.onTouchEvent(event);
//	}

//	/**
//	 * 单击
//	 */
//	public void onSingleTouch() {
//		if (onSingleTouchListener != null) {
//			onSingleTouchListener.onSingleTouch();
//		}
//	}

	/**
	 * 创建点击事件接口
	 * 
	 * @author wanpg
	 * 
	 */
	public interface OnSingleTouchListener {
		public void onSingleTouch();
	}

	public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
		this.onSingleTouchListener = onSingleTouchListener;
	}
}
