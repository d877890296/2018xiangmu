package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

import java.util.List;

/**
 * Created by 10835 on 2018/9/1.
 */

public class AllShopsModel extends Response{


    /**
     * retCode : 1
     * data : {"shops":{"shopId":1,"shopSn":"111","shopName":"幸福商城北京分店11","provinceId":"北京市","cityId":"1","areaId":"海淀区","detail":"北京市海淀区中关村大街59号","shopAddress":"10.100.100.100","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张三","telephone":"111111111111","shopStatus":3,"statusDesc":null,"description":"好店面1","orderCount":0,"turnover":null,"createTime":1532266531000,"updateTime":1533155960000},"shopsList":[{"shopId":4,"shopSn":null,"shopName":"hello","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"hello","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"dawei","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964562000,"updateTime":1532964562000},{"shopId":5,"shopSn":null,"shopName":"hello","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"hello","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"dawei","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964664000,"updateTime":1532964664000},{"shopId":6,"shopSn":null,"shopName":"nihao","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964748000,"updateTime":1532964748000},{"shopId":7,"shopSn":null,"shopName":"nihao","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964825000,"updateTime":1532964825000},{"shopId":8,"shopSn":null,"shopName":"nihao","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"sdfsdf","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964949000,"updateTime":1532964949000},{"shopId":9,"shopSn":null,"shopName":"nihao","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"sdfsdf","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532965067000,"updateTime":1532965067000},{"shopId":10,"shopSn":null,"shopName":"nihao123","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"nohao welcome bj","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532965094000,"updateTime":1533140763000},{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"省份","cityId":"城市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000},{"shopId":3,"shopSn":null,"shopName":"你的好心情","provinceId":"上海","cityId":"上海市","areaId":null,"detail":null,"shopAddress":"黄浦区花园","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964393000,"updateTime":1533829873000},{"shopId":13,"shopSn":null,"shopName":"北京麻辣拌","provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"昌平区北七家王府农场","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"18210987654","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534669081000,"updateTime":1534669081000},{"shopId":14,"shopSn":null,"shopName":"北京麻辣拌","provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"昌平区北七家王府农场","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"18210987654","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534669098000,"updateTime":1534669098000},{"shopId":15,"shopSn":null,"shopName":"北京麻辣拌","provinceId":"海南","cityId":"澄迈县","areaId":null,"detail":null,"shopAddress":"昌平区北七家王府农场","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"18210987654","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534669215000,"updateTime":1534669215000},{"shopId":12,"shopSn":null,"shopName":"好店铺--有商品","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"海淀","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"13283679708","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534336488000,"updateTime":1534336492000},{"shopId":1,"shopSn":"111","shopName":"幸福商城北京分店11","provinceId":"北京市","cityId":"1","areaId":"海淀区","detail":"北京市海淀区中关村大街59号","shopAddress":"10.100.100.100","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张三","telephone":"111111111111","shopStatus":3,"statusDesc":null,"description":"好店面1","orderCount":0,"turnover":null,"createTime":1532266531000,"updateTime":1533155960000},{"shopId":2,"shopSn":"222","shopName":"幸福商城北京分店22","provinceId":"北京市","cityId":"1","areaId":"丰台区","detail":"北京市海淀区中关村大街60号","shopAddress":"10.100.100.100","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张四","telephone":"222222222222","shopStatus":2,"statusDesc":null,"description":"好店面2","orderCount":0,"turnover":null,"createTime":1532267603000,"updateTime":1532267605000},{"shopId":16,"shopSn":null,"shopName":"庆丰包子铺","provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"昌平区北七家王府农场","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"李伟","telephone":"123456734588","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534670604000,"updateTime":1534670604000},{"shopId":18,"shopSn":null,"shopName":"昌平包子铺","provinceId":"省份","cityId":"城市","areaId":null,"detail":null,"shopAddress":"北京市北京市海淀区吴玉章路","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"12345673456","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534763713000,"updateTime":1534764690000},{"shopId":17,"shopSn":null,"shopName":"面皮店","provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"北京市北京市朝阳区蟹岛路","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"12345673456","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534762332000,"updateTime":1534762332000}]}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * shops : {"shopId":1,"shopSn":"111","shopName":"幸福商城北京分店11","provinceId":"北京市","cityId":"1","areaId":"海淀区","detail":"北京市海淀区中关村大街59号","shopAddress":"10.100.100.100","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张三","telephone":"111111111111","shopStatus":3,"statusDesc":null,"description":"好店面1","orderCount":0,"turnover":null,"createTime":1532266531000,"updateTime":1533155960000}
         * shopsList : [{"shopId":4,"shopSn":null,"shopName":"hello","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"hello","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"dawei","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964562000,"updateTime":1532964562000},{"shopId":5,"shopSn":null,"shopName":"hello","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"hello","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"dawei","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964664000,"updateTime":1532964664000},{"shopId":6,"shopSn":null,"shopName":"nihao","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964748000,"updateTime":1532964748000},{"shopId":7,"shopSn":null,"shopName":"nihao","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964825000,"updateTime":1532964825000},{"shopId":8,"shopSn":null,"shopName":"nihao","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"sdfsdf","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964949000,"updateTime":1532964949000},{"shopId":9,"shopSn":null,"shopName":"nihao","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"sdfsdf","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532965067000,"updateTime":1532965067000},{"shopId":10,"shopSn":null,"shopName":"nihao123","provinceId":"??","cityId":"1","areaId":null,"detail":null,"shopAddress":"nohao welcome bj","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"adad","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532965094000,"updateTime":1533140763000},{"shopId":11,"shopSn":null,"shopName":"nihao1234","provinceId":"省份","cityId":"城市","areaId":null,"detail":null,"shopAddress":"nohao","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"123456734588","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1533180961000,"updateTime":1533829823000},{"shopId":3,"shopSn":null,"shopName":"你的好心情","provinceId":"上海","cityId":"上海市","areaId":null,"detail":null,"shopAddress":"黄浦区花园","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"12345673456","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1532964393000,"updateTime":1533829873000},{"shopId":13,"shopSn":null,"shopName":"北京麻辣拌","provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"昌平区北七家王府农场","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"18210987654","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534669081000,"updateTime":1534669081000},{"shopId":14,"shopSn":null,"shopName":"北京麻辣拌","provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"昌平区北七家王府农场","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"18210987654","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534669098000,"updateTime":1534669098000},{"shopId":15,"shopSn":null,"shopName":"北京麻辣拌","provinceId":"海南","cityId":"澄迈县","areaId":null,"detail":null,"shopAddress":"昌平区北七家王府农场","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"18210987654","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534669215000,"updateTime":1534669215000},{"shopId":12,"shopSn":null,"shopName":"好店铺--有商品","provinceId":"北京市","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"海淀","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"黎明","telephone":"13283679708","shopStatus":2,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534336488000,"updateTime":1534336492000},{"shopId":1,"shopSn":"111","shopName":"幸福商城北京分店11","provinceId":"北京市","cityId":"1","areaId":"海淀区","detail":"北京市海淀区中关村大街59号","shopAddress":"10.100.100.100","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张三","telephone":"111111111111","shopStatus":3,"statusDesc":null,"description":"好店面1","orderCount":0,"turnover":null,"createTime":1532266531000,"updateTime":1533155960000},{"shopId":2,"shopSn":"222","shopName":"幸福商城北京分店22","provinceId":"北京市","cityId":"1","areaId":"丰台区","detail":"北京市海淀区中关村大街60号","shopAddress":"10.100.100.100","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张四","telephone":"222222222222","shopStatus":2,"statusDesc":null,"description":"好店面2","orderCount":0,"turnover":null,"createTime":1532267603000,"updateTime":1532267605000},{"shopId":16,"shopSn":null,"shopName":"庆丰包子铺","provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"昌平区北七家王府农场","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"李伟","telephone":"123456734588","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534670604000,"updateTime":1534670604000},{"shopId":18,"shopSn":null,"shopName":"昌平包子铺","provinceId":"省份","cityId":"城市","areaId":null,"detail":null,"shopAddress":"北京市北京市海淀区吴玉章路","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"12345673456","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534763713000,"updateTime":1534764690000},{"shopId":17,"shopSn":null,"shopName":"面皮店","provinceId":"北京","cityId":"北京市","areaId":null,"detail":null,"shopAddress":"北京市北京市朝阳区蟹岛路","longitude":116.322199,"latitude":39.975783,"shopkeeperName":"张晓明","telephone":"12345673456","shopStatus":1,"statusDesc":null,"description":null,"orderCount":null,"turnover":null,"createTime":1534762332000,"updateTime":1534762332000}]
         */

