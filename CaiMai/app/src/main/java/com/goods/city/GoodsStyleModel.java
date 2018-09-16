package com.goods.city;

/**
 * Created by 10835 on 2018/8/30.
 */

public class GoodsStyleModel {

    public String styleName;
    /** 店id **/
    public String styleId;
    public String categoryId;
    /** 店中分类 **/
    public String goodsName;
    public String headImg;
    public String image;

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public String getStyleId() {
        return styleId;
    }

    public String getStyleName() {
        return styleName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
