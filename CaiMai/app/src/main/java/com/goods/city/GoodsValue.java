package com.goods.city;

import com.goods.details.ShoppingCarModel;

import java.util.ArrayList;
import java.util.List;

/****
 * 商品传值
 */
public class GoodsValue {
	public static GoodsValue sureOrderModel;
	public GoodsListModel goodsListModel;

	public synchronized static GoodsValue getInstance() {
		if (null == sureOrderModel) {
			sureOrderModel = new GoodsValue();
		}
		return sureOrderModel;
	}

	public void init() {

	}

	public void reSet() {
		if (sureOrderModel != null && goodsListModel != null) {

			goodsListModel = null;
			sureOrderModel = null;
		}
	}

	public GoodsListModel getGoodsListModel() {
		return goodsListModel;
	}

	public void setGoodsListModel(GoodsListModel goodsListModel) {
		this.goodsListModel = goodsListModel;
	}





}
