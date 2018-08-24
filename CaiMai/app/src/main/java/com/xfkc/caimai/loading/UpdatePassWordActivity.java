package com.xfkc.caimai.loading;

import android.content.Intent;
import android.os.Bundle;
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

public class UpdatePassWordActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.password)
    EditText password_et;
    @Bind(R.id.login_password_edit)
    EditText loginPasswordEdit;
    @Bind(R.id.login_btn)
    StateButton loginBtn;

    private String password;
    private String again_password;
    private String verCode;
    private String phone;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        toolbarTitle.setText("重置密码");

        verCode = getIntent().getStringExtra("verCode");
        phone = getIntent().getStringExtra("phone");
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
        if (TextUtils.isEmpty(again_password) || !password.equals(again_password)) {
            ToastUtil.showToast("俩次密码不一致!");
            return;
        }

        PayFactory.getPayService()
                .updatePwd(phone, password, verCode)
                .compose(RxHelper.<EmptyBean>io_main())
                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
                    @Override
                    public void onNext(EmptyBean emptyBean) {
                        ToastUtil.showToast("密码修改成功");
                        finish();
                        setResult(1002,new Intent());
                    }
                });

    }

}
