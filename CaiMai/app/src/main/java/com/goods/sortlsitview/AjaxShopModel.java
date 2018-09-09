package com.goods.sortlsitview;

import com.goods.city.GoodsListModel;
import com.json.NetJsonFiled;

import java.util.ArrayList;

/**
 * Created by 10835 on 2018/9/1.
 */

public class AjaxShopModel {
    @NetJsonFiled
    public String shops;

    @NetJsonFiled(objClassName = "com.goods.sortlsitview.ShopsList")
    public ArrayList<ShopsList> shopsList;//学习列表信息

    @NetJsonFiled(objClassName = "com.goods.city.GoodsListModel")
    public ArrayList<GoodsListModel> list;//学习列表信息

    @NetJsonFiled(objClassName = "com.goods.mineOrderforgoods.OrderModel")
    public ArrayList<OrderModel> data;//学习列表信息
}
