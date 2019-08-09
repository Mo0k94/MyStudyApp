package com.example.mystudyapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mystudyapp.R;

import retrofit2.Retrofit;

public class WeatherApiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_api);

    }
}
