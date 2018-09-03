package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/3.
 */

public class RegistBean extends Response {


    /**
     * retCode : 1
     * data : {"id":22,"kcId":"kc_000021","phone":"13935972477","userPwd":null,"nicName":null,"realName":null,"idCard":null,"userImg":null,"province":null,"city":null,"area":null,"detailAdress":null,"inviteCode":"18701546674","shopId":null,"createTime":1535963672942,"userAccount":null,"status":1,"nextNum":0,"level":0}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * id : 22
         * kcId : kc_000021
         * phone : 13935972477
         * userPwd : null
         * nicName : null
         * realName : null
         * idCard : null
         * userImg : null
         * province : null
         * city : null
         * area : null
         * detailAdress : null
         * inviteCode : 18701546674
         * shopId : null
         * createTime : 1535963672942
         * userAccount : null
         * status : 1
         * nextNum : 0
         * level : 0
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
        public long createTime;
        public String userAccount;
        public int status;
        public int nextNum;
        public int level;
    }
}
