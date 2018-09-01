package com.goods.model;

import java.util.List;



public class GoodsModel {
public String image,image2;
public String title;
public String goodsName;
public  double goodsPrice,storePrice;
public String goodsMainPhotoId;
public String goodsDetails;
public String goodsProperty;

/**商品id**/
public String goodsId;
/**商城id**/
public String goodsStoreId;


public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getImage2() {
	return image2;
}

public void setImage2(String image2) {
	this.image2 = image2;
}

public String getGoodsName() {
	return goodsName;
}

public void setGoodsName(String goodsName) {
	this.goodsName = goodsName;
}

public double getGoodsPrice() {
	return goodsPrice;
}

public void setGoodsPrice(double goodsPrice) {
	this.goodsPrice = goodsPrice;
}

public String getGoodsMainPhotoId() {
	return goodsMainPhotoId;
}

public void setGoodsMainPhotoId(String goodsMainPhotoId) {
	this.goodsMainPhotoId = goodsMainPhotoId;
}


public String getGoodsId() {
	return goodsId;
}

public void setGoodsId(String goodsId) {
	this.goodsId = goodsId;
}

public String getGoodsStoreId() {
	return goodsStoreId;
}

public void setGoodsStoreId(String goodsStoreId) {
	this.goodsStoreId = goodsStoreId;
}

public String getGoodsDetails() {
	return goodsDetails;
}

public void setGoodsDetails(String goodsDetails) {
	this.goodsDetails = goodsDetails;
}

public double getStorePrice() {
	return storePrice;
}

public void setStorePrice(double storePrice) {
	this.storePrice = storePrice;
}

public String getGoodsProperty() {
	return goodsProperty;
}

public void setGoodsProperty(String goodsProperty) {
	this.goodsProperty = goodsProperty;
}


}
