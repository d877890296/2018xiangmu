package com.goods.city;

import com.json.NetJsonFiled;

/**
 * Created by 10835 on 2018/9/1.
 */

public class GoodsListModel {
    @NetJsonFiled
    public int id;//": 1,
    @NetJsonFiled
    public int itemId;//": 1133895167,
    @NetJsonFiled
    public String title;//": "甜瓜1133895167",
    @NetJsonFiled
    public String sellPoint;//": "你好",
    @NetJsonFiled
    public String category;//": null,
    @NetJsonFiled
    public String pic;//": "",
    @NetJsonFiled
    public int status;//": 1,
    @NetJsonFiled
    public int createTime;//": "",
    @NetJsonFiled
    public int updateTime;//": "",
    @NetJsonFiled
    public int scid;//": 1,
    @NetJsonFiled //分期类型
    public int mailType;
    @NetJsonFiled
    public int itemType;//": 1,
    @NetJsonFiled
    public int itemPrice;
    @NetJsonFiled
    public String allParamData;
    @NetJsonFiled
    public String paramData;
    @NetJsonFiled
    public int buyNum;
    @NetJsonFiled
    public int shopId;
    @NetJsonFiled
    public int mailPrice;
    @NetJsonFiled
    public int inventory;
    @NetJsonFiled
    public String receiveProvince;
    @NetJsonFiled
    public String unit;
    @NetJsonFiled
    public int cid;
    @NetJsonFiled
    public int topCategoryId;

    @NetJsonFiled //分期数
    public int periodTime;
    @NetJsonFiled //分期数
    public double backSelf;
    @NetJsonFiled //分期数
    public int saleType;
    @NetJsonFiled //分期数
    public int backType;
    @NetJsonFiled //分期数
    public int firstBack;
    @NetJsonFiled //分期数
    public int secondBack;
    @NetJsonFiled //分期数
    public int useType;
    @NetJsonFiled
    public String content;

    @NetJsonFiled
    public String secondCategory;


    //一级列表数据
    @NetJsonFiled
    public String cname;//": "水果",
    @NetJsonFiled
    public int rank;//": 1,
    //二级列表数据
    @NetJsonFiled
    public String sname;
    @NetJsonFiled
    public int categoryId;


}
