package com.example.mystudyapp.Retrofit2;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBarcode {

    private Retrofit mRetrofit;

    private BarcodeApi mBarcodeApi;

    public RetrofitBarcode(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BarcodeApi.BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBarcodeApi = mRetrofit.create(BarcodeApi.class);
    }

    public BarcodeApi getBarcodeApi(){
        return mBarcodeApi;

    }
}
