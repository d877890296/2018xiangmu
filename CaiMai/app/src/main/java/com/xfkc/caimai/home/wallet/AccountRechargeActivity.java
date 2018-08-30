package com.xfkc.caimai.home.wallet;

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

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 账户充值
 */
public class AccountRechargeActivity extends BaseActivity {


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
    @Bind(R.id.price_1000)
    TextView price1000;
    @Bind(R.id.price_2000)
    TextView price2000;
    @Bind(R.id.price_et)
    TextView priceEt;
    @Bind(R.id.weixin_rb)
    RadioButton weixinRb;
    @Bind(R.id.zhifubao_rb)
    RadioButton zhifubaoRb;
    @Bind(R.id.pay_btn)
    StateButton payBtn;

    //标题集合
    private ArrayList<TextView> list_tv = new ArrayList<>();
    //支付方式 0微信  1支付宝
    private int PAY_WAY = 0 , PRICE_NUMBER = 1000;
    //选择支付方式集合
    private ArrayList<RadioButton> list_radio = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_account_recharge;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("账户充值");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        list_tv.add(price1000);
        list_tv.add(price2000);
        list_tv.add(priceEt);

        list_radio.add(weixinRb);
        list_radio.add(zhifubaoRb);
    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.toolbar_left_img, R.id.price_1000, R.id.price_2000, R.id.price_et, R.id.weixin_rb, R.id.zhifubao_rb, R.id.pay_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.price_1000:
                PRICE_NUMBER = 1000;
                updateShow(0);
                break;
            case R.id.price_2000:
                PRICE_NUMBER = 2000;
                updateShow(1);
                break;
            case R.id.price_et:
                PRICE_NUMBER = 0;
                updateShow(2);
                break;
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
        /*查询线条变化*/

    private void updateShow(int id) {
        for (int i = 0; i < list_tv.size(); i++) {
            if (id == i) {
                list_tv.get(i).setBackgroundResource(R.drawable.wallet_tv_bg);
            } else {
                list_tv.get(i).setBackgroundResource(R.drawable.wallet_tv_bg03);
            }
        }
        loadData();
    }

}

