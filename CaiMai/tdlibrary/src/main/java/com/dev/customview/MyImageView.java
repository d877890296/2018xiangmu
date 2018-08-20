package com.dev.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/***
 * 
 * 
 * 
 * 自定义圆形图片的类
 * 
 * 
 * @author Lyy
 *  com.dev.customview.MyImageView
 */
public class MyImageView extends ImageView {

	private Paint paint = new Paint();

	public MyImageView(Context context) {
		super(context);
	}

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Drawable drawable = getDrawable();
		try {

			if (null != drawable) {
				Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
				Bitmap b = toRoundCorner(bitmap,
						(getMeasuredWidth() + getMeasuredHeight()) / 4);

				final Rect rect = new Rect(0, 0, getMeasuredWidth(),
						getMeasuredHeight());
				paint.reset();
				canvas.drawBitmap(b, rect, rect, paint);

			} else {
				super.onDraw(canvas);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	
	private Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(getMeasuredWidth(),
				getMeasuredHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Rect rect = new Rect(0, 0, getMeasuredWidth(),
				getMeasuredHeight());
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		int x = getMeasuredWidth();
		canvas.drawCircle(x / 2, x / 2, x / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap,
				new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), rect,
				paint);
		return output;
	}

}