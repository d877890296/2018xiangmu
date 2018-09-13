package com.xfkc.caimai.bean;

import java.util.List;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/13.
 */

public class LogisticsBean {


    /**
     * data : [{"date":"2016-09-25 19:32:23","logisticsInfo":"快件已到达【合肥包河二部】"},{"date":"2014-08-14 21:10:37","logisticsInfo":"快件由【杭州分拨 】发往【苏州分拨】"}]
     * message : 成功
     * retCode : 1
     */

    public String message;
    public int retCode;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * date : 2016-09-25 19:32:23
         * logisticsInfo : 快件已到达【合肥包河二部】
         */

        public String date;
        public String logisticsInfo;
    }
}
