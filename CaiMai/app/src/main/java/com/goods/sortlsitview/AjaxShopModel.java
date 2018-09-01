package com.goods.sortlsitview;

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
}
