package com.xfkc.caimai.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xfkc.caimai.R;
import com.xfkc.caimai.config.Constant;
import com.xfkc.caimai.loading.LoadingActivity;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
        api.handleIntent(getIntent(), this);

//        try {
//            boolean result =  api.handleIntent(getIntent(), this);
//            if(!result){
//                Logger.e(TAG,"参数不合法，未被SDK处理，退出");
//                finish();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        Logger.e("--resp--", resp.getType()+"============="+resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {//登录
            //根据下面的errCode表中 switch一下即可
            //如果是0的话,就是成功,然后这里去服务器查询具体的支付结果,注意服务器查询的支付结果才是可靠地支付结果
            switch (resp.errCode) {
                case 0://成功
                    String code = ((SendAuth.Resp) resp).code;
                    startActivity(new Intent(WXEntryActivity.this, LoadingActivity.class)
                            .putExtra("code",code));
                    break;
                case -1://错误  可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
                    break;
                case -2://用户取消
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED://被拒绝
                    break;
            }
            finish();
        } else if (resp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                        Toast.makeText(WXEntryActivity.this, "分享成功!", Toast.LENGTH_SHORT).show();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    Toast.makeText(WXEntryActivity.this, "分享取消!", Toast.LENGTH_SHORT).show();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    break;
                default:
                    break;
            }
//            Intent intent = new Intent();
//            intent.setAction(APIDefineConst.BROADCAST_ACTION_WEIXIN_SHARE);
//            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
//            lbm.sendBroadcast(intent);
            finish();
        }

    }

    @Override
    protected void onDestroy() {
//        ShareRequst.getInstance().unregister();
        super.onDestroy();
    }
}
