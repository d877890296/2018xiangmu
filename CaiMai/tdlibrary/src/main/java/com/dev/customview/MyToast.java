package com.dev.customview;



import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyf.tdlibrary.R;

/***
 * 
 * 自定义toast
 * 
 * @author Lyy
 * 
 */
public class MyToast {
	private static Toast myToast, myToast2;
	private static View view, view2;
	private static TextView textView, textView_state_type, textView_currentTime, textView_totaltTime;
	private static LinearLayout liner_toast_type1, liner_toast_type2;
	private static ImageView imageView;

	public static void showMyToast(Context context, String content, int gravityStyle) {
		if (myToast == null) {
			myToast = new Toast(context);
			// 加载一个View
			view = LayoutInflater.from(context).inflate(R.layout.my_toast, null);
			textView = (TextView) view.findViewById(R.id.textView_state);
			textView_state_type = (TextView) view.findViewById(R.id.textView_state_type);
			liner_toast_type1 = (LinearLayout) view.findViewById(R.id.liner_toast_type1);
			liner_toast_type2 = (LinearLayout) view.findViewById(R.id.liner_toast_type2);
			// 设置到Toast中
			setContent(gravityStyle, content);

		} else {
			// 设置到Toast中
			setContent(gravityStyle, content);
		}
		myToast.show();

	}

	public static void showMyToast2(Context context, String content, String content2, int gravityStyle,
			int leftOrRight) {
		if (myToast2 == null) {
			myToast2 = new Toast(context);
			// 加载一个View
			view2 = LayoutInflater.from(context).inflate(R.layout.my_toast2, null);
			textView_currentTime = (TextView) view2.findViewById(R.id.textView_currentTime);
			textView_totaltTime = (TextView) view2.findViewById(R.id.textView_totaltTime);
			imageView = (ImageView) view2.findViewById(R.id.imageView);
			// 设置到Toast中
			setContent2(gravityStyle, content, content2, leftOrRight);

		} else {
			// 设置到Toast中
			setContent2(gravityStyle, content, content2, leftOrRight);
		}
		myToast2.show();

	}

	public static void setContent(int gravityStyle, String content) {
		myToast.setView(view);
	
		if (gravityStyle == 0) {
			textView.setText(content);
			textView.setVisibility(View.VISIBLE);
			liner_toast_type1.setVisibility(View.VISIBLE);
			textView_state_type.setVisibility(View.GONE);
			liner_toast_type2.setVisibility(View.GONE);
			myToast.setGravity(Gravity.TOP, 0, 70);
		} else if (gravityStyle == 1) {
			textView.setVisibility(View.GONE);
			liner_toast_type1.setVisibility(View.GONE);
			textView_state_type.setVisibility(View.VISIBLE);
			liner_toast_type2.setVisibility(View.VISIBLE);
			myToast.setGravity(Gravity.CENTER, 0, 0);
			textView_state_type.setText(content);
		} else if (gravityStyle == -1) {
			textView.setVisibility(View.GONE);
			liner_toast_type1.setVisibility(View.GONE);
			textView_state_type.setVisibility(View.VISIBLE);
			liner_toast_type2.setVisibility(View.VISIBLE);
			myToast.setGravity(Gravity.CENTER, 0, 0);
			textView_state_type.setTextSize(15);
			textView_state_type.setText(content);
		} else if (gravityStyle == -2) {
			textView.setVisibility(View.GONE);
			liner_toast_type1.setVisibility(View.GONE);
			textView_state_type.setVisibility(View.VISIBLE);
			liner_toast_type2.setVisibility(View.VISIBLE);
			myToast.setGravity(Gravity.CENTER, 0, 0);
			textView_state_type.setTextSize(30);
			textView_state_type.setText(content);
		} else {
			textView.setText(content);
			textView.setVisibility(View.VISIBLE);
			liner_toast_type1.setVisibility(View.VISIBLE);
			textView_state_type.setVisibility(View.GONE);
			liner_toast_type2.setVisibility(View.GONE);
			myToast.setGravity(Gravity.BOTTOM, 0, 0);
		}
		myToast.setDuration(Toast.LENGTH_SHORT);
	}

	public static void setContent2(int gravityStyle, String content, String content2, int leftOrRight) {
		myToast2.setView(view2);
		if (gravityStyle == 2) {
			if (leftOrRight == 0) {
				imageView.setImageResource(R.drawable.video_pre);
			} else {
				imageView.setImageResource(R.drawable.icon_progressleft);
			}
			myToast2.setGravity(Gravity.CENTER, 0, 0);

			textView_currentTime.setText(content);
			textView_currentTime.setTextSize(20);
			textView_totaltTime.setText("/" + content2);
			textView_totaltTime.setTextSize(20);
		} else {
			textView_state_type.setText(content);
			myToast2.setGravity(Gravity.BOTTOM, 0, 0);
		}
		myToast2.setDuration(Toast.LENGTH_SHORT);
	}
}
