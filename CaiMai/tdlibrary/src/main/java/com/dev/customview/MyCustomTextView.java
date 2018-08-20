package com.dev.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * 自定义
 * 
 * @author XinYu
 * com.pull.list.custom.MyCustomTextView
 */
public class MyCustomTextView extends  View implements Runnable {
	private Paint paint;
	private String musicNmae = "在线学习";
	private int textX = this.getWidth() >> 6;
	private int size=20;

	public MyCustomTextView(Context context) {
		super(context);
		init();

	}

	public MyCustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void init() {
		paint = new Paint();
		paint.setColor(0xffffffff);
		paint.setTextSize(size);
		paint.setAntiAlias(true);
	
		new Thread(this).start();

	}

	@Override
	public void draw(Canvas canvas) {

		canvas.drawText(musicNmae, textX, this.getHeight() / 2 + 10, paint);
		this.postInvalidate();

	}
	public void setTextSize(int size){
		this.size=size;
		paint.setTextSize(size);
	}

	public void setText(String musicNmae) {
		this.musicNmae = musicNmae;

	}
	
	

	/**
	 * 
	 * 返回字符串的尺寸
	 * 
	 * @param str
	 */
	public Rect getTextSize(String str) {
		Rect rect = new Rect();
		paint.getTextBounds(str, 0, str.length(), rect);
		return rect;
	}

	public void run() {

		while (true) {
			textX -= 2;
			if (textX < -getTextSize(musicNmae).width()) {

				textX = this.getWidth() >> 6;
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}