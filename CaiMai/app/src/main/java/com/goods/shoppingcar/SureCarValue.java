package com.goods.shoppingcar;

import com.goods.city.GoodsListModel;
import com.goods.details.ShoppingCarModel;

import java.util.ArrayList;
import java.util.List;



public class SureCarValue {
	public static SureCarValue sureOrderModel;
	public List<GoodsListModel> addressData;

	public synchronized static SureCarValue getInstance() {
		if (null == sureOrderModel) {
			sureOrderModel = new SureCarValue();
		}
		return sureOrderModel;
	}

	public void init() {
		addressData = new ArrayList<GoodsListModel>();
	}

	public void reSet() {
		if (sureOrderModel != null && addressData != null) {
			addressData.clear();
			addressData = null;
			sureOrderModel = null;
		}
	}

	public List<GoodsListModel> getAddressData() {
		return addressData;
	}

	public void setAddressData(GoodsListModel model) {
		if (isRepeateData(model.id+"") == false) {
			addressData.add(model);
		} else {
			int id_ = isRepeate(model.id+"");
			if (id_ != -1) {
				addressData.remove(id_);
			}
		}

	}

	public void setAllData(List<GoodsListModel> data) {
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
		for (GoodsListModel model : addressData) {
			if (model.id.equals(goodsId)) {
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
			GoodsListModel model = addressData.get(i);
			if (model.id.equals(goodsId)) {
				return i;
			}
		}
		return -1;
	}
}
