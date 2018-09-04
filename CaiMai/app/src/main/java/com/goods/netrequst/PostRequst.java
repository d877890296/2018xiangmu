package com.goods.netrequst;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import com.goods.city.GoodsListModel;
import com.goods.city.GoodsValue;
import com.json.NetJsonFiled;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xfkc.caimai.bean.GoodsKey;
import com.xfkc.caimai.config.Constant;

import org.apache.http.NameValuePair;

import java.util.List;

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
        params.addBodyParameter("topCategoryId", goodsKey.topCategoryId+"");
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
     */
    public void addProduct(Handler handler, GoodsKey goodsKey
    ) {
        this.handler = handler;
        url = Constant.BASE_URL + "/api/shopcart/addProduct?";
        params = new RequestParams();
        params.addBodyParameter("token", goodsKey.token);
        GoodsListModel goodsListModel= GoodsValue.getInstance().getGoodsListModel();
        params.addBodyParameter("id", goodsListModel.id+"");
        params.addBodyParameter("itemId", goodsListModel.itemId+"");
        params.addBodyParameter("title", goodsListModel.title+"");
        params.addBodyParameter("sellPoint", goodsListModel.sellPoint+"");
        params.addBodyParameter("category", goodsListModel.category+"");
        params.addBodyParameter("pic", goodsListModel.pic+"");
        params.addBodyParameter("status", goodsListModel.status+"");
//        params.addBodyParameter("createTime", goodsListModel.createTime+"");
//        params.addBodyParameter("updateTime", goodsListModel.updateTime+"");
        params.addBodyParameter("scid", goodsListModel.scid+"");
        params.addBodyParameter("mailType", goodsListModel.mailType+"");
        params.addBodyParameter("itemType", goodsListModel.itemType+"");
        params.addBodyParameter("itemPrice", goodsListModel.itemPrice+"");
        params.addBodyParameter("allParamData", goodsListModel.allParamData+"");
        params.addBodyParameter("paramData", goodsListModel.paramData+"");
        params.addBodyParameter("buyNum", goodsListModel.buyNum+"");
        params.addBodyParameter("shopId", goodsListModel.shopId+"");
        params.addBodyParameter("mailPrice", goodsListModel.mailPrice+"");
        params.addBodyParameter("inventory", goodsListModel.inventory+"");
        params.addBodyParameter("receiveProvince", goodsListModel.receiveProvince+"");
        params.addBodyParameter("unit", goodsListModel.unit+"");
        params.addBodyParameter("cid", goodsListModel.cid+"");
        params.addBodyParameter("topCategoryId", goodsListModel.topCategoryId+"");
        params.addBodyParameter("periodTime", goodsListModel.periodTime+"");
        params.addBodyParameter("backSelf", goodsListModel.backSelf+"");
        params.addBodyParameter("saleType", goodsListModel.saleType+"");
        params.addBodyParameter("backType", goodsListModel.backType+"");
        params.addBodyParameter("firstBack", goodsListModel.firstBack+"");
        params.addBodyParameter("secondBack", goodsListModel.secondBack+"");
        params.addBodyParameter("useType", goodsListModel.useType+"");
        params.addBodyParameter("content", goodsListModel.content+"");
        params.addBodyParameter("secondCategory", goodsListModel.secondCategory+"");
        uploadMethod(params, url);
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
