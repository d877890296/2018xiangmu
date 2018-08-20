package com.dev.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * GridView点击空白地方事件扩展
 * @author Administrator
 *
 */
public class CustomGridView extends GridView {

	public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomGridView(Context context) {
		super(context);
	}

	public interface OnTouchInvalidPositionListener {
		/**
		 * motionEvent 可使用 MotionEvent.ACTION_DOWN 或者
		 * MotionEvent.ACTION_UP等来按需要进行判断
		 * 
		 * @return 是否要终止事件的路由
		 */
		boolean onTouchInvalidPosition(int motionEvent);
	}

	public OnTouchInvalidPositionListener mTouchInvalidPosListener;

	/**
	 * 点击空白区域时的响应和处理接口
	 */
	public void setOnTouchInvalidPositionListener(OnTouchInvalidPositionListener listener) {
		mTouchInvalidPosListener = listener;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (mTouchInvalidPosListener == null) {
			return super.onTouchEvent(event);
		}

		if (!isEnabled()) {
			// 被禁用的可点击视图仍然会消耗触摸事件，它只是不响应它们。
			return isClickable() || isLongClickable();
		}

		final int motionPosition = pointToPosition((int) event.getX(), (int) event.getY());

		if (motionPosition == INVALID_POSITION) {
			super.onTouchEvent(event);
			return mTouchInvalidPosListener.onTouchInvalidPosition(event.getActionMasked());
		}

		return super.onTouchEvent(event);
	}

//	/**
//	 * 主要是重载了触摸处理函数，并利用api pointToPosition取得当前点击的item,当你点击空白区域的时候，
//	 * 
//	 * 会返回INVALID_POSITION，由此便可判断点击了空白区域。
//	 * 
//	 * 
//	 * 
//	 * 使用的话如下代码所示。在onTouchInvalidPosition事件中你可以做自己的响应操作，
//	 * 
//	 * 通常用得比较多的是“点击空白地方取消当前操作”。这里我是用来实现点击空白地方让父容器
//	 * 
//	 * 显示触摸变色效果。 注意return返回值的意义，当返回false的时候代表交由父级控件处理，这里的
//	 * 
//	 * 效果通常会让外层的Layout显示被点击效果。当return true的时候表示你已经处理了该事件并不
//	 * 
//	 * 让该事件再往上传递。
//	 */
//	mGridView.setOnTouchInvalidPositionListener(new OnTouchInvalidPositionListener() {
//        @Override
//        public boolean onTouchInvalidPosition(int motionEvent) {
//            return false; //不终止路由事件让父级控件处理事件
//        }
//    });    
}
