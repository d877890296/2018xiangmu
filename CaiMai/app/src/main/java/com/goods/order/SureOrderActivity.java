package com.goods.order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.customview.MyListView;
import com.goods.city.GoodsListModel;
import com.goods.city.GoodsValue;
import com.goods.details.GoodsDetailsActivity;
import com.goods.mineinfo.DeleteView;
import com.goods.shoppingcar.SureCarValue;
import com.google.gson.Gson;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.AddOrderBean;
import com.xfkc.caimai.bean.AddressBean;
import com.xfkc.caimai.bean.FreightBean;
import com.xfkc.caimai.bean.UserInfoBean;
import com.xfkc.caimai.config.Constant;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.dialog.ShowPassWordDialog;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;
import com.xfkc.caimai.order.ChooseAddressActivity;
import com.xfkc.caimai.pay.PaySuccessActivity;
import com.xfkc.caimai.pay.SettingPayPasswordActivity;

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
    //    @Bind(R.id.pay_way)
//    TextView payWay;
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
    @Bind(R.id.payway)
    RelativeLayout payway;
    @Bind(R.id.peisong_tv)
    TextView peisongTv;
    @Bind(R.id.payway_tv)
    TextView paywayTv;
    private String allPrace;
    private TextView allPrace_textView;
    private TextView getGoods_pople, getGoods_phone, getGoods_address, yunfei;

    private LinearLayout fristliner, orderdetails_liner, choose_address;

    private TextView uporder_textView, shopAllprice, reallyPay;
    private MyListView goodsMyListView;
    private SureGoodsAdapter sureGoodsAdapter;
    private List<GoodsListModel> goodsData;
    private DeleteView deleteView;

    private String addressId = "1", province = "北京";

    private ArrayList<TextView> list_way = new ArrayList<>();
    private ArrayList<TextView> list_payway = new ArrayList<>();

    private int WAY = 1, PAY_WAY = 1;
    private int sourceType = 2;
    private String shopId = "", itemId = "", itemPrice = "", paramData = "";
    private double freight = 0, reall_price = 0;
    private ShowPassWordDialog showPassWordDialog;


    @Override
    protected int getLayoutResource() {
        return R.layout.goods_sureorder_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //SureCarValue.getInstance().init();
        goodsData = new ArrayList<GoodsListModel>();
        allPrace = getIntent().getStringExtra("allPrace");
        goodsData = SureCarValue.getInstance().getAddressData();
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);

        list_way.add(wayYouji);
        list_way.add(wayZiqu);

        list_payway.add(kangbi);
        list_payway.add(daijinjuan);
        sourceType = getIntent().getIntExtra("sourceType", 2);
        showPassWordDialog = new ShowPassWordDialog(this);

        viewInit();
    }

    //地址集合
    private ArrayList<AddressBean.DataBean> list_address = new ArrayList();

    protected void requstAddress() {

        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        PayFactory.getPayService().getReceiveAdress(token)
                .compose(RxHelper.<AddressBean>io_main())
                .subscribe(new ProgressSubscriber<AddressBean>(this) {
                    @Override
                    public void onNext(AddressBean addressBean) {
                        dissMbProgress();
                        if (addressBean.data != null && addressBean.data.size() != 0) {
                            list_address.addAll(addressBean.data);
                            addressId = addressBean.data.get(0).id + "";
                            getGoods_pople.setText(addressBean.data.get(0).receiveName + "");
                            getGoods_phone.setText(addressBean.data.get(0).phone + "");
                            getGoods_address.setText(addressBean.data.get(0).detailAdress + "");


                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        dissMbProgress();
                    }
                });


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
        yunfei = (TextView) findViewById(R.id.yunfei);
        choose_address = (LinearLayout) findViewById(R.id.choose_address);
        shopAllprice = (TextView) findViewById(R.id.shop_allprice);
        reallyPay = (TextView) findViewById(R.id.really_pay);

        // fristliner = (LinearLayout) findViewById(R.id.fristliner);
        // orderdetails_liner = (LinearLayout)
        // findViewById(R.id.orderdetails_liner);

        goodsMyListView = (MyListView) findViewById(R.id.goodsMyListView);
        goodsMyListView.setOnItemClickListener(onItemClickListener);
        sureGoodsAdapter = new SureGoodsAdapter(mContext);
        sureGoodsAdapter.setGoodsData(goodsData);
        goodsMyListView.setAdapter(sureGoodsAdapter);

        allPrace_textView = (TextView) findViewById(R.id.allPrace_textView);
        choose_address.setOnClickListener(onClickListener);
        // fristliner.setOnClickListener(onClickListener);
        // orderdetails_liner.setOnClickListener(onClickListener);

        shopAllprice.setText("￥" + allPrace);
        uporder_textView = (TextView) findViewById(R.id.uporder_textView);
        uporder_textView.setOnClickListener(onClickListener);
        showMbProgress("数据求情中...");
        requstAddress();
        requstFreight();
        wayYouji.setOnClickListener(onClickListener);
        wayZiqu.setOnClickListener(onClickListener);
        peisongway.setOnClickListener(onClickListener);
        kangbi.setOnClickListener(onClickListener);
        daijinjuan.setOnClickListener(onClickListener);
        paywayLayout.setOnClickListener(onClickListener);
        payway.setOnClickListener(onClickListener);
    }

    /*获取邮费信息*/
    private void requstFreight() {
        for (GoodsListModel mdoel : goodsData) {
            itemId += mdoel.itemId + ",";
            buyNum += mdoel.buyNum + ",";
            itemPrice += mdoel.itemPrice + ",";
            if (Tools.IsEmpty(mdoel.paramData)) {
                mdoel.paramData = "0";
            }
            paramData += mdoel.paramData + ",";

        }

        itemId = itemId.substring(0, itemId.length() - 1);
        buyNum = buyNum.substring(0, buyNum.length() - 1);
        itemPrice = itemPrice.substring(0, itemPrice.length() - 1);
        paramData = paramData.substring(0, paramData.length() - 1);

        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("shopId", goodsData.get(0).shopId);//店铺id

        params.put("itemId", itemId);//商品id
        params.put("buyNum", buyNum);//购买数量
//        params.put("itemPrice", itemPrice);//商品单价
        params.put("paramData", paramData);//商品参数
        params.put("receiveProvince", province);

        OkGo.post(Constant.BASE_URL + "/api/order/getOrderFreight")
                .tag(this)//url请求地址
                .params(params)
                .isMultipart(true)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        FreightBean freightBean = gson.fromJson(s, FreightBean.class);
                        if (freightBean.retCode == 1) {
                            freight = freightBean.data;
                            yunfei.setText("￥" + freight);
                            reall_price = Double.parseDouble(allPrace) + freight;
                            reall_price = (double) (Math.round(reall_price * 10000)) / 10000;
                            allPrace_textView.setText("￥" + reall_price);
                            reallyPay.setText("￥" + reall_price);
                        } else {
                            ToastUtil.showToast(freightBean.message);
                        }
                        dissMbProgress();
                    }
                });
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
                    addOrder();

                    break;
                case R.id.way_youji:
                    WAY = 1;
                    setWay(0);
                    peisongTv.setText("邮寄");
                    break;
                case R.id.way_ziqu:
                    WAY = 2;
                    setWay(1);
                    peisongTv.setText("自取");
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
                    paywayTv.setText("康币");
                    break;
                case R.id.daijinjuan:
                    PAY_WAY = 2;
                    setPayWay(1);
                    paywayTv.setText("定级币");
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
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            // TODO Auto-generated method stub
            GoodsValue.getInstance().setGoodsListModel(goodsData.get(arg2));
            skip_classView(GoodsDetailsActivity.class, extraMap, false);
        }
    };
    private String message;
    private String buyNum = "";

    /* *
    点击立即支付时生成订单相关
    */
    private void addOrder() {


        String note = buyerSay.getText().toString();


        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("shopsId", goodsData.get(0).shopId);//店铺id

        params.put("itemId", itemId);//商品id
        params.put("buyNum", buyNum);//购买数量
        params.put("itemPrice", itemPrice);//商品单价
        params.put("paramData", paramData);//商品参数
        params.put("periodTime", goodsData.get(0).periodTime);//商品期数


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
                        Log.e("--2--", s);
                        Gson gson = new Gson();
                        AddOrderBean addOrderBean = gson.fromJson(s, AddOrderBean.class);
                        if (addOrderBean.retCode == 1) {
                            if ( kbAmount > 0 || reall_price > kbAmount){
                                message = addOrderBean.message;
                                showPassWordDialog.showTimeDialog02(SureOrderActivity.this, reall_price, kbAmount+"");
                            }else {
                                ToastUtil.showToast("余额不足!");
                            }
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
        params.put("orderPrice", reall_price);
        params.put("payPwd", pwd);
        params.put("token", token);

        OkGo.post(Constant.BASE_URL + "/api/order/actualPayment")
                .tag(this)//url请求地址
                .params(params)
                .isMultipart(true)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("----------", s);
                        Gson gson = new Gson();
                        AddOrderBean addOrderBean = gson.fromJson(s, AddOrderBean.class);
                        if (addOrderBean.retCode == 1) {
                            extraMap.put("type","1");
                            skip_classView(PaySuccessActivity.class, extraMap, false,101);
                        } else {
                            if (Tools.IsEmpty(pwdword)){
                                skip_classView(SettingPayPasswordActivity.class, extraMap, false);
                            }else {
                                ToastUtil.showToast(addOrderBean.message);
                            }
                        }
                        dissMbProgress();
                    }

                });
    }

    @Override
    protected void loadData() {
        getData();
    }

    /*选取邮寄方式*/
    private void setWay(int id) {
        for (int i = 0; i < list_way.size(); i++) {
            if (i == id) {
                list_way.get(i).setBackgroundResource(R.drawable.type_grid_bg02);
            } else {
                list_way.get(i).setBackgroundResource(R.drawable.type_grid_bg);
            }
        }
    }

    /*选取支付方式*/
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
            province = data.getStringExtra("province");
            getGoods_pople.setText(name);
            getGoods_phone.setText(phone);
            getGoods_address.setText(address);
            requstFreight();
//            addAddressIv.setVisibility(View.GONE);
//            shouhuoAddress.setVisibility(View.VISIBLE);
        }else if (requestCode == 101 && resultCode == 102){
            backHistory(103,true,true,extraMap);
        }
    }



    private double kbAmount;
    private String pwdword;
    /*获取个人信息*/
    private void getData() {
        PayFactory.getPayService()
                .findUserDetByPhone(token)
                .compose(RxHelper.<UserInfoBean>io_main())
                .subscribe(new ProgressSubscriber<UserInfoBean>(mContext) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        kbAmount = userInfoBean.data.kbAmount;
                        pwdword = userInfoBean.data.payPwd;
                    }
                });
    }


}
