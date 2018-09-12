package com.xfkc.caimai.home.vipcard;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.WXBean;
import com.xfkc.caimai.bean.ZfbBean;
import com.xfkc.caimai.config.Constant;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;
import com.xfkc.caimai.pay.AuthResult;
import com.xfkc.caimai.pay.PayResult;
import com.xfkc.caimai.pay.PaySuccessActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.Map;

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
    private int cartype = 0;

    private String name = "", car_price="" , carid="";

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


        name = getIntent().getStringExtra("name");
        car_price = getIntent().getStringExtra("price");
        carid = getIntent().getStringExtra("carid");
        cartype = getIntent().getIntExtra("cartype", 0);
        token = SharedPrefUtil.get(this, SharedPref.TOKEN);

        setVipCardType();
    }

    /*设置会员卡类别*/
    private void setVipCardType() {

        days.setText("有效期: 365天");
        title.setText(name);
        price.setText("￥ "+car_price);
        vipCardNo.setText("VIP 123456789");


        switch (cartype) {
            case 0:
                imageBg.setBackgroundResource(R.mipmap.vip01);
                break;
            case 1:
                imageBg.setBackgroundResource(R.mipmap.vip03);
                break;
            case 2:
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
                if (PAY_WAY == 0){
//                    ToastUtil.showToast("该功能暂未开放!");
                    getWeiXinData();
                }else {
                    getPayData();
                }
                break;
            case R.id.toolbar_left_img:
                finish();
                break;
        }
    }

    /*获取微信支付数据*/
    private void getWeiXinData() {
        PayFactory.getPayService()
                .wxPay(token,carid)
                .compose(RxHelper.<WXBean>io_main())
                .subscribe(new ProgressSubscriber<WXBean>(this) {
                    @Override
                    public void onNext(WXBean wxPayBean) {
                        toWXPay(wxPayBean);
                    }
                });

    }

    /*获取订单信息*/
    private void getPayData() {
        PayFactory.getPayService()
                .zfbPay(carid , token)
                .compose(RxHelper.<ZfbBean>io_main())
                .subscribe(new ProgressSubscriber<ZfbBean>(this) {
                    @Override
                    public void onNext(ZfbBean zfbBean) {
                        getPay(zfbBean.data.sign);
                    }
                });

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


    //支付宝支付
    public void getPay(final String orderInfo) {
        // 订单信息（app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&连接。）
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(VipContentActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(VipContentActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                        finish();
                        startPaySuccess();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(VipContentActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(VipContentActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(VipContentActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

    /*跳转至支付成功页*/
    private void startPaySuccess() {
        extraMap.put("type","2");
        skip_classView(PaySuccessActivity.class,extraMap,false,1003);
    }


    private IWXAPI iwxapi;

    //微信支付api
     /** *调起微信支付的方法 **/
    private void toWXPay(final WXBean wxPayBean) {

        iwxapi = WXAPIFactory.createWXAPI(this, null);
//        初始化微信api
        iwxapi.registerApp(Constant.APP_ID);
        iwxapi.registerApp(wxPayBean.data.appId);

//        注册appid appid可以在开发平台获取
        Runnable payRunnable = new Runnable() {
//            这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq();
//                调起微信APP的对象
//                 下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = Constant.APP_ID;
                request.appId = wxPayBean.data.appId;
                request.partnerId = wxPayBean.data.partnerid;
                request.prepayId = wxPayBean.data.prepay_id;
                request.packageValue = "Sign=WXPay";
                request.nonceStr = wxPayBean.data.nonceStr;
                request.timeStamp = wxPayBean.data.timeStamp+"";
                request.sign = wxPayBean.data.sign;
                iwxapi.sendReq(request);
//                发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
