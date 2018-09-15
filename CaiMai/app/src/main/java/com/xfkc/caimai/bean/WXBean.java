package com.xfkc.caimai.bean;

import com.google.gson.annotations.SerializedName;
import com.xfkc.caimai.net.Response;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/10.
 */

public class WXBean extends Response {


    /**
     * retCode : 1
     * data : {"appid":"wxa42ad5697b6659a2","noncestr":"5eLoGHVGP34qcmv2","package":"Sign=WXPay","partnerid":"1491269672","prepayid":"wx142207574656983d7621819c4042256882","sign":"49018B6508C77B233C187080D78C3CDA","timestamp":1536934078}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * appid : wxa42ad5697b6659a2
         * noncestr : 5eLoGHVGP34qcmv2
         * package : Sign=WXPay
         * partnerid : 1491269672
         * prepayid : wx142207574656983d7621819c4042256882
         * sign : 49018B6508C77B233C187080D78C3CDA
         * timestamp : 1536934078
         */

        public String appid;
        public String noncestr;
        @SerializedName("package")
        public String packageX;
        public String partnerid;
        public String prepayid;
        public String sign;
        public String timestamp;
    }
}
