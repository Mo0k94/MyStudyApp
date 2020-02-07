package com.example.mystudyapp.Retrofit2;

import com.example.mystudyapp.models.Login;

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
import retrofit2.http.Query;

public interface UserApi {

    String BaseUrl = "http://tkdanr2427.cafe24.com/Gwangju_Univ/";


//    @Multipart
//    @POST("NickLogin.php")
//    Call<ResponseBody>Nick_login(@Part("Nick_Name") RequestBody title,
//                                  @Part("PW") RequestBody pw);

    //@FormUrlEncoded
    @GET("retrofit_login.php")
    Call<ResultModel> Nick_login(@Query("Nick_Name") String nick_name,
                                 @Query("PW") String password);


    @GET("retrofit_login.php")
    Call<ResultModel> Nick_loginLog(@Query("Nick_Name") String nick_name,
                                    @Query("PW") String password,
                                    @Query("LastedDate") String lastdate,
                                    @Query("UUID") String uuid);

    //@FormUrlEncoded
    @POST("LastedLogin.php")
    Call<List<ResultModel>> getLastLoginDate(@Query("Nick_Name") String nick_name);
}
