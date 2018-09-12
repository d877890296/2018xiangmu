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
     * retCode : 1
     * data : {"nextMoney":0,"nextMemUserPage":{"total":0,"list":[{"id":18,"kcId":"18701546674","phone":"18701546674","userPwd":"469ec3b15de6d1d259268b8126df9e90","nicName":"123","realName":"48694649464","idCard":"230202199508101222","userImg":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180906/1536213384039.jpg","province":"北京市","city":"北京市","area":"朝阳区","detailAdress":"北京市-北京市-朝阳区","inviteCode":"13163214367","shopId":0,"createTime":1535815853000,"userAccount":null,"status":null,"nextNum":1,"level":0,"nextMoney":0}],"pageNum":0,"pageSize":20,"size":1,"startRow":1,"endRow":1,"pages":0,"prePage":0,"nextPage":0},"nextNum":0}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * nextMoney : 0.0
         * nextMemUserPage : {"total":0,"list":[{"id":18,"kcId":"18701546674","phone":"18701546674","userPwd":"469ec3b15de6d1d259268b8126df9e90","nicName":"123","realName":"48694649464","idCard":"230202199508101222","userImg":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180906/1536213384039.jpg","province":"北京市","city":"北京市","area":"朝阳区","detailAdress":"北京市-北京市-朝阳区","inviteCode":"13163214367","shopId":0,"createTime":1535815853000,"userAccount":null,"status":null,"nextNum":1,"level":0,"nextMoney":0}],"pageNum":0,"pageSize":20,"size":1,"startRow":1,"endRow":1,"pages":0,"prePage":0,"nextPage":0}
         * nextNum : 0
         */

        public double nextMoney;
        public NextMemUserPageBean nextMemUserPage;
        public int nextNum;

        public static class NextMemUserPageBean {
            /**
             * total : 0
             * list : [{"id":18,"kcId":"18701546674","phone":"18701546674","userPwd":"469ec3b15de6d1d259268b8126df9e90","nicName":"123","realName":"48694649464","idCard":"230202199508101222","userImg":"http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180906/1536213384039.jpg","province":"北京市","city":"北京市","area":"朝阳区","detailAdress":"北京市-北京市-朝阳区","inviteCode":"13163214367","shopId":0,"createTime":1535815853000,"userAccount":null,"status":null,"nextNum":1,"level":0,"nextMoney":0}]
             * pageNum : 0
             * pageSize : 20
             * size : 1
             * startRow : 1
             * endRow : 1
             * pages : 0
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
                 * id : 18
                 * kcId : 18701546674
                 * phone : 18701546674
                 * userPwd : 469ec3b15de6d1d259268b8126df9e90
                 * nicName : 123
                 * realName : 48694649464
                 * idCard : 230202199508101222
                 * userImg : http://xf-kc.oss-cn-beijing.aliyuncs.com/image/20180906/1536213384039.jpg
                 * province : 北京市
                 * city : 北京市
                 * area : 朝阳区
                 * detailAdress : 北京市-北京市-朝阳区
                 * inviteCode : 13163214367
                 * shopId : 0
                 * createTime : 1535815853000
                 * userAccount : null
                 * status : null
                 * nextNum : 1
                 * level : 0
                 * nextMoney : 0.0
                 */

                public int id;
                public String kcId;
                public String phone;
                public String userPwd;
                public String nicName;
                public String realName;
                public String idCard;
                public String userImg;
                public String province;
                public String city;
                public String area;
                public String detailAdress;
                public String inviteCode;
                public int shopId;
                public long createTime;
                public String userAccount;
                public String status;
                public int nextNum;
                public int level;
                public double nextMoney;
            }
        }
    }
}
