package com.goods.order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.customview.MyListView;
import com.goods.details.GoodsDetailsActivity;
import com.goods.details.ShoppingCarModel;
import com.goods.mineinfo.DeleteView;
import com.google.gson.Gson;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.AddOrderBean;
import com.xfkc.caimai.config.Constant;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.dialog.ShowPassWordDialog;
import com.xfkc.caimai.order.ChooseAddressActivity;
import com.xfkc.caimai.pay.PaySuccessActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 10835 on 2018/9/1.
 */

public class SureOrderActivity extends BaseActivity {


    @Bind(R.id.way_youji)
    TextView wayYouji;
    @Bind(R.id.way_ziqu)
    TextView wayZiqu;
    @Bind(R.id.pay_way)
    TextView payWay;
    @Bind(R.id.kangbi)
    TextView kangbi;
    @Bind(R.id.daijinjuan)
    TextView daijinjuan;
    @Bind(R.id.way_layout)
    LinearLayout wayLayout;
    @Bind(R.id.peisongway)
    RelativeLayout peisongway;
    @Bind(R.id.payway_layout)
    LinearLayout paywayLayout;
    @Bind(R.id.buyer_say)
    EditText buyerSay;
    private String allPrace;
    private TextView allPrace_textView;
    private TextView getGoods_pople, getGoods_phone, getGoods_address;

    private LinearLayout fristliner, orderdetails_liner, choose_address;

    private TextView uporder_textView;


    private MyListView goodsMyListView;
    private SureGoodsAdapter sureGoodsAdapter;
    private List<ShoppingCarModel> goodsData;
    private DeleteView deleteView;

    private String addressId = "1";

    private ArrayList<TextView> list_way = new ArrayList<>();
    private ArrayList<TextView> list_payway = new ArrayList<>();

    private int WAY = 1, PAY_WAY = 1;
    private int freight = 0, sourceType = 2;
    private String shopId = "12", itemId = "1", itemPrice = "1", paramData = "1";

    private ShowPassWordDialog showPassWordDialog;

