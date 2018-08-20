package com.dev.mbprogress;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
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

public class MbDialog extends Dialog {
	public Window window = null;
	public boolean dialogStyle;
	public int dialogType = 0;
	public Handler handler;
	public int maxValues, values;
	public int phoneResolution_h=320;
	/**
	 * dialog的重心位置
	 * 
	 */
	public DIALOG_GRAVITY dialogGravity = DIALOG_GRAVITY.CENTER;

	public enum DIALOG_GRAVITY {
		TOP, LEFT, RIGHT, RIGHT_FULL, CENTER, CENTER_XY, BOTTOM, CENTER_FULL;
	}

	public DIALOG_GRAVITY getDialogGravity() {
		return dialogGravity;
	}

	
	
	public void setPhoneResolution_h(int phoneResolution_h) {
		this.phoneResolution_h = phoneResolution_h;
	}



	public void setDialogGravity(DIALOG_GRAVITY dialogGravity) {
		this.dialogGravity = dialogGravity;
	}

	public int getDialogType() {
		return dialogType;
	}

	public void setDialogType(int dialogType) {
		this.dialogType = dialogType;
	}

	public void setHandler(Handler handler, int maxValues, int values) {
		this.handler = handler;
		this.maxValues = maxValues;
		this.values = values;

	}

	public MbDialog(Context context, int theme, boolean dialogStyle) {
		super(context, theme);
		this.dialogStyle = dialogStyle;
		// TODO Auto-generated constructor stub
	}

	protected MbDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	public MbDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (dialogType == 0) {
			return super.onKeyDown(keyCode, event);
		} else {
			switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				dismiss();
				break;
			case KeyEvent.KEYCODE_VOLUME_DOWN:
				if (values > 0) {
					values--;
				}
				break;
			case KeyEvent.KEYCODE_VOLUME_UP:
				if (values < maxValues) {
					values++;
				}
				break;
			}
			Message msg = Message.obtain();
			msg.arg1 = values;
			msg.what = 1000;
			handler.sendMessage(msg);
			return false;
		}
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
		if (dialogStyle == true) {
			setCanceledOnTouchOutside(true);
		} else {
			setCanceledOnTouchOutside(false);
		}
		try{
			show();
		}catch (Exception e) {
			// TODO: handle exception
		}
	
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
		window.setBackgroundDrawableResource(R.color.vifrification); // 设置对话框背景为透明
		// window.setWindowAnimations(R.style.dialogWindowAnim); // 设置窗口弹出动画
		switch (dialogGravity) {
		case TOP:
			wl.gravity = Gravity.TOP;// 设置重心
			window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			break;
		case LEFT:
			wl.gravity = Gravity.LEFT;// 设置重心
			window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			break;
		case RIGHT_FULL: 
			window.setWindowAnimations(R.style.dialogWindowAnimlist);
			wl.gravity = Gravity.RIGHT;// 设置重心
			window.setLayout(phoneResolution_h >> 1, LayoutParams.MATCH_PARENT);
			break;
		case RIGHT:
			wl.gravity = Gravity.RIGHT;// 设置重心
			window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			break;
		case CENTER:
			
			wl.gravity = Gravity.CENTER;// 设置重心
			window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			break; 
		case CENTER_FULL:
			window.setWindowAnimations(R.style.dialogWindowAnimlist);
			wl.gravity = Gravity.CENTER;// 设置重心
			window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.FILL_PARENT);
			break;
			case CENTER_XY:
				break;
			case BOTTOM:

			window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			wl.gravity = Gravity.BOTTOM;// 设置重心

			break;
		default:
			window.setWindowAnimations(R.style.dialogWindowAnimlist);
			wl.gravity = Gravity.CENTER;// 设置重心
			window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			break;
		}

		window.setAttributes(wl);
	}
}
