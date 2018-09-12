package com.xfkc.caimai.cjs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/***
 * 安卓4.4后实现透明状态栏
 * 
 * 安卓5.0后实现透明状态栏
 * 
 * @author lyy
 * 
 *
 */
@SuppressLint("NewApi")
public class SettingSystemBar {
	public Activity mActivity;
	public String barColor = "#15B4EB";

	public SettingSystemBar(Activity mActivity) {
		this.mActivity = mActivity;
	}

	public String getBarColor() {
		return barColor;
	}

	public void setBarColor(String barColor) {
		this.barColor = barColor;
	}

	/***
	 * 获取手机系统版本号api4.4+
	 * 
	 * 
	 * 在activity对应的布局文件中加入两行代码
	 * 
	 * android:fitsSystemWindows="true"
	 * 
	 * android:clipToPadding="true"
	 * 
	 * 
	 */
	public void setSystemBar() {

		int sysVersion = Integer.parseInt(VERSION.SDK);
		if (sysVersion < 19) {
			return;
		} else if (sysVersion >= 19 && sysVersion < 21) {
			initSystemBar();
		} else {
			systemVersion5plus();
		}
	}

	/**
	 * 设置通知栏 这个方法在onCreate()实现，
	 * 
	 * 
	 * 如果是在父类的onCreate()中添加，即使所有继承了该父类都会有沉浸通知栏。
	 */
	public void initSystemBar() {
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
			SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
			tintManager.setStatusBarTintEnabled(true);
			// tintManager.setStatusBarTintResource(0x15B4EB);
			tintManager.setStatusBarTintColor(Color.parseColor(barColor));
		}
	}

	/**
	 * 设置通知栏的状态
	 * 
	 * @param on
	 */
	@SuppressLint("InlinedApi")
	private void setTranslucentStatus(boolean on) {
		Window win = mActivity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	/***
	 * 
	 * 手机系统5.0以上的
	 * 
	 */
	@SuppressLint("InlinedApi")
	public void systemVersion5plus() {
		if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
			Window window = mActivity.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
					| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(Color.parseColor(barColor));
			window.setNavigationBarColor(Color.parseColor(barColor));
		}
	}
}
