package com.dev.customview;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

/***
 * 
 * com.dev.customview.BadgeCountTextView
 * 
 * @author HR
 *
 */
@SuppressLint("DrawAllocation")
public class BadgeCountTextView extends AppCompatTextView {

	Paint paint;
	String content = "";

	public BadgeCountTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public BadgeCountTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);

	}

	public void init(Context context) {
		paint = new Paint();
		paint.setColor(Color.parseColor("#FF0000"));
		this.setTextColor(Color.WHITE);
		paint.setAntiAlias(true);
		paint.setTextAlign(Align.CENTER);

	}

	public void setTextBackGroundColor(String color) {
		paint.setColor(Color.parseColor(color));
		this.invalidate();
	}

	public void setTextBackGroundColor(int color) {
		paint.setColor(color);
		this.invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		content = getText().toString();

		int width = this.getWidth();
		if (content != null) {
			int num = Integer.parseInt(content);
			if (num < 60) {
				canvas.drawCircle(width / 2, width / 2, width / 2, paint);
			} else {
				// // 定义一个矩形
				RectF rect = new RectF(0, 0, width, width);
				canvas.drawRoundRect(rect, 10, 10, paint);
			}
			super.onDraw(canvas);
			this.setText(content);
		} else {
			super.onDraw(canvas);
		}
	}

	public void sds(String content) {
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(content);
		if (m.matches()) {
			Toast.makeText(this.getContext(), "输入的是数字", Toast.LENGTH_SHORT).show();
		}
		p = Pattern.compile("[a-zA-Z]");
		m = p.matcher(content);
		if (m.matches()) {
			Toast.makeText(this.getContext(), "输入的是字母", Toast.LENGTH_SHORT).show();
		}
		p = Pattern.compile("[\u4e00-\u9fa5]");
		m = p.matcher(content);
		if (m.matches()) {
			Toast.makeText(this.getContext(), "输入的是汉字", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub

		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
