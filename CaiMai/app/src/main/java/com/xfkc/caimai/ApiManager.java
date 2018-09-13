package com.xfkc.caimai;

import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by huangrui on 2017-10-13.
 */

public class ApiManager {


    private static ApiManager mRetrofitManager;
    private Retrofit mRetrofit;
    private ApiService mApiService;
  // private String baseUrl = "https://buy.terrynie.com/appapi/";
  private String baseUrl = "https://admin.xingfukangcheng.com/appapi/";


    private ApiManager() {
        initRetrofit();
    }




    public static synchronized ApiManager getInstance(){
        if (mRetrofitManager == null){
            mRetrofitManager = new ApiManager();
        }
        return mRetrofitManager;
    }


    private void initRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.addInterceptor(getHttpLoggingInterceptor());
        builder.retryOnConnectionFailure(true);
//        builder.cookieJar(MyApplication.cookieJar);

        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build() ;
        mApiService = mRetrofit.create(ApiService.class);
    }

    //下载pdf和word文件
    public void loadPdfFile(String url, Callback<ResponseBody> callback) {
        if (!TextUtils.isEmpty(url)) {
            Call<ResponseBody> call = mApiService.loadPdfFile(url);
            call.enqueue(callback);
        }
    }


    /**
     * 日志输出
     * 自行判定是否添加
     *
     * @return
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("huangrui", "Retrofit====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

}
