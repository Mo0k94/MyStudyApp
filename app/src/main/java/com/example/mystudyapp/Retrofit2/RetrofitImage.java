package com.example.mystudyapp.Retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImage {

    private static final String BaseUrl = "http://tkdanr2427.cafe24.com/imageblob/";


    private Retrofit mRetrofit;

    private ImageApi mImageApi;

    public RetrofitImage(){

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mImageApi = mRetrofit.create(ImageApi.class);
    }

    public ImageApi getImageApi(){
        return mImageApi;

    }
}
