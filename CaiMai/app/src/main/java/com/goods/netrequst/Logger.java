package com.goods.netrequst;

import android.util.Log;

/***
 * 自定义log输出
 * 
 * @author Lyy
 * 
 */
public class Logger {
	// 0表示输出调试模式、默认为0
	public static int LOG_LEVEL = 1;
	public static int ERROR = 0;
	public static int WARN = 2;
	public static int INFO = 3;
	public static int DEBUG = 4;
	public static int VERBOS = 5;

	public static void setDugError(int error) {
		ERROR = error;

	}

	/***
	 * 0 显示 1 隐藏
	 * 
	 * @param error
	 */
	public static void setErrorLog(int error) {
		ERROR = error;
	}

	public static void e(String tag, String msg) {
		if (LOG_LEVEL > ERROR)
			Log.e(tag, msg);
	}

	public static void w(String tag, String msg) {
		if (LOG_LEVEL > WARN)
			Log.w(tag, msg);
	}

	public static void i(String tag, String msg) {
		if (LOG_LEVEL > INFO)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (LOG_LEVEL > DEBUG)
			Log.d(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (LOG_LEVEL > VERBOS)
			Log.v(tag, msg);
	}

}