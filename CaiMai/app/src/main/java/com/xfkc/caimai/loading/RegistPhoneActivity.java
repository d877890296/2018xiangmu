package com.xfkc.caimai.loading;

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
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.rx.activity.RxActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/***
 * 用户微信登录注册手机号
 */
public class RegistPhoneActivity extends RxActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
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
    @Bind(R.id.login_btn)
    StateButton loginBtn;


    private String key = "";
    private String sign = "";
    private String phone = "";//手机号码
    private String password = "";//用户密码
    private String openid = "", access_token = "";
//    private WXLoginBean wxLoginBean;//微信用户信息

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_regist_phone;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarLeftImg.setImageResource(R.mipmap.ic_launcher);
        toolbarTitle.setText("验证手机");

        openid = getIntent().getStringExtra("openid");
        access_token = getIntent().getStringExtra("access_token");

    }

    @Override
    protected void loadData() {
        //获取验证码加密key
//        getAeskey();

        //获取微信用户信息
//        getUserInfo();
    }

    /*获取微信用户信息*/
    private void getUserInfo() {
//     String  url= "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid;
//        OkGo.get(url)                            // 请求方式和请求url
//                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
//                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Gson gson = new Gson();
//                        wxLoginBean = gson.fromJson(s, WXLoginBean.class);
//                    }
//                });

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


    @OnClick({R.id.toolbar_left_img, R.id.get_yanzheng_btn, R.id.login_btn})
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
                //
                getValidateMobile();
                break;
        }
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
//            sign = Aes.aesEncrypt(date + phone, key);
//            PayFactory.getPayService()
//                    .getValidateCode(new GetValdateCodeApi("04", phone, date, StringUtils.replaceSpecialStr(sign)))
//                    .compose(RxHelper.<ValidateCode>io_main())
//                    .subscribe(new ProgressSubscriber<ValidateCode>(this) {
//                        @Override
//                        public void onNext(ValidateCode validateCode) {
//                            getYanzhengBtn.setClickable(false);
                            timer.start();
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

    /**/
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
            //用户注册
            userRegist(phone, password);
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
//        userRegistApi.code = openid;
//        userRegistApi.custMobile = phone;
//        userRegistApi.msgCode = msgcode;
//        userRegistApi.nickName = wxLoginBean.nickname;
//        userRegistApi.headFileId = wxLoginBean.headimgurl;
//        userRegistApi.userPwd = MD5Util.generatePassword(password);
//        PayFactory.getPayService().wxRegist(userRegistApi)
//                .compose(RxHelper.<LoginInfoBean>io_main())
//                .subscribe(new ProgressSubscriber<LoginInfoBean>(this) {
//                    @Override
//                    public void onNext(LoginInfoBean loginInfoBean) {
//                        SharedPrefUtil.put(mContext, SharedPref.TOKEN, loginInfoBean.token);
//                        SharedPrefUtil.put(mContext, SharedPref.ISVIP, loginInfoBean.isVip);
//                        backHistory(106, true, true, extraMap);
//                    }
//                });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
