package com.example.mystudyapp.Retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ImageApi {

      String BaseUrl = "http://tkdanr2427.cafe24.com/imageblob/";



    //@FormUrlEncoded
    @POST("getServerImage.php")
    Call<List<ResultModel>> get_Image();
}
