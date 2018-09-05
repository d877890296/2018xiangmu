package com.xfkc.caimai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dev.customview.MyToast;
import com.goods.order.SureOrderActivity;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;

import java.util.ArrayList;

/**
 * 提示消息对话框
 * 
 * @author Administrator
 *
 */
public class ShowPassWordDialog {

	private ArrayList<EditText> mEditList;
	private BaseActivity context;
	boolean b = true;
	/**
	 * 未识别到相应信息   提示继续扫描   或  联系管理员  获取验证码
	 * @param context
	 */
	public ShowPassWordDialog(BaseActivity context) {
		this.context = context;
		mEditList = new ArrayList<EditText>();
	}


	/**
	 * 显示对话框 验证码
	 * 	 
	 * @param type
	 * @param context
	 */
	private EditText editText0, editText1, editText2, editText3, editText4, editText5;

	public void showTimeDialog02(final SureOrderActivity context, final double price, final int kangbiyue  ) {
			final Dialog dialog = new Dialog(context, R.style.BottomDialog);
			View contentView = LayoutInflater.from(context).inflate(R.layout.verification_code_layout, null);


		editText0 = (EditText) contentView.findViewById(R.id.ed_code0);
		editText1 = (EditText) contentView.findViewById(R.id.ed_code1);
		editText2 = (EditText) contentView.findViewById(R.id.ed_code2);
		editText3 = (EditText) contentView.findViewById(R.id.ed_code3);
		editText4 = (EditText) contentView.findViewById(R.id.ed_code4);
		editText5 = (EditText) contentView.findViewById(R.id.ed_code5);

		TextView cancle = (TextView) contentView.findViewById(R.id.cancle);
		TextView commit = (TextView) contentView.findViewById(R.id.commit);
		TextView price_tv = (TextView) contentView.findViewById(R.id.price);
		TextView kbye_tv = (TextView) contentView.findViewById(R.id.kbye_tv);

		price_tv.setText(price+"康币");
		kbye_tv.setText("康币余额: "+kangbiyue);

		// 获取输入框内容
		int one = editText0.getText().toString().replace(" ", "").length();
		int Two = editText1.getText().toString().replace(" ", "").length();
		int Three = editText2.getText().toString().replace(" ", "").length();
		int Four = editText3.getText().toString().replace(" ", "").length();
		int Five = editText4.getText().toString().replace(" ", "").length();
		int Six = editText5.getText().toString().replace(" ", "").length();
		// 输入框内容都为0时，默认焦点在第一个
		if (one == 0 && Two == 0 && Three == 0 && Four == 0 && Five == 0 && Six == 0) {
//			Logger.e("lengh=======", "------空空空-----");
			editText0.setFocusable(true);
			editText1.setFocusable(false);
			editText2.setFocusable(false);
			editText3.setFocusable(false);
			editText4.setFocusable(false);
			editText5.setFocusable(false);
		}
		// 为输入框设置输入监听
		editText0.addTextChangedListener(mTextWatcher);
		editText1.addTextChangedListener(mTextWatcher);
		editText2.addTextChangedListener(mTextWatcher);
		editText3.addTextChangedListener(mTextWatcher);
		editText4.addTextChangedListener(mTextWatcher);
		editText5.addTextChangedListener(mTextWatcher);
		// 为输入框设置删除监听
		editText0.setOnKeyListener(onKeyListener);
		editText1.setOnKeyListener(onKeyListener);
		editText2.setOnKeyListener(onKeyListener);
		editText3.setOnKeyListener(onKeyListener);
		editText4.setOnKeyListener(onKeyListener);
		editText5.setOnKeyListener(onKeyListener);

		cancle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});


		commit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String code = getEditNumber();

				if (Tools.IsEmpty(code)) {
					MyToast.showMyToast(context, "密码不能为空", 0);
				} else {
                    context.payOrder(code);
				}
				dialog.dismiss();
			}
		});


			dialog.setContentView(contentView);
			ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
			layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
			contentView.setLayoutParams(layoutParams);
			dialog.getWindow().setGravity(Gravity.CENTER);
			dialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
			dialog.show();

