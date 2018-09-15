package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/15.
 */

public class CollectBean extends Response {


    /**
     * retCode : 1
     * data : {"total":1,"list":[{"id":3,"name":"好看的视频","image":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180827/1535368195641.png","url":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180913/1536815579694.docx","createTime":1535368289000,"updateTime":1535368289000,"status":1,"type":0,"collect":null}],"pageNum":0,"pageSize":20,"size":1,"startRow":1,"endRow":1,"pages":1,"prePage":0,"nextPage":0}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * total : 1
         * list : [{"id":3,"name":"好看的视频","image":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180827/1535368195641.png","url":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180913/1536815579694.docx","createTime":1535368289000,"updateTime":1535368289000,"status":1,"type":0,"collect":null}]
         * pageNum : 0
         * pageSize : 20
         * size : 1
         * startRow : 1
         * endRow : 1
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
             * id : 3
             * name : 好看的视频
             * image : http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180827/1535368195641.png
             * url : http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180913/1536815579694.docx
             * createTime : 1535368289000
             * updateTime : 1535368289000
             * status : 1
             * type : 0
             * collect : null
             */

            public int id;
            public String name;
            public String image;
            public String url;
            public long createTime;
            public long updateTime;
            public int status;
            public int type;
            public Object collect;
        }
    }
}
