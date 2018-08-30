package com.xfkc.caimai.home.vipcard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.customview.StateButton;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class VipContentActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
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
    @Bind(R.id.weixin_rb)
    RadioButton weixinRb;
    @Bind(R.id.zhifubao_rb)
    RadioButton zhifubaoRb;
    @Bind(R.id.pay_btn)
    StateButton payBtn;
    //选择支付方式集合
    private ArrayList<RadioButton> list_radio = new ArrayList<>();
    //支付方式 0微信  1支付宝
    private int PAY_WAY = 0;
    //会员卡类别
    private int TYPE = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_vip_content;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("会员详情");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        list_radio.add(weixinRb);
        list_radio.add(zhifubaoRb);

        TYPE = getIntent().getIntExtra("type", 0);
        setVipCardType();
    }

    /*设置会员卡类别*/
    private void setVipCardType() {
        switch (TYPE) {
            case 0:
                days.setText("有效期: 365天");
                title.setText("初级会员");
                price.setText("￥36.5");
                imageBg.setBackgroundResource(R.mipmap.vip01);
                break;
            case 1:
                days.setText("有效期: 365天");
                title.setText("中级会员");
                price.setText("￥365");
                imageBg.setBackgroundResource(R.mipmap.vip03);
                break;
            case 2:
                days.setText("有效期: 365天");
                title.setText("高级会员");
                price.setText("￥3650");
                imageBg.setBackgroundResource(R.mipmap.vip02);
                break;
        }

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.weixin_rb, R.id.zhifubao_rb, R.id.pay_btn, R.id.toolbar_left_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weixin_rb:
                PAY_WAY = 0;
                setRadioButton(0);
                break;
            case R.id.zhifubao_rb:
                PAY_WAY = 1;
                setRadioButton(1);
                break;
            case R.id.pay_btn:

                break;
            case R.id.toolbar_left_img:
                finish();
                break;
        }
    }

    //设置radiobutton  选中
    private void setRadioButton(int number) {
        for (int i = 0; i < list_radio.size(); i++) {
            if (i == number) {
                list_radio.get(i).setChecked(true);
            } else {
                list_radio.get(i).setChecked(false);
            }
        }
    }
}
