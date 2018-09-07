package com.goods.netrequst;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import com.goods.city.GoodsListModel;
import com.goods.city.GoodsValue;
import com.hyf.tdlibrary.utils.Tools;
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
        params.addBodyParameter("topCategoryId", goodsKey.topCategoryId + "");
        params.addBodyParameter("recordName", goodsKey.recordName);
        uploadMethod(params, url);
    }

    /****
     *通过店铺+二级类别展示所有商品
     * @param handler
     * @param goodsKey
     */
    public void getProductBySecoCate(Handler handler, GoodsKey goodsKey
    ) {
        this.handler = handler;
        url = Constant.BASE_URL + "/api/happycommune/getProductBySecoCate?";
        params = new RequestParams();
        params.addBodyParameter("token", goodsKey.token);
        params.addBodyParameter("pageNum", goodsKey.pageNum);
        params.addBodyParameter("pageSize", goodsKey.pageSize);
        params.addBodyParameter("shopId", goodsKey.shopId);
        params.addBodyParameter("secoCategory", goodsKey.categoryId);
        uploadMethod(params, url);
    }

    /***
     * 添加购物车
     * @param handler
     * @param goodsKey
     * @param number
     */
    public void addProduct(Handler handler, GoodsKey goodsKey,
                           int number) {
        this.handler = handler;
        url = Constant.BASE_URL + "/api/shopcart/addProduct?";
        params = new RequestParams();

        GoodsListModel goodsListModel = GoodsValue.getInstance().getGoodsListModel();
        params.addBodyParameter("token", goodsKey.token);
        String id="";
        if (!Tools.IsEmpty(goodsListModel.id+"")){
            id = goodsListModel.id+"";
        }
        params.addBodyParameter("id",id );
        params.addBodyParameter("itemId", goodsListModel.itemId + "");
        params.addBodyParameter("title", goodsListModel.title + "");
        params.addBodyParameter("sellPoint", goodsListModel.sellPoint + "");
        //  params.addBodyParameter("category", goodsListModel.category + "");
        params.addBodyParameter("pic", goodsListModel.pic + "");
        params.addBodyParameter("status", goodsListModel.status + "");
        // params.addBodyParameter("createTime", goodsListModel.createTime+"");
       //  params.addBodyParameter("updateTime", goodsListModel.updateTime+"");
        params.addBodyParameter("scid", goodsListModel.scid + "");
        params.addBodyParameter("mailType", goodsListModel.mailType + "");
        params.addBodyParameter("itemType", goodsListModel.itemType + "");
        params.addBodyParameter("itemPrice", goodsListModel.itemPrice + "");
        String allparamData = "0";
        if (!Tools.IsEmpty(goodsListModel.allParamData)){
            allparamData = goodsListModel.allParamData;
        }
        params.addBodyParameter("allParamData", allparamData);
        String paramData = "0";
        if (!Tools.IsEmpty(goodsListModel.paramData)){
            paramData = goodsListModel.paramData;
        }
        params.addBodyParameter("paramData",paramData );
        params.addBodyParameter("buyNum", number+"");
        params.addBodyParameter("shopId", goodsListModel.shopId + "");
        params.addBodyParameter("mailPrice", goodsListModel.mailPrice + "");
        params.addBodyParameter("inventory", goodsListModel.inventory + "");
        params.addBodyParameter("receiveProvince", goodsListModel.receiveProvince + "");
        params.addBodyParameter("unit", goodsListModel.unit + "");
        params.addBodyParameter("cid", goodsListModel.cid + "");
        params.addBodyParameter("topCategoryId", goodsListModel.topCategoryId + "");
        params.addBodyParameter("periodTime", goodsListModel.periodTime + "");
        params.addBodyParameter("backSelf", goodsListModel.backSelf + "");
        params.addBodyParameter("saleType", goodsListModel.saleType + "");
        params.addBodyParameter("backType", goodsListModel.backType + "");
        params.addBodyParameter("firstBack", goodsListModel.firstBack + "");
        params.addBodyParameter("secondBack", goodsListModel.secondBack + "");
        params.addBodyParameter("useType", goodsListModel.useType + "");
        params.addBodyParameter("content", goodsListModel.content + "");
        // params.addBodyParameter("secondCategory", goodsListModel.secondCategory + "");

        uploadMethod(params, url);
//构造请求参数
//        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
//        reqBody.put("token", goodsKey.token);
//        reqBody.put("id", goodsListModel.id + "");
//        reqBody.put("itemId", goodsListModel.itemId + "");
//        reqBody.put("title", goodsListModel.title + "");
//        reqBody.put("sellPoint", goodsListModel.sellPoint + "");
//        // params.addBodyParameter("category", goodsListModel.category + "");
//        reqBody.put("pic", goodsListModel.pic + "");
//        reqBody.put("status", goodsListModel.status + "");
//        // params.addBodyParameter("createTime", goodsListModel.createTime+"");
//////        params.addBodyParameter("updateTime", goodsListModel.updateTime+"");
//        reqBody.put("scid", goodsListModel.scid + "");
//        reqBody.put("mailType", goodsListModel.mailType + "");
//        reqBody.put("itemType", goodsListModel.itemType + "");
//        reqBody.put("itemPrice", goodsListModel.itemPrice + "");
//        reqBody.put("allParamData", goodsListModel.allParamData + "");
//        reqBody.put("paramData", goodsListModel.paramData + "");
//        reqBody.put("buyNum", goodsListModel.buyNum + "");
//        reqBody.put("shopId", goodsListModel.shopId + "");
//        reqBody.put("mailPrice", goodsListModel.mailPrice + "");
//        reqBody.put("inventory", goodsListModel.inventory + "");
//        reqBody.put("receiveProvince", goodsListModel.receiveProvince + "");
//        reqBody.put("unit", goodsListModel.unit + "");
//        reqBody.put("cid", goodsListModel.cid + "");
//        //reqBody.put("topCategoryId", goodsListModel.topCategoryId+"");
//        reqBody.put("periodTime", goodsListModel.periodTime + "");
//        reqBody.put("backSelf", goodsListModel.backSelf + "");
//        reqBody.put("saleType", goodsListModel.saleType + "");
//        reqBody.put("backType", goodsListModel.backType + "");
//        reqBody.put("firstBack", goodsListModel.firstBack + "");
//        reqBody.put("secondBack", goodsListModel.secondBack + "");
//        reqBody.put("useType", goodsListModel.useType + "");
//        reqBody.put("content", goodsListModel.content + "");
//        //    reqBody.put("secondCategory", goodsListModel.secondCategory + "");
//        //获取网络请求工具类实例
//        NetUtils netUtils = NetUtils.getInstance();
//        netUtils.postDataAsynToNet(url, reqBody, new NetUtils.MyNetCall() {
//            @Override
//            public void success(Call call, Response response) throws IOException {
//                Log.i("tag", "success");
//                String result = response.body().string();
//
//                //解析数据
//
//
//            }
//
//            @Override
//            public void failed(Call call, IOException e) {
//                Log.i("tag", "failed");
//
//            }
//        });
//

    }

    /****
     * 更新购物车商品数量
     * @param handler
     * @param goodsKey
     */
    public void editCartItemsNum(Handler handler, GoodsKey goodsKey
    ) {
        this.handler = handler;
        url = Constant.BASE_URL + "/api/shopcart/editCartItemsNum?";
        params = new RequestParams();
        params.addBodyParameter("token", goodsKey.token);
        params.addBodyParameter("shopId", goodsKey.shopId);
        params.addBodyParameter("itemId", goodsKey.itemId);
        params.addBodyParameter("buyNum", goodsKey.buyNum);

        uploadMethod(params, url);
    }

    /***
     * 根据当前用户获取购物车商品
     * @param handler
     * @param goodsKey
     */
    public void getShopCart(Handler handler, GoodsKey goodsKey
    ) {
        this.handler = handler;
        url = Constant.BASE_URL + "/api/shopcart/getShopCart?";
        params = new RequestParams();
        params.addBodyParameter("token", goodsKey.token);
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
