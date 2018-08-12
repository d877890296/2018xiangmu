package com.xfkc.caimai.loading;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.ToastUtil;
import com.xfkc.caimai.MainActivity;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.customview.StateButton;

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String code = intent.getStringExtra("code");
//        获取用户信息
//        getUserInfo(code);
    }

    /*获取用户信息*/
    private void getUserInfo(String code) {
//        String url = wx_url + code;
//        OkGo.get(url)                            // 请求方式和请求url
//                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
//                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Gson gson = new Gson();
//                        WXLoginBean wxLoginBean = gson.fromJson(s, WXLoginBean.class);
//                        getToken(wxLoginBean);
//                    }
//                });

    }

    /*请求获取本地*/
//    private void getToken(final WXLoginBean wxLoginBean) {
//        GetAllId getAllId = new GetAllId();
//        getAllId.code = wxLoginBean.openid;
//        PayFactory.getPayService().wxCodeValidate(getAllId)
//                .compose(RxHelper.<LoginInfoBean>io_main())
//                .subscribe(new ProgressSubscriber<LoginInfoBean>(this) {
//                    @Override
//                    public void onNext(LoginInfoBean loginInfoBean) {
//                        SharedPrefUtil.put(mContext, SharedPref.USER_ID, loginInfoBean.userId);
//                        SharedPrefUtil.put(mContext, SharedPref.TOKEN, loginInfoBean.token);
//                        SharedPrefUtil.put(mContext, SharedPref.ISVIP, loginInfoBean.isVip);
//                        SharedPrefUtil.put(mContext, SharedPref.CUST_NAME, loginInfoBean.name);
//                        SharedPrefUtil.put(mContext, SharedPref.CUST_MOBILE, loginInfoBean.userPhone);
//                        backHistory(104, true, true, extraMap);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        dismissProgressDialog();
//                        ApiException apiException = (ApiException) e;
//                        if (apiException.getErrorCode().equals("888887")) {
//                            startActivityForResult(new Intent(LoadingActivity.this, RegistPhoneActivity.class)
//                                    .putExtra("openid", wxLoginBean.openid)
//                                    .putExtra("access_token", wxLoginBean.access_token), 107);
//                        } else {
//                            super.onError(e);
//                        }
//
//                    }
//                });
//    }

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
                getWXLoad();
                break;
        }
    }

    /*获取登陆请求*/
    private void getWXLoad() {
//        ShareRequst.getInstance().register(this);
//        ShareRequst.getInstance().LoadWX();
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
            ToastUtil.showToast("登录密码为6-20位数字、字母");
            return;
        }
        //登陆请求
        request(phone, password);
    }

    /*登陆请求*/
    private void request(String phone, String password) {

//        PayFactory.getPayService()
//                .getLogin(new LoginApi(phone, MD5Util.generatePassword(password)))
//                .compose(RxHelper.<LoginInfoBean>io_main())
//                .subscribe(new ProgressSubscriber<LoginInfoBean>(this) {
//                    @Override
//                    public void onNext(LoginInfoBean userInfoBean) {
//                        SharedPrefUtil.put(mContext, SharedPref.USER_ID, userInfoBean.userId);
//                        SharedPrefUtil.put(mContext, SharedPref.TOKEN, userInfoBean.token);
//                        SharedPrefUtil.put(mContext, SharedPref.ISVIP, userInfoBean.isVip);
//                        SharedPrefUtil.put(mContext, SharedPref.CUST_NAME, userInfoBean.name);
//                        SharedPrefUtil.put(mContext, SharedPref.CUST_MOBILE, userInfoBean.userPhone);
//                        backHistory(104, true, true, extraMap);
//                    }
//
//                });
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 107 && resultCode == 106) {
            backHistory(104, true, true, extraMap);
        }
    }


}
