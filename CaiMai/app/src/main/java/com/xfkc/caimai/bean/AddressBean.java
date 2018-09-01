package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/2.
 */

public class AddressBean extends Response {


    /**
     * retCode : 1
     * data : [{"id":5,"receiveName":"dong","phone":"18701546674","province":"北京市","city":"北京市","area":"朝阳区","detailAdress":"北京市-北京市-朝阳区  1231132","createTime":1535818379000,"acquiesce":1,"memUserId":18},{"id":6,"receiveName":"dong","phone":"18701546674","province":"北京市","city":"北京市","area":"朝阳区","detailAdress":"北京市-北京市-朝阳区  1231132","createTime":1535818396000,"acquiesce":2,"memUserId":18}]
     */

    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 5
         * receiveName : dong
         * phone : 18701546674
         * province : 北京市
         * city : 北京市
         * area : 朝阳区
         * detailAdress : 北京市-北京市-朝阳区  1231132
         * createTime : 1535818379000
         * acquiesce : 1
         * memUserId : 18
         */

        public int id;
        public String receiveName;
        public String phone;
        public String province;
        public String city;
        public String area;
        public String detailAdress;
        public long createTime;
        public int acquiesce;
        public int memUserId;
    }
}
