package com.dev.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/***
 * 
 * @author lyy
 * 
 *         圆角矩形的imageView
 * 
 *         com.dev.customview.RoundRectImageView
 *
 */
public class RoundRectImageView extends ImageView {
	public int cornerRadius = 5;
	public int margin = 0;
	private Paint paint = new Paint();

	public RoundRectImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public RoundRectImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public void setRoundedBitmapDisplayer(int cornerRadiusPixels, int marginPixels) {
		this.cornerRadius = cornerRadiusPixels;
		this.margin = marginPixels;
		this.postInvalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Drawable drawable = getDrawable();

		try {
			if (null != drawable) {
				Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
				Bitmap b = toRoundRect(bitmap,cornerRadius);
				final Rect rect = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
				paint.reset();
				canvas.drawBitmap(b, rect, rect, paint);

			} else {
				super.onDraw(canvas);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private Bitmap toRoundRect(Bitmap bitmap, int cornerRadius) {
		Bitmap output = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		// 画圆
		// final Rect rect = new Rect(0, 0, getMeasuredWidth(),
		// getMeasuredHeight());
		// int x = getMeasuredWidth();
		// canvas.drawCircle(x / 2, x / 2, x / 2, paint);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		// 画矩形
		RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
		canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), rectF, paint);
		return output;
	}

}
