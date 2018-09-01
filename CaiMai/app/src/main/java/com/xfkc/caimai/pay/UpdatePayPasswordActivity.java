package com.xfkc.caimai.pay;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.ToastUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;

import butterknife.Bind;
import butterknife.OnClick;

/***
 *    修改支付密码
 */

public class UpdatePayPasswordActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar_title_image)
    ImageView toolbarTitleImage;
    @Bind(R.id.toolbar_right_text)
    TextView toolbarRightText;
    @Bind(R.id.toolbar_right_img)
    ImageView toolbarRightImg;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.login_phone_edit)
    EditText loginPhoneEdit;
    @Bind(R.id.login_num_edit)
    EditText loginNumEdit;
    @Bind(R.id.get_yanzheng_btn)
    StateButton getYanzhengBtn;
    @Bind(R.id.login_password_edit)
    EditText loginPasswordEdit;
    @Bind(R.id.again_password_edit)
    EditText againPasswordEdit;
    @Bind(R.id.commit)
    StateButton commit;

    private String phone = "";//手机号码
    private String password = "";//用户密码
    private String msgcode;
    private String again_password;

    private String payPwd = "";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_update_pay_password;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        toolbarTitle.setText("注册");
    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.toolbar_left_img, R.id.get_yanzheng_btn, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.get_yanzheng_btn:
                //获取验证码
                getVerificationCode();
                break;
            case R.id.commit:
                //注册
                setUpdatePay();
                break;
        }
    }

    private void setUpdatePay() {
        phone = loginPhoneEdit.getText().toString().trim();
        password = loginPasswordEdit.getText().toString().trim();
        msgcode = loginNumEdit.getText().toString().trim();
        again_password = againPasswordEdit.getText().toString().trim();

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

        if (password.length() == 6) {
            userRegistData();//注册数据提交
        } else {
            ToastUtil.showToast("密码长度不符");
        }
    }


    /*提交注册数据*/
    private void userRegistData() {
        PayFactory.getPayService()
                .updatePayPwd(phone, msgcode, password)
                .compose(RxHelper.<EmptyBean>io_main())
                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
                    @Override
                    public void onNext(EmptyBean userInfoBean) {

                        ToastUtil.showToast("支付密码修改成功！");
                        finish();
                    }
                });

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
