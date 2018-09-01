package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * 1.轮播图
 * 2.@dongjinxu
 * 3.@2018/9/1.
 */

public class BannerBean extends Response {


    /**
     * retCode : 1
     * data : {"total":2,"list":[{"id":1,"title":"开业活动","url":null,"startTime":1535525707000,"endTime":1535525711000,"status":1,"createTime":1535525715000,"image":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180827/1535368195641.png"},{"id":2,"title":"开门大吉","url":"http://www.360doc.","startTime":1535541468000,"endTime":1541762273000,"status":1,"createTime":1535541555000,"image":""}],"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"pages":1,"prePage":0,"nextPage":0}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * total : 2
         * list : [{"id":1,"title":"开业活动","url":null,"startTime":1535525707000,"endTime":1535525711000,"status":1,"createTime":1535525715000,"image":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180827/1535368195641.png"},{"id":2,"title":"开门大吉","url":"http://www.360doc.","startTime":1535541468000,"endTime":1541762273000,"status":1,"createTime":1535541555000,"image":""}]
         * pageNum : 1
         * pageSize : 10
         * size : 2
         * startRow : 1
         * endRow : 2
         * pages : 1
         * prePage : 0
         * nextPage : 0
         */

        public int total;
        public int pageNum;
        public int pageSize;
        public int size;
        public int startRow;
        public int endRow;
        public int pages;
        public int prePage;
        public int nextPage;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * id : 1
             * title : 开业活动
             * url : null
             * startTime : 1535525707000
             * endTime : 1535525711000
             * status : 1
             * createTime : 1535525715000
             * image : http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180827/1535368195641.png
             */

            public int id;
            public String title;
            public Object url;
            public long startTime;
            public long endTime;
            public int status;
            public long createTime;
            public String image;
        }
    }
}
