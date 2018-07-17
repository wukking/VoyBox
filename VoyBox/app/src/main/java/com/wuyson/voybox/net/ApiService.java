package com.wuyson.voybox.net;

import com.wuyson.voybox.net.base.BaseModel;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {
    @GET("data/福利/10/1")
    Call<BaseModel> listRepos();
    @GET
    Call<ResponseBody> listRepos2(@Url String url);
    @GET("data/福利/10/1")
    Observable<BaseModel> listRxRepos();

    @FormUrlEncoded
    @POST("openapi/1")
    Observable<ResponseBody> post(@Body RequestBody body);
}
