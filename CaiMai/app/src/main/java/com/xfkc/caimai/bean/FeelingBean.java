package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * 1.情怀链解析
 * 2.@dongjinxu
 * 3.@2018/9/10.
 */

public class FeelingBean extends Response{


    /**
     * data : {"nextMemUserPage":{"endRow":1,"list":[{"city":"北京市","createTime":1535963673000,"id":22,"inviteCode":"18701546674","kcId":"kc_000021","level":0,"nextMoney":0,"nextNum":0,"phone":"13935972477","province":"北京市","userImg":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180906/1536213384039.jpg","userPwd":"701168c0b138f1758cf2d041273af015"}],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":1,"startRow":1,"total":1},"nextMoney":0,"nextNum":1}
     * retCode : 1
     */

    public DataBean data;

    public static class DataBean {
        /**
         * nextMemUserPage : {"endRow":1,"list":[{"city":"北京市","createTime":1535963673000,"id":22,"inviteCode":"18701546674","kcId":"kc_000021","level":0,"nextMoney":0,"nextNum":0,"phone":"13935972477","province":"北京市","userImg":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180906/1536213384039.jpg","userPwd":"701168c0b138f1758cf2d041273af015"}],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":1,"startRow":1,"total":1}
         * nextMoney : 0.0
         * nextNum : 1
         */

        public NextMemUserPageBean nextMemUserPage;
        public double nextMoney;
        public int nextNum;

        public static class NextMemUserPageBean {
            /**
             * endRow : 1
             * list : [{"city":"北京市","createTime":1535963673000,"id":22,"inviteCode":"18701546674","kcId":"kc_000021","level":0,"nextMoney":0,"nextNum":0,"phone":"13935972477","province":"北京市","userImg":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180906/1536213384039.jpg","userPwd":"701168c0b138f1758cf2d041273af015"}]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 20
             * pages : 1
             * prePage : 0
             * size : 1
             * startRow : 1
             * total : 1
             */

            public int endRow;
            public int nextPage;
            public int pageNum;
            public int pageSize;
            public int pages;
            public int prePage;
            public int size;
            public int startRow;
            public int total;
            public List<ListBean> list;

            public static class ListBean {
                /**
                 * city : 北京市
                 * createTime : 1535963673000
                 * id : 22
                 * inviteCode : 18701546674
                 * kcId : kc_000021
                 * level : 0
                 * nextMoney : 0.0
                 * nextNum : 0
                 * phone : 13935972477
                 * province : 北京市
                 * userImg : http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180906/1536213384039.jpg
                 * userPwd : 701168c0b138f1758cf2d041273af015
                 */

                public String city;
                public long createTime;
                public int id;
                public String inviteCode;
                public String kcId;
                public int level;
                public double nextMoney;
                public int nextNum;
                public String phone;
                public String province;
                public String userImg;
                public String userPwd;
            }
        }
    }
}
