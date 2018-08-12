package com.xfkc.caimai.loading;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.ToastUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.customview.StateButton;

import butterknife.Bind;
import butterknife.OnClick;

public class ForgetPassWordActivity extends BaseActivity {

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

    private String key = "";
    private String sign = "";
    private String phone="";//手机号码
    private String password="";//用户密码
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_forget_pass_word;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("找回密码");
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

    }

    @Override
    protected void loadData() {
        //获取验证码加密的key
//        getAeskey();
    }


    @OnClick({R.id.toolbar_left_img, R.id.get_yanzheng_btn, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.get_yanzheng_btn:
                getVerificationCode();
                break;
            case R.id.login_btn:
                //提交修改密码
                getForgetPassWord();
                break;
        }
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

    /*获取验证码*/
    public void getVerificationCode() {
        try {
            phone = loginPhoneEdit.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                ToastUtil.showToast("手机号码不能为空");
                return;
            }
//            String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//            sign = Aes.aesEncrypt(date + phone, key);
//            PayFactory.getPayService()
//                    .getValidateCode(new GetValdateCodeApi("02", phone, date, StringUtils.replaceSpecialStr(sign)))
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
            getYanzhengBtn.setBackgroundColor(Color.GRAY);
            getYanzhengBtn.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            getYanzhengBtn.setBackgroundColor(Color.parseColor("#d51628"));
            getYanzhengBtn.setClickable(true);
            getYanzhengBtn.setEnabled(true);
            getYanzhengBtn.setText("获取验证码");
        }
    };

    /*提交修改密码*/
    public void getForgetPassWord() {
        phone = loginPhoneEdit.getText().toString().trim();
        password = loginPasswordEdit.getText().toString().trim();
        String msgcode = loginNumEdit.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("手机号码不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast("密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(msgcode)) {
            ToastUtil.showToast("验证码不能为空");
            return;
        }

//        UpdatePassword updatePassword=new UpdatePassword();
//        updatePassword.setCustMobile(phone);
//        updatePassword.setUserPwd(MD5Util.generatePassword(password));
//        updatePassword.setMsgCode(msgcode);
//        updatePassword.setPwdType("1");
//        PayFactory.getPayService()
//                .userUpdate(updatePassword)
//                .compose(RxHelper.<EmptyBean>io_main())
//                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
//            @Override
//            public void onNext(EmptyBean emptyBean) {
                ToastUtil.showToast("密码修改成功");
//                finish();
//            }
//        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
