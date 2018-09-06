package com.goods.city;

import com.json.NetJsonFiled;

/**
 * Created by 10835 on 2018/9/1.
 */

public class GoodsListModel {
    @NetJsonFiled
    public Integer id;//": 1,
    @NetJsonFiled
    public Integer itemId;//": 1133895167,
    @NetJsonFiled
    public String title;//": "甜瓜1133895167",
    @NetJsonFiled
    public String sellPoint;//": "你好",
    @NetJsonFiled
    public Integer category;//": null,
    @NetJsonFiled
    public String pic;//": "",
    @NetJsonFiled
    public Integer status;//": 1,
    @NetJsonFiled
    public Integer createTime;//": "",
    @NetJsonFiled
    public Integer updateTime;//": "",
    @NetJsonFiled
    public Integer scid;//": 1,
    @NetJsonFiled //分期类型
    public Integer mailType;
    @NetJsonFiled
    public Integer itemType;//": 1,
    @NetJsonFiled
    public Integer itemPrice;
    @NetJsonFiled
    public String allParamData;
    @NetJsonFiled
    public String paramData;
    @NetJsonFiled
    public Integer buyNum;
    @NetJsonFiled
    public Integer shopId;
    @NetJsonFiled
    public Integer mailPrice;
    @NetJsonFiled
    public Integer inventory;
    @NetJsonFiled
    public String receiveProvince;
    @NetJsonFiled
    public String unit;
    @NetJsonFiled
    public Integer cid;
    @NetJsonFiled
    public Integer topCategoryId;

    @NetJsonFiled //分期数
    public Integer periodTime;
    @NetJsonFiled //分期数
    public Integer backSelf;
    @NetJsonFiled //分期数
    public Integer saleType;
    @NetJsonFiled //分期数
    public Integer backType;
    @NetJsonFiled //分期数
    public Integer firstBack;
    @NetJsonFiled //分期数
    public Integer secondBack;
    @NetJsonFiled //分期数
    public Integer useType;
    @NetJsonFiled
    public String content;

    @NetJsonFiled
    public Integer secondCategory;


    //一级列表数据
    @NetJsonFiled
    public String cname;//": "水果",
    @NetJsonFiled
    public Integer rank;//": 1,
    //二级列表数据
    @NetJsonFiled
    public String sname;
    @NetJsonFiled
    public Integer categoryId;


}
