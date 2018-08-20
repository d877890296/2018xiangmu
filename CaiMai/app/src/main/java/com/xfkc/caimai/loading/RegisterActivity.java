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
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.util.Aes;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    private String key = "";
    private String sign = "";
    private String phone = "";//手机号码
    private String password = "";//用户密码

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        toolbarTitle.setText("注册");
        loginBtn.setFocusable(false);
        loginBtn.setClickable(false);
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
        //获取验证码加密key
//        getAeskey();
    }

    /*获取验证码加密key*/
    private void getAeskey() {
//        HashMap<String, String> map = new HashMap<>();
//        PayFactory.getPayService()
//                .getAESKey(map)
//                .compose(RxHelper.<GetAESKey>io_main())
//                .subscribe(new Subscriber<GetAESKey>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(GetAESKey getAESKey) {
//                        key = Aes.aesDecrypt(getAESKey.key);
//                    }
//                });
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
                //校验手机号码是否可以注册
                getValidateMobile();
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

    /*校验手机号码是否可以注册*/
    private void getValidateMobile() {
        phone = loginPhoneEdit.getText().toString().trim();
        password = loginPasswordEdit.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("手机号码不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast("密码不能为空");
            return;
        }
        if (password.length() >= 6 && password.length() <= 16) {
//            PayFactory.getPayService()
//                    .getValidateMobile(new GetValidateMobileApi(phone))
//                    .compose(RxHelper.<EmptyBean>io_main())
//                    .subscribe(new Subscriber<EmptyBean>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            ToastUtil.showToast("该手机号已注册", 0);
//                        }
//
//                        @Override
//                        public void onNext(EmptyBean emptyBean) {
//                            //用户注册
//                            userRegist(phone, password);
//                        }
//                    });
        } else {
            ToastUtil.showToast("密码长度不符");
        }

    }

    /*用户注册*/
    private void userRegist(String phone, String password) {
        String msgcode = loginNumEdit.getText().toString().trim();
        if (TextUtils.isEmpty(msgcode)) {
            ToastUtil.showToast("验证码不能为空");
            return;
        }
//        UserRegistApi userRegistApi = new UserRegistApi();
//        userRegistApi.setCustMobile(phone);
//        userRegistApi.setUserPwd(MD5Util.generatePassword(password));
//        userRegistApi.setMsgCode(msgcode);
//        PayFactory.getPayService()
//                .userRegist(userRegistApi)
//                .compose(RxHelper.<LoginInfoBean>io_main())
//                .subscribe(new ProgressSubscriber<LoginInfoBean>(this) {
//                    @Override
//                    public void onNext(LoginInfoBean userInfoBean) {
//                        SharedPrefUtil.put(mContext, SharedPref.USER_ID, userInfoBean.userId);
//                        SharedPrefUtil.put(mContext, SharedPref.TOKEN, userInfoBean.token);
//                        SharedPrefUtil.put(mContext, SharedPref.ISVIP, userInfoBean.isVip);
//                        ToastUtil.showToast("注册成功");
//                        finish();
//                        //展示注册成功对话框
                        showRegisterDialog();
//                    }
//                });
    }

    /*展示注册成功的对话框*/
    private void showRegisterDialog() {
            startActivity(new Intent(this,RegisterSuccessActivity.class));
    }

    /*获取验证码*/
    public void getVerificationCode() {
        try {
            phone = loginPhoneEdit.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                ToastUtil.showToast("手机号码不能为空");
                return;
            }
            String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            sign = Aes.aesEncrypt(date + phone, key);
//            PayFactory.getPayService()
//                    .getValidateCode(new GetValdateCodeApi("01", phone, date, StringUtils.replaceSpecialStr(sign)))
//                    .compose(RxHelper.<ValidateCode>io_main())
//                    .subscribe(new ProgressSubscriber<ValidateCode>(this) {
//                        @Override
//                        public void onNext(ValidateCode validateCode) {
//                            getYanzhengBtn.setClickable(false);
//                            timer.start();
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            super.onError(e);
//                            timer.cancel();
//                        }
//                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

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
