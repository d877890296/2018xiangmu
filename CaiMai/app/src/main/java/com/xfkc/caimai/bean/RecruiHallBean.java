package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.io.Serializable;
import java.util.List;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/1.
 */

public class RecruiHallBean extends Response {


    /**
     * data : {"endRow":1,"list":[{"inrecruiList":[{"cityId":"北京市","createTime":1536236640000,"joinPersonNumber":0,"kangbiCount":1000,"latitude":39.963839,"longitude":116.374517,"partnerType":"A","personNumber":10,"provinceId":"北京","rate":10,"shopAddress":"北京市北京市海淀区学院南路6号","shopId":22,"shopName":"幸福三号店铺","shopStatus":2,"shopkeeperName":"张先生","status":1,"telephone":"18210987756","type":0,"updateTime":1536478817000,"welfareUnit":1},{"cityId":"北京市","createTime":1536236640000,"joinPersonNumber":0,"kangbiCount":2000,"latitude":39.963839,"longitude":116.374517,"partnerType":"B","personNumber":20,"provinceId":"北京","shopAddress":"北京市北京市海淀区学院南路6号","shopId":22,"shopName":"幸福三号店铺","shopStatus":2,"shopkeeperName":"张先生","status":1,"telephone":"18210987756","type":1,"updateTime":1536478817000,"welfareUnit":1}],"shopName":"幸福三号店铺"},{"inrecruiList":[{"cityId":"北京市","createTime":1536163111000,"joinPersonNumber":0,"kangbiCount":100000,"latitude":39.933748,"longitude":116.393489,"partnerType":"A","personNumber":20000,"provinceId":"北京","rate":3,"shopAddress":"北京市北京市西城区","shopId":21,"shopName":"幸福一号店铺","shopStatus":2,"shopkeeperName":"李先生","status":1,"telephone":"18756098756","type":0,"updateTime":1536478828000,"welfareUnit":1},{"cityId":"北京市","createTime":1536163111000,"joinPersonNumber":0,"kangbiCount":2000,"latitude":39.933748,"longitude":116.393489,"partnerType":"B","personNumber":50,"provinceId":"北京","shopAddress":"北京市北京市西城区","shopId":21,"shopName":"幸福一号店铺","shopStatus":2,"shopkeeperName":"李先生","status":1,"telephone":"18756098756","type":1,"updateTime":1536478828000,"welfareUnit":1}],"shopName":"幸福一号店铺"}],"nextPage":0,"pageNum":1,"pageSize":2,"pages":1,"prePage":0,"size":2,"startRow":0,"total":2}
     * retCode : 1
     */

    public DataBean data;

    public static class DataBean {
        /**
         * endRow : 1
         * list : [{"inrecruiList":[{"cityId":"北京市","createTime":1536236640000,"joinPersonNumber":0,"kangbiCount":1000,"latitude":39.963839,"longitude":116.374517,"partnerType":"A","personNumber":10,"provinceId":"北京","rate":10,"shopAddress":"北京市北京市海淀区学院南路6号","shopId":22,"shopName":"幸福三号店铺","shopStatus":2,"shopkeeperName":"张先生","status":1,"telephone":"18210987756","type":0,"updateTime":1536478817000,"welfareUnit":1},{"cityId":"北京市","createTime":1536236640000,"joinPersonNumber":0,"kangbiCount":2000,"latitude":39.963839,"longitude":116.374517,"partnerType":"B","personNumber":20,"provinceId":"北京","shopAddress":"北京市北京市海淀区学院南路6号","shopId":22,"shopName":"幸福三号店铺","shopStatus":2,"shopkeeperName":"张先生","status":1,"telephone":"18210987756","type":1,"updateTime":1536478817000,"welfareUnit":1}],"shopName":"幸福三号店铺"},{"inrecruiList":[{"cityId":"北京市","createTime":1536163111000,"joinPersonNumber":0,"kangbiCount":100000,"latitude":39.933748,"longitude":116.393489,"partnerType":"A","personNumber":20000,"provinceId":"北京","rate":3,"shopAddress":"北京市北京市西城区","shopId":21,"shopName":"幸福一号店铺","shopStatus":2,"shopkeeperName":"李先生","status":1,"telephone":"18756098756","type":0,"updateTime":1536478828000,"welfareUnit":1},{"cityId":"北京市","createTime":1536163111000,"joinPersonNumber":0,"kangbiCount":2000,"latitude":39.933748,"longitude":116.393489,"partnerType":"B","personNumber":50,"provinceId":"北京","shopAddress":"北京市北京市西城区","shopId":21,"shopName":"幸福一号店铺","shopStatus":2,"shopkeeperName":"李先生","status":1,"telephone":"18756098756","type":1,"updateTime":1536478828000,"welfareUnit":1}],"shopName":"幸福一号店铺"}]
         * nextPage : 0
         * pageNum : 1
         * pageSize : 2
         * pages : 1
         * prePage : 0
         * size : 2
         * startRow : 0
         * total : 2
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

        public static class ListBean implements Serializable{
            /**
             * inrecruiList : [{"cityId":"北京市","createTime":1536236640000,"joinPersonNumber":0,"kangbiCount":1000,"latitude":39.963839,"longitude":116.374517,"partnerType":"A","personNumber":10,"provinceId":"北京","rate":10,"shopAddress":"北京市北京市海淀区学院南路6号","shopId":22,"shopName":"幸福三号店铺","shopStatus":2,"shopkeeperName":"张先生","status":1,"telephone":"18210987756","type":0,"updateTime":1536478817000,"welfareUnit":1},{"cityId":"北京市","createTime":1536236640000,"joinPersonNumber":0,"kangbiCount":2000,"latitude":39.963839,"longitude":116.374517,"partnerType":"B","personNumber":20,"provinceId":"北京","shopAddress":"北京市北京市海淀区学院南路6号","shopId":22,"shopName":"幸福三号店铺","shopStatus":2,"shopkeeperName":"张先生","status":1,"telephone":"18210987756","type":1,"updateTime":1536478817000,"welfareUnit":1}]
             * shopName : 幸福三号店铺
             */

            public String shopName;
            public List<InrecruiListBean> inrecruiList;

            public static class InrecruiListBean {
                /**
                 * cityId : 北京市
                 * createTime : 1536236640000
                 * joinPersonNumber : 0
                 * kangbiCount : 1000.0
                 * latitude : 39.963839
                 * longitude : 116.374517
                 * partnerType : A
                 * personNumber : 10
                 * provinceId : 北京
                 * rate : 10.0
                 * shopAddress : 北京市北京市海淀区学院南路6号
                 * shopId : 22
                 * shopName : 幸福三号店铺
                 * shopStatus : 2
                 * shopkeeperName : 张先生
                 * status : 1
                 * telephone : 18210987756
                 * type : 0
                 * updateTime : 1536478817000
                 * welfareUnit : 1
                 */

                public String cityId;
                public long createTime;
                public int joinPersonNumber;
                public double kangbiCount;
                public double latitude;
                public double longitude;
                public String partnerType;
                public int personNumber;
                public String provinceId;
                public double rate;
                public String shopAddress;
                public String shopId;
                public String shopName;
                public int shopStatus;
                public String shopkeeperName;
                public int status;
                public String telephone;
                public int type;
                public long updateTime;
                public int welfareUnit;
            }
        }
    }
}
