package com.dev.customview;



import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.hyf.tdlibrary.R;

/****
 * 
 * 
 * @author Lyy
 * 
 */
@SuppressLint("InlinedApi")
public class SBDialog extends Dialog {

	Window window = null;

	public SBDialog(Context context, int theme) {
		super(context, theme);

		// TODO Auto-generated constructor stub

	}

	protected SBDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	public SBDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
		// |
		// WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}

	public void showDialog(View view, int x, int y) {
		setContentView(view);
		windowDeploy(x, y);
		// 设置触摸对话框意外的地方取消对话框

		setCanceledOnTouchOutside(true);

		show();
	}

	/** 取消一个dialog */
	public void dismiss(View view) {
		dismiss(view);
	}

	// 设置窗口显示
	public void windowDeploy(int x, int y) {
		window = getWindow(); // 得到对话框

		// window.setBackgroundDrawableResource(R.color.blue); // 设置对话框背景为透明
		LayoutParams wl = window.getAttributes();
		// 根据x，y坐标设置窗口需要显示的位置
		wl.x = x; // x小于0左移，大于0右移
		wl.y = y; // y小于0上移，大于0下移
		// wl.alpha = 0.6f; //设置透明度
		// 设置窗口弹出动画
		window.setBackgroundDrawableResource(R.color.lightblack); // 设置对话框背景为透明
		// window.setWindowAnimations(R.style.dialogWindowAnim); // 设置窗口弹出动画
		wl.gravity = Gravity.TOP;// 设置重心
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		window.setAttributes(wl);
	}

}
