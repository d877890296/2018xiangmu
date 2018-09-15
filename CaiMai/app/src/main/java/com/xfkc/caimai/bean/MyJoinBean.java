package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/12.
 */

public class MyJoinBean extends Response {


    /**
     * retCode : 1
     * data : {"joinTypeMsg":"我是A事业合伙人","shopName":"昌平杂货铺","inrecruiList":[{"shopId":45,"shopSn":null,"shopName":"昌平杂货铺","shopNameFirstLetter":null,"provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"北京市北京市朝阳区","longitude":116.445806,"latitude":40.001433,"shopkeeperName":"王先生","telephone":"13789087654","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1536572965000,"updateTime":1536572965000,"roleId":null,"partnerType":"A","welfareUnit":2,"rate":3,"kangbiCount":0.01,"status":2,"type":0,"personNumber":20,"joinPersonNumber":4},{"shopId":45,"shopSn":null,"shopName":"昌平杂货铺","shopNameFirstLetter":null,"provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"北京市北京市朝阳区","longitude":116.445806,"latitude":40.001433,"shopkeeperName":"王先生","telephone":"13789087654","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1536572965000,"updateTime":1536572965000,"roleId":null,"partnerType":"B","welfareUnit":1,"rate":null,"kangbiCount":0.01,"status":2,"type":1,"personNumber":20,"joinPersonNumber":1}]}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * joinTypeMsg : 我是A事业合伙人
         * shopName : 昌平杂货铺
         * inrecruiList : [{"shopId":45,"shopSn":null,"shopName":"昌平杂货铺","shopNameFirstLetter":null,"provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"北京市北京市朝阳区","longitude":116.445806,"latitude":40.001433,"shopkeeperName":"王先生","telephone":"13789087654","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1536572965000,"updateTime":1536572965000,"roleId":null,"partnerType":"A","welfareUnit":2,"rate":3,"kangbiCount":0.01,"status":2,"type":0,"personNumber":20,"joinPersonNumber":4},{"shopId":45,"shopSn":null,"shopName":"昌平杂货铺","shopNameFirstLetter":null,"provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"北京市北京市朝阳区","longitude":116.445806,"latitude":40.001433,"shopkeeperName":"王先生","telephone":"13789087654","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1536572965000,"updateTime":1536572965000,"roleId":null,"partnerType":"B","welfareUnit":1,"rate":null,"kangbiCount":0.01,"status":2,"type":1,"personNumber":20,"joinPersonNumber":1}]
         */

        public String joinTypeMsg;
        public String shopName;
        public List<InrecruiListBean> inrecruiList;

        public static class InrecruiListBean {
            /**
             * shopId : 45
             * shopSn : null
             * shopName : 昌平杂货铺
             * shopNameFirstLetter : null
             * provinceId : 北京
             * cityId : 北京市
             * areaId : null
             * detail : null
             * shopAddress : 北京市北京市朝阳区
             * longitude : 116.445806
             * latitude : 40.001433
             * shopkeeperName : 王先生
             * telephone : 13789087654
             * shopStatus : 2
             * statusDesc : null
             * description : null
             * orderCount : null
             * turnover : null
             * createTime : 1536572965000
             * updateTime : 1536572965000
             * roleId : null
             * partnerType : A
             * welfareUnit : 2
             * rate : 3.0
             * kangbiCount : 0.01
             * status : 2
             * type : 0
             * personNumber : 20
             * joinPersonNumber : 4
             * comKangbi
             */

            public int shopId;
            public Object shopSn;
            public String shopName;
            public Object shopNameFirstLetter;
            public String provinceId;
            public String cityId;
            public Object areaId;
            public Object detail;
            public String shopAddress;
            public double longitude;
            public double latitude;
            public String shopkeeperName;
            public String telephone;
            public int shopStatus;
            public Object statusDesc;
            public Object description;
            public Object orderCount;
            public Object turnover;
            public long createTime;
            public long updateTime;
            public Object roleId;
            public String partnerType;
            public int welfareUnit;
            public double rate;
            public double kangbiCount;
            public int status;
            public int type;
            public int personNumber;
            public int joinPersonNumber;
            public String comKangbi;
        }
    }
}
