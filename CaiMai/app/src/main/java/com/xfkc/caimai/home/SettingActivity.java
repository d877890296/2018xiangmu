package com.xfkc.caimai.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.loading.UpdatePassWordActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 设置
 */
public class SettingActivity extends BaseActivity {


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
    @Bind(R.id.update_password_tv)
    SuperTextView updatePasswordTv;
    @Bind(R.id.yjfk_text)
    SuperTextView yjfkText;
    @Bind(R.id.gy_mine_tv)
    SuperTextView gyMineTv;
    @Bind(R.id.use_xy_tv)
    SuperTextView useXyTv;
    @Bind(R.id.lxwm_tv)
    SuperTextView lxwmTv;
    @Bind(R.id.vertion_num)
    SuperTextView vertionNum;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("设置");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.toolbar_left_img, R.id.update_password_tv,
            R.id.yjfk_text, R.id.gy_mine_tv, R.id.use_xy_tv, R.id.lxwm_tv,
            R.id.vertion_num ,R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.update_password_tv:
                skip_classView(UpdatePassWordActivity.class,extraMap,false);
                break;
            case R.id.yjfk_text://意见反馈
                break;
            case R.id.gy_mine_tv://关于我们
                break;
            case R.id.use_xy_tv://用户协议
                break;
            case R.id.lxwm_tv://联系我们
                break;
            case R.id.vertion_num://版本号
                break;
            case R.id.commit://退出登录
                break;
        }
    }
}