package com.xfkc.caimai.loading;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.customview.StateButton;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterSuccessActivity extends BaseActivity {


    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar_right_text)
    TextView toolbarRightText;
    @Bind(R.id.login_btn)
    StateButton loginBtn;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register_success;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("注册登录");
        toolbarRightText.setText("取消");


    }

    @Override
    protected void loadData() {

    }



    @OnClick({R.id.toolbar_right_text, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_right_text:
                finish();
                break;
            case R.id.login_btn:

                break;
        }
    }
}
