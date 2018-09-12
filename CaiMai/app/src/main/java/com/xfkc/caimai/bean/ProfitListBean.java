package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/12.
 */

public class ProfitListBean extends Response {


    /**
     * retCode : 1
     * data : {"total":2,"list":[{"id":54,"orderNum":"20180911231339797428706","memUserId":26,"tradeType":3,"tradeWay":3,"tradeMoney":3.0E-4,"createTime":1536679074000,"tradeComment":"会员充值返佣","payType":1,"rebateMemUserId":16,"rebateType":2,"rebateWay":1},{"id":57,"orderNum":"20180912011606098449706","memUserId":26,"tradeType":3,"tradeWay":3,"tradeMoney":3.0E-4,"createTime":1536686173000,"tradeComment":"会员充值返佣","payType":1,"rebateMemUserId":16,"rebateType":2,"rebateWay":1}],"pageNum":1,"pageSize":20,"size":2,"startRow":1,"endRow":2,"pages":1,"prePage":0,"nextPage":0}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * total : 2
         * list : [{"id":54,"orderNum":"20180911231339797428706","memUserId":26,"tradeType":3,"tradeWay":3,"tradeMoney":3.0E-4,"createTime":1536679074000,"tradeComment":"会员充值返佣","payType":1,"rebateMemUserId":16,"rebateType":2,"rebateWay":1},{"id":57,"orderNum":"20180912011606098449706","memUserId":26,"tradeType":3,"tradeWay":3,"tradeMoney":3.0E-4,"createTime":1536686173000,"tradeComment":"会员充值返佣","payType":1,"rebateMemUserId":16,"rebateType":2,"rebateWay":1}]
         * pageNum : 1
         * pageSize : 20
         * size : 2
         * startRow : 1
         * endRow : 2
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
             * id : 54
             * orderNum : 20180911231339797428706
             * memUserId : 26
             * tradeType : 3
             * tradeWay : 3
             * tradeMoney : 3.0E-4
             * createTime : 1536679074000
             * tradeComment : 会员充值返佣
             * payType : 1
             * rebateMemUserId : 16
             * rebateType : 2
             * rebateWay : 1
             */

            public int id;
            public String orderNum;
            public int memUserId;
            public int tradeType;
            public int tradeWay;
            public double tradeMoney;
            public long createTime;
            public String tradeComment;
            public int payType;
            public int rebateMemUserId;
            public int rebateType;
            public int rebateWay;
        }
    }
}
