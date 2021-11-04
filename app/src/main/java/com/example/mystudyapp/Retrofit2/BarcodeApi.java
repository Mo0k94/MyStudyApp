package com.example.mystudyapp.Retrofit2;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BarcodeApi {

      String BaseUrl = "http://openapi.foodsafetykorea.go.kr/";
    //http://openapi.foodsafetykorea.go.kr/api/sample/C005/xml/1/5


    //@FormUrlEncoded BAR_CD={BAR_CD}
    @POST("api/08a0baf585284624857b/C005/json/1/5/BAR_CD={BAR_CD}")
    Call<C005> getItem(@Path("BAR_CD") String bar_cd);

    @GET("api/08a0baf585284624857b/C005/json/1/5/")
    Call<C005> getItems();



}
