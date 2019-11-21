package com.example.mystudyapp.Retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImage {

    private Retrofit mRetrofit;

    private ImageApi mImageApi;

    public RetrofitImage(){

        mRetrofit = new Retrofit.Builder()
                .baseUrl(ImageApi.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mImageApi = mRetrofit.create(ImageApi.class);
    }

    public ImageApi getImageApi(){
        return mImageApi;

    }
}
