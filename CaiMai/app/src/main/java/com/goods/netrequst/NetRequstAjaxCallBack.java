package com.goods.netrequst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.dev.customview.MyToast;
import com.goods.city.GoodsListModel;
import com.goods.sortlsitview.AjaxShopModel;
import com.goods.sortlsitview.CharacterParser;
import com.goods.sortlsitview.ShopsList;
import com.goods.sortlsitview.SortModel;
import com.hyf.tdlibrary.utils.Tools;
import com.json.CommonConvert;
import com.net.http.AjaxCallBack;

import java.util.ArrayList;


/***
 * NetRequstAjaxCallBack
 *
 * @author lyy
 *
 */
public class NetRequstAjaxCallBack {
    private Context mContext;
    private String userpwd;
    public OnNetRequstAjaxCallBack onNetRequstAjaxCallBack;

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public void setOnNetRequstAjaxCallBack(OnNetRequstAjaxCallBack onNetRequstAjaxCallBack) {
        this.onNetRequstAjaxCallBack = onNetRequstAjaxCallBack;
    }

    public NetRequstAjaxCallBack(Context mContext) {
        this.mContext = mContext;

    }


    /***
     * 注销登录回调
     */
    public AjaxCallBack<AjaxShopModel> getAllShopsAndNearshopCallBack = new AjaxCallBack<AjaxShopModel>() {
        @Override
        public void onFailure(Throwable t, int errorNo, String strMsg) {
            // TODO Auto-generated method stub
            super.onFailure(t, errorNo, strMsg);
            // 失败的handler回调
            MyToast.showMyToast(mContext, "服务异常", -1);
            if (onNetRequstAjaxCallBack != null) {
                onNetRequstAjaxCallBack.MsgCallBack(false, "error:" + strMsg, "数据获取失败");
            }
        }

        @Override
        public void onSuccess(AjaxShopModel t) {
            // TODO Auto-generated method stub
            super.onSuccess(t);
            if (t == null) {
                MyToast.showMyToast(mContext, "数据获取失败", -1);
                if (onNetRequstAjaxCallBack != null) {
                    onNetRequstAjaxCallBack.MsgCallBack(false, "", "数据获取失败");
                }
                return;
            }
            ArrayList<SortModel> sortData = new ArrayList<SortModel>();
            String shops = t.shops;
            ArrayList<ShopsList> shopsList = t.shopsList;
            analisyShops(sortData, shops,shopsList);
            if (onNetRequstAjaxCallBack != null) {
                onNetRequstAjaxCallBack.MsgCallBack(true, "", sortData);
            }

        }
    };
    private CharacterParser characterParser;

    public void analisyShops(ArrayList<SortModel> sortData, String shops, ArrayList<ShopsList> shopsList ) {
        try {
            // 实例化汉字转拼音类
            characterParser = CharacterParser.getInstance();
            JSONObject obj = new JSONObject(shops);
            CommonConvert convert = new CommonConvert(obj);
            int shopId = convert.getInt("shopId");
            String shopName = convert.getString("shopName");
            if (Tools.IsEmpty(shopName)) {
                shopName = "空";
            }

            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(shopName);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            String provinceId = convert.getString("provinceId");
            SortModel model = new SortModel();
            model.setName(shopName);
            model.setProvinceId(provinceId);
            model.setShopId(shopId + "");
            model.setSortLetters(sortString);
            sortData.add(model);

            for (int i=0;i<shopsList.size();i++){
                int shopId_ = shopsList.get(i).shopId;
                String shopName_ =  shopsList.get(i).shopName;
                if (Tools.IsEmpty(shopName)) {
                    shopName = "空";
                }
                // 汉字转换成拼音
                String pinyin_ = characterParser.getSelling(shopName_);
                String sortString_ = pinyin_.substring(0, 1).toUpperCase();

                String provinceId_ = shopsList.get(i).provinceId;
                SortModel model_ = new SortModel();
                model_.setName(shopName_);
                model_.setProvinceId(provinceId_);
                model_.setShopId(shopId_ + "");
                model_.setSortLetters(sortString_);
                sortData.add(model_);
            }

//            "cityId": "1",
//                    "areaId": "海淀区",
//                    "detail": "北京市海淀区中关村大街59号",
//                    "shopAddress": "10.100.100.100",
//                    "longitude": 116.322199,
//                    "latitude": 39.975783,
//                    "shopkeeperName": "张三",
//                    "telephone": "111111111111",


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }






    /***
     * 注销登录回调
     */
    public AjaxCallBack<AjaxShopModel> getProductBySearch = new AjaxCallBack<AjaxShopModel>() {
        @Override
        public void onFailure(Throwable t, int errorNo, String strMsg) {
            // TODO Auto-generated method stub
            super.onFailure(t, errorNo, strMsg);
            // 失败的handler回调
            MyToast.showMyToast(mContext, "服务异常", -1);
            if (onNetRequstAjaxCallBack != null) {
                onNetRequstAjaxCallBack.MsgCallBack(false, "error:" + strMsg, "数据获取失败");
            }
        }

        @Override
        public void onSuccess(AjaxShopModel t) {
            // TODO Auto-generated method stub
            super.onSuccess(t);
            if (t == null) {
                MyToast.showMyToast(mContext, "数据获取失败", -1);
                if (onNetRequstAjaxCallBack != null) {
                    onNetRequstAjaxCallBack.MsgCallBack(false, "", "数据获取失败");
                }
                return;
            }


            ArrayList<GoodsListModel> shopsList = t.list;
            if (onNetRequstAjaxCallBack != null&&shopsList!=null&&shopsList.size()>0) {
                onNetRequstAjaxCallBack.MsgCallBack(true, "", shopsList);
            }else{
                onNetRequstAjaxCallBack.MsgCallBack(false, "", shopsList);
            }

        }
    };
    /***
     * 一级列表数据返回
     */
    public AjaxCallBack<AjaxShopModel> getAllCategory = new AjaxCallBack<AjaxShopModel>() {
        @Override
        public void onFailure(Throwable t, int errorNo, String strMsg) {
            // TODO Auto-generated method stub
            super.onFailure(t, errorNo, strMsg);
            // 失败的handler回调
            MyToast.showMyToast(mContext, "服务异常", -1);
            if (onNetRequstAjaxCallBack != null) {
                onNetRequstAjaxCallBack.MsgCallBack(false, "error:" + strMsg, "数据获取失败");
            }
        }

        @Override
        public void onSuccess(AjaxShopModel t) {
            // TODO Auto-generated method stub
            super.onSuccess(t);
            if (t == null) {
                MyToast.showMyToast(mContext, "数据获取失败", -1);
                if (onNetRequstAjaxCallBack != null) {
                    onNetRequstAjaxCallBack.MsgCallBack(false, "", "数据获取失败");
                }
                return;
            }
//
//            obj = new JSONObject(jsonObj);
//            CommonConvert convert = new CommonConvert(obj);
//            jsonObj = convert.getString("data");
            ArrayList<GoodsListModel> shopsList = t.list;
            if (onNetRequstAjaxCallBack != null&&shopsList!=null&&shopsList.size()>0) {
                onNetRequstAjaxCallBack.MsgCallBack(true, "", shopsList);
            }else{
                onNetRequstAjaxCallBack.MsgCallBack(false, "", shopsList);
            }

        }
    };

    public interface OnNetRequstAjaxCallBack {
        public void MsgCallBack(boolean isSuccess, String errorMsg, Object object);
    }

}
