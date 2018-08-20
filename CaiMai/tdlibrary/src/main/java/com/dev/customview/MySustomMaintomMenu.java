package com.dev.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * 自定义textview
 * 
 * @author XinYu
 * com.pull.list.custom.MySustomMaintomMenu
 */
public class MySustomMaintomMenu extends View implements Runnable {
	private Paint paint;
	private String musicNmae = "name";
	private int textX = this.getWidth() >> 6;

	public MySustomMaintomMenu(Context context) {
		super(context);
		init();

	}

	public MySustomMaintomMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void init() {
		paint = new Paint();
		paint.setColor(0xffffffff);
		paint.setTextSize(25);
		paint.setAntiAlias(true);
	
		new Thread(this).start();

	}

	@Override
	public void draw(Canvas canvas) {

		canvas.drawText(musicNmae, textX, this.getHeight() / 2 + 10, paint);
		
		this.postInvalidate();

	}

	public void setText(String musicNmae) {
		this.musicNmae = musicNmae;

	}

	/**
	 * 
	 * �����ַ�ĳߴ�
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