    @Override
    protected int getLayoutResource() {
        return R.layout.goods_sureorder_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        // SureOrderValue.getInstance().init();
        goodsData = new ArrayList<ShoppingCarModel>();
        allPrace = getIntent().getStringExtra("allPrace");

        list_way.add(wayYouji);
        list_way.add(wayZiqu);

        list_payway.add(kangbi);
        list_payway.add(daijinjuan);
        sourceType = getIntent().getIntExtra("sourceType", 2);
        showPassWordDialog = new ShowPassWordDialog(this);

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

        wayYouji.setOnClickListener(onClickListener);
        wayZiqu.setOnClickListener(onClickListener);
        peisongway.setOnClickListener(onClickListener);
        kangbi.setOnClickListener(onClickListener);
        daijinjuan.setOnClickListener(onClickListener);
        paywayLayout.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.back_img_btn:
                    backHistory(-1, true, false, extraMap);
                    break;
                case R.id.choose_address:
                    extraMap.put("isShow", true);
                    skip_classView(ChooseAddressActivity.class, extraMap, false, 1004);
                    break;
                case R.id.fristliner:// 查看物流
                    //   skip_classView(OrderforgoodsLogisticsActivity.class, extraMap, false, -1);
                    break;
                case R.id.orderdetails_liner:
                    skip_classView(GoodsDetailsActivity.class, extraMap, false, -1);

                    break;
                case R.id.uporder_textView:// 提价订单

//                    if (Tools.IsEmpty(addressId)) {
//                        MyToast.showMyToast(mContext, "请添加收货地址！", -1);
//                    } else {
                    showMbProgress("数据求情中...");
                    //  app.netRequst.shoppingOrderSaveRequst(acc.getUserId(), "10", getIdString(), allPrace, "1",addressId,acc.getStoreId(),
                    //      netRequstAjaxCallBack.shopingCarRemoveCallback);

//                    }
                    addOrder();

                    break;
                case R.id.way_youji:
                    WAY = 1;
                    setWay(0);
                    break;
                case R.id.way_ziqu:
                    WAY = 2;
                    setWay(1);
                    break;
                case R.id.peisongway:
                    if (wayLayout.getVisibility() == View.GONE) {
                        wayLayout.setVisibility(View.VISIBLE);
                    } else {
                        wayLayout.setVisibility(View.GONE);
                    }
                    break;
                case R.id.kangbi:
                    PAY_WAY = 1;
                    setPayWay(0);
                    break;
                case R.id.daijinjuan:
                    PAY_WAY = 2;
                    setPayWay(1);
                    break;
                case R.id.payway:
                    if (paywayLayout.getVisibility() == View.GONE) {
                        paywayLayout.setVisibility(View.VISIBLE);
                    } else {
                        paywayLayout.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }

        }

    };

    private String message;

    /* 点击立即支付时生成订单相关 */
    private void addOrder() {
        String note = buyerSay.getText().toString();
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        HttpParams params = new HttpParams();
        params.put("itemId", itemId);//商品id
        params.put("buyNum", "1");//购买数量
        params.put("itemPrice", itemPrice);//商品单价
        params.put("paramData", paramData);//商品参数
        params.put("periodTime", "12");//商品期数

        params.put("token", token);
        params.put("shopsId", shopId);//店铺id
        params.put("price", allPrace);
        params.put("freight", freight);
        params.put("pickupWay", WAY);
        params.put("paymentWay", PAY_WAY);//支付方式
        params.put("note", note + "");//备注
        params.put("sourceType", sourceType);//来源 1 直接购买 2购物车
        params.put("receiveAdressId", addressId);

        OkGo.post(Constant.BASE_URL + "/api/order/addOrder")
                .tag(this)//url请求地址
                .params(params)
                .isMultipart(true)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("--2--",s);
                        Gson gson = new Gson();
                        AddOrderBean addOrderBean = gson.fromJson(s, AddOrderBean.class);
                        if (addOrderBean.retCode == 1) {
                            message = addOrderBean.message;
                            showPassWordDialog.showTimeDialog02(SureOrderActivity.this, 28.9, 1000);
                        } else {
                            ToastUtil.showToast(addOrderBean.message);
                        }
                        dissMbProgress();
                    }
                });
    }

    /*支付订单*/
    public void payOrder(String pwd) {
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        HttpParams params = new HttpParams();
        params.put("orderNum", message);
        params.put("paymentWay", PAY_WAY);
        params.put("orderPrice", "1");
        params.put("payPwd", pwd);
        params.put("token", token);

        OkGo.post(Constant.BASE_URL + "/api/order/actualPayment")
                .tag(this)//url请求地址
                .params(params)
                .isMultipart(true)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("----------", s);
                        Gson gson = new Gson();
                        AddOrderBean addOrderBean = gson.fromJson(s, AddOrderBean.class);
                        if (addOrderBean.retCode == 1) {
                           skip_classView(PaySuccessActivity.class,extraMap,true);
                        } else {
                            ToastUtil.showToast(addOrderBean.message);
                        }
                        dissMbProgress();
                    }
                });
    }

    @Override
    protected void loadData() {

    }

    private void setWay(int id) {
        for (int i = 0; i < list_way.size(); i++) {
            if (i == id) {
                list_way.get(i).setBackgroundResource(R.drawable.type_grid_bg02);
            } else {
                list_way.get(i).setBackgroundResource(R.drawable.type_grid_bg);
            }
        }
    }

    private void setPayWay(int id) {
        for (int i = 0; i < list_payway.size(); i++) {
            if (i == id) {
                list_payway.get(i).setBackgroundResource(R.drawable.type_grid_bg02);
            } else {
                list_payway.get(i).setBackgroundResource(R.drawable.type_grid_bg);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if (requestCode == 1004 && resultCode == 1005) {
            addressId = data.getStringExtra("addressId");
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String address = data.getStringExtra("address");

            getGoods_pople.setText("收货人: " + name);
            getGoods_phone.setText(phone);
            getGoods_address.setText("收货地址: " + address);

//            addAddressIv.setVisibility(View.GONE);
//            shouhuoAddress.setVisibility(View.VISIBLE);
        }
    }

}
