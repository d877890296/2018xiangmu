package com.xfkc.caimai.net;


import com.xfkc.caimai.bean.AddressBean;
import com.xfkc.caimai.bean.AllShopsModel;
import com.xfkc.caimai.bean.BannerBean;
import com.xfkc.caimai.bean.BigLectureBean;
import com.xfkc.caimai.bean.CollectBean;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.bean.FeelingBean;
import com.xfkc.caimai.bean.GoodsCarNumBean;
import com.xfkc.caimai.bean.GoodsCityModel;
import com.xfkc.caimai.bean.GoodsKey;
import com.xfkc.caimai.bean.LoginInfo;
import com.xfkc.caimai.bean.MineVipCardBean;
import com.xfkc.caimai.bean.MyJoinBean;
import com.xfkc.caimai.bean.ProfitListBean;
import com.xfkc.caimai.bean.RecruBean;
import com.xfkc.caimai.bean.RecruiHallBean;
import com.xfkc.caimai.bean.RegistBean;
import com.xfkc.caimai.bean.RevenueBean;
import com.xfkc.caimai.bean.TopCategory;
import com.xfkc.caimai.bean.UserInfoBean;
import com.xfkc.caimai.bean.VipCardBean;
import com.xfkc.caimai.bean.WXBean;
import com.xfkc.caimai.bean.ZfbBean;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by LK on 2017/5/5 10:41.
 */

public interface PayService {

    //首页轮播图
    @POST("SY0001.json")
    Observable<EmptyBean> getHomeImg(@Body EmptyBean id);

    //获取验证码
    @GET("/api/memUser/getSmsCode")
    Observable<EmptyBean> getSmsCode(@Query("phone") String phone);

    //注册
    @GET("/api/memUser/register")
    Observable<RegistBean> registerInfo(@Query("phone") String phone, @Query("verCode") String verCode,
                                        @Query("userPwd") String userPwd, @Query("inviteCode") String inviteCode
            , @Query("realName") String realName);

    //获取验证码
    @GET("/api/memUser/login")
    Observable<LoginInfo> login(@Query("phone") String phone, @Query("userPwd") String userPwd);


    //修改密码
    @GET("/api/memUser/updateUserPwd")
    Observable<EmptyBean> updatePwd(@Query("phone") String phone, @Query("userPwd") String userPwd, @Query("verCode") String verCode);

    //查询用户购买的会员卡
    @GET("/api/memCard/findUserCard")
    Observable<MineVipCardBean> getUserVipCard(@Query("token") String token);

