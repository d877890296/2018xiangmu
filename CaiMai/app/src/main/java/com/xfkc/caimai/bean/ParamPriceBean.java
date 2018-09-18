package com.xfkc.caimai.bean;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/17.
 */

public class ParamPriceBean  {


    /**
     * message : 查询成功
     * retCode : 1
     * data : {"id":120,"shopId":21,"shop":null,"shopItemId":19,"itemId":1536308289,"item":null,"itemPriceId":14,"inventory":14,"createTime":1536928853000,"updateTime":1536928853000,"paramData":"[\"32g\",\"16g\",\"红色\"]","price":16}
     */

    public String message;
    public int retCode;
    public DataBean data;

    public static class DataBean {
        /**
         * id : 120
         * shopId : 21
         * shop : null
         * shopItemId : 19
         * itemId : 1536308289
         * item : null
         * itemPriceId : 14
         * inventory : 14
         * createTime : 1536928853000
         * updateTime : 1536928853000
         * paramData : ["32g","16g","红色"]
         * price : 16.0
         */

        public int id;
        public int shopId;
        public Object shop;
        public int shopItemId;
        public int itemId;
        public Object item;
        public int itemPriceId;
        public int inventory;
        public long createTime;
        public long updateTime;
        public String paramData;
        public double price;
    }
}
