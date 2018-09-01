package com.dev.customview;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/***
 * textView的操作类
 * 
 * @author Lyy
 * 
 * @date 2015年，9月23日
 */
public class TextViewUtils {
	/** 点击成功的what值 **/
	public static final int CLICKOK = 0;

	/***
	 * 要点击的对象TextView
	 * 
	 * @param mClickableText
	 *            上下文对象
	 * @param context
	 *            更新ui
	 * @param handler
	 *            内容
	 * @param content
	 *            点击开始位置
	 * @param clickStart
	 */
	public static void textViewClick(TextView mClickableText,
			final Context context, final Handler handler, String content,
			int clickStart) {
		mClickableText.setText(getClickableSpan(context, handler, content,
				clickStart));
		mClickableText.setMovementMethod(LinkMovementMethod.getInstance());
	}

	public static SpannableString getClickableSpan(final Context context,
			final Handler handler, String content, int clickStart) {
		OnClickListener clickable = new OnClickListener() {
			@Override
			public void onClick(View v) {
				handler.sendEmptyMessage(CLICKOK);
			}
		};
		SpannableString spanableInfo = new SpannableString(content);
		int end = spanableInfo.length();
		String numberIndex[] = backStrNumberIndex(spanableInfo.toString());
		int strNumber = Integer.parseInt(numberIndex[0]);
		int second = Integer.parseInt(numberIndex[1]);
		if (strNumber <= 9) {
			/** 指定位置设置颜色 */
			spanableInfo.setSpan(
					new ForegroundColorSpan(Color.parseColor("#18B4ED")),
					second, second + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		} else {
			spanableInfo.setSpan(
					new ForegroundColorSpan(Color.parseColor("#18B4ED")),
					second, second + 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		// 遵循设定的开始于结束位置的文本块。SPAN_EXCLUSIVE_EXCLUSIVE
		spanableInfo.setSpan(new Clickable(clickable), end - 2, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		/* 去掉下划线* */
		if (spanableInfo instanceof Spannable) {
			Spannable s = (Spannable) spanableInfo;

		}

		return spanableInfo;
	}

	public static class Clickable extends ClickableSpan implements
			OnClickListener {
		private final OnClickListener mListener;

		public Clickable(OnClickListener listener) {
			mListener = listener;
		}

		@Override
		public void onClick(View v) {
			mListener.onClick(v);
		}
	}

	/***
	 * 设置指定位置的颜色
	 * 
	 * @param view
	 * @param start
	 * @param end
	 * @param color
	 */
	public static void setdesignatedStrColor(TextView view, int start, int end,
			String color) {
		SpannableString spanableInfo = new SpannableString(view.getText()
				.toString());
		/** 指定位置设置颜色 */
		spanableInfo.setSpan(
				new ForegroundColorSpan(Color.parseColor("#18B4ED")), start,
				end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		view.setMovementMethod(LinkMovementMethod.getInstance());
	}

	/***
	 * 设置指定位置的颜色 带数字的内容
	 * 
	 * @param view
	 * @param start
	 * @param end
	 * @param color
	 */
	public static void setdesignatedStrColor(TextView view, int start, int end,
			int color) {
		SpannableString spanableInfo = new SpannableString(view.getText()
				.toString());
		/** 指定位置设置颜色 */
		spanableInfo.setSpan(new ForegroundColorSpan(color), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		view.setMovementMethod(LinkMovementMethod.getInstance());
	}

	/***
	 * 设置指定位置的颜色
	 * 
	 * @param view

	 */
	public static void setdesignatedNumberStrColor(TextView view) {
		SpannableString spanableInfo = new SpannableString(view.getText()
				.toString());

		String numberIndex[] = backStrNumberIndex(spanableInfo.toString());
		int strNumber = Integer.parseInt(numberIndex[0]);
		int second = Integer.parseInt(numberIndex[1]);
		if (strNumber <= 9) {
			/** 指定位置设置颜色 */
			spanableInfo.setSpan(
					new ForegroundColorSpan(Color.parseColor("#18B4ED")),
					second, second + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		} else {
			spanableInfo.setSpan(
					new ForegroundColorSpan(Color.parseColor("#18B4ED")),
					second, second + 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		view.setMovementMethod(LinkMovementMethod.getInstance());
	}

	/****
	 * 
	 * 设置字体的颜色
	 * 
	 * @param str

	 * @return
	 */
	public static SpannableStringBuilder setTextContentColor(String str) {

		SpannableStringBuilder style = new SpannableStringBuilder(str);
		// str代表要显示的全部字符串
		String numberIndex[] = backStrNumberIndex(str.toString());
		int strNumber = Integer.parseInt(numberIndex[0]);
		int second = Integer.parseInt(numberIndex[1]);
		if (strNumber <= 9) {
			style.setSpan(new ForegroundColorSpan(Color.parseColor("#18B4ED")),
					second, second + 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		} else {
			style.setSpan(new ForegroundColorSpan(Color.parseColor("#18B4ED")),
					second, second + 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		// ３代表从第几个字符开始变颜色，注意第一个字符序号是０．
		// ８代表变色到第几个字符．
		return style;
	}

	/***
	 * 设置TextView 的颜色
	 * 
	 * @param view
	 * @param content
	 *            内容
	 * @param color
	 *            颜色
	 * @param start
	 *            开始文字
	 * @param end
	 *            结束文字
	 */
	public static void setContentColor(TextView view, String content,
			String color, int start, int end) {
		SpannableStringBuilder style = new SpannableStringBuilder(content);
		style.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start,
				end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		view.setText(style);

	}

	/***
	 * 设置EditText 的颜色
	 * 
	 * @param view
	 * @param content
	 *            内容
	 * @param color
	 *            颜色
	 * @param start
	 *            开始文字
	 * @param end
	 *            结束文字
	 */
	public static void setEditTextContentColor(EditText view, String content,
			String color, int start, int end) {
		SpannableStringBuilder style = new SpannableStringBuilder(content);

		style.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start,
				end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		view.setText(style);

	}

	/***
	 * 设置TextView 的颜色
	 * 
	 * @param view
	 * @param content
	 *            内容
	 * @param size
	 *            字体大小
	 * @param start
	 *            开始文字
	 * @param end
	 *            结束文字
	 */
	public static void setContentTextSize(TextView view, String content,
			int size, int start, int end) {
		SpannableStringBuilder style = new SpannableStringBuilder(content);
		// style.setSpan(new ForegroundColorSpan(Color.parseColor(color)),
		// start,
		// end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		style.setSpan(new AbsoluteSizeSpan(size), start, end,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		view.setText(style);

	}

	/***
	 * 返回内容中含数字的下标 例如：中国10年长城
	 * 
	 * @param str
	 * @return
	 */
	public static String[] backStrNumberIndex(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		int startIndex = 0;
		String isString = "";
		int fristNumber = 0;
		String strArray[] = new String[2];
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch >= '0' && ch <= '9') {
				isString += ch;
				fristNumber++;
				// 标记第一次出现的数字字符出现的下标
				if (fristNumber == 1) {
					startIndex = i;
				}
			}
		}
		if(isString.equals("")){
			isString="0";
		}
		// 判断是几位数的
		strArray[0] = isString;
		// 标记第一次出现的数字字符出现的下标
		strArray[1] = String.valueOf(startIndex);
		return strArray;
	}


}
