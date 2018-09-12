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
     * data : {"appId":"wxa42ad5697b6659a2","nonceStr":"pHyA4VVtbqxz3O4z","prepay_id":null,"signType":"MD5","timeStamp":1536588779}
     */
    public DataBean data;

    public static class DataBean {
        /**
         * appId : wxa42ad5697b6659a2
         * nonceStr : pHyA4VVtbqxz3O4z
         * prepay_id : null
         * signType : MD5
         * timeStamp : 1536588779
         */

        public String appId;
        public String nonceStr;
        public String prepay_id;
        public String signType;
        public int timeStamp;
    }
}
