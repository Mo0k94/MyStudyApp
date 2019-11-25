package com.example.mystudyapp.Retrofit2;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ImageApi {

      String BaseUrl = "http://tkdanr2427.cafe24.com/Gwangju_Univ/";



    //@FormUrlEncoded
    @POST("getServerImage.php")
    Call<List<ResultModel>> get_Image();


    @Multipart
    @POST("Gwangju_Board.php")
    Call<ResponseBody>InsertBoard(@Part("USER") RequestBody user,
                                  @Part("TITLE") RequestBody title,
                                  @Part("CONTENT") RequestBody content,
                                  @Part("DATE") RequestBody date,
                                  @Part MultipartBody.Part image);

}
