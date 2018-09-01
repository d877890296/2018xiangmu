package com.xfkc.caimai.net;


import com.xfkc.caimai.bean.AllShopsModel;
import com.xfkc.caimai.bean.BannerBean;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.bean.GoodsCityModel;
import com.xfkc.caimai.bean.GoodsKey;
import com.xfkc.caimai.bean.LoginInfo;
import com.xfkc.caimai.bean.VipCardBean;
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
    Observable<EmptyBean> registerInfo(@Query("phone") String phone, @Query("verCode") String verCode,
                                       @Query("userPwd") String userPwd, @Query("inviteCode") String inviteCode);

    //获取验证码
    @GET("/api/memUser/login")
    Observable<LoginInfo> login(@Query("phone") String phone, @Query("userPwd") String userPwd);


    //修改密码
    @GET("/api/memUser/updateUserPwd")
    Observable<EmptyBean> updatePwd(@Query("phone") String phone, @Query("userPwd") String userPwd, @Query("verCode") String verCode);

    //查询用户购买的会员卡
    @GET("/api/memUser/findMemCardByUserId")
    Observable<VipCardBean> getUserVipCard(@Query("token") String token);

    //查询用户购买的会员卡
    @GET("/api/memCard/findAll")
    Observable<VipCardBean> findAllVipCard(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    //正在招募，已完成招募列表
    @GET("/api/recruite/inrecruitment")
    Observable<EmptyBean> recruitmentHall(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize, @Query("token") String token);

    //会员购买创建订单
    @GET("/api/appPay/crateMemOrder")
    Observable<ZfbBean> zfbPay(@Query("memCardId") String memCardId ,@Query("token") String token);

    //获取轮播图
    @GET("/api/banner/findAll")
    Observable<BannerBean> getBannerData(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    /****
     *
     * 商城列表
     */
    @POST("/api/happycommune/getProductBySearch")
    Observable<GoodsCityModel> getGoodsCityListData(@Body GoodsKey id );
    /****
     *
     * 所以商店列表
     */
    @POST("/api/happycommune/getAllShopsAndNearshop")
    Observable<AllShopsModel> getAllShopsAndNearshop(@Body GoodsKey id );
}
