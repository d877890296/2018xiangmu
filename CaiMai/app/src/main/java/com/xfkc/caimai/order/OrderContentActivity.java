package com.xfkc.caimai.order;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.customview.MyListView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.order.adapter.OrderContentListAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 订单详情
 */
public class OrderContentActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.order_status_iv)
    ImageView orderStatusIv;
    @Bind(R.id.order_status_tv)
    TextView orderStatusTv;
    @Bind(R.id.order_ts_tv)
    TextView orderTsTv;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.address_icon_tv)
    TextView addressIconTv;
    @Bind(R.id.address_content)
    TextView addressContent;
    @Bind(R.id.shop_price)
    TextView shopPrice_tv;
    @Bind(R.id.qh_way)
    TextView qhWay;
    @Bind(R.id.pay_way)
    TextView payWay;
    @Bind(R.id.yf_price)
    TextView yfPrice;
    @Bind(R.id.really_price)
    TextView reallyPrice;
    @Bind(R.id.all_price)
    TextView allPrice;
    @Bind(R.id.pay_bt)
    StateButton payBt;
    @Bind(R.id.cancle_pay_bt)
    StateButton canclePayBt;
    @Bind(R.id.bt_layout)
    LinearLayout btLayout;
    @Bind(R.id.look_wl)
    StateButton lookWl;
    @Bind(R.id.list_shop)
    MyListView listShop;

    private OrderContentListAdapter orderContentListAdapter;
    private ArrayList<EmptyBean> list_data =new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_order_content;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("订单详情");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        list_data.add(new EmptyBean());
        orderContentListAdapter.setData(list_data);
        listShop.setAdapter(orderContentListAdapter);
    }

    @Override
    protected void loadData() {


    }

    @OnClick({R.id.toolbar_left_img, R.id.cancle_pay_bt, R.id.bt_layout, R.id.look_wl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.cancle_pay_bt:
                break;
            case R.id.bt_layout:
                break;
            case R.id.look_wl:
                break;
        }
    }


}
