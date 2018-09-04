package com.xfkc.caimai.order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.StatusBarUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xfkc.caimai.R;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.rx.activity.RxActivity;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.OnClick;

/***
 * 确认订单
 */
public class ConfirmOrderActivity extends RxActivity {

    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.dingwei_iv)
    ImageView dingweiIv;
    @Bind(R.id.get_goods_name)
    TextView getGoodsName;
    @Bind(R.id.get_goods_phone)
    TextView getGoodsPhone;
    @Bind(R.id.get_goods_address)
    TextView getGoodsAddress;
    @Bind(R.id.youjiantou_iv)
    ImageView jiantou;
    @Bind(R.id.shouhuo_address)
    RelativeLayout shouhuoAddress;
    @Bind(R.id.shop_image)
    ImageView shopImage;
    @Bind(R.id.shop_dazhe)
    ImageView shopDazhe;
    @Bind(R.id.shop_title)
    TextView shopTitle;
    @Bind(R.id.shop_price)
    TextView shopPrice;
    @Bind(R.id.shop_unit_price)
    TextView shopUnitPrice;
    @Bind(R.id.delete_goods_tv)
    TextView deleteGoodsTv;
    @Bind(R.id.add_goods_tv)
    TextView addGoodsTv;
    @Bind(R.id.rl_edit)
    LinearLayout rlEdit;
    @Bind(R.id.tv_buyer)
    TextView tvBuyer;
    @Bind(R.id.buyer_say)
    EditText buyerSay;
    @Bind(R.id.he_tv)
    TextView heTv;
    @Bind(R.id.noebuytv_price)
    TextView noebuytvPrice;
    @Bind(R.id.ishave_yunfei)
    TextView ishaveYunfei;
    @Bind(R.id.commit_goods)
    TextView commitGoods;
    @Bind(R.id.show_goodsnum_tv)
    TextView showGoodsnumTv;
    @Bind(R.id.add_address_iv)
    ImageView addAddressIv;
    @Bind(R.id.shop_guige)
    TextView shopGuige;
    @Bind(R.id.shop_content)
    TextView shopContent;
    @Bind(R.id.shop_allprice)
    TextView shopAllprice;
    @Bind(R.id.yunfei)
    TextView yunfei;
    @Bind(R.id.really_pay)
    TextView reallyPay;
    @Bind(R.id.way_youji)
    TextView wayYouji;
    @Bind(R.id.way_ziqu)
    TextView wayZiqu;
    @Bind(R.id.way_layout)
    LinearLayout wayLayout;
    @Bind(R.id.peisongway)
    RelativeLayout peisongway;
    @Bind(R.id.kangbi)
    TextView kangbi;
    @Bind(R.id.daijinjuan)
    TextView daijinjuan;
    @Bind(R.id.payway_layout)
    LinearLayout paywayLayout;
    @Bind(R.id.payway)
    RelativeLayout payway;

    private int number = 1;//商品数量
    //选择支付方式集合
//    private ArrayList<RadioButton> list_radio = new ArrayList<>();
    //支付方式 0微信  1支付宝 2银联 3工商 4招商
    private int PAY_WAY = 1;

    /**
     * token
     * mpmodityId 商品id
     * mpmodityNumber 购买数量
     * addressId   收货地址ID
     * orderRemarks 订单备注
     * invoIceclient  发票抬头
     * mpmodityPrice 商品价格
     */
    private String mpmodityId = "", mpmodityNumber = "", addressId = "", orderRemarks = "", invoIceclient = "";
    private float mpmodityPrice, mpmodityFreight;//快递费
    //商品图片   //商品名称    //商品简介    //商品产地
    private String imageUrl, mpmodityInfoname, mpmodityBrief, mpmodityGongyingdi;
    //评论数 //总销量   //商品单位  //好评率   月销量
    private String commentCount, alreadySell, mpmodityCompany, likeRate, monthNum;

    //支付时  商品名称  商品金额  商品订单号   创建订单状态
    private String payShop_name = "", payShop_price = "", payShop_orderId = "", payShop_orderType = "";
    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        toolbarTitle.setText("确认订单");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setVisibility(View.VISIBLE);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

//        list_radio.add(weixinRb);
//        list_radio.add(zhifubaoRb);
//        list_radio.add(yinlianRb);
//        list_radio.add(gongshangRb);
//        list_radio.add(zhaoshangRb);

        //商品id