//		LayoutInflater inflater = context.getLayoutInflater();
//		View view = inflater.inflate(R.layout.verification_code_layout, null);
//		// 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
//		AlertDialog.Builder builder = new AlertDialog.Builder(context);
//		builder.setCancelable(false);
//		// 设置Title的内容
//		builder.setTitle("请输入验证码");
//		// 设置Content来显示一个信息
//		builder.setView(view);
		// 获取对话框上输入框



//		// 设置一个PositiveButton
//		builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//			}
//		});
//		// 显示出该对话框
//		builder.show();
	}

	// 这里的mTextWatcher就是输入文字变化监听器了，实现如下：
	private TextWatcher mTextWatcher = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable mEditable) {
			// 判断输入框输入前后状态，改变焦点值
			if (mEditable.toString().length() == 1) {
				if (editText0.isFocused()) {
					editText0.setFocusable(false);
					editText1.requestFocus();
				} else if (editText1.isFocused()) {
					editText1.setFocusable(false);
					editText2.requestFocus();
				} else if (editText2.isFocused()) {
					editText2.setFocusable(false);
					editText3.requestFocus();
				} else if (editText3.isFocused()) {
					editText3.setFocusable(false);
					editText4.requestFocus();
				} else if (editText4.isFocused()) {
					editText4.setFocusable(false);
					editText5.requestFocus();
				} else if (editText5.isFocused()) {
					InputMethodManager imm = (InputMethodManager) context
							.getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(editText5.getWindowToken(), 0);
				}
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// 输入之后状态的改变 前一个输入完，焦点向后一个移动
			if (s.length() == 1) {
				if (editText0.isFocusable()) {
					editText1.setFocusable(true);
					editText1.setFocusableInTouchMode(true);
				} else if (editText1.isFocusable()) {
					editText2.setFocusable(true);
					editText2.setFocusableInTouchMode(true);
				} else if (editText2.isFocusable()) {
					editText3.setFocusable(true);
					editText3.setFocusableInTouchMode(true);
				} else if (editText3.isFocusable()) {
					editText4.setFocusable(true);
					editText4.setFocusableInTouchMode(true);
				} else if (editText4.isFocusable()) {
					editText5.setFocusable(true);
					editText5.setFocusableInTouchMode(true);
				}
			}
		}
	};

	/**
	 * 获取输入框的内容
	 * 
	 * @return
	 */
	public String getEditNumber() {
		String number = editText0.getText().toString();
		number += editText1.getText().toString();
		number += editText2.getText().toString();
		number += editText3.getText().toString();
		number += editText4.getText().toString();
		number += editText5.getText().toString();
		return number;
	}

	private OnKeyListener onKeyListener = new OnKeyListener() {

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub

			if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
				if (keyCode == KeyEvent.KEYCODE_DEL) {
					if (editText5.isFocused()) {
						if (!editText5.getText().toString().equals("")) {
							editText5.getText().clear();
							editText5.requestFocus();
							b = false;
						} else if (!b) {
							editText5.clearFocus();
							editText5.setFocusable(false);
							editText4.setFocusableInTouchMode(true);
							editText4.getText().clear();
							editText4.requestFocus();
							b = true;
						} else {
							editText5.getText().clear();
							editText5.requestFocus();
							b = false;
						}
					} else if (editText4.isFocused()) {
						editText4.clearFocus();
						editText4.setFocusable(false);
						editText3.setFocusableInTouchMode(true);
						editText3.getText().clear();
						editText3.requestFocus();
					} else if (editText3.isFocused()) {
						editText3.clearFocus();
						editText3.setFocusable(false);
						editText2.setFocusableInTouchMode(true);
						editText2.getText().clear();
						editText2.requestFocus();
					} else if (editText2.isFocused()) {
						editText2.clearFocus();
						editText2.setFocusable(false);
						editText1.setFocusableInTouchMode(true);
						editText1.getText().clear();
						editText1.requestFocus();
					} else if (editText1.isFocused()) {
						editText1.clearFocus();
						editText1.setFocusable(false);
						editText0.setFocusableInTouchMode(true);
						editText0.getText().clear();
						editText0.requestFocus();
					}
				}
				return true;
			}
			return false;
		}
	};

}
