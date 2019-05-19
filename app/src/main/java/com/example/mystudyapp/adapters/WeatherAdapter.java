package com.example.mystudyapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudyapp.R;
import com.example.mystudyapp.models.ListItem;
import com.example.mystudyapp.models.Weather;

import java.util.List;

public class WeatherAdapter extends BaseAdapter {

    private Context mContext;
    private List<Weather> mData;

    public WeatherAdapter(Context context, List<Weather> data){
        this.mContext = context;
        this.mData = data;
    }



    // 아이템 갯수
    @Override
    public int getCount() {
        return mData.size();
    }

    // position번째 아이템을 반환
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    // position번째 id
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position번째의 레이아웃
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WeatherAdapter.ViewHolder viewHolder;
        //convertView : 재사용 되는뷰
        if (convertView == null) {
            viewHolder = new WeatherAdapter.ViewHolder();

            //뷰를 새로 만들 때
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_weather,parent,false);

            // 레이아웃 들고 오기
            ImageView weather_img = convertView.findViewById(R.id.weather_image);
            TextView location_txt = (TextView) convertView.findViewById(R.id.location_text);
            TextView temp_txt = (TextView) convertView.findViewById(R.id.temperature_text);

            viewHolder.weather_image = weather_img;
            viewHolder.location_text = location_txt;
            viewHolder.temp_text = temp_txt;

            convertView.setTag(viewHolder);

        }else {
            // 재사용 할 때
            viewHolder = (WeatherAdapter.ViewHolder) convertView.getTag();
        }

        // 데이터
        Weather weatheritem = mData.get(position);


        // 화면에 뿌리기
        viewHolder.weather_image.setImageResource(weatheritem.getImageRes()); //이미지
        viewHolder.location_text.setText(weatheritem.getLocation());//지역
        viewHolder.temp_text.setText(weatheritem.getTemp());   //기온


        //홀수 줄은 빨간 색
        if (position % 2 == 1){
            convertView.setBackgroundColor(Color.RED);
        }else{
            convertView.setBackgroundColor(Color.WHITE);
        }
        if (mSelectedPosition == position){
            convertView.setBackgroundColor(Color.YELLOW);
        }
        return convertView;
    }


    // -1이면 선택된게 없다
    private int mSelectedPosition = -1;

    public void setSelect(int position){
        mSelectedPosition = position;
    }

    private static class ViewHolder {
        ImageView weather_image;
        TextView location_text;
        TextView temp_text;
    }
}
