package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/3.
 */

public class TopCategory extends Response {


    /**
     * retCode : 1
     * data : {"total":4,"list":[{"id":1,"name":"幸福仓库","rank":1,"status":1},{"id":2,"name":"物尽其用","rank":2,"status":1},{"id":3,"name":"人尽其才","rank":3,"status":1},{"id":4,"name":"药类超市","rank":4,"status":1}],"pageNum":1,"pageSize":4,"size":4,"startRow":0,"endRow":3,"pages":1,"prePage":0,"nextPage":0}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * total : 4
         * list : [{"id":1,"name":"幸福仓库","rank":1,"status":1},{"id":2,"name":"物尽其用","rank":2,"status":1},{"id":3,"name":"人尽其才","rank":3,"status":1},{"id":4,"name":"药类超市","rank":4,"status":1}]
         * pageNum : 1
         * pageSize : 4
         * size : 4
         * startRow : 0
         * endRow : 3
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
             * name : 幸福仓库
             * rank : 1
             * status : 1
             */

            public int id;
            public String name;
            public int rank;
            public int status;
        }
    }
}
