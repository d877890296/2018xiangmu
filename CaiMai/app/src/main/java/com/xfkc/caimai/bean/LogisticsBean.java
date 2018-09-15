package com.xfkc.caimai.bean;

import java.util.List;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/13.
 */

public class LogisticsBean {

    /**
     * message : 成功
     * retCode : 1
     * data : {"courierName":null,"courierNum":"70376119678650","logisticsInfo":[{"date":"2017-11-25 19:33:05","logisticsInfo":"[宜春市]宜春市【高安】，合作门店65栋小邹代 已签收"},{"date":"2017-11-24 10:00:11","logisticsInfo":"[宜春市]宜春市【高安】，【邹正淳/18779862918】正在派件"},{"date":"2017-11-23 06:11:30","logisticsInfo":"[南昌市]南昌市【南昌转运中心】，正发往【高安】"},{"date":"2017-11-23 04:39:56","logisticsInfo":"[南昌市]到南昌市【南昌转运中心】"},{"date":"2017-11-22 09:34:43","logisticsInfo":"[临沂市]临沂市【临沂转运中心】，正发往【南昌转运中心】"},{"date":"2017-11-21 21:02:42","logisticsInfo":"[临沂市]到临沂市【临沂转运中心】"},{"date":"2017-11-21 16:07:04","logisticsInfo":"[临沂市]临沂市【临沂罗庄一部】，【陈经理/18653925850】已揽收"}]}
     */

    public String message;
    public int retCode;
    public DataBean data;

    public static class DataBean {
        /**
         * courierName : null
         * courierNum : 70376119678650
         * logisticsInfo : [{"date":"2017-11-25 19:33:05","logisticsInfo":"[宜春市]宜春市【高安】，合作门店65栋小邹代 已签收"},{"date":"2017-11-24 10:00:11","logisticsInfo":"[宜春市]宜春市【高安】，【邹正淳/18779862918】正在派件"},{"date":"2017-11-23 06:11:30","logisticsInfo":"[南昌市]南昌市【南昌转运中心】，正发往【高安】"},{"date":"2017-11-23 04:39:56","logisticsInfo":"[南昌市]到南昌市【南昌转运中心】"},{"date":"2017-11-22 09:34:43","logisticsInfo":"[临沂市]临沂市【临沂转运中心】，正发往【南昌转运中心】"},{"date":"2017-11-21 21:02:42","logisticsInfo":"[临沂市]到临沂市【临沂转运中心】"},{"date":"2017-11-21 16:07:04","logisticsInfo":"[临沂市]临沂市【临沂罗庄一部】，【陈经理/18653925850】已揽收"}]
         */

        public String courierName;
        public String stateDesc;
        public String courierNum;
        public List<LogisticsInfoBean> logisticsInfo;

        public static class LogisticsInfoBean {
            /**
             * date : 2017-11-25 19:33:05
             * logisticsInfo : [宜春市]宜春市【高安】，合作门店65栋小邹代 已签收
             */

            public String date;
            public String logisticsInfo;
        }
    }
}