//        mpmodityId = getIntent().getStringExtra("mpmodityId");
//        //商品价格
//        mpmodityPrice = Float.parseFloat(getIntent().getStringExtra("mpmodityPrice"));
//        imageUrl = getIntent().getStringExtra("imageUrl");
//        mpmodityInfoname = getIntent().getStringExtra("mpmodityInfoname");
//        mpmodityBrief = getIntent().getStringExtra("mpmodityBrief");
//        mpmodityGongyingdi = getIntent().getStringExtra("mpmodityGongyingdi");
//        //快递费  评论数  总销量   商品单位
//        mpmodityFreight = Float.parseFloat(getIntent().getStringExtra("mpmodityFreight"));
//        commentCount = getIntent().getStringExtra("commentCount");
//        alreadySell = getIntent().getStringExtra("alreadySell");
//        mpmodityCompany = getIntent().getStringExtra("mpmodityCompany");
//        monthNum = getIntent().getStringExtra("monthNum");
//        //好评率
//        likeRate = getIntent().getStringExtra("likeRate");

        //设置商品显示数据
        setShopInfo();

    }

    @Override
    protected void onResume() {
        super.onResume();
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        if (!Tools.IsEmpty(token) && addAddressIv.getVisibility() == View.VISIBLE) {
            getMoRen();
        }
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
    }

    @Override
    protected void loadData() {

    }

    /*默认地址*/
    private void getMoRen() {
//        GetAllId getAllId = new GetAllId();
//        getAllId.setToken(token);
//        PayFactory.getPayService().getWstAddress(getAllId)
//                .compose(RxHelper.<AddressListBean>io_main())
//                .subscribe(new ProgressSubscriber<AddressListBean>(this) {
//                    @Override
//                    public void onNext(AddressListBean addressListBean) {
//                        //查找是否有默认地址
//                        if (addressListBean.addressList != null && addressListBean.addressList.size() != 0) {
//                            for (int i = 0; i < addressListBean.addressList.size(); i++) {
//                                if (addressListBean.addressList.get(i).isDefault.equals("0")) {
//                                    addressId = addressListBean.addressList.get(i).addressId + "";
//                                    String name = addressListBean.addressList.get(i).userName;
//                                    String phone = addressListBean.addressList.get(i).userPhone;
//                                    String address = addressListBean.addressList.get(i).usAddress;
//
//                                    getGoodsName.setText("收货人: " + name);
//                                    getGoodsPhone.setText(phone);
//                                    getGoodsAddress.setText("收货地址: " + address);
//
//                                    addAddressIv.setVisibility(View.GONE);
//                                    shouhuoAddress.setVisibility(View.VISIBLE);
//                                }
//                            }
//                        }
//                    }
//                });
    }

    /*设置商品显示数据*/
    private void setShopInfo() {
        Glide.with(this).load(imageUrl).error(R.mipmap.error_icon).into(shopImage);
        shopDazhe.setVisibility(View.GONE);

        //商品名称
        shopTitle.setText(mpmodityInfoname + " " + mpmodityBrief);
        //商品价格
        shopPrice.setText(mpmodityPrice + "");
        shopUnitPrice.setText("元/" + mpmodityCompany);
        //月销量
//        shopNumber.setText("月销量:" + alreadySell);
        //产地
//        shopOrigin.setText("供应地:" + mpmodityGongyingdi);

        //总评价数
//        shopTalk.setText("评价:" + commentCount);

//        shopGoodsTalk.setText("好评率:" + likeRate);

//        kuaidiPrice.setText("￥" + mpmodityFreight);

        noebuytvPrice.setText("￥" + decimalFormat.format(mpmodityPrice + mpmodityFreight));
    }

    /***
     * 设置总价
     */
    private float allPrice;

    private void setAllPrice(int number) {
        //共计数量
//        gongNumTv.setText(number + "");
        showGoodsnumTv.setText(number + "");
        float price = number * mpmodityPrice;
        allPrice = (float) (Math.round(price * 10000)) / 10000;
        noebuytvPrice.setText("￥" + decimalFormat.format(allPrice + mpmodityFreight));
    }


    @OnClick({R.id.toolbar_left_img, R.id.shouhuo_address, R.id.delete_goods_tv, R.id.add_goods_tv, R.id.commit_goods,
//            R.id.close, R.id.weixin_rb, R.id.zhifubao_rb, R.id.yinlian_rb, R.id.gongshang_rb, R.id.zhaoshang_rb,
//            R.id.commit,
            R.id.add_address_iv,R.id.way_youji, R.id.way_ziqu, R.id.peisongway, R.id.kangbi, R.id.daijinjuan, R.id.payway})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.shouhuo_address:
                startActivityForResult(new Intent(this, ChooseAddressActivity.class)
                        .putExtra("isShow", true), 1004);
                break;
            case R.id.delete_goods_tv:
                if (number > 1) {
                    number--;
                    setAllPrice(number);
                }
                break;
            case R.id.add_goods_tv:
                number++;
                setAllPrice(number);
                break;
            case R.id.commit_goods:
//                paywayLayout.setVisibility(View.VISIBLE);
                ToastUtil.showToast("支付功能暂未开放");
                break;
//            case R.id.close:
//                paywayLayout.setVisibility(View.GONE);
//                break;
//            case R.id.weixin_rb:
//                PAY_WAY = 0;
//                setRadioButton(0);
//                break;
//            case R.id.zhifubao_rb:
//                PAY_WAY = 1;
//                setRadioButton(1);
//                break;
//            case R.id.yinlian_rb:
//                PAY_WAY = 2;
//                setRadioButton(2);
//                break;
//            case R.id.gongshang_rb:
//                PAY_WAY = 3;
//                setRadioButton(3);
//                break;
//            case R.id.zhaoshang_rb:
//                PAY_WAY = 4;
//                setRadioButton(4);
//                break;
//            case R.id.commit:
//                if (Tools.IsEmpty(token)) {
//                    ToastUtil.showToast("请登录");
//                    skip_classView(LoadingActivity.class, extraMap, false, 101);
//                } else {
//                    paywayLayout.setVisibility(View.GONE);
//                    upOrder();//提交订单
//                }
//                break;
            case R.id.add_address_iv://添加地址
//                if (Tools.IsEmpty(token)) {
//                    ToastUtil.showToast("请登录");
//                    skip_classView(LoadingActivity.class, extraMap, false, 101);
//                } else {
                startActivityForResult(new Intent(this, ChooseAddressActivity.class)
                        .putExtra("isShow", true), 1004);
//                }
                break;
            case R.id.way_youji:
                break;
            case R.id.way_ziqu:
                break;
            case R.id.peisongway:
                if (wayLayout.getVisibility() == View.GONE){
                    wayLayout.setVisibility(View.VISIBLE);
                }else {
                    wayLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.kangbi:

                break;
            case R.id.daijinjuan:

                break;
            case R.id.payway:
                if (paywayLayout.getVisibility() == View.GONE){
                    paywayLayout.setVisibility(View.VISIBLE);
                }else {
                    paywayLayout.setVisibility(View.GONE);
                }
                break;
        }
    }

    /*提交订单*/
    private void upOrder() {
        if (Tools.IsEmpty(addressId)) {
            ToastUtil.showToast("地址信息为空");
            return;
        }
//        orderRemarks = buyerSay.getText().toString();
//        SaveOrderId saveOrderId = new SaveOrderId();
//        saveOrderId.setToken(token);
//        saveOrderId.setAddressId(addressId);
//        saveOrderId.setMpmodityId(mpmodityId);
//        saveOrderId.setInvoIceclient(invoIceclient);
//        saveOrderId.setOrderRemarks(orderRemarks);
//        saveOrderId.setMpmodityNumber(number + "");
//        PayFactory.getPayService().saveOrderList(saveOrderId).compose(RxHelper.<SaveOrderBean>io_main())
//                .subscribe(new ProgressSubscriber<SaveOrderBean>(this) {
//                    @Override
//                    public void onNext(SaveOrderBean saveOrderBean) {
//                        payShop_orderType = saveOrderBean.shareInfo.ordertype;
//                        payShop_name = saveOrderBean.shareInfo.mpmodityInfoname;
//                        payShop_price = saveOrderBean.shareInfo.totalMoney;
//                        payShop_orderId = saveOrderBean.shareInfo.orderNo;
//                        if (payShop_orderType.equals("1")) {
//                            setPay();//支付
//                        } else {
//                            ToastUtil.showToast("订单创建失败");
//                        }
//                    }
//                });
    }

    /*支付*/
    private void setPay() {
        switch (PAY_WAY) {
            case 0:
                Logger.e("payway", "微信");
                getloadData();
                break;
            case 1:
                Logger.e("payway", "支付宝");
                getZfbInfo();
                break;
            case 2:
                Logger.e("payway", "银联");
                break;
            case 3:
                Logger.e("payway", "工商");
                break;
            case 4:
                Logger.e("payway", "招商");
                break;
        }
    }

    /*获取支付宝   支付请求信息*/
    private void getZfbInfo() {
//        GetAllId getAllId = new GetAllId();
//        getAllId.orderNo = payShop_orderId;
//        getAllId.setAmount(payShop_price);
//        getAllId.setBody(payShop_name);
//        getAllId.setToken(token);
//        PayFactory.getPayService()
//                .zfbPay(getAllId)
//                .compose(RxHelper.<ZfbBean>io_main())
//                .subscribe(new ProgressSubscriber<ZfbBean>(this) {
//                    @Override
//                    public void onNext(ZfbBean zfbBean) {
//                        getPay(zfbBean.shareInfo);
//                    }
//                });
    }

    /*微信支付 请求支付信息*/
    public void getloadData() {
//        GetAllId getAllId = new GetAllId();
//        getAllId.orderNo = payShop_orderId;
//        getAllId.setAmount(payShop_price);
//        getAllId.setBody(payShop_name);
//        getAllId.setToken(token);
//        PayFactory.getPayService()
//                .wxPay(getAllId)
//                .compose(RxHelper.<WxPayBean>io_main())
//                .subscribe(new ProgressSubscriber<WxPayBean>(this) {
//                    @Override
//                    public void onNext(WxPayBean wxPayBean) {
//                        toWXPay(wxPayBean);
//                    }
//                });
    }

    //设置radiobutton  选中
    private void setRadioButton(int number) {
//        for (int i = 0; i < list_radio.size(); i++) {
//            if (i == number) {
//                list_radio.get(i).setChecked(true);
//            } else {
//                list_radio.get(i).setChecked(false);
//            }
//        }

    }

    private IWXAPI iwxapi;

    //微信支付api
    // /** *调起微信支付的方法 **/
