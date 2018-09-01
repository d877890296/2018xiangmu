package com.goods.mineinfo;

import com.dev.mbprogress.MbDialog;
import com.dev.mbprogress.MbDialog.DIALOG_GRAVITY;
import com.xfkc.caimai.R;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class DeleteView {
	private MbDialog mb;
	private Activity mContext;
	private Handler handler;
	private View rootView;
	private String troubleID;
	private int type;
	private TextView tip_textView, dcd_select_canncel, dcd_select_sure;

	public DeleteView(Activity mContext, Handler handler) {
		this.mContext = mContext;
		this.handler = handler;
		viewInit();
	}

	public void viewInit() {
		mb = new MbDialog(mContext, R.style.lookmode, false);
		mb.setDialogGravity(DIALOG_GRAVITY.CENTER);
		rootView = LayoutInflater.from(mContext).inflate(R.layout.gd_tabledetails_delete, null);
		tip_textView = (TextView) rootView.findViewById(R.id.tip_textView);

		dcd_select_canncel = (TextView) rootView.findViewById(R.id.dcd_select_canncel);
		dcd_select_sure = (TextView) rootView.findViewById(R.id.dcd_select_sure);

		dcd_select_canncel.setOnClickListener(onClickListener);
		dcd_select_sure.setOnClickListener(onClickListener);

	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.dcd_select_canncel: {
				mb.dismiss();
			
			}
				break;
			case R.id.dcd_select_sure:
				Message msg = new Message();
				msg.obj = troubleID;
				if (type == 4) {
					msg.what = 1006;
				} else if (type == 7) {
					msg.what = 1007;
				} else {
					msg.what = 1005;
				}

				handler.sendMessage(msg);
				mb.dismiss();
				break;

			default:
				break;
			}
		}

	};

	public void showDialog(String troubleID, int type) {
		this.type = type;
		this.troubleID = troubleID;
		if (type == 0) {
			tip_textView.setText("删除后无法恢复，确定删除？");
		} else if (type == 2) {

			tip_textView.setText("你将消耗30积分，你确定兑换码？");
		} else if (type == 4) {
			tip_textView.setText("您确定要提交吗？");
		} else if (type == 5) {
			tip_textView.setText("确认清除缓存？");
		} else if (type == 6) {
			tip_textView.setText("订单号已生成,确定付款？");
		} else if (type == 7) {
			tip_textView.setText("去意已决、留下看看");
		} else {
			tip_textView.setText("您确定要删除这条日志信息吗？");
		}
		mb.showDialog(rootView, 0, 0);
	}

}
