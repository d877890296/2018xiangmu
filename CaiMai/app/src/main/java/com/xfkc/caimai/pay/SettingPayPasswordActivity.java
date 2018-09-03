package com.xfkc.caimai.pay;

import android.os.Bundle;
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

/**
 * 设置支付密码
 */
public class SettingPayPasswordActivity extends BaseActivity {

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
    @Bind(R.id.password)
    EditText password_et;
    @Bind(R.id.login_password_edit)
    EditText loginPasswordEdit;
    @Bind(R.id.login_btn)
    StateButton loginBtn;

    private String password;
    private String again_password;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_settingpay_password;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        toolbarTitle.setText("设置支付密码");

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.toolbar_left_img, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.login_btn:
                getForgetPassWord();
                break;
        }
    }

    /*提交修改密码*/
    public void getForgetPassWord() {
        password = password_et.getText().toString().trim();
        again_password = loginPasswordEdit.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast("密码不能为空!");
            return;
        }
        if (password.length() != 6 ){
            ToastUtil.showToast("密码长度不对!");
            return;
        }

        if (TextUtils.isEmpty(again_password) || !password.equals(again_password)) {
            ToastUtil.showToast("俩次密码不一致!");
            return;
        }


        request();

    }

    /**
     * 设置密码
     */
    private void request() {

        PayFactory.getPayService()
                .setPayPwd(password)
                .compose(RxHelper.<EmptyBean>io_main())
                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
                    @Override
                    public void onNext(EmptyBean emptyBean) {
                        ToastUtil.showToast("支付密码设置成功!");
                        finish();
                    }
                });

    }


}
