package com.xfkc.caimai.net;


import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.bean.LoginInfo;

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
    Observable<EmptyBean> registerInfo(@Query("phone") String phone,@Query("verCode") String verCode,
                                       @Query("userPwd") String userPwd,@Query("inviteCode") String inviteCode);

    //获取验证码
    @GET("/api/memUser/login")
    Observable<LoginInfo> login(@Query("phone") String phone, @Query("userPwd") String userPwd);

    //修改密码
    @GET("/api/memUser/updateUserPwd")
    Observable<EmptyBean> updatePwd(@Query("phone") String phone, @Query("userPwd") String userPwd,@Query("verCode") String verCode);

}
