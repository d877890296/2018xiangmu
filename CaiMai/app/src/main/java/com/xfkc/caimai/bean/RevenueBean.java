package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

/**
 * 1.我的收益
 * 3.@2018/9/12.
 */

public class RevenueBean extends Response {


    /**
     * retCode : 1
     * data : {"huiyuanCount":0,"shangchengCount":0,"totalCount":0}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * huiyuanCount : 0.0
         * shangchengCount : 0.0
         * totalCount : 0.0
         */

        public double huiyuanCount;
        public double shangchengCount;
        public double totalCount;
    }
}
