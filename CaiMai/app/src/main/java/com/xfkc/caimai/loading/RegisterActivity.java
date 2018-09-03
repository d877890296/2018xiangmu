package com.xfkc.caimai.loading;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.ToastUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.bean.RegistBean;
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;

import butterknife.Bind;
import butterknife.OnClick;

/***
 * 注册页面
 */
public class RegisterActivity extends BaseActivity {

    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.login_phone_edit)
    EditText loginPhoneEdit;
    @Bind(R.id.login_num_edit)
    EditText loginNumEdit;
    @Bind(R.id.get_yanzheng_btn)
    StateButton getYanzhengBtn;
    @Bind(R.id.login_password_edit)
    EditText loginPasswordEdit;
    @Bind(R.id.login_btn)
    StateButton loginBtn;
    @Bind(R.id.agree_checkbox)
    CheckBox agreeCheckbox;
    @Bind(R.id.fwtk_tv)
    TextView fwtkTv;
    @Bind(R.id.privacy_tv)
    TextView privacyTv;
    @Bind(R.id.have_tv)
    TextView haveTv;
    @Bind(R.id.again_password_edit)
    EditText againPasswordEdit;
    @Bind(R.id.yaoqing_edit)
    EditText yaoqingEdit;

    private String phone = "";//手机号码
    private String password = "";//用户密码
    private String msgcode;
    private String again_password;
    private String yq_code;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        toolbarTitle.setText("注册");
//        loginBtn.setFocusable(false);
//        loginBtn.setClickable(false);
        agreeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    loginBtn.setNormalBackgroundColor(Color.RED);
                    loginBtn.setClickable(true);
                    loginBtn.setFocusable(true);
                } else {
                    loginBtn.setNormalBackgroundColor(Color.parseColor("#D9D9D9"));
                    loginBtn.setFocusable(false);
                    loginBtn.setClickable(false);
                }
            }
        });

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.toolbar_left_img, R.id.get_yanzheng_btn, R.id.login_btn, R.id.fwtk_tv, R.id.privacy_tv, R.id.have_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.get_yanzheng_btn:
                //获取验证码
                getVerificationCode();
                break;
            case R.id.login_btn:
                //注册
                userRegist();
                break;
            case R.id.fwtk_tv:
//                startActivity(new Intent(mContext, TDWebViewActivity.class)
//                        .putExtra(Constant.WEB_URL,Constant.FWTK_URL));
                break;
            case R.id.privacy_tv:
//                startActivity(new Intent(mContext, TDWebViewActivity.class)
//                        .putExtra(Constant.WEB_URL,Constant.YSXGZC_URL));
                break;
            case R.id.have_tv:
                finish();
                break;
        }
    }


    /*用户注册*/
    private void userRegist() {
        phone = loginPhoneEdit.getText().toString().trim();
        password = loginPasswordEdit.getText().toString().trim();
        msgcode = loginNumEdit.getText().toString().trim();
        again_password = againPasswordEdit.getText().toString().trim();
        yq_code = yaoqingEdit.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("手机号码不能为空!");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast("密码不能为空!");
            return;
        }
        if (TextUtils.isEmpty(msgcode)) {
            ToastUtil.showToast("验证码不能为空!");
            return;
        }
        if (TextUtils.isEmpty(again_password) || !again_password.equals(password)) {
            ToastUtil.showToast("俩次输入密码不符!");
            return;
        }
        if (TextUtils.isEmpty(yq_code)) {
            ToastUtil.showToast("邀请码不能为空!");
            return;
        }

        if (password.length() >= 6 && password.length() <= 16) {
            userRegistData();//注册数据提交
        } else {
            ToastUtil.showToast("密码长度不符");
        }
    }

    /*注册数据提交*/
    private void userRegistData() {
        PayFactory.getPayService()
                .registerInfo(phone,msgcode,password,yq_code)
                .compose(RxHelper.<RegistBean>io_main())
                .subscribe(new ProgressSubscriber<RegistBean>(this) {
                    @Override
                    public void onNext(RegistBean registBean) {
//                        //展示注册成功对话框
                        showRegisterDialog(registBean);
                    }
                });

    }

    /*展示注册成功的对话框*/
    private void showRegisterDialog(RegistBean registBean) {
        startActivity(new Intent(this, RegisterSuccessActivity.class).putExtra("phone",registBean.data.phone));
        finish();
    }

    /*获取验证码*/
    public void getVerificationCode() {
        phone = loginPhoneEdit.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("手机号码不能为空");
            return;
        }
        PayFactory.getPayService()
                .getSmsCode(phone)
                .compose(RxHelper.<EmptyBean>io_main())
                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
                    @Override
                    public void onNext(EmptyBean emptyBean) {
                        getYanzhengBtn.setClickable(false);
                        timer.start();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        timer.cancel();
                    }
                });

    }

    /*倒计时*/
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            getYanzhengBtn.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            getYanzhengBtn.setClickable(true);
            getYanzhengBtn.setEnabled(true);
            getYanzhengBtn.setText("获取验证码");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
