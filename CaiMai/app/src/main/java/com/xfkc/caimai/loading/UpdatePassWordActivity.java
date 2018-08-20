package com.xfkc.caimai.loading;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.customview.StateButton;

import butterknife.Bind;
import butterknife.OnClick;

public class UpdatePassWordActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login_password_edit)
    EditText loginPasswordEdit;
    @Bind(R.id.login_btn)
    StateButton loginBtn;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        toolbarTitle.setText("重置密码");
    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.toolbar_left_img, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
            case R.id.login_btn:
                finish();
                break;
        }
    }
}