    //查询所有会员卡类别
    @GET("/api/memCard/findAll")
    Observable<VipCardBean> findAllVipCard(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    //正在招募，已完成招募列表
    @GET("/api/recruite/inrecruitment")
    Observable<RecruiHallBean> recruitmentHall(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize,
                                               @Query("token") String token, @Query("shopStatus") String shopStatus);

    //会员购买创建订单
    @GET("/api/appPay/crateMemOrder")
    Observable<ZfbBean> zfbPay(@Query("memCardId") String memCardId, @Query("token") String token);

    //支付宝充值
    @GET("/api/appPay/memCharge")
    Observable<ZfbBean> memCharge(@Query("price") String price, @Query("token") String token);

    //获取轮播图
    @GET("/api/banner/findAll")
    Observable<BannerBean> getBannerData(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    /****
     *
     * 商城列表
     */
    @POST("/api/happycommune/getProductBySearch")
    Observable<GoodsCityModel> getGoodsCityListData(@Body GoodsKey id);

    /****
     *
     * 所以商店列表
     */
    @POST("/api/happycommune/getAllShopsAndNearshop")
    Observable<AllShopsModel> getAllShopsAndNearshop(@Body GoodsKey id);

    //首次设置支付密码
    @GET("/api/memUser/addPayPwd")
    Observable<EmptyBean> setPayPwd(@Query("payPwd") String payPwd, @Query("token") String token);

    //修改支付密码
    @GET("/api/memUser/updatePayPwd")
    Observable<EmptyBean> updatePayPwd(@Query("phone") String phone, @Query("verCode") String verCode,
                                       @Query("payPwd") String payPwd, @Query("token") String token);

    //获取用户信息
    @GET("/api/memUser/findUserDetByPhone")
    Observable<UserInfoBean> findUserDetByPhone(@Query("token") String token);

    //添加收货地址
    @GET("/api/receiveAdress/addReceiveAdressOrUpdate")
    Observable<EmptyBean> AddWstAddress(@Query("name") String name, @Query("phone") String phone,
                                        @Query("token") String token, @Query("province") String province
            , @Query("city") String city, @Query("area") String area
            , @Query("detailAdress") String detailAdress, @Query("acquiesce") int acquiesce);

    //查询用户收货地址
    @GET("/api/receiveAdress/getReceiveAdress")
    Observable<AddressBean> getReceiveAdress(@Query("token") String token);

    //实名认证
    @GET("/api/memUser/certification")
    Observable<EmptyBean> certification(@Query("realName") String realName, @Query("idCard") String idCard,
                                        @Query("province") String province, @Query("city") String city,
                                        @Query("area") String area, @Query("detailAdress") String detailAdress,
                                        @Query("phone") String phone);

    //顶部类别列表
    @GET("/api/happycommune/getTopCategory")
    Observable<TopCategory> getTopCategory();

    //更新购物车数量
    @POST("/api/shopcart/editCartItemsNum ")
    Observable<EmptyBean> editCartItemsNum(@Body GoodsKey id);

    //更新购物车数量
    @GET("/api/shopcart/getShopCartItemNum")
    Observable<GoodsCarNumBean> getShopCartItemNum(@Query("token") String token);

    //查询我加入的店铺
    @GET("/api/recruite/myjoinshop")
    Observable<MyJoinBean> myjoinshop(@Query("token") String token);


    //查询我的情怀链
    @GET("/api/memUser/findChain")
    Observable<FeelingBean> findChain(@Query("token") String token, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    //微信支付
    @GET("/api/WXPay/crateMemOrder")
    Observable<WXBean> wxPay(@Query("token") String token, @Query("memCardId") String memCardId);


    //修改用户昵称
    @GET("/api/memUser/updateUserNicName")
    Observable<EmptyBean> updateUserNicName(@Query("token") String token, @Query("nicName") String nicName);

    //正在招募，已完成招募列表
    @GET("/api/recruite/inrecruitment")
    Observable<RecruiHallBean> recruitmentHall(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize,
                                               @Query("token") String token, @Query("shopStatus") String shopStatus, @Query("shopId") String shopId);

    //我的总收益
    @GET("/api/tradeInfo/findMyRevenue")
    Observable<RevenueBean> findMyRevenue(@Query("token") String token);

    //我的总收益
    @GET("/api/tradeInfo/findMyRevenueDet")
    Observable<ProfitListBean> findMyRevenueDet(@Query("token") String token, @Query("pageNum") int pageNum,
                                                @Query("pageSize") int pageSize, @Query("type") String type);

    //查询课件
    @GET("/api/vedio/findVedioByType")
    Observable<BigLectureBean> findVedioByType(@Query("token") String token, @Query("type") String type);

    //加入招募
    @GET("/api/appPay/recruitOrder")
    Observable<RecruBean> recruitOrder(@Query("token") String token, @Query("shopId") String shopId,
                                       @Query("partnerType") String partnerType, @Query("payAmount") String payAmount);

    //收支明细
    @GET("/api/tradeInfo/receiptDet")
    Observable<ProfitListBean> receiptDet(@Query("token") String token,@Query("pageNum") int pageNum,@Query("pageSize") int pageSize);


    //收藏
    @GET("/api/vedio/vedioCollect")
    Observable<EmptyBean> vedioCollect(@Query("token") String token,@Query("vedioId") String vedioId);

    @GET("/api/vedio/cancelVedioCollect")
    Observable<EmptyBean> novedioCollect(@Query("token") String token,@Query("vedioId") String vedioId);

    //查询我收入的列表
    @GET("/api/vedio/findMyCollectList")
    Observable<CollectBean> findMyCollectList(@Query("token") String token, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    //微信充值支付
    @GET("/api/WXPay/memCharge")
    Observable<WXBean> wxmemCharge(@Query("token") String token, @Query("price") String price);

    //招募微信充值支付
    @GET("/api/WXPay/recruitOrder")
    Observable<WXBean> wxrecruitOrder(@Query("token") String token, @Query("shopId") String shopId,
                                    @Query("partnerType") String partnerType, @Query("payAmount") String payAmount);


    @GET("/api/receiveAdress/rmReceiveAdress")
    Observable<EmptyBean> rmReceiveAdress(@Query("token") String token, @Query("id") String id);

}
