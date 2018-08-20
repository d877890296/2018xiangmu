package com.dev.mbprogress;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hyf.tdlibrary.R;


/****
 * 搜索的viewdialog
 * 
 * @author Lyy
 * 
 */
public class MyDialog extends Dialog {

	public Window window = null;

	public int dialogAnimationStyle = 0;

	public MyDialog(Context context, int theme, int dialogAnimationStyle) {
		super(context, theme);
		this.dialogAnimationStyle = dialogAnimationStyle;
		// TODO Auto-generated constructor stub

	}

	protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	public MyDialog(Context context) {
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

	/****
	 * 自动弹起输入键盘
	 * 
	 * 显示隐藏键盘
	 * 
	 * searchfilter_edit编辑的输入
	 * 
	 * @param ishide
	 */
	public void openKey(final int ishide, final EditText searchfilter_edit) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				searchfilter_edit.requestFocus();
				// 强制使输入框弹出来
				searchfilter_edit.setFocusable(true);
				InputMethodManager imm = (InputMethodManager) searchfilter_edit.getContext()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
				if (ishide == 0) {
					imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				} else {
					// 隐藏键盘
					((InputMethodManager) searchfilter_edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
							.hideSoftInputFromWindow(searchfilter_edit.getWindowToken(),
									InputMethodManager.HIDE_NOT_ALWAYS);

				}
			}
		}, 400);

	}
	public void showDialog(View view, int x, int y) {
		setContentView(view);
		windowDeploy(x, y);
		// 设置触摸对话框意外的地方取消对话框
		if (dialogAnimationStyle == 2 || dialogAnimationStyle == 4) {
			setCanceledOnTouchOutside(true);
		} else {
			setCanceledOnTouchOutside(false);
		}
		show();
	}

	/** 取消一个dialog */
	public void dismiss(View view) {
		dismiss(view);
	}

	// 1.<style name="MyDialogStyle">
	// 2. <item
	// name="android:windowBackground">@android:color/transparent</item><!--背景透明-->
	// 3. <item name="android:windowFrame">@null</item><!--边框-->
	// 4. <item name="android:windowNoTitle">true</item><!--无标题-->
	// 5. <item
	// name="android:windowIsFloating">true</item><!--是否浮现在activity之上-->
	// 6. <item name="android:windowIsTranslucent">true</item><!--半透明-->
	// 7. <item name="android:windowContentOverlay">@null</item><!--内容覆盖 -->
	// 8. <item
	// name="android:windowAnimationStyle">@android:style/Animation.Dialog</item><!--
	// 窗口样式Dialog -->
	// 9. <item name="android:backgroundDimEnabled">true</item><!--模糊-->
	// 10. </style>

	// 设置窗口显示
	public void windowDeploy(int x, int y) {
		window = getWindow(); // 得到对话框
		// window.setBackgroundDrawableResource(R.color.blue); // 设置对话框背景为透明
		LayoutParams wl = window.getAttributes();
		// 根据x，y坐标设置窗口需要显示的位置
		wl.x = x; // x小于0左移，大于0右移
		wl.y = y; // y小于0上移，大于0下移
		// wl.alpha = 0.6f; //设置透明度
		if (dialogAnimationStyle == 1) {

			window.setWindowAnimations(R.style.dialogWindowAnim); // 设置窗口弹出动画
			wl.gravity = Gravity.TOP;// 设置重心

		} else if (dialogAnimationStyle == 2) {
			// window.setWindowAnimations(R.style.dialogWindowAnim1); //
			// 设置窗口弹出动画
			//window.setBackgroundDrawableResource(R.drawable.contacts_buttom_bg); // 设置对话框背景为透明
			wl.gravity = Gravity.TOP;// 设置重心

		} else if (dialogAnimationStyle == 4) {
			window.setWindowAnimations(R.style.dialogWindowAnimlist);
			// window.setBackgroundDrawableResource(R.drawable.bg_pop_queue);
			//  设置对话框背景为透明
			wl.gravity = Gravity.TOP;// 设置重心

		} else {
			wl.gravity = Gravity.CENTER;// 设置重心

		}
		window.setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		window.setAttributes(wl);
	}

	

}
