package com.hyf.tdlibrary.utils;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 工具类
 * 
 */
public class Tools {
	/** 根据手机的分辨率来设置text的大小 **/
	public static int accordingTophoneResolutionSetTextSize_(int phoneResolution) {
		if (phoneResolution <= 320) {
			return 8;
		} else if (320 < phoneResolution && phoneResolution <= 540) {
			return 12;
		} else if (540 < phoneResolution && phoneResolution <= 800) {
			return 14;
		} else {
			return 20;
		}

	}

	/** 根据手机的分辨率来设置pattingleft的大小 **/
	public static int accordingTophoneResolutionSetButtonPatting(int phoneResolution) {
		if (phoneResolution <= 320) {
			return 8;
		} else if (320 < phoneResolution && phoneResolution <= 540) {
			return 50;
		} else if (540 < phoneResolution && phoneResolution <= 800) {
			return 10;
		} else {
			return 10;
		}
	}

	public static int getFontSize(Context context, int textSize) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		int screenHeight = dm.heightPixels;
		// screenWidth = screenWidth > screenHeight ? screenWidth :
		// screenHeight;
		int rate = (int) (textSize * (float) screenHeight / 1280);
		return rate;
	}

	/** 根据手机的分辨率来设置text的大小 **/
	public static int accordingTophoneResolutionSetTextSize(int phoneResolution) {
		if (phoneResolution <= 320) {
			return 14;
		} else if (320 < phoneResolution && phoneResolution <= 480) {
			return 16;
		} else if (480 < phoneResolution && phoneResolution <= 600) {
			return 16;
		} else if (600 < phoneResolution && phoneResolution <= 720) {
			return 16;
		} else if (720 < phoneResolution && phoneResolution <= 800) {
			return 16;
		} else {
			return 16;
		}
	}

	/** 根据手机的分辨率来设置text的大小 **/
	public static int accordingTophoneResolutionSetTextSize_2(int phoneResolution) {

		if (phoneResolution <= 320) {
			return 12;
		} else if (320 < phoneResolution && phoneResolution <= 480) {
			return 14;
		} else if (480 < phoneResolution && phoneResolution <= 600) {
			return 14;
		} else if (600 < phoneResolution && phoneResolution <= 720) {
			return 15;
		} else if (720 < phoneResolution && phoneResolution <= 800) {
			return 15;
		} else {
			return 30;
		}
	}

	/** 根据手机的分辨率来设置text的大小 **/
	public static int accordingTophoneResolutionSetTextSize_1(int phoneResolution) {
		if (phoneResolution <= 320) {
			return 15;
		} else if (320 < phoneResolution && phoneResolution <= 480) {
			return 25;
		} else if (480 < phoneResolution && phoneResolution < 800) {
			return 36;
		} else {
			return 50;
		}
	}

	/** 根据手机的分辨率来Margin的间隔大小 **/
	public static int accordingTophoneResolutionSetMargin(int phoneResolution) {
		if (phoneResolution <= 320) {
			return 1;
		} else if (320 < phoneResolution && phoneResolution <= 480) {
			return 2;
		} else if (480 < phoneResolution && phoneResolution < 800) {
			return 10;
		} else {
			return 12;
		}

	}

	/***
	 * 检查sd卡是否存在
	 * 
	 * @return
	 */
	public static boolean ExistSDCard() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}

	/***
	 * 检查sd卡剩余空间
	 * 
	 * @return
	 */
	public long getSDFreeSize() {
		// 取得SD卡文件路径
		File path = Environment.getExternalStorageDirectory();
		StatFs sf = new StatFs(path.getPath());
		// 获取单个数据块的大小(Byte)
		long blockSize = sf.getBlockSize();
		// 空闲的数据块的数量
		long freeBlocks = sf.getAvailableBlocks();
		// 返回SD卡空闲大小
		// return freeBlocks * blockSize; //单位Byte
		// return (freeBlocks * blockSize)/1024; //单位KB
		return (freeBlocks * blockSize) / 1024 / 1024; // 单位MB
	}

	/***
	 * 检查sd卡总容量
	 * 
	 * @return
	 */
	public long getSDAllSize() {
		// 取得SD卡文件路径
		File path = Environment.getExternalStorageDirectory();
		StatFs sf = new StatFs(path.getPath());
		// 获取单个数据块的大小(Byte)
		long blockSize = sf.getBlockSize();
		// 获取所有数据块数
		long allBlocks = sf.getBlockCount();
		// 返回SD卡大小
		// return allBlocks * blockSize; //单位Byte
		// return (allBlocks * blockSize)/1024; //单位KB
		return (allBlocks * blockSize) / 1024 / 1024; // 单位MB
	}

	/***
	 * 
	 * 根据内容长度来计算内容显示的长度 不同手机的分辨率不同，显示文字的大小就不同
	 * 
	 * @param text
	 * @return
	 */
	public static int sumTextLenght(String text, int phoneResolution) {
		if (text.equals("")) {
			return 15;
		}
		return text.length() * Tools.accordingTophoneResolutionSetTextSize_1(phoneResolution);

	}

	/** 根据手机的分辨率来设置text的大小 **/
	public static int accordingTophoneResolutionSetPatting(int phoneResolution) {
		if (phoneResolution <= 320) {
			return 8;
		} else if (320 < phoneResolution && phoneResolution <= 480) {
			return 25;
		} else if (480 < phoneResolution && phoneResolution < 600) {
			return 60;
		} else {
			return 25;
		}

	}

	/** 判断字符串是否为空 */
	public static boolean IsEmpty(final String object) {
		if ((object == null) || (object.length() <= 0) || object.equals("null")) {
			return true;
		}
		return false;
	}

	public static boolean IsEmpty(final byte[] object) {
		if ((object == null) || (object.length <= 0)) {
			return true;
		}
		return false;
	}

	// android 去除字符串中的空格、回车、换行符、制表符
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 
	 * 判断邮箱是否规范
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isVaildEmail(String email) {

		String emailPattern = "[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+";
		boolean result = Pattern.matches(emailPattern, email);
		if (result == true) {
			return true;
		}

		return false;
	}

	/***
	 * 判断邮箱
	 * 
	 * @param email
	 * @return
	 */
