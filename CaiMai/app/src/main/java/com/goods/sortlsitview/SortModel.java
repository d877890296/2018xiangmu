package com.goods.sortlsitview;

import com.json.NetJsonFiled;

import java.util.ArrayList;

public class SortModel {
    private String name;   //显示的数据
    private String sortLetters;  //显示数据拼音的首字母
    private String shopId;
    private String provinceId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
