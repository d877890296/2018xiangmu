package com.xfkc.caimai.loading;

import android.content.Intent;
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
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;

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
                String verCode = loginNumEdit.getText().toString();
                extraMap.put("verCode",verCode);
                extraMap.put("phone",phone);
                skip_classView(UpdatePassWordActivity.class,extraMap,false,1001);
                break;
        }
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
            getYanzhengBtn.setBackgroundColor(Color.GRAY);
            getYanzhengBtn.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            getYanzhengBtn.setBackgroundColor(Color.parseColor("#ff704d"));
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001 && resultCode == 1002) {
            finish();
        }
    }
}
