package com.dev.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 * com.pull.list.custom.ImageViewCareIME
 * 
 * @author Lyy
 * 
 */
public class ImageViewCareIME extends ImageView {

	public ImageViewCareIME(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ImageViewCareIME(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public static interface ImeUiChangeListener {
		public void onImeUiChange(int bottom);
	}

	ImeUiChangeListener mImeUiChangeListener;

	private int mPreBottom = 0;

	public void setOnImeUiChangeListener(ImeUiChangeListener imeUiChangeListener) {
		mImeUiChangeListener = imeUiChangeListener;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		 Rect r = new Rect();
		 if (getGlobalVisibleRect(r)) {
		 if (r.bottom != mPreBottom) {
		 mPreBottom = r.bottom;
		
		 // System.out.println("Change:   " + r.bottom);
		
		 // Toast.makeText(this.getContext(), "Change:   " + r.bottom,
		// 1).show();
		 }
		 }

		if (null != mImeUiChangeListener)
			mImeUiChangeListener.onImeUiChange(r.bottom);
	}

}
