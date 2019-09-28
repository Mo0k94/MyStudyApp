package com.example.mystudyapp.Retrofit2;

import com.example.mystudyapp.models.Login;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private Retrofit mRetrofit;

    private UserApi mUserApi;

    public RetrofitUtil(){

        mRetrofit = new Retrofit.Builder()
                .baseUrl(UserApi.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mUserApi = mRetrofit.create(UserApi.class);
    }

    public UserApi getUserApi(){
        return mUserApi;

    }
}