//	public static boolean isMarkEmail(String email) {
////		 gaolx@cjh.com.cn
//		if (email.contains("@cjh.com.cn") && email.length() > 11) {
//			return true;
//		}
//		if (Util.isValidEmail(email)) {
//			return true;
//		} else {
//			return false;
//
//		}
//	}

	/**
	 * 判断是否为手机号格式
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(phone);
		return m.matches();
	}

	/**
	 * 获取应用程序的版本号
	 * 
	 * @return
	 */
	public static String getVersionName(Context context) {
		String code = "";

		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = null;
			pi = pm.getPackageInfo(context.getPackageName(), 0);
			code = pi.versionName;
		} catch (Exception e) {
			code = "1"; // failed, ignored
		}
		return code;
	}

	/**
	 * 根据手机的分辨率�?dp 的单�?转成�?px(像素)
	 */
	public static float dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (float) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率�?px(像素) 的单�?转成�?dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 判断输入的类容是不是以数字开头的
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isNumberFrist(String content) {
		if (content == null) {
			return false;
		}
		char fristchar = content.charAt(0);
		if ((fristchar >= '0' && fristchar <= '9') || fristchar < '0') {
			return true;
		} else if (fristchar == '!' || fristchar == '@' || fristchar == '#' || fristchar == '$' || fristchar == '%'
				|| fristchar == '^') {
			return true;
		}

		return false;
	}

	/****
	 * 
	 * 
	 * sitetype 站别 N string PP雨量站，ZQ,水文站，ZZ水位站,RR水库站，可以为空，搜索所有类别站点
	 * 
	 * @param sitetype
	 * @return
	 */

	public static String convertSitetype(String sitetype) {

		if (sitetype == null) {
			return "";
		} else if (sitetype.equals("PP")) {
			return "雨量站";
		} else if (sitetype.equals("ZQ")) {
			return "水文站";
		} else if (sitetype.equals("ZZ")) {
			return "水位站";
		} else if (sitetype.equals("RR")) {
			return "水库站";
		} else {
			return "";
		}

	}

	/***
	 * 
	 * level 报汛等级 1是中央报讯站，2是省级报讯站，3是其他
	 * 
	 */

	public static String convertLevel(String level) {
		if (level == null) {
			return "其他报汛站";
		} else if (level.equals("1")) {
			return "中央报讯站";

		} else if (level.equals("2")) {
			return "流域报讯站";

		} else {
			return "其他报汛站";
		}
	}

	/****
	 * 时间转换
	 * 
	 * @param time
	 * @return
	 */
	public static String labletime(String time) {

		if (time == null) {
			return "2000年10月";
		}
		String newTIme[] = time.split("-");
		return newTIme[0] + "年" + newTIme[1] + "月";

	}

	/***
	 * 
	 * 默认时间
	 * 
	 * @param time
	 * @return
	 */
	public static String newTime(String time) {
		if (time.length() > 6) {
			return time + " 08:10:00";
		}
		return time + "00-00" + " 08:10:00";
	}

	/***
	 * 
	 * 默认时间
	 * 
	 * @param time
	 * @return
	 */
	public static String initializeTime(String time) {
		return time + " 08:10:00";
	}

	/****
	 * 默认年的补充
	 * 
	 * @param time
	 * @return
	 */
	public static String initializeYear(String time) {
		return time + "-00-00 08:10:00";

	}

	/****
	 * 默认年的补充
	 * 
	 * @param time
	 * @return
	 */
	public static String initializeMonth(String time) {
		return time + "-00 08:10:00";

	}

	/***
	 * 获取天数的总和
	 * 
	 * @param time
	 * @return
	 */
	public static int SumTime(String time) {
		// 2011-12-05 17:00:00
		if (time == null || time.equals("")) {
			return 2000;
		}
		int year = Integer.parseInt(time.substring(0, time.length() - 15));
		int month = Integer.parseInt(time.substring(4, time.length() - 12));
		int subday = Integer.parseInt(time.substring(time.length() - 11, time.length() - 9));
		Log.e("-------------", year + "年" + month + "月" + subday + "日");
		return year + month + subday;
	}

	public static String subTimeString(String time) {

		return time.substring(0, time.length() - 6);
	}

	/***
	 * 比较两个时间的大小
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean date1_lessthan_date2(int date1, int date2) {
		if (date1 <= date2) {
			return true;
		}
		return false;
	}

	/***
	 * 
	 * 判断是否画小红旗
	 * 
	 */
	public static int isdrawimg(String str1, String str2, String str3) {
		if (Tools.IsEmpty(str1))
			return 0;
		else if (Tools.IsEmpty(str2))
			return 0;
		else if (Tools.IsEmpty(str3))
			return 0;
		float one = Float.parseFloat(str1);
		float two = Float.parseFloat(str2);
		float three = Float.parseFloat(str3);
		if (two - one < 0.5) {
			return 1;
		} else {
			if (three - one < 0.5) {
				return 2;
			} else {
				return 0;
			}
		}

	}

	/***
	 * 
	 * 
	 * 截取时间
	 * 
	 * @param time
	 * @return
	 */
	public static String subtime(String time) {
		// 1954-08-14 00:00:00
		// 08-14 00:00
		if (Tools.IsEmpty(time))
			return " - ";
		return time.substring(5, time.length() - 3);
	}

	/***
	 * 
	 * 根据水势等级取值
	 * 
	 * @param level
	 * @return
	 */
	public static String getlevelstate(String level) {
		if (level.equals("4")) {
			return "落";
		} else if (level.equals("5")) {
			return "涨";
		} else if (level.equals("6")) {
			return "平";
		} else {
			return " - ";
		}

	}

	/****
	 * 
	 * 
	 * 截取时间
	 * 
	 * @param time
	 * @return
	 */
	public static String subTime(String time) {
		if (IsEmpty(time)) {
			return "";
		}
		return time.substring(0, time.length() - 3);
	}

	/**
	 * url地址装换成16位进制符
	 * 
	 * @param url
	 * @return
	 */
	public static String getTextChar(String url) {
		if (url == null) {
			return "sorry,没有更新时间及其内容。";

		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < url.length(); i++) {
			char ch = url.charAt(i);

			String cr = Integer.toHexString((int) ch).toUpperCase();
			sb.append(cr);

		}

		return sb.toString();

	}

	public static String subStringTime(String time) {

		String newStr = time.substring(0, time.length() - 9);

		if (isSame(newStr) == true) {
			Log.e("-------------我是相同的-----", time.substring(time.length() - 9, time.length()));

			return time.substring(time.length() - 9, time.length());
		} else {
			Log.e("------------我是不相同的-----", time + "---");
			return time;
		}

	}

	/**
	 * 
	 * 判断名字重复
	 * 
	 * @param
	 * @param str
	 * @return
	 */
	public static boolean isSame(String str) {
		ArrayList<String> newList = null;
		List<Map<String, String>> data = null;
		if (newList == null) {
			newList = new ArrayList<String>();
			data = new ArrayList<Map<String, String>>();
		}
		newList.add(str);
		Log.e("我添加的数据是：--------------", str + "-------");
		for (int i = 0; i < newList.size(); i++) {
			if (newList.get(i).equals(str)) {
				return true;
			}

		}

		return false;
	}

	/***
	 * 
	 * 对空字符创的处理
	 * 
	 * @param string
	 * @return
	 */
	public static String nullStringDeal(String string) {

		String str = "";
		try {
			if (string != null && !string.equals("") && !string.equals("null")) {
				return string;
			} else {
				str = "90";
				return str;
			}
		} catch (NullPointerException e) {
			str = "90";
			return str;
		}

	}

	/**
	 * 缩放图片
	 * 
	 * @param bitmap
	 * @param width
	 * @param height
	 * @return
	 */
	@SuppressWarnings("unused")
	public static Bitmap setBitmapSize(Bitmap bitmap, int width, int height) {
		return Bitmap.createScaledBitmap(bitmap, width, height, true);
	}

	// 根据路径获得图片并压缩，返回bitmap用于显示
	public static Bitmap getSmallBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 180, 180);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}

	// 计算图片的缩放值
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	public static Bitmap comp(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 180f;// 这里设置高度为800f
		float ww = 180f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	/**
	 * 获取手机SD卡的根目录?
	 * 
	 * @return
	 */
	public static String getSdcardRootDir() {

		return Environment.getExternalStorageDirectory().getPath().toString() + "/Gxclass/";

	}

	/***
	 * 改变设定字符串中字体的颜色
	 * 
	 * @param str
	 * @return
	 */
	public static SpannableString sp(String str) {
		SpannableString sps = new SpannableString(str);
		int numberIndex = 0;
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (Character.isDigit(ch) == true) {
				// x类型转换然后统计
				count++;
				if (count < 2) {
					numberIndex = i + 1;
				} else if (count < 3) {
					numberIndex = i + 1;
				} else if (count < 4) {
					numberIndex = i + 1;
				}

			}
		}
		if (str != null) {
			// 改变第二个字符字体的大小
			sps.setSpan(new AbsoluteSizeSpan(24), 1, numberIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			// 改变第二个字符字体的颜色
			sps.setSpan(new ForegroundColorSpan(0xff65C0BE), 1, numberIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return sps;
	}

	// 11:09:22
	// ܰ�� 2014-6-25 11:09:22
	// // 02:12:43=(2*60*60)+(12*60)+43=7200+720+43=7963��
	/**
	 * 时间 分秒格式的处理
	 * 
	 * @param time
	 * @return
	 */
	public static String dealTimeMethod(int time) {

		// 分
		int min = (int) (time / 60);// 12
		// 秒
		int sec = (int) time % 60;// 43
		String m = "";
		String s = "";

		if (min < 10) {
			m = "0" + min;
		} else {
			m = min + "";
		}
		if (sec < 10) {
			s = "0" + sec;
		} else {
			s = sec + "";
		}

		return m + "分" + s + "秒";
	}

	/**
	 * 时间转换
	 * 
	 * @param time
	 * @return
	 */
	public static String dealTime(int time) {
		// 时
		int hour = (int) (time / 3600);// 2.21==2;
		// 分
		int min = (int) (time / 60 % 60);// 12
		// 秒
		int sec = (int) time % 60;// 43
		String h = "";
		String m = "";
		String s = "";
		if (hour < 10) {
			h = "0" + hour;
		} else {
			h = hour + "";
		}
		if (min < 10) {
			m = "0" + min;
		} else {
			m = min + "";
		}
		if (sec < 10) {
			s = "0" + sec;
		} else {
			s = sec + "";
		}
		return h + "时" + m + "分" + s + "秒";
	}

	/**
	 * 时间转换
	 * 
	 * @param time
	 * @return
	 */
	public static String dealtoTime(int time) {
		// 时
		int hour = (int) (time / 3600);// 2.21==2;
		// 分
		int min = (int) (time / 60 % 60);// 12
		String h = "";
		String m = "";
		h = hour + "";
		m = min + "";
		if (h.equals("") || h.equals("0") || h.equals("00")) {
			return m + "分钟";
		} else if (m.equals("") || m.equals("0") || m.equals("00")) {
			return Integer.parseInt(h) * 60 + "分钟";
		} else {
			return (Integer.parseInt(h) * 60 + Integer.parseInt(m)) + "分钟";
		}

	}

	/**
	 * 时间转换
	 * 
	 * @param time
	 * @return
	 */
	public static String dealplayTime(int time) {
		// 时
		int hour = (int) (time / 3600);// 2.21==2;
		// 分
		int min = (int) (time / 60 % 60);// 12
		// 秒
		int sec = (int) time % 60;// 43
		String h = "";
		String m = "";
		String s = "";
		if (hour < 10) {
			h = "0" + hour;
		} else {
			h = hour + "";
		}
		if (min < 10) {
			m = "0" + min;
		} else {
			m = min + "";
		}
		if (sec < 10) {
			s = "0" + sec;
		} else {
			s = sec + "";
		}
		if (h.equals("") || h.equals("0") || h.equals("00")) {
			return m + "分钟" + s + "秒";
		} else if (m.equals("") || m.equals("0") || m.equals("00")) {
			return s + "秒";
		} else {
			return h + "时" + m + "分" + s + "秒";
		}
	}

	/***
	 * 
	 * 获取当前的时间
	 * 
	 * @return
	 */
	public static int getCurrentTime() {

		Time localTime = new Time("Asia/Hong_Kong");
		localTime.setToNow();
		String date = localTime.format("%H:%M:%S");
		// String date = DateFormat.format("hh:mm:ss", new Date()).toString();
		// 02:57:20----Jul 1, 2014 11:30:00 AM
		// 判断24小时制的
		// boolean ff = android.text.format.DateFormat.is24HourFormat(this);
		// if (ff == true)
		// {
		// Log.e("activity", "24");
		// } else {
		// Log.e("activity", "12");
		// }
		String time[] = date.split(":");
		int h = getIntTime(time[0]);
		int m = getIntTime(time[1]);
		int s = getIntTime(time[2]);
		int totals = h * 3600 + m * 60 + s;
		return totals;
	}

	/***
	 * 
	 * 获取当前的字符时间
	 * 
	 * @return
	 */
	public static String getCurrentStringTime() {
		Time localTime = new Time("Asia/Hong_Kong");
		localTime.setToNow();
		String date = localTime.format("%Y-%m-%d %H:%M:%S");
		return date.toString();
	}

	/***
	 * 将字符串转为字符串时间戳
	 * 
	 * 
	 * @return
	 */
	public static String getlongtime() {
		String date = DateFormat.format("yyyy年MM月dd日hh时mm分ss秒", new Date()).toString();
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
		Date d = null;
		try {
			d = sdf.parse(date);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 10);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re_time;
	}

	/***
	 * 
	 * 将时间戳转为字符串
	 * 
	 * @param longtime
	 * @return
	 */
	public static String getStringTime(String longtime) {
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
		long lcc_time = Long.valueOf(longtime);
		re_time = sdf.format(lcc_time * 1000L);
		return re_time;
	}

	/***
	 * 获取整数时间
	 * 
	 * @param time
	 * @return
	 */
	public static int getIntTime(String time) {
		if (time != null && !time.equals("") && !time.equals("null")) {

			// if (time.contains("0")) {
			// return Integer.parseInt(time.substring(1, time.length()));
			// } else {
			return Integer.parseInt(time);
		} else {
			return 0;
		}

	}

	/* 时间戳转换成字符窜 */

	public static String getDataToString(long time) {
		Date d = new Date(time);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		return sf.format(d);
	}

	/***
	 * 
	 * 获取内容时间的格式
	 * 
	 * 传入的时间和系统24小时制时间对比
	 * 
	 * 实现类似qq列表上的时间
	 * 
	 * @param userTime
	 * @param context
	 * @author Lyy
	 * @date 2016-01-14 11:47
	 * @return
	 */
	public static String getContentDateFormat(String userTime, Context context) {
		if (userTime == null || userTime.equals("")) {
			return "--";
		}
		// 当前用户时间的数组/年月日---时分秒,2016-01-13 18:06:52
		String userTimeData[] = userTime.split(" ");
		// 年月日
		long userYMD = getYmdLongTime(userTimeData[0]);

		// 时分秒
		String userHMS = userTimeData[1];
		// ========当前系统时间=================================
		Time localTime = new Time("Asia/Hong_Kong");
		localTime.setToNow();
		// 2016-01-13 18:06:52
		String date = localTime.format("%Y-%m-%d %H:%M:%S");
		String timeData[] = date.split(" ");
		// 年月日
		long YMD = getYmdLongTime(timeData[0]);
		// 时分秒
		// String HMS = timeData[1];
		// ========当前系统时间===================================
		String AM_PM = "";
		long mitime = YMD - userYMD;
		// long minutes = mitime / 1000 / 60;
		// long hours = mitime / 1000 / 3600;
		int days = (int) (mitime / 1000 / 3600 / 24);
		int hTime = Integer.parseInt(userHMS.substring(0, 2));
		// 判断24小时制的
		boolean is24Hour = DateFormat.is24HourFormat(context);
		if (is24Hour == true) {
			if (hTime > 12) {
				AM_PM = "下午";
			} else {
				AM_PM = "上午";
			}
			// Log.e("24小时制date" + date, "24");
		} else {
			Log.e("12小时制date" + date, "12");
		}

		if (days == 0)
			return AM_PM + userHMS.substring(0, 5);
		else if (days == 1)
			return "昨天";
		else if (days > 1 && days < 7)
			return getDayOfWeekByDate(userTime);
		else
			return userTime;

	}

	/***
	 * 获得年月日的long数据
	 * 
	 * @param time
	 * @return
	 */
	public static Long getYmdLongTime(String time) {
		if (time == null || time.equals("")) {
			return 0L;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(time);
			return date.getTime();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return 0L;
	}

	/**
	 * 根据日期 找到对应日期的 星期
	 */
	public static String getDayOfWeekByDate(String date) {
		// 2016年 月份代码 512573514621
		// 日期数大于7的时候 ：（日期数+月份代码）/7取余
		// 小于7的时候：7-日期数
		// 等于7就是星期天
		// 以上也可以算出
		String dayOfweek = "-1";
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = myFormatter.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat("E");
			String str = formatter.format(myDate);
			dayOfweek = str;

		} catch (Exception e) {
			System.out.println("错误!");
		}
		return getWeek(dayOfweek);
	}

	/**
	 * 判断当前日期是星期几<br>
	 * <br>
	 * 
	 * @param pTime
	 *            修要判断的时间<br>
	 * @return dayForWeek 判断结果<br>
	 * @Exception 发生异常<br>
	 */
	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/*
	 * 基姆拉尔森计算公式 W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
	 * 
	 * 在公式中d表示日期中的日数，m表示月份数，y表示年数。
	 * 
	 * 
	 * 注意：在公式中有个与其他公式不同的地方：
	 * 
	 * 把一月和二月看成是上一年的十三月和十四月，例：如果是2004-1-10则换算成：2003-13-10来代入公式计算。 //
	 * 以公元元年为参考，公元元年1月1日为星期一
	 * 
	 * @param userTime
	 * 
	 * @param context
	 * 
	 * @author Lyy
	 * 
	 * @date 2016-01-14 11:47
	 */
	public static String caculateWeekDay(int y, int m, int d) {
		if (m == 1 || m == 2) {
			m += 12;
			y--;
		}
		int iWeek = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
		switch (iWeek) {
		case 0:
			return "星期一";
		case 1:
			return "星期二";
		case 2:
			return "星期三";
		case 3:
			return "星期四";
		case 4:
			return "星期五";
		case 5:
			return "星期六";
		case 6:
			return "星期日";
		}
		return "--";
	}

	/***
	 * 星期转换
	 * 
	 * @param week
	 * 
	 * @return
	 */
	public static String getWeek(String week) {
		if (week == null || week.equals("")) {
			return "--";
		}
		if (week.equals("周一")) {
			return "星期一";
		} else if (week.equals("周二")) {
			return "星期二";
		} else if (week.equals("周三")) {
			return "星期三";
		} else if (week.equals("周四")) {
			return "星期四";
		} else if (week.equals("周五")) {
			return "星期五";
		} else if (week.equals("周六")) {
			return "星期六";
		} else if (week.equals("周日")) {
			return "星期日";
		}
		return week;
	}

	/***
	 * 获取整数时间
	 * 
	 * @param time
	 *            把一个时间取整 例如 String str="09" 变成 9
	 * @return
	 */
	public static int getIntTime_(String time) {
		if (time != null && !time.equals("") && !time.equals("null")) {
			if (time.contains("0")) {
				return Integer.parseInt(time.substring(1, time.length()));
			} else {
				return 0;
			}
		}
		return 0;
	}

	public static int gettime(String date) {
		int totals = 0;
		if (date != null && !date.equals("") && !date.equals("null")) {
			String time[] = date.split(":");
			int h = Tools.getIntTime(time[0]);
			int m = Tools.getIntTime(time[1]);
			int s = Tools.getIntTime(time[2]);
			totals = h * 3600 + m * 60 + s;
			return totals;
		} else {
			return totals;
		}
	}

	public static String getdeartime(String time) {
		if (time != null && !time.equals("") && !time.equals("null")) {
			String time_[] = time.split(":");
			int h = Integer.parseInt(time_[0]) + 12;

			return h + "" + ":" + time_[1];
		} else {
			return "0分钟";
		}

	}

	/*
	 * ConnectivityManager网络管理器
	 * 
	 * 判断网络连接是否已开
	 * 
	 * true 已打开 false 未打开
	 */
	public static boolean isConn(Context context) {
		boolean bisConnFlag = false;
		ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = conManager.getActiveNetworkInfo();
		if (network != null) {
			bisConnFlag = conManager.getActiveNetworkInfo().isAvailable();
		}
		return bisConnFlag;
	}

	/***
	 * 
	 * 打开设置网络的界面
	 * 
	 * @param context
	 */
	public static void setNetworkMethod(final Context context) {
		Builder builder = new Builder(context);
		builder.setTitle("提示").setMessage("没有可用的网络，是否进行网络设置?")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Intent intent = null;
						// if (android.os.Build.VERSION.SDK_INT > 10) {
						// intent = new Intent(
						// android.provider.Settings.ACTION_WIRELESS_SETTINGS);
						// } else {
						// intent = new Intent();
						// ComponentName component = new ComponentName(
						// "com.android.settings",
						// "com.android.settings.WirelessSettings");
						// intent.setComponent(component);
						// intent.setAction("android.intent.action.VIEW");
						// }
						if (android.os.Build.VERSION.SDK_INT > 10) {
							// 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
							context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
						} else {
							context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
						}
						// context.startActivity(intent);

					}

				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
					}
				}).show();
	}

	/**
	 * 小写字母转大写字母
	 * 
	 * @param lowercase
	 * @return
	 */
	public static String lowercaseChangeUpperCase(String lowercase) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < lowercase.length(); i++) {
			String ch = String.valueOf(lowercase.charAt(i)).toUpperCase();

			sb.append(ch);

		}
		return sb.toString();

	}

	/***
	 * 
	 * 
	 * 时间改变才成中文的
	 * 
	 * @param date
	 * @return
	 */
	public static String test(String date) {

		if (date.contains("PM") || date.contains("AM")) {

			// Aug 3,2015 6:00:00 PM
			String[] time = date.split(",");
			String month_day = time[0];
			// 年
			String last = time[1].substring(0, 5);
			// 后面的时间
			String datetimne = time[1].substring(5, time[1].length());
			if (datetimne.contains("PM")) {
				// datetimne = datetimne.replace("PM", "上午");
				datetimne = datetimne.substring(0, datetimne.length() - 3);

			} else {
				int ff = (int) datetimne.charAt(0);
				datetimne = datetimne.replace(String.valueOf(ff), String.valueOf((ff + 12)));
				// datetimne = datetimne.replace("PM", "下午");
				datetimne = datetimne.substring(0, datetimne.length() - 3);
			}
			return last + "年 " + ff(month_day) + datetimne;
		} else if (date == null || date.equals("") || date.equals("null")) {
			return "";
		} else {
			return date;
		}
	}

	public static String ff(String str) {
		String newstr = "";
		// 一月January
		if (str.contains("Jan")) {
			newstr = str.replace("Jan", "1月");

		}
		// 二月February
		else if (str.contains("Feb")) {

			newstr = str.replace("Feb", "2月");
		}
		// 三月March
		else if (str.contains("Mar")) {
			newstr = str.replace("Mar", "3月");
		}
		// 四月April
		else if (str.contains("Apr")) {
			newstr = str.replace("Apr", "4月");
		}
		// 五月May
		else if (str.contains("May")) {

			newstr = str.replace("May", "5月");
		}
		// 六月June
		else if (str.contains("Jun")) {
			newstr = str.replace("Jun", "6月");
		}
		// 七月July
		else if (str.contains("Jul")) {
			newstr = str.replace("Jul", "7月");
		}
		// 八月August
		else if (str.contains("Aug")) {
			newstr = str.replace("Aug", "8月");
		}
		// 九月September
		else if (str.contains("Sep")) {
			newstr = str.replace("Sep", "9月");
		}
		// 十月October
		else if (str.contains("Oct")) {
			newstr = str.replace("Oct", "10月");
		}
		// 十一November
		else if (str.contains("Nov")) {
			newstr = str.replace("Nov", "11月");
		}
		// 十二月December
		else {
			newstr = "12月";
		}
		return newstr + "日";
	}

	/**
	 * 
	 * 字节转换
	 * 
	 * @param size
	 * @return
	 */
	public static String getfileSize(long size) {
		/** 格式转换 */
		DecimalFormat df = new DecimalFormat("####.##");

		if (size < 1024) {
			return size + "字节";
		} else if (size < 1024 * 1024) {
			return df.format(size / 1024) + "KB";
		} else if (size < 1024 * 1024 * 1024) {
			return df.format(size / (1024 * 1024)) + "MB";
		} else if (size < 1024 * 1024 * 1024 * 1024) {
			return df.format(size / (1024 * 1024 * 1024)) + "GB";
		}
		return size + "字节";

	}

	/***
	 * file:///exam/9090/1/topic/fdfdfdfd123dfds.jpg
	 * 
	 * file:///exam/9090就能删掉整个文件的
	 * 
	 * 删文件
	 * 
	 * @param root
	 */
	public static void deleteAllFiles(File root) {
		File files[] = root.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) { // 判断是否为文件夹
					deleteAllFiles(f);
					try {
						f.delete();
					} catch (Exception e) {
					}
				} else {
					if (f.exists()) { // 判断是否存在
						deleteAllFiles(f);
						try {
							f.delete();
						} catch (Exception e) {
						}
					}
				}
			}
			root.delete();
		}
	}

	/**
	 * 这里就是判断字符是否为表情；
	 * 
	 * @param codePoint
	 * @return
	 */
	public static boolean isEmojiCharacter(char codePoint) {
		return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 这里就是判断字符是否为表情；
	 * 
	 * @param codePoint
	 * @return
	 */
	public static Pattern isEmojiCharacter() {
		Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
				Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		return emoji;
	}

	public static int[] chinese = { 65285, 65286, 65374, 65283, 65290, 65284, 65371, 65373, 65339, 65341 };// 中文的：百分比字符/&符号/~符号/#符号/*符号/$符号/65371,65373中文{}/65339,65341中文【】
	public static int[] math = { 65291, 65309, 65293, 65308, 65310 };// 数字的：加号/等号/减号/小于号/大于号
	public static char[] chineseFH = { '，', '。', '？', '！', '、', '：', '；', '“', '”', '˜', '&', '˜', '（', '）', '﹛', '﹜',
			'《', '》', '【', '】', '‘', '’', '%', '%', '#', '﹫', '*', '﹩', '&', '￥', '|', '/', '.', '-', '+', '·', '…',
			'ˆ' };// 中文的符号

	/**
	 * 判断是某个值是否存在指定int数组中
	 * 
	 * @param num
	 * @param temp
	 * @return
	 */
	public static boolean isContains(int num, int[] temp) {
		if (temp.length > 0) {
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == num) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}

	}

	/***
	 * 返回一个时间范围
	 * 
	 * @param date
	 * @return
	 */
	public static String backOneTimeDuring(String date) {
		// curTime 2015-10-29 16:15:45
		String curTime = Tools.getCurrentStringTime();
		if (Tools.IsEmpty(date)) {
			date = curTime;
		}
		// 当前时间年月日
		String s[] = curTime.split(" ");
		int subTime = Integer.parseInt(s[0].replace("-", ""));
		// 当前考试时间年月日
		String ds[] = date.split(" ");
		int subDate = Integer.parseInt(ds[0].replace("-", ""));
		int subtraction = subTime - subDate;
		if (subtraction <= 0) {
			return "今天";
		} else if (subtraction > 0 && subtraction < 2) {
			return "昨天";
		} else if (subtraction >= 2 && subtraction <= 7) {
			return "一周以内";
		} else {
			return "更早";
		}
	}

	/**
	 * 判断是否是平板
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	public static boolean isChineseChar(String str) {
		boolean temp = false;
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			temp = true;
		}
		return temp;
	}

	/**
	 * 获取头像名称
	 * 
	 * @param pathandname
	 * @return
	 */
	public static String getFileName(String pathandname) {
		File f = new File(pathandname);
		File[] files = f.listFiles();// 列出所有文件
		String pathname = "";
		// 将所有文件存入list中
		if (files != null && files.length > 0) {
			File file = files[0];
			pathname = file.getAbsolutePath();
		} else {
			return pathname;
		}

		int start = pathname.lastIndexOf("/");
		if (start != -1 && pathname.length() != -1) {
			return pathname.substring(start + 1, pathname.length());
		} else {
			return pathname;
		}
	}

	/**
	 * 获取头像绝对路径
	 * 
	 * @param pathandname
	 * @return
	 */
	public static String getFilePath(String pathandname) {
		File f = new File(pathandname);
		File[] files = f.listFiles();// 列出所有文件
		String pathname = "";
		// 将所有文件存入list中
		if (files != null && files.length > 0) {
			File file = files[0];
			return pathname = file.getAbsolutePath();
		} else {
			return pathname;
		}
	}

	/**
	 * 
	 * 根据文件获取文件的字节信息
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static byte[] getFileContent(File file) throws Exception {

		byte[] ret = null;
		try {
			if (file == null) {
				// log.error("helper:the file is null!");
				return null;
			}
			FileInputStream in = new FileInputStream(file);

			ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
			int buffersize = 1024;

			byte[] b = new byte[buffersize];
			int n = 0;
			while ((n = in.read(b)) != -1) {
				out.write(b, 0, n);
			}
			in.close();
			out.close();
			ret = out.toByteArray();
		} catch (IOException e) {
			// log.error("helper:get bytes from file process error!");
			e.printStackTrace();
		}
		return ret;

	}

	/***
	 * 
	 * 二进制转十六字符串
	 * 
	 * String stmp = java.lang.Integer.toHexString(b[n] & 0XFF)
	 * 在32位的电脑中数字都是以32格式存放的，如果是一个byte(8位)类型的数字，他的高24位里面都是随机数字，低8位
	 * 才是实际的数据。java.lang.Integer.toHexString()
	 * 方法的参数是int(32位)类型，如果输入一个byte(8位)类型的数字，这个 方法会把这个数字的高24为也看作有效位，这就必然导致错误，使用&
	 * 0XFF操作，可以把高24位置0以避免这样错误 的发生。
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		String stmp = "";

		for (byte bt : b) {
			stmp = Integer.toHexString(bt & 0XFF);
			if (stmp.length() == 1) {
				sb.append("0" + stmp);
			} else {
				sb.append(stmp);
			}
		}
		return sb.toString();
	}

	/***
	 * 
	 * 二进制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static byte[] hex2byte(String str) { // 字符串转二进制
		if (str == null)
			return null;
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1)
			return null;
		byte[] b = new byte[len / 2];
		try {
			for (int i = 0; i < str.length(); i += 2) {
				b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	/***
	 * 处理性别
	 * 
	 * @param sex
	 * @return
	 */
	public static int getSex(String sex) {
		if (Tools.IsEmpty(sex)) {
			return 0;
		}

		if (sex.equals("男")) {
			return 0;
		} else {
			return 1;
		}

	}

	/***
	 * 计算及格率
	 * 
	 * @param passrate
	 * @return
	 */
	public static int backPassRate(String passrate) {
		// 0.3456
		if (Tools.IsEmpty(passrate)) {
			passrate = "0";
		}
		double rate = 0.00;
		if (passrate.length() > 5) {// 取两位有效数字
			rate = Double.parseDouble(passrate.substring(0, 3));
			String rate1 = passrate.substring(3, 4);
			if (Integer.parseInt(rate1) >= 5) {// 四舍五入
				rate = rate + 0.01;
			}
			rate = rate * 100;
		} else {
			rate = Double.parseDouble(passrate);
			rate = rate * 100;
		}
		return (int) rate;
	}

	/***
	 * 返回整型分数
	 * 
	 * @param socre
	 * @return
	 */
	public static int backIntegerScore(String socre) {
		// 0.3456
		if (IsEmpty(socre)||socre.equals("0")) {
			socre = "0.0";
		}
		String arr[]=socre.toString().split("\\.");
		int viableNum0 = Integer.parseInt(arr[0]+"");
		int viableNum1 = Integer.parseInt(arr[1]+"");
		if (viableNum1 >= 5) {
			viableNum0 = viableNum0 + 1;
		}
		return viableNum0;
	}
}
