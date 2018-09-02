package com.dev.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/***
 * 
 * 我的viewpager
 * 
 * @author com.dev.customview.MyWebView
 */
public class MyWebView extends WebView {
	// 控制滑动的boolean锁
	public boolean scrollable = true;

	public MyWebView(Context context) {
		super(context);

	}

	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public void setScrollable(boolean enable) {
		this.scrollable = enable;

	}
	

	/***
	 * 
	 * 改变高度 其中onMeasure函数决定了组件显示的高度与宽度；
	 * makeMeasureSpec函数中第一个函数决定布局空间的大小，第二个参数是布局模式
	 * MeasureSpec.AT_MOST的意思就是子控件需要多大的控件就扩展到多大的空间
	 * 之后在ScrollView中添加这个组件就OK了，同样的道理，ListView也适用。
	 */
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}


}
