package com.xfkc.caimai.bean;

import com.xfkc.caimai.net.Response;

/**
 * 1.类的用途
 * 2.@dongjinxu
 * 3.@2018/9/12.
 */

public class RecruBean extends Response {


    /**
     * retCode : 1
     * data : {"sign":"app_id=2017101709349686&biz_content=%7B%22out_trade_no%22%3A%2220180912212051955294307%22%2C%22total_amount%22%3A%2210000.0%22%2C%22subject%22%3A%22%E6%8B%9B%E5%8B%9F%22%2C%22timeout_express%22%3A%22120m%22%2C%22body%22%3A%22%E6%8B%9B%E5%8B%9F%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=utf-8&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F47.105.126.49%3A9999%2Fapi%2FappPay%2Frecruit%2Falipay%2Fnotify&sign_type=RSA2&timestamp=2018-09-12+21%3A20%3A51&version=1.0&sign=FoqH60QVJF4a5yVreuJTK7sId2zoQ1%2B5A%2BK3fvYTgHx6qcSrN50nsYqm%2BF8OA0R1FNcD1TOt8vhjvg7f6izu%2Bwx4WZO8YxMYNZTbvuaqfF8RIL%2FmXfOthRga9YzJNMo%2FoDVk3FSLWumwQ3tnWuQj466HmAZmMWVrCRYXlImC8ir%2BVoKLazPJdgsaT64xo%2BQxhmDwbt38s%2FYikJG5hK5MfEZ3TCUJhb5ppQtTLxtdAvAnFfFoZ9hB4q6ggM5Xr8nJp4R5FJzQCVNI%2BA1U0mmogmWQF26IMQ3AbY6G7vzylJgH3qT9Hq0SsoYNq9hCYNXIqSepBXXhZVI5RX9hPkon8g%3D%3D","orderNum":"20180912212051955294307"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * sign : app_id=2017101709349686&biz_content=%7B%22out_trade_no%22%3A%2220180912212051955294307%22%2C%22total_amount%22%3A%2210000.0%22%2C%22subject%22%3A%22%E6%8B%9B%E5%8B%9F%22%2C%22timeout_express%22%3A%22120m%22%2C%22body%22%3A%22%E6%8B%9B%E5%8B%9F%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=utf-8&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F47.105.126.49%3A9999%2Fapi%2FappPay%2Frecruit%2Falipay%2Fnotify&sign_type=RSA2&timestamp=2018-09-12+21%3A20%3A51&version=1.0&sign=FoqH60QVJF4a5yVreuJTK7sId2zoQ1%2B5A%2BK3fvYTgHx6qcSrN50nsYqm%2BF8OA0R1FNcD1TOt8vhjvg7f6izu%2Bwx4WZO8YxMYNZTbvuaqfF8RIL%2FmXfOthRga9YzJNMo%2FoDVk3FSLWumwQ3tnWuQj466HmAZmMWVrCRYXlImC8ir%2BVoKLazPJdgsaT64xo%2BQxhmDwbt38s%2FYikJG5hK5MfEZ3TCUJhb5ppQtTLxtdAvAnFfFoZ9hB4q6ggM5Xr8nJp4R5FJzQCVNI%2BA1U0mmogmWQF26IMQ3AbY6G7vzylJgH3qT9Hq0SsoYNq9hCYNXIqSepBXXhZVI5RX9hPkon8g%3D%3D
         * orderNum : 20180912212051955294307
         */

        public String sign;
        public String orderNum;
    }
}
