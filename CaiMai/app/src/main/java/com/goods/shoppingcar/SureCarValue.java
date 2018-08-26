package com.goods.shoppingcar;

import com.goods.details.ShoppingCarModel;

import java.util.ArrayList;
import java.util.List;



public class SureCarValue {
	public static SureCarValue sureOrderModel;
	public List<ShoppingCarModel> addressData;

	public synchronized static SureCarValue getInstance() {
		if (null == sureOrderModel) {
			sureOrderModel = new SureCarValue();
		}
		return sureOrderModel;
	}

	public void init() {
		addressData = new ArrayList<ShoppingCarModel>();
	}

	public void reSet() {
		if (sureOrderModel != null && addressData != null) {
			addressData.clear();
			addressData = null;
			sureOrderModel = null;
		}
	}

	public List<ShoppingCarModel> getAddressData() {
		return addressData;
	}

	public void setAddressData(ShoppingCarModel model) {
		if (isRepeateData(model.getShopGoodsId()) == false) {
			addressData.add(model);
		} else {
			int id_ = isRepeate(model.getShopGoodsId());
			if (id_ != -1) {
				addressData.remove(id_);
			}
		}

	}

	public void setAllData(List<ShoppingCarModel> data) {
		addressData.clear();
		addressData.addAll(data);
	}

	public void removeAllData() {
		if (sureOrderModel != null && addressData != null) {
			addressData.clear();
		}

	}

	/***
	 * 
	 * 是否有重复对象
	 * 
	 * @param
	 * @return
	 */
	public boolean isRepeateData(String goodsId) {
		for (ShoppingCarModel model : addressData) {
			if (model.getShopGoodsId().equals(goodsId)) {
				return true;
			}
		}
		return false;
	}

	/***
	 * 
	 * 是否有重复对象
	 * 
	 * @param
	 */
	public int isRepeate(String goodsId) {
		int count = addressData.size();
		for (int i = 0; i < count; i++) {
			ShoppingCarModel model = addressData.get(i);
			if (model.getShopGoodsId().equals(goodsId)) {
				return i;
			}
		}
		return -1;
	}
}
