package com.goods.netrequst;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xfkc.caimai.bean.GoodsKey;
import com.xfkc.caimai.config.Constant;

/***
 *
 *
 * 上传的类
 *
 * @author Lyy
 *
 */
@TargetApi(Build.VERSION_CODES.FROYO)
public class PostRequst {
    // 上传的路径
    public String url;
    public RequestParams params;
    public HttpUtils http;
    public Handler handler;


    public PostRequst(Handler handler) {
        this.handler = handler;
        http = new HttpUtils();
    }


    /***
     *
     * @param handler
     * @param goodsKey
     */
    public void getAllShopsAndNearshop(Handler handler, GoodsKey goodsKey
    ) {
        this.handler = handler;

        url = Constant.BASE_URL + "/api/happycommune/getAllShopsAndNearshop?";
        params = new RequestParams();
        params.addBodyParameter("token", goodsKey.token);
        params.addBodyParameter("longitude", goodsKey.longitude);
        params.addBodyParameter("latitude", goodsKey.latitude);
        uploadMethod(params, url);
    }

    /***
     *通过搜索查询商品/展示所有商品
     * @param handler
     * @param goodsKey
     */
    public void getProductBySearch(Handler handler, GoodsKey goodsKey
    ) {
        this.handler = handler;
        url = Constant.BASE_URL + "/api/happycommune/getProductBySearch?";
        params = new RequestParams();
        params.addBodyParameter("token", goodsKey.token);
        params.addBodyParameter("pageNum", goodsKey.pageNum);
        params.addBodyParameter("pageSize", goodsKey.pageSize);
        params.addBodyParameter("shopId", goodsKey.shopId);
        uploadMethod(params, url);
    }


    /***
     *
     * 上传的方法
     *
     * @param params
     * @param url
     */
    public void uploadMethod(final RequestParams params, final String url) {
        http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                // TODO Auto-generated method stub
                Message msg = Message.obtain();
                msg.what = UPSUCCESS;
                msg.arg1 = 0;
                msg.obj = arg1;
                handler.sendMessage(msg);
            }

            @Override
            public void onSuccess(ResponseInfo<String> response) {
                // TODO Auto-generated method stub
                Message msg = Message.obtain();
                msg.what = UPSUCCESS;
                msg.arg1 = 1;
                msg.obj = response.result;
                handler.sendMessage(msg);
            }
        });

    }

    // 发送成功
    public static final int UPSUCCESS = 100;

}
