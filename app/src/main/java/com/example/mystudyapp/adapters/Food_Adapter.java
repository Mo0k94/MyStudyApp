package com.example.mystudyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mystudyapp.R;
import com.example.mystudyapp.models.FoodMenu;
import com.example.mystudyapp.models.ListItem;

import java.util.ArrayList;
import java.util.List;

public class Food_Adapter extends BaseAdapter {
    private List<FoodMenu> mData;
    private Context context;

    public Food_Adapter(Context context,List<FoodMenu> listitem) {
        this.context = context;
        this.mData = listitem;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //convertView : 재사용 되는뷰
        if (convertView == null) {
            viewHolder = new ViewHolder();

            //뷰를 새로 만들 때
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_foodmenu,parent,false);

            // 레이아웃 들고 오기
            TextView date_txt = (TextView) convertView.findViewById(R.id.date_txt);
            TextView day_txt = (TextView) convertView.findViewById(R.id.day_txt);
            TextView rice_text = (TextView) convertView.findViewById(R.id.rice_txt);
            TextView soup_text = (TextView) convertView.findViewById(R.id.soup_txt);
            TextView ban1_text = (TextView) convertView.findViewById(R.id.ban1_txt);
            TextView ban2_text = (TextView) convertView.findViewById(R.id.ban2_txt);
            TextView ban3_text = (TextView) convertView.findViewById(R.id.ban3_txt);
            TextView ban4_text = (TextView) convertView.findViewById(R.id.ban4_txt);

            viewHolder.date_txt = date_txt;
            viewHolder.day_txt = day_txt;
            viewHolder.rice_txt = rice_text;
            viewHolder.soup_txt = soup_text;
            viewHolder.ban1_txt = ban1_text;
            viewHolder.ban2_txt = ban2_text;
            viewHolder.ban3_txt = ban3_text;
            viewHolder.ban4_txt = ban4_text;

            convertView.setTag(viewHolder);
        }else {
            // 재사용 할 때
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 데이터
        FoodMenu listitem = mData.get(position);


        // 화면에 뿌리기
        viewHolder.date_txt.setText(listitem.getDate());
        viewHolder.day_txt.setText(listitem.getDay());
        viewHolder.rice_txt.setText(listitem.getRice());
        viewHolder.soup_txt.setText(listitem.getSoup());
        viewHolder.ban1_txt.setText(listitem.getBan1());
        viewHolder.ban2_txt.setText(listitem.getBan2());
        viewHolder.ban3_txt.setText(listitem.getBan3());
        viewHolder.ban4_txt.setText(listitem.getBan4());

        return convertView;
    }



    private static class ViewHolder {
        TextView date_txt;
        TextView day_txt;
        TextView rice_txt;
        TextView soup_txt;
        TextView ban1_txt;
        TextView ban2_txt;
        TextView ban3_txt;
        TextView ban4_txt;
    }
}
