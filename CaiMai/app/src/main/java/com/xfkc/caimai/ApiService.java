package com.xfkc.caimai;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by huangrui on 2017-10-13.
 */

public interface ApiService {

    //下载文件
    @GET
    Call<ResponseBody> loadPdfFile(@Url String fileUrl);


}
