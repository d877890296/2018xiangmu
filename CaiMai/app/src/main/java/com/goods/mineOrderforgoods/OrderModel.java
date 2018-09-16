package com.goods.mineOrderforgoods;

import com.goods.city.GoodsListModel;
import com.json.NetJsonFiled;

import java.util.ArrayList;

/**
 * Created by 10835 on 2018/9/8.
 *
 * com.goods.mineOrderforgoods.OrderModel
 */

public class OrderModel {
    @NetJsonFiled
    public Integer  id;//": 22,
    @NetJsonFiled
    public Integer  memUserId;//": 19,
    @NetJsonFiled
    public String  orderNum;//": "20180904200127346249764",
    @NetJsonFiled
    public Double price;//": 111.0,
    @NetJsonFiled
    public Double freight;//": 1111.0,
    @NetJsonFiled
    public Integer shopsId;//": 12,
    @NetJsonFiled
    public Integer  status;//": 1,
    @NetJsonFiled
    public Integer  pickupWay;//": 2,
    @NetJsonFiled
    public Integer  paymentWay;//": 2,
    @NetJsonFiled
    public String  note;//": "fdgdfg",
    @NetJsonFiled
    public Integer receiveAdressId;//": 7,
    @NetJsonFiled
    public String courierName;//: null,
    @NetJsonFiled
    public Integer courierNum;//": null,

    @NetJsonFiled(objClassName = "com.goods.city.GoodsListModel")
    public ArrayList<GoodsListModel> itemOrderDetailList;//学习列表信息

    @NetJsonFiled
    public long createTime;//": null,

    @NetJsonFiled
    public long paymentEndTime;//": null,
}
