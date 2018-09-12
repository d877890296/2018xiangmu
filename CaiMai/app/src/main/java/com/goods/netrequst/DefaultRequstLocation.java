package com.goods.netrequst;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.goods.sortlsitview.AjaxShopModel;
import com.goods.sortlsitview.SortModel;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.json.CommonConvert;
import com.xfkc.caimai.application.MyApplication;
import com.xfkc.caimai.bean.GoodsKey;
import com.xfkc.caimai.config.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.goods.netrequst.PostRequst.UPSUCCESS;

/**
 * Created by 10835 on 2018/9/1.
 */

public class DefaultRequstLocation  {
private Context mContext;
    private String userToken;
    private PostRequst postRequst;
    private NetRequstAjaxCallBack ajaxCallBack;
    private OnLocationCallBack onLocationCallBack;

  public   DefaultRequstLocation( Context mContext){
      this.mContext=mContext;
      init();
    }

    public OnLocationCallBack getOnLocationCallBack() {
        return onLocationCallBack;
}

    public void setOnLocationCallBack(OnLocationCallBack onLocationCallBack) {
        this.onLocationCallBack = onLocationCallBack;
    }

    public  void init(){
        postRequst = new PostRequst(handler);
        ajaxCallBack = new NetRequstAjaxCallBack(mContext);
        ajaxCallBack.setOnNetRequstAjaxCallBack(onNetRequstAjaxCallBack);
    }
    public void startLocation(String longitude,String latitude){

        userToken = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        GoodsKey goodsKey = new GoodsKey();
        goodsKey.token = userToken;
        goodsKey.longitude =longitude;
        goodsKey.latitude = latitude;
        postRequst.getAllShopsAndNearshop(handler, goodsKey);
    }

    private NetRequstAjaxCallBack.OnNetRequstAjaxCallBack onNetRequstAjaxCallBack = new NetRequstAjaxCallBack.OnNetRequstAjaxCallBack() {

        @Override
        public void MsgCallBack(boolean isSuccess, String errorMsg, Object object) {
            // TODO Auto-generated method stub
            if (isSuccess ==true){
                ArrayList<SortModel> sortData=(ArrayList)object;
                if (onLocationCallBack!=null&&sortData!=null&&sortData.size()>0){
                    onLocationCallBack.locationCallBack(isSuccess,"",sortData.get(0));
                }else{
                    onLocationCallBack.locationCallBack(isSuccess,"地位错误！",null);
                }
            }else {
                ToastUtil.showToast("定位失败，请检查权限是否打开");
            }
        }

    };

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPSUCCESS://数据获取成功
                    if (msg.arg1 == 1) {//成功
                        String jsonObj = msg.obj.toString();
                        if (Tools.IsEmpty(jsonObj)) {
                            android.widget.Toast.makeText(mContext,
                                    "数据错误", Toast.LENGTH_LONG).show();
                            return;
                        }
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(jsonObj);
                            CommonConvert convert = new CommonConvert(obj);
                            jsonObj= convert.getString("data");
                            MyApplication.getInstance().jsonHttp.getJsonObj(jsonObj, AjaxShopModel.class,
                                    ajaxCallBack.getAllShopsAndNearshopCallBack);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {//失败

                    }

                    break;
            }
        }
    };


    public interface OnLocationCallBack {
        public void locationCallBack(boolean isSuccess, String errorMsg, SortModel object);
    }
}
