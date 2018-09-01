package com.goods.order;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.customview.MyListView;
import com.dev.customview.MyToast;
import com.goods.details.GoodsDetailsActivity;
import com.goods.details.ShoppingCarModel;
import com.goods.mineinfo.DeleteView;
import com.goods.shoppingcar.SureCarValue;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10835 on 2018/9/1.
 */

public class SureOrderActivity extends BaseActivity {


    private String allPrace;
    private TextView allPrace_textView;
    private TextView getGoods_pople, getGoods_phone, getGoods_address;

    private LinearLayout fristliner, orderdetails_liner, choose_address;

    private TextView uporder_textView;


    private MyListView goodsMyListView;
    private SureGoodsAdapter sureGoodsAdapter;
    private List<ShoppingCarModel> goodsData;
    private DeleteView deleteView;

    private String addressId;


    @Override
    protected int getLayoutResource() {
        return  R.layout.goods_sureorder_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
       // SureOrderValue.getInstance().init();
        goodsData = new ArrayList<ShoppingCarModel>();
        allPrace = getIntent().getStringExtra("allPrace");
        viewInit();
    }

    public void viewInit() {
        back_btn = (ImageButton) findViewById(R.id.back_img_btn);
        topbar_title = (TextView) findViewById(R.id.text_title_content);
        topbar_title.setText("确认订单");
        back_btn.setOnClickListener(onClickListener);

        getGoods_pople = (TextView) findViewById(R.id.getGoods_pople);
        getGoods_phone = (TextView) findViewById(R.id.getGoods_phone);
        getGoods_address = (TextView) findViewById(R.id.getGoods_address);

        nodataview_textview = (TextView) findViewById(R.id.nodataview_textview);

        choose_address = (LinearLayout) findViewById(R.id.choose_address);

        // fristliner = (LinearLayout) findViewById(R.id.fristliner);
        // orderdetails_liner = (LinearLayout)
        // findViewById(R.id.orderdetails_liner);

        goodsMyListView = (MyListView) findViewById(R.id.goodsMyListView);
        //goodsMyListView.setOnItemClickListener(onItemClickListener);
        sureGoodsAdapter = new SureGoodsAdapter(mContext);
        sureGoodsAdapter.setGoodsData(goodsData);
        goodsMyListView.setAdapter(sureGoodsAdapter);

        allPrace_textView = (TextView) findViewById(R.id.allPrace_textView);
        choose_address.setOnClickListener(onClickListener);
        // fristliner.setOnClickListener(onClickListener);
        // orderdetails_liner.setOnClickListener(onClickListener);
        allPrace_textView.setText("￥" + allPrace);
        uporder_textView = (TextView) findViewById(R.id.uporder_textView);
        uporder_textView.setOnClickListener(onClickListener);
       // showMbProgress("数据求情中...");
       // app.netRequst.goodsAddressDatasRequst(acc.getUserId(), "1", "20", netRequstAjaxCallBack.shopingCarDataCallback);

    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.back_img_btn:
                 backHistory(-1,true,false,extraMap);
                    break;
                case R.id.choose_address:
                  //  skip_classView(ChooseGoodsAddressActivity.class, extraMap, false, 200);

                    break;
                case R.id.fristliner:// 查看物流
                 //   skip_classView(OrderforgoodsLogisticsActivity.class, extraMap, false, -1);
                    break;
                case R.id.orderdetails_liner:
                    skip_classView(GoodsDetailsActivity.class, extraMap, false, -1);

                    break;
                case R.id.uporder_textView:// 提价订单

                    if (Tools.IsEmpty(addressId)) {
                        MyToast.showMyToast(mContext, "请添加收货地址！", -1);
                    } else {
                        showMbProgress("数据求情中...");
                      //  app.netRequst.shoppingOrderSaveRequst(acc.getUserId(), "10", getIdString(), allPrace, "1",addressId,acc.getStoreId(),
                          //      netRequstAjaxCallBack.shopingCarRemoveCallback);

                    }

                    break;

                default:
                    break;
            }

        }

    };
    @Override
    protected void loadData() {

    }
}
