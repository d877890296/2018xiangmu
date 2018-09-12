package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/4.
 */

public class MineVipCardBean extends Response {


    /**
     * retCode : 1
     * data : [{"id":null,"cardName":"理发卡","cardPrice":0.01,"interests":"免运费","cardTypeName":null,"carTypeId":1,"cardStatus":1,"carRebate":null,"createTime":null,"createBy":null,"type":null,"startTime":1535904000000,"endTime":1567440000000,"remainDays":363}]
     */

    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : null
         * cardName : 理发卡
         * cardPrice : 0.01
         * interests : 免运费
         * cardTypeName : null
         * carTypeId : 1
         * cardStatus : 1
         * carRebate : null
         * createTime : null
         * createBy : null
         * type : null
         * startTime : 1535904000000
         * endTime : 1567440000000
         * remainDays : 363
         */

        public String id;
        public String cardName;//会员卡名字
        public double cardPrice;//会员卡价格
        public String interests;//会员卡权益
        public String cardTypeName;//会员类别名
        public int carTypeId;//会员卡类别
        public int cardStatus;//会员卡状态  1弃用  0未启用
        public String carRebate;//返佣
        public String createTime;//创建时间
        public String createBy;
        public String type;
        public long startTime;
        public long endTime;
        public int remainDays;//剩余天数
    }
}
