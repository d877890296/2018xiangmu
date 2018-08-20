package com.dev.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/***
 * 
 * com.dev.customview.PieChartProgress
 * 
 * @author lyy
 * 
 *         create by 2017,03,21
 *
 */

public class PieChartProgress extends View {

	private Context context;
	private Paint paint;

	private int y;
	private int x;
	private boolean isLeft;
	private int mWidth;
	private int mHeight;
	private int mPercent = 0;
	private Paint mTextPaint;

	public PieChartProgress(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public PieChartProgress(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);

	}

	public PieChartProgress(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public void init(Context context) {
		this.context = context;

		// 画圆
		paint = new Paint();
		paint.setAntiAlias(true); // 消除锯齿
		paint.setStyle(Style.STROKE); // 绘制空心圆或 空心矩形

		// 画文字
		mTextPaint = new Paint();

		mTextPaint.setAntiAlias(true);

	}

	int center;

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);

		// 半径
		center = getWidth() / 2;
		// ==========================其他======================
		// drawQpath(canvas);
		// 绘制圆
		drawCircleRing(canvas);

		postInvalidateDelayed(5);
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (widthMode == MeasureSpec.EXACTLY) {
			mWidth = widthSize;
		}
		if (heightMode == MeasureSpec.EXACTLY) {
			mHeight = heightSize;
		}
		y = mHeight;
		setMeasuredDimension(mWidth, mHeight);
	}

	public void drawCircleRing(Canvas canvas) {
		// 内圆半径
		int innerCircle = dip2px(context, center / 2);
		// 圆环宽度
		int ringWidth = dip2px(context, 6);
		int strokeWidth = dip2px(context, 2);
		// 内部圆的半径
		int insideRadius = innerCircle - ringWidth;
		// =====================绘制内圆=========================
		paint.setColor(Color.parseColor("#FF0000"));
		// 设置圆环宽度
		paint.setStrokeWidth(strokeWidth);
		canvas.drawCircle(center, center, insideRadius, paint);
		// // ====================== 绘制圆环=========================
		// paint.setColor(Color.parseColor("#C5EBF9"));
		// paint.setStrokeWidth(ringWidth);
		// 画圆
		// paint.setColor(Color.parseColor("#F26956"));
		// canvas.drawCircle(center, center, innerCircle - ringWidth + ringWidth
		// / 2, this.paint);
		// 画圆弧
		// paint.setColor(Color.parseColor("#FBA62E"));
		// RectF oval = new RectF(center - innerCircle + ringWidth / 2, center -
		// innerCircle + ringWidth / 2,
		// center + innerCircle - ringWidth / 2, center + innerCircle -
		// ringWidth / 2);
		// canvas.drawArc(oval, -90, 270, false, paint);
		// // ========================绘制外圆=========================
		paint.setColor(Color.parseColor("#FF0000"));
		paint.setStrokeWidth(strokeWidth);
		canvas.drawCircle(center, center, innerCircle, paint);

	}

	public void drawText(Canvas canvas, int y) {
		int y1 = 0, y2 = 0;
		if (mPercent > 60) {
			y1 = mHeight / 2 + 15;
			y2 = mHeight / 2 + 50;
		} else if (mPercent >= 0 && mPercent < 20) {
			y1 = mHeight / 2 + 15;
			y2 = mHeight / 2 + 50;
		} else {
			y1 = y - 50;
			y2 = y + 40 - 50;
		}

		/// ========================画文字=====================
		String str = mPercent + "";
		// 画数字
		if (mPercent > 60) {
			mTextPaint.setColor(Color.parseColor("#FFFFFF"));
		} else {
			mTextPaint.setColor(Color.parseColor("#0000ff"));
		}

		mTextPaint.setTextSize(80);
		float txtLength = mTextPaint.measureText(str);
		canvas.drawText(str, mWidth / 2 - txtLength / 2 - 10, y1, mTextPaint);
		// 画百分号
		float txtLength1 = mTextPaint.measureText(str + "%");
		mTextPaint.setTextSize(40);
		canvas.drawText("%", mWidth / 2 + txtLength1 / 4, y1, mTextPaint);
		// 画及格率
		String content = "及格率";
		float txtLength_ = mTextPaint.measureText(content);
		mTextPaint.setTextSize(30);
		if (mPercent >= 80) {
			mTextPaint.setColor(Color.parseColor("#FFFFFF"));
		} else {

			mTextPaint.setColor(Color.parseColor("#000000"));
		}

		canvas.drawText(content, mWidth / 2 - txtLength_ / 3, y2, mTextPaint);
	}

	/* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
	public int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public void setPercent(int percent) {
		mPercent = percent;
	}

}
