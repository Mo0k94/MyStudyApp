package com.example.mystudyapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private List<ListItem> mData;
    public MyAdapter(ArrayList<ListItem> listitem) {
        mData = listitem;

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
                    .inflate(R.layout.list,parent,false);

            // 레이아웃 들고 오기
            TextView titleTextView = (TextView) convertView.findViewById(R.id.title_txt);
            TextView title2TextView = (TextView) convertView.findViewById(R.id.title2_txt);
            viewHolder.titleTextView = titleTextView;
            viewHolder.title2TextView = title2TextView;

            convertView.setTag(viewHolder);
        }else {
            // 재사용 할 때
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 데이터
        ListItem listitem = mData.get(position);


        // 화면에 뿌리기
        viewHolder.titleTextView.setText(listitem.getTitle());
        viewHolder.title2TextView.setText(listitem.getTitle2());

        return convertView;
    }



    private static class ViewHolder {
        TextView titleTextView;
        TextView title2TextView;
    }
}
