package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * 1.会员卡
 * 3.@2018/8/30.
 */

public class VipCardBean extends Response {


    /**
     * retCode : 1
     * data : {"total":6,"list":[{"id":1,"cardName":"理发卡","cardPrice":0.01,"interests":"免运费","cardTypeName":null,"carTypeId":1,"cardStatus":1,"carRebate":null,"createTime":1532680459000,"createBy":null,"type":null},{"id":9,"cardName":"大家好","cardPrice":0.01,"interests":"黄金特权","cardTypeName":null,"carTypeId":1,"cardStatus":1,"carRebate":null,"createTime":1533906346000,"createBy":null,"type":null},{"id":10,"cardName":"你好","cardPrice":0.01,"interests":"你好大家好","cardTypeName":null,"carTypeId":1,"cardStatus":1,"carRebate":null,"createTime":1533906513000,"createBy":null,"type":null},{"id":11,"cardName":"你好","cardPrice":0.01,"interests":"你好大家好","cardTypeName":null,"carTypeId":1,"cardStatus":1,"carRebate":null,"createTime":1533906523000,"createBy":null,"type":null},{"id":12,"cardName":"你好","cardPrice":0.01,"interests":"你好大家好","cardTypeName":null,"carTypeId":1,"cardStatus":0,"carRebate":null,"createTime":1533906636000,"createBy":null,"type":null},{"id":13,"cardName":"你好","cardPrice":0.01,"interests":"你好大家好","cardTypeName":null,"carTypeId":1,"cardStatus":0,"carRebate":null,"createTime":1533906724000,"createBy":null,"type":null}],"pageNum":1,"pageSize":20,"size":6,"startRow":1,"endRow":6,"pages":1,"prePage":0,"nextPage":0}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * total : 6
         * list : [{"id":1,"cardName":"理发卡","cardPrice":0.01,"interests":"免运费","cardTypeName":null,"carTypeId":1,"cardStatus":1,"carRebate":null,"createTime":1532680459000,"createBy":null,"type":null},{"id":9,"cardName":"大家好","cardPrice":0.01,"interests":"黄金特权","cardTypeName":null,"carTypeId":1,"cardStatus":1,"carRebate":null,"createTime":1533906346000,"createBy":null,"type":null},{"id":10,"cardName":"你好","cardPrice":0.01,"interests":"你好大家好","cardTypeName":null,"carTypeId":1,"cardStatus":1,"carRebate":null,"createTime":1533906513000,"createBy":null,"type":null},{"id":11,"cardName":"你好","cardPrice":0.01,"interests":"你好大家好","cardTypeName":null,"carTypeId":1,"cardStatus":1,"carRebate":null,"createTime":1533906523000,"createBy":null,"type":null},{"id":12,"cardName":"你好","cardPrice":0.01,"interests":"你好大家好","cardTypeName":null,"carTypeId":1,"cardStatus":0,"carRebate":null,"createTime":1533906636000,"createBy":null,"type":null},{"id":13,"cardName":"你好","cardPrice":0.01,"interests":"你好大家好","cardTypeName":null,"carTypeId":1,"cardStatus":0,"carRebate":null,"createTime":1533906724000,"createBy":null,"type":null}]
         * pageNum : 1
         * pageSize : 20
         * size : 6
         * startRow : 1
         * endRow : 6
         * pages : 1
         * prePage : 0
         * nextPage : 0
         */

        public int total;
        public int pageNum;
        public int pageSize;
        public int size;
        public int startRow;
        public int endRow;
        public int pages;
        public int prePage;
        public int nextPage;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * id : 1
             * cardName : 理发卡
             * cardPrice : 0.01
             * interests : 免运费
             * cardTypeName : null
             * carTypeId : 1
             * cardStatus : 1
             * carRebate : null
             * createTime : 1532680459000
             * createBy : null
             * type : null
             */

            public int id;
            public String cardName;
            public double cardPrice;
            public String interests;
            public String cardTypeName;
            public int carTypeId;
            public int cardStatus;
            public String carRebate;
            public long createTime;
            public String createBy;
            public String type;
        }
    }
}
