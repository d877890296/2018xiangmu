package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/1.
 */

public class UserInfoBean extends Response {


    /**
     * retCode : 1
     * data : {"id":13,"kcId":"kc_0001","phone":"18701546674","userPwd":"e10adc3949ba59abbe56e057f20f883e","nicName":null,"realName":null,"idCard":null,"userImg":null,"province":"北京市","city":"北京市","area":"昌平","detailAdress":"天通苑","inviteCode":"12345678910","shopId":null,"createTime":null,"userAccount":null,"status":null,"nextNum":0,"level":0,"kbAmount":100000,"cashCoupon":0,"payPwd":"e10adc3949ba59abbe56e057f20f883e"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * id : 13
         * kcId : kc_0001
         * phone : 18701546674
         * userPwd : e10adc3949ba59abbe56e057f20f883e
         * nicName : null
         * realName : null
         * idCard : null
         * userImg : null
         * province : 北京市
         * city : 北京市
         * area : 昌平
         * detailAdress : 天通苑
         * inviteCode : 12345678910
         * shopId : null
         * createTime : null
         * userAccount : null
         * status : null
         * nextNum : 0
         * level : 0
         * kbAmount : 100000.0
         * cashCoupon : 0.0
         * payPwd : e10adc3949ba59abbe56e057f20f883e
         */

        public int id;
        public String kcId;
        public String phone;
        public String userPwd;
        public String nicName;
        public String realName;
        public String idCard;
        public String userImg;
        public String province;
        public String city;
        public String area;
        public String detailAdress;
        public String inviteCode;
        public String shopId;
        public String createTime;
        public String userAccount;
        public String status;
        public int nextNum;
        public int level;
        public double kbAmount;
        public double cashCoupon;
        public String payPwd;
    }
}
