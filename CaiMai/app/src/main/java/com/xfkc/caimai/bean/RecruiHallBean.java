package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/1.
 */

public class RecruiHallBean extends Response {


    /**
     * retCode : 1
     * data : {"total":20,"list":[{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000,"partnerType":"C","welfareUnit":3,"rate":15,"kangbiCount":2000,"status":1,"type":0,"personNumber":200,"joinPersonNumber":0},{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000,"partnerType":"B","welfareUnit":1,"rate":8,"kangbiCount":32000,"status":1,"type":0,"personNumber":3200,"joinPersonNumber":0},{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000,"partnerType":"C","welfareUnit":3,"rate":10,"kangbiCount":1000,"status":1,"type":0,"personNumber":100,"joinPersonNumber":0},{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000,"partnerType":"A","welfareUnit":3,"rate":null,"kangbiCount":900,"status":1,"type":1,"personNumber":900,"joinPersonNumber":0},{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000,"partnerType":"E","welfareUnit":1,"rate":6,"kangbiCount":40000,"status":1,"type":0,"personNumber":40,"joinPersonNumber":0},{"shopId":10,"shopSn":null,"shopName":"nihao123","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao welcome bj","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532965094000,"updateTime":1533140763000,"partnerType":"A","welfareUnit":1,"rate":null,"kangbiCount":1000,"status":1,"type":0,"personNumber":1000,"joinPersonNumber":0},{"shopId":10,"shopSn":null,"shopName":"nihao123","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao welcome bj","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532965094000,"updateTime":1533140763000,"partnerType":null,"welfareUnit":1,"rate":6,"kangbiCount":666666,"status":1,"type":0,"personNumber":66,"joinPersonNumber":0},{"shopId":10,"shopSn":null,"shopName":"nihao123","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao welcome bj","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532965094000,"updateTime":15}]}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * total : 20
         * list : [{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000,"partnerType":"C","welfareUnit":3,"rate":15,"kangbiCount":2000,"status":1,"type":0,"personNumber":200,"joinPersonNumber":0},{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000,"partnerType":"B","welfareUnit":1,"rate":8,"kangbiCount":32000,"status":1,"type":0,"personNumber":3200,"joinPersonNumber":0},{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000,"partnerType":"C","welfareUnit":3,"rate":10,"kangbiCount":1000,"status":1,"type":0,"personNumber":100,"joinPersonNumber":0},{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000,"partnerType":"A","welfareUnit":3,"rate":null,"kangbiCount":900,"status":1,"type":1,"personNumber":900,"joinPersonNumber":0},{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000,"partnerType":"E","welfareUnit":1,"rate":6,"kangbiCount":40000,"status":1,"type":0,"personNumber":40,"joinPersonNumber":0},{"shopId":10,"shopSn":null,"shopName":"nihao123","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao welcome bj","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532965094000,"updateTime":1533140763000,"partnerType":"A","welfareUnit":1,"rate":null,"kangbiCount":1000,"status":1,"type":0,"personNumber":1000,"joinPersonNumber":0},{"shopId":10,"shopSn":null,"shopName":"nihao123","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao welcome bj","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532965094000,"updateTime":1533140763000,"partnerType":null,"welfareUnit":1,"rate":6,"kangbiCount":666666,"status":1,"type":0,"personNumber":66,"joinPersonNumber":0},{"shopId":10,"shopSn":null,"shopName":"nihao123","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"nohao welcome bj","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532965094000,"updateTime":15}]
         */
        /**
         * total : 0
         * list : []
         * pageNum : 1
         * pageSize : 0
         * size : 0
         * startRow : 0
         * endRow : 0
         * pages : 0
         * prePage : 0
         * nextPage : 0
         */

        public int pageNum;
        public int pageSize;
        public int size;
        public int startRow;
        public int endRow;
        public int pages;
        public int prePage;
        public int nextPage;
        public int total;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * shopId : 11
             * shopSn : null
             * shopName : nihao1234
             * provinceId : 北京市
             * cityId : 北京市
             * areaId : null
             * detail : null
             * shopAddress : nohao
             * longitude : 116.322199
             * latitude : 39.975783
             * shopkeeperName : 黎明
             * telephone : 123456734588
             * shopStatus : 2
             * statusDesc : null
             * description : null
             * orderCount : null
             * turnover : null
             * createTime : 1533180961000
             * updateTime : 1533829823000
             * partnerType : C
             * welfareUnit : 3
             * rate : 15.0
             * kangbiCount : 2000.0
             * status : 1
             * type : 0
             * personNumber : 200
             * joinPersonNumber : 0
             */

            public int shopId;
            public String shopSn;
            public String shopName;
            public String provinceId;
            public String cityId;
            public String areaId;
            public String detail;
            public String shopAddress;
            public double longitude;
            public double latitude;
            public String shopkeeperName;
            public String telephone;
            public int shopStatus;
            public String statusDesc;
            public String description;
            public String orderCount;
            public String turnover;
            public long createTime;
            public long updateTime;
            public String partnerType;
            public int welfareUnit;
            public double rate;
            public double kangbiCount;
            public int status;
            public int type;
            public int personNumber;
            public int joinPersonNumber;
        }
    }

}
