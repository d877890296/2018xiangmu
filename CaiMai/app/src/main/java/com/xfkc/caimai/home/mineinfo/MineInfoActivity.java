package com.xfkc.caimai.home.mineinfo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 社员信息
 */
public class MineInfoActivity extends BaseActivity {


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
    @Bind(R.id.account_iv)
    CircleImageView accountIv;
    @Bind(R.id.title_youjiantou)
    ImageView titleYoujiantou;
    @Bind(R.id.person_title_image)
    RelativeLayout personTitleImage;
    @Bind(R.id.person_name)
    SuperTextView personName;
    @Bind(R.id.real_name)
    SuperTextView real_name_tv;
    @Bind(R.id.phone)
    SuperTextView phone;
    @Bind(R.id.person_regist_address)
    SuperTextView personRegistAddress;
    @Bind(R.id.get_goods_address)
    SuperTextView getGoodsAddress;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_mine_info;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("社员信息");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        toolbarRightText.setText("保存");
    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.toolbar_left_img, R.id.account_iv, R.id.person_title_image, R.id.person_name, R.id.real_name, R.id.phone, R.id.person_regist_address, R.id.get_goods_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
//            case R.id.account_iv://头像
//                break;
            case R.id.person_title_image://头像
                break;
            case R.id.person_name:
                break;
            case R.id.real_name:
                break;
            case R.id.phone:
                break;
            case R.id.person_regist_address:
                break;
            case R.id.get_goods_address:
                break;
            case R.id.toolbar_right_text:
                break;
        }
    }
}
