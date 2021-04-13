package com.eric.jetpack.retrofit.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Author: eric
 * @CreateDate: 3/10/21 2:04 PM
 * @Description: java类作用描述
 */
public interface ApiService {
  //   imSunLight/article/details/113843639?spm=1001.2014.3001.5501
    @GET("imSunLight/article/details/{id}")
    Call<ResponseBody> getData(@Path("id")String id,@Query("spm") String spm);
 // https://blog.csdn.net/imSunLight/article/details/113843639?spm=1001.2014.3001.5501
}