        public ShopsBean shops;
        public List<ShopsListBean> shopsList;

        public static class ShopsBean {
            /**
             * shopId : 1
             * shopSn : 111
             * shopName : 幸福商城北京分店11
             * provinceId : 北京市
             * cityId : 1
             * areaId : 海淀区
             * detail : 北京市海淀区中关村大街59号
             * shopAddress : 10.100.100.100
             * longitude : 116.322199
             * latitude : 39.975783
             * shopkeeperName : 张三
             * telephone : 111111111111
             * shopStatus : 3
             * statusDesc : null
             * description : 好店面1
             * orderCount : 0
             * turnover : null
             * createTime : 1532266531000
             * updateTime : 1533155960000
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
            public Object statusDesc;
            public String description;
            public int orderCount;
            public Object turnover;
            public long createTime;
            public long updateTime;
        }

        public static class ShopsListBean {
            /**
             * shopId : 4
             * shopSn : null
             * shopName : hello
             * provinceId : ??
             * cityId : 1
             * areaId : null
             * detail : null
             * shopAddress : hello
             * longitude : 116.322199
             * latitude : 39.975783
             * shopkeeperName : dawei
             * telephone : 12345673456
             * shopStatus : 2
             * statusDesc : null
             * description : null
             * orderCount : null
             * turnover : null
             * createTime : 1532964562000
             * updateTime : 1532964562000
             */

            public int shopId;
            public Object shopSn;
            public String shopName;
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
        }
    }
}
