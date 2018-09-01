package com.goods.mineOrderforgoods;

import com.goods.details.ShoppingCarModel;

import java.util.List;



/***
 * 
 * 商品模型
 * 
 * 
 * @author lyy
 *
 */
public class GoodsDataModel {
	public String orderId;
	public String id;
	public List<ShoppingCarModel> addressData;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<ShoppingCarModel> getAddressData() {
		return addressData;
	}

	public void setAddressData(List<ShoppingCarModel> addressData) {
		this.addressData = addressData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
