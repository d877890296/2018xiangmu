package com.xfkc.caimai.home.wallet;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.ZfbBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;
import com.xfkc.caimai.pay.AuthResult;
import com.xfkc.caimai.pay.PayResult;
import com.xfkc.caimai.pay.PaySuccessActivity;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.xfkc.caimai.R.id.price_et;

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
    @Bind(price_et)
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
    private int PAY_WAY = 0;
    private String  PRICE_NUMBER = "1000";
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


    @OnClick({R.id.toolbar_left_img, R.id.price_1000, R.id.price_2000, price_et, R.id.weixin_rb, R.id.zhifubao_rb, R.id.pay_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.price_1000:
                PRICE_NUMBER = "1000";
                updateShow(0);
                break;
            case R.id.price_2000:
                PRICE_NUMBER = "2000";
                updateShow(1);
                break;
            case price_et:
//                PRICE_NUMBER = 0;
                skip_classView(EditPayNumActivity.class, extraMap, false, 1007);
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
                if (PAY_WAY == 1) {
                    getPayData();
                } else {

                }
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


    /*获取订单信息*/
    private void getPayData() {
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        PayFactory.getPayService()
                .memCharge(PRICE_NUMBER + "", token)
                .compose(RxHelper.<ZfbBean>io_main())
                .subscribe(new ProgressSubscriber<ZfbBean>(this) {
                    @Override
                    public void onNext(ZfbBean zfbBean) {
                        getPay(zfbBean.data.sign);
                    }
                });

    }

    //支付宝支付
    public void getPay(final String orderInfo) {
        // 订单信息（app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&连接。）
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(AccountRechargeActivity.this);
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
                        Toast.makeText(AccountRechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                        finish();
                        startPaySuccess();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(AccountRechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(AccountRechargeActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(AccountRechargeActivity.this,
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
        skip_classView(PaySuccessActivity.class, extraMap, false, 1003);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1007 && resultCode == 1008) {
            PRICE_NUMBER = data.getStringExtra("paynum");
            priceEt.setText( PRICE_NUMBER);
        }

    }
}

