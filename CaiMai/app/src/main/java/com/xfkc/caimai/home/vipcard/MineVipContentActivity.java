package com.xfkc.caimai.home.vipcard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.Bind;
import butterknife.OnClick;

public class MineVipContentActivity extends BaseActivity {


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
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.days)
    TextView days;
    @Bind(R.id.vip_card_no)
    TextView vipCardNo;
    @Bind(R.id.image_bg)
    AutoLinearLayout imageBg;


    //会员卡类别
    private int TYPE = 0;
    private String cardTypeName , remainDays;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_minevip_content;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("会员详情");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        cardTypeName = getIntent().getStringExtra("cardTypeName");
        remainDays =getIntent().getStringExtra("remainDays");
        TYPE =getIntent().getIntExtra("carTypeId",1);
        setVipCardType();
    }

    @Override
    protected void loadData() {



    }


    /*设置会员卡类别*/
    private void setVipCardType() {
        days.setText("剩余有效期:"+remainDays+"天");
        title.setText(cardTypeName);
        switch (TYPE) {
            case 1:
                imageBg.setBackgroundResource(R.mipmap.vip01);
                break;
            case 2:
                imageBg.setBackgroundResource(R.mipmap.vip03);
                break;
            case 3:
                imageBg.setBackgroundResource(R.mipmap.vip02);
                break;
        }

    }


    @OnClick(R.id.toolbar_left_img)
    public void onViewClicked() {
        finish();
    }
}