//    private void toWXPay(final WxPayBean wxPayBean) {
//
//        iwxapi = WXAPIFactory.createWXAPI(this, null);
//        //初始化微信api
//        iwxapi.registerApp(Constant.APP_ID);
//
//        //注册appid appid可以在开发平台获取
//        Runnable payRunnable = new Runnable() {
//            //这里注意要放在子线程
//            @Override
//            public void run() {
//                PayReq request = new PayReq();
//                //调起微信APP的对象
//                // 下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
//                request.appId = Constant.APP_ID;
//                request.partnerId = wxPayBean.shareInfo.partnerid;
//                request.prepayId = wxPayBean.shareInfo.prepayid;
//                request.packageValue = "Sign=WXPay";
//                request.nonceStr = wxPayBean.shareInfo.noncestr;
//                request.timeStamp = wxPayBean.shareInfo.timestamp;
//                request.sign = wxPayBean.shareInfo.sign;
//                iwxapi.sendReq(request);
//                //发送调起微信的请求
//            }
//        };
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }

    //支付宝支付
    public void getPay(final String orderInfo) {
//        // 订单信息（app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&连接。）
//        Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                PayTask alipay = new PayTask(ConfirmOrderActivity.this);
//                Map<String, String> result = alipay.payV2(orderInfo, true);
//
//                Message msg = new Message();
//                msg.what = SDK_PAY_FLAG;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
//            }
//        };
//        // 必须异步调用
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
    }

    //    private static final int SDK_PAY_FLAG = 1;
//    private static final int SDK_AUTH_FLAG = 2;
//    @SuppressLint("HandlerLeak")
//    private Handler mHandler = new Handler() {
//        @SuppressWarnings("unused")
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case SDK_PAY_FLAG: {
//                    @SuppressWarnings("unchecked")
//                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
//                    /**
//                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//                     */
//                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//                    String resultStatus = payResult.getResultStatus();
//                    // 判断resultStatus 为9000则代表支付成功
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(ConfirmOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
////                        finish();
//                        startPaySuccess();
//                    } else {
//                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        Toast.makeText(ConfirmOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                }
//                case SDK_AUTH_FLAG: {
//                    @SuppressWarnings("unchecked")
//                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
//                    String resultStatus = authResult.getResultStatus();
//
//                    // 判断resultStatus 为“9000”且result_code
//                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
//                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
//                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
//                        // 传入，则支付账户为该授权账户
//                        Toast.makeText(ConfirmOrderActivity.this,
//                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
//                                .show();
//                    } else {
//                        // 其他状态值则为授权失败
//                        Toast.makeText(ConfirmOrderActivity.this,
//                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
//
//                    }
//                    break;
//                }
//                default:
//                    break;
//            }
//        }
//
//    };
//
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

            getGoodsName.setText("收货人: " + name);
            getGoodsPhone.setText(phone);
            getGoodsAddress.setText("收货地址: " + address);

            addAddressIv.setVisibility(View.GONE);
            shouhuoAddress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        startPaySuccess();
    }

    /*支付成功页面跳转*/
    private void startPaySuccess() {
//        startActivity(new Intent(this, PaySuccessActivity.class)
//                .putExtra("mpmodityPrice", mpmodityPrice + "")
//                .putExtra("imageUrl", imageUrl)
//                .putExtra("mpmodityInfoname", mpmodityInfoname)
//                .putExtra("mpmodityBrief", mpmodityBrief)
//                .putExtra("mpmodityGongyingdi", mpmodityGongyingdi)
//                .putExtra("mpmodityCompany", mpmodityCompany)
//                .putExtra("allprice", allPrice + "")
//                .putExtra("allnumber", number + "")
//                .putExtra("orderNo", payShop_orderId)
//        );
//        finish();
    }


}
