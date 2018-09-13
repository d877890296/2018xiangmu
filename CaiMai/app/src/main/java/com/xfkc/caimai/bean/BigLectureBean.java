package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * 1.类的用途
 * 3.@2018/9/12.
 */

public class BigLectureBean extends Response {


    /**
     * retCode : 1
     * data : [{"id":6,"name":"酷客多培训","image":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180912/1536739915720.jpg","url":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180912/1536739973932.pdf","createTime":1536740484000,"updateTime":1536740484000,"status":1,"type":1},{"id":5,"name":"小程序介绍","image":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180912/1536739810191.jpg","url":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180912/1536739827828.pdf","createTime":1536739826000,"updateTime":1536739826000,"status":1,"type":1},{"id":4,"name":"每晚八点半","image":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180906/1536247577754.jpg","url":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180906/1536247582238.xlsx","createTime":1536247789000,"updateTime":1536247789000,"status":1,"type":1}]
     */

    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 6
         * name : 酷客多培训
         * image : http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180912/1536739915720.jpg
         * url : http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180912/1536739973932.pdf
         * createTime : 1536740484000
         * updateTime : 1536740484000
         * status : 1
         * type : 1
         */

        public int id;
        public String name;
        public String image;
        public String url;
        public long createTime;
        public long updateTime;
        public int status;
        public int type;
        public int collect;//1 收藏  2 未收藏
    }
}
