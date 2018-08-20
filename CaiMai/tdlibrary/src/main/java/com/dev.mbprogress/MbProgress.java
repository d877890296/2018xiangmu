package com.dev.mbprogress;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hyf.tdlibrary.R;

/***
 * 
 * 自定义dialog
 * 
 * @author Lyy
 * 
 */
@SuppressLint("InflateParams")
public class MbProgress {
	private Context context;
	private View contentView;
	private MbDialog mbDialog;
	private TextView mbpro_textview;

	private MbOnDismissListener mbOnDismissListener;
	private boolean isAddOnDismissListener;

	public MbOnDismissListener getMbOnDismissListener() {
		return mbOnDismissListener;
	}

	public void setMbOnDismissListener(MbOnDismissListener mbOnDismissListener) {
		this.mbOnDismissListener = mbOnDismissListener;
	}

	/***
	 * 
	 * 
	 * @param context
	 *            是否添加回到接口
	 * @param isAddOnDismissListener
	 */

	public MbProgress(Context context, boolean isAddOnDismissListener) {
		this.context = context;
		this.isAddOnDismissListener = isAddOnDismissListener;
		viewInit();
	}

	public void viewInit() {
	
			mbDialog = new MbDialog(context, R.style.lookmode, false);
			contentView = LayoutInflater.from(context).inflate(
					R.layout.mbprogress_dialogview, null);
			mbpro_textview = (TextView) contentView
					.findViewById(R.id.mbpro_textview);
			mbDialog.setOnDismissListener(onDismissListener);
		
	}

	private OnDismissListener onDismissListener = new OnDismissListener() {

		@Override
		public void onDismiss(DialogInterface dialog) {
			// TODO Auto-generated method stub
			if (isAddOnDismissListener == true) {
				mbOnDismissListener.onDismiss(dialog);
			}
		}
	};

	public void showMbDialog(String showStr) {
		mbpro_textview.setText(showStr);
		mbDialog.showDialog(contentView, 0, 0);
	}
	public void showMbDialog(String showStr,int textColor) {
		mbpro_textview.setText(showStr);
		mbpro_textview.setTextColor(textColor);
		mbDialog.showDialog(contentView, 0, 0);
	}
	public void dismiss() {
		mbDialog.dismiss();
	}

	public interface MbOnDismissListener {

		public void onDismiss(DialogInterface dialog);
	};
}
