package com.dev.customview;

import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;

/**
 * @author liyuanyou E-mail:aidiyuanyuan@qq.com
 * 
 * @version 创建时间：2016年4月1日 上午11:27:51
 * 
 *          类说明 用一个view 设置带动画效果的bar
 * 
 *          根据参考视图来设置bar的宽度（参考视图的一半的一半） 和setMargins
 * 
 *          用法 设置barline barLineSetting = new BarLineSetting(barLine);
 * 
 *          设置barView barLineSetting.setBarView(barView);
 * 
 *          设置参考的view视图 barLineSetting.setReferenceView(radio_list);
 */
public class BarLineSetting {
	// 下一个位置的x值
	private int nextX;
	// 每次移动的距离
	private int moveX;
	private int CurrentX;
	// 参考视图
	private View referenceView;
	// 线的视图
	private View barLine;
	// 参考视图的宽度
	private int referenceViewWidth;
	private List<RadioButton> barView;
	// 选中的bar字体颜色
	private int curSelectBarTextColor = 0xFFF02111;
	// 默认bar字体颜色
	private int defaultBarTextColor = 0xFF818181;
	private int curSelectBarTextColor1;

	public int getCurSelectBarTextColor() {
		return curSelectBarTextColor;
	}

	public void setCurSelectBarTextColor(int curSelectBarTextColor) {
		this.curSelectBarTextColor = curSelectBarTextColor;
	}

	public int getDefaultBarTextColor() {
		return defaultBarTextColor;
	}

	public void setDefaultBarTextColor(int defaultBarTextColor) {
		this.defaultBarTextColor = defaultBarTextColor;
	}

	public BarLineSetting(View barLine) {
		this.barLine = barLine;

	}

	public List<RadioButton> getBarView() {
		return barView;
	}

	/***
	 * 设置barView
	 * 
	 * @param barView
	 */
	public void setBarView(List<RadioButton> barView,int phoneResolution_w) {
		this.barView = barView;
		if (this.barView == null) {
			setBarNumer(1,phoneResolution_w);
		}
		setBarNumer(this.barView.size(),phoneResolution_w);
	}

	/***
	 * 设置导航的个数
	 * 
	 * @param barnumber
	 */
	public void setBarNumer(int barnumber,int phoneResolution_w) {
		if (barnumber == 0) {
			barnumber = 1;
		}
		int screenWidth =phoneResolution_w;
		moveX = screenWidth / barnumber;
	}

	/***
	 * 设置参考的view视图
	 * 
	 * @param referenceView
	 */
	public void setReferenceView(View referenceView) {
		this.referenceView = referenceView;
		referenceView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				// 在这里获取到宽高
				referenceViewWidth = BarLineSetting.this.referenceView.getMeasuredWidth();
				setBarParamsAndMargin(referenceViewWidth);
				// int height = radio_list.getMeasuredHeight();
				// Logger.e("------d-----------" + referenceViewWidth,
				// "-------ddd-------");
			}
		});
	}

	/***
	 * 设置bar的params 和Margin
	 * 
	 * @param referenceViewWidth
	 */
	public void setBarParamsAndMargin(int referenceViewWidth) {
		LayoutParams params = (LayoutParams) barLine.getLayoutParams();
		// params.leftMargin
		// 宽度设置成参考视图宽的一半
		params.width = referenceViewWidth >> 1;
		params.setMargins(referenceViewWidth >> 2, 15, 0, 0);
		barLine.setLayoutParams(params);
	}

	/**
	 * 动画
	 * 
	 * @param pager
	 */
	public void Amination(int pager) {
		nextX = moveX * pager;
		Animation animation = new TranslateAnimation(CurrentX, nextX, 0, 0);
		animation.setDuration(100);
		animation.setFillAfter(true);
		barLine.startAnimation(animation);
		CurrentX = nextX;
		setBarTextColor(pager);
	}

	/***
	 * 设置bar字体颜色
	 * 
	 * @param pager
	 */
	public void setBarTextColor(int pager) {
		// barView
		for (int i = 0; i < barView.size(); i++) {
			RadioButton view = barView.get(i);
			if (i == pager) {
				view.setTextColor(curSelectBarTextColor);
			} else {
				view.setTextColor(defaultBarTextColor);
			}
		}
	}
	
	
	/***
	 * 设置参考的view视图
	 * 
	 * @param referenceView
	 */
	public void setReferenceView(View referenceView,String type) {
		this.referenceView = referenceView;
		referenceView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				// 在这里获取到宽高
				referenceViewWidth = BarLineSetting.this.referenceView.getMeasuredWidth();
				setBarParamsAndMargin(referenceViewWidth, "");
				// int height = radio_list.getMeasuredHeight();
				// Logger.e("------d-----------" + referenceViewWidth,
				// "-------ddd-------");
			}
		});
	}
	
	/***
	 * 设置bar的params 和Margin
	 * 
	 * @param referenceViewWidth
	 */
	public void setBarParamsAndMargin(int referenceViewWidth, String type) {
		LayoutParams params = (LayoutParams) barLine.getLayoutParams();
		// params.leftMargin
		// 宽度设置成参考视图宽的一半
		params.width = referenceViewWidth;
//		params.setMargins(referenceViewWidth >> 2, 15, 0, 15);
		barLine.setLayoutParams(params);
	}

	/**
	 * 动画
	 * 
	 * @param pager
	 */
	public void Amination(int pager, String type) {
		curSelectBarTextColor1 = Color.parseColor("#18B4ED");
		nextX = moveX * pager;
		Animation animation = new TranslateAnimation(CurrentX, nextX, 0, 0);
		animation.setDuration(100);
		animation.setFillAfter(true);
		barLine.startAnimation(animation);
		CurrentX = nextX;
		setBarTextColor(pager, type);
	}

	/***
	 * 设置bar字体颜色
	 * 
	 * @param pager
	 */
	public void setBarTextColor(int pager, String type) {
		
		// barView
		for (int i = 0; i < barView.size(); i++) {
			RadioButton view = barView.get(i);
			if (i == pager) {
				view.setTextColor(curSelectBarTextColor1);
			} else {
				view.setTextColor(defaultBarTextColor);
			}
		}
	}
}
