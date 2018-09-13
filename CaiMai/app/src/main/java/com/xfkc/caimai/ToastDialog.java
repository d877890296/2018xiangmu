package com.xfkc.caimai;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

/**
 * Created by huangrui on 2017-11-2.
 */


public class ToastDialog extends Dialog {

        private TextView tvLoadInfo;
        private boolean isAllowClose = true;

        public ToastDialog(Context context) {
            this(context, R.style.NoFrameNoDim_Dialog);
        }

        private ToastDialog(Context context, int theme) {
            super(context, theme);
            init();
        }

        private void init() {
            setContentView(R.layout.dialog_login_toast);
            tvLoadInfo = (TextView) findViewById(R.id.dialogToast_tvLoadInfo);
            setCanceledOnTouchOutside(false);
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        /**
         * 设置加载消息内容
         *
         * @param msg
         */
        public ToastDialog setMsg(String msg) {
            tvLoadInfo.setText(msg);
            return this;
        }

        /**
         * 是否允许关闭对话框
         *
         * @param isAllowClose
         * @return
         */
        public ToastDialog setIsAllowClose(boolean isAllowClose) {
            setCanceledOnTouchOutside(isAllowClose);
            this.isAllowClose = isAllowClose;
            return this;
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (!isAllowClose) {
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
    }

