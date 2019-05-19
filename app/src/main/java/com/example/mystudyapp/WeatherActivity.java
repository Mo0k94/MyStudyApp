package com.example.mystudyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.mystudyapp.adapters.WeatherAdapter;
import com.example.mystudyapp.models.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView mList_view;
    private GridView mGrid_view;
    private Spinner mSpinner;
    private WeatherAdapter mAdapter;
    private List<Weather> mWeatherList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        init();

        //날씨 데이터
        mWeatherList = new ArrayList<>();
        mWeatherList.add(new Weather(R.drawable.wheather_sun,"광주","30도"));
        mWeatherList.add(new Weather(R.drawable.wheather_rain,"서울","25도"));
        mWeatherList.add(new Weather(R.drawable.wheather_sun,"대구","35도"));
        mWeatherList.add(new Weather(R.drawable.wheather_snow,"부산","15도"));
        mWeatherList.add(new Weather(R.drawable.wheather_sun,"울산","30도"));
        mWeatherList.add(new Weather(R.drawable.wheather_rain,"인천","27도"));
        mWeatherList.add(new Weather(R.drawable.wheather_sun,"대전","30도"));
        mWeatherList.add(new Weather(R.drawable.wheater_cloud,"포천","24도"));
        mWeatherList.add(new Weather(R.drawable.wheater_cloud,"의정부","25도"));

        //어댑터
        mAdapter = new WeatherAdapter(this,mWeatherList);
        mGrid_view.setAdapter(mAdapter);
        mList_view.setAdapter(mAdapter);
        mSpinner.setAdapter(mAdapter);
        mList_view.setOnItemClickListener(this);
        mList_view.setOnItemLongClickListener(this);
    }



    private void init() {
        mList_view = findViewById(R.id.list_view);
        mGrid_view = findViewById(R.id.grid_view);
        mSpinner = findViewById(R.id.spinner_view);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.setSelect(position);
        //데이터가 변경됨을 알려줌 = 다시 그려라
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //롱클릭시 데이터 제거
        mWeatherList.remove(position);
        mAdapter.notifyDataSetChanged();
        return false;
    }
}
