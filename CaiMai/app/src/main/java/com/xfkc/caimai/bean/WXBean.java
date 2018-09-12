package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/10.
 */

public class WXBean extends Response {


    /**
     * retCode : 1
     * data : {"appId":"wxa42ad5697b6659a2","nonceStr":"rszWeNU3rBEvStqD","partnerid":"1491269672","prepay_id":"wx122312544135533eba314f853517509374","sign":"6B8CE0480B398F7C6C9E1455C69AFA48","signType":"MD5","timeStamp":1536765174}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * appId : wxa42ad5697b6659a2
         * nonceStr : rszWeNU3rBEvStqD
         * partnerid : 1491269672
         * prepay_id : wx122312544135533eba314f853517509374
         * sign : 6B8CE0480B398F7C6C9E1455C69AFA48
         * signType : MD5
         * timeStamp : 1536765174
         */

        public String appId;
        public String nonceStr;
        public String partnerid;
        public String prepay_id;
        public String sign;
        public String signType;
        public int timeStamp;
    }
}
