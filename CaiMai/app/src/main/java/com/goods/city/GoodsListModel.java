package com.goods.city;

import com.json.NetJsonFiled;

/**
 * Created by 10835 on 2018/9/1.
 */

public class GoodsListModel {
    @NetJsonFiled
    public  int  id;//": 1,

    @NetJsonFiled
    public  int   shopId;
    @NetJsonFiled
    public  int itemId;//": 1133895167,
    @NetJsonFiled
    public  String  title;//": "甜瓜1133895167",
    @NetJsonFiled
    public  String sellPoint;//": "你好",
    @NetJsonFiled
    public  String category;//": null,
    @NetJsonFiled
    public  String pic;//": "",
    @NetJsonFiled
    public int status;//": 1,
    @NetJsonFiled
    public String  itemPrice;
    @NetJsonFiled
    public String  content;

    //一级列表数据
    @NetJsonFiled
    public String cname;//": "水果",
    @NetJsonFiled
    public int rank;//": 1,

    //二级列表数据
    @NetJsonFiled
    public String sname;
    @NetJsonFiled
    public  int categoryId;

}
