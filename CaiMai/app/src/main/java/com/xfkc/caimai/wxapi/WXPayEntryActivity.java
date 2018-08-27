package com.xfkc.caimai.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hyf.tdlibrary.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xfkc.caimai.R;
import com.xfkc.caimai.config.Constant;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
//        api = WXAPIFactory.createWXAPI(this, Constant.PAYAPP_ID);
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        Logger.e(TAG, "onPayFinish, errCode = ==" + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            //根据下面的errCode表中 switch一下即可
            //如果是0的话,就是成功,然后这里去服务器查询具体的支付结果,注意服务器查询的支付结果才是可靠地支付结果
            switch (resp.errCode) {
                case 0://成功
//                    startActivity(new Intent(WXPayEntryActivity.this, ConfirmOrderActivity.class));
//                    startActivity(new Intent(WXPayEntryActivity.this, VipConfirmOrderActivity.class));
                    ToastUtil.showToast("支付成功");
                    break;
                case -1://错误  可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
                    ToastUtil.showToast("支付失败");
                    break;
                case -2://用户取消
                    ToastUtil.showToast("支付取消");
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED://被拒绝

                    break;
                default:
                    break;
            }

        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data,this);
    }

    @Override
    protected void onDestroy() {
//        ShareRequst.getInstance().unregister();
        super.onDestroy();
    }


}