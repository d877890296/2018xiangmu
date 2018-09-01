package com.xfkc.caimai.bean;


import com.xfkc.caimai.net.Response;

/**
 * 1.用于只返回正确与否没有信息的数据
 * 3.@2018/4/17.
 */

public class ZfbBean extends Response {


    /**
     * retCode : 1
     * data : {"sign":"app_id=2017101709349686&biz_content=%7B%22out_trade_no%22%3A%2220180901130219612693953%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%22%E4%BC%9A%E5%91%98%E5%8D%A1%E8%B4%AD%E4%B9%B0%22%2C%22timeout_express%22%3A%22120m%22%2C%22body%22%3A%22%E4%BC%9A%E5%91%98%E5%8D%A1%E8%B4%AD%E4%B9%B0%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=utf-8&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F47.105.126.49%3A9999%2F%2Fapi%2FappPay%2FmemCard%2Falipay%2Fnotify%2Ftest&sign_type=RSA2&timestamp=2018-09-01+13%3A02%3A19&version=1.0&sign=QGvMv8hiD56aWalt3nQ5azQD11hDG%2BugL%2BlOl1%2FNk%2B9nFIc%2FISsSzc%2B6tJKIMHEO12ZKAeNFE9h%2F7zPjE4GFUN1jDvhX%2Bv9BBTQm38742zDQ5x9yKUv1CmJ4aj%2BBZU4B1sDhFVEyCSEqmKlpiAYClCN9MtvmIgWdToC2TgotmIba7YLRwRJK5scB83j69bqrUgc6hdeu%2F8PSjwcT79vk%2FOnwMUfU1BmGAIuqXuO9GQTME3jiQuMfjtuYy68EfzEau4eM%2FCMDEvNWVS5WTyKFoy%2BFFOmmsC76jJbYStz7Bjuz8%2FSiqf74%2FjZWIp2Bj7aOkhoEnHF0ckbMfDQT5z%2FSWw%3D%3D","orderNum":"20180901130219604795592"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * sign : app_id=2017101709349686&biz_content=%7B%22out_trade_no%22%3A%2220180901130219612693953%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%22%E4%BC%9A%E5%91%98%E5%8D%A1%E8%B4%AD%E4%B9%B0%22%2C%22timeout_express%22%3A%22120m%22%2C%22body%22%3A%22%E4%BC%9A%E5%91%98%E5%8D%A1%E8%B4%AD%E4%B9%B0%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=utf-8&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F47.105.126.49%3A9999%2F%2Fapi%2FappPay%2FmemCard%2Falipay%2Fnotify%2Ftest&sign_type=RSA2&timestamp=2018-09-01+13%3A02%3A19&version=1.0&sign=QGvMv8hiD56aWalt3nQ5azQD11hDG%2BugL%2BlOl1%2FNk%2B9nFIc%2FISsSzc%2B6tJKIMHEO12ZKAeNFE9h%2F7zPjE4GFUN1jDvhX%2Bv9BBTQm38742zDQ5x9yKUv1CmJ4aj%2BBZU4B1sDhFVEyCSEqmKlpiAYClCN9MtvmIgWdToC2TgotmIba7YLRwRJK5scB83j69bqrUgc6hdeu%2F8PSjwcT79vk%2FOnwMUfU1BmGAIuqXuO9GQTME3jiQuMfjtuYy68EfzEau4eM%2FCMDEvNWVS5WTyKFoy%2BFFOmmsC76jJbYStz7Bjuz8%2FSiqf74%2FjZWIp2Bj7aOkhoEnHF0ckbMfDQT5z%2FSWw%3D%3D
         * orderNum : 20180901130219604795592
         */

        public String sign;
        public String orderNum;
    }
}
