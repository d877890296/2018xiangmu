package com.xfkc.caimai.loading;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.xfkc.caimai.MainActivity;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.LoginInfo;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 登录
 */
public class LoadingActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.login_phone_edit)
    EditText loginPhoneEdit;
    @Bind(R.id.login_num_edit)
    EditText loginNumEdit;
    @Bind(R.id.login_password_edit)
    EditText loginPasswordEdit;
    @Bind(R.id.login_btn)
    StateButton loginBtn;
    @Bind(R.id.forget_password_text)
    TextView forgetPasswordText;
    @Bind(R.id.relative_pwd)
    RelativeLayout relativePwd;
    @Bind(R.id.wx_tv)
    TextView wxTv;
    @Bind(R.id.toolbar_right_text)
    TextView toolbarRightText;

    @Override
    protected int getLayoutResource() {
        return R.layout.loading_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        toolbarTitle.setText("登录");
        toolbarRightText.setText("注册");
    }

    @Override
    protected void loadData() {

    }



    @OnClick({R.id.toolbar_left_img, R.id.login_btn, R.id.toolbar_right_text, R.id.forget_password_text, R.id.wx_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.login_btn:
                login();
                break;
            case R.id.toolbar_right_text:
//            case R.id.register_text://注册
                startActivity(new Intent(LoadingActivity.this, RegisterActivity.class));
                break;
            case R.id.forget_password_text://忘记密码
                startActivity(new Intent(LoadingActivity.this, ForgetPassWordActivity.class));
                break;
            case R.id.wx_tv:
                break;
        }
    }


    /*登录信息*/
    private void login() {
        String phone = loginPhoneEdit.getText().toString().trim();
        String password = loginPasswordEdit.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("登录名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast("登录密码不能为空");
            return;
        } else if (password.length() < 6) {
            ToastUtil.showToast("登录密码为6-16位数字、字母");
            return;
        }
        //登陆请求
        request(phone, password);
    }

    /*登陆请求*/
    private void request(String phone, String password) {

        PayFactory.getPayService()
                .login(phone,password)
                .compose(RxHelper.<LoginInfo>io_main())
                .subscribe(new ProgressSubscriber<LoginInfo>(this) {
                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        SharedPrefUtil.put(mContext, SharedPref.TOKEN,loginInfo.data);
                        skip_classView(MainActivity.class,extraMap,true);
                    }
                });
    }



}
