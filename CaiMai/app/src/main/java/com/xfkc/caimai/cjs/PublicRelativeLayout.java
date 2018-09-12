package com.xfkc.caimai.cjs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/***
 * 
 * @author liyuanyou
 *
 *         E-mail:aidiyuanyuan@qq.com
 * @version 创建时间：2016年4月6日 上午9:58:50 类说明
 *          com.dev.custom.widget.PublicRelativeLayout
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class PublicRelativeLayout extends RelativeLayout {
	public SettingSystemBar settingSystemBar;
	public Activity mActivity;

	public void setBarColor(String barColor) {
		settingSystemBar.setBarColor(barColor);
	}
	
	/**
	 * 布局加载完后修改沉浸式状态栏颜色
	 * @param barColor
	 */
	public void setChangeBarColor(String barColor) {
		settingSystemBar.setBarColor(barColor);
		settingSystemBar.setSystemBar();
	}

	// new SettingSystemBar(this).setSystemBar();
	//new SettingSystemBar(this).setSystemBar();

	// new SettingSystemBar(this).setSystemBar();
	public PublicRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}

	public PublicRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public PublicRelativeLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		this.setClipToPadding(true);
		this.setFitsSystemWindows(true);
	}

	public void settingSystemBar(Activity mActivity) {
		this.mActivity = mActivity;
		settingSystemBar = new SettingSystemBar(mActivity);
		settingSystemBar.setSystemBar();
	}

}
