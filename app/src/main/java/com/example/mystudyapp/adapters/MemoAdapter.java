package com.example.mystudyapp.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudyapp.R;
import com.example.mystudyapp.models.MemoItem;
import com.example.mystudyapp.models.Weather;

import java.util.List;

public class MemoAdapter extends BaseAdapter {

    private final List<MemoItem> mData;
    public MemoAdapter(List<MemoItem> memoList){
        mData = memoList;
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
        ViewHolder viewHolder;
        //convertView : 재사용 되는뷰
        if (convertView == null) {
            viewHolder = new ViewHolder();

            //뷰를 새로 만들 때
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_memo,parent,false);

            // 레이아웃 들고 오기
            TextView title_txt = (TextView) convertView.findViewById(R.id.title_text);
            TextView content_txt = (TextView) convertView.findViewById(R.id.content_text);

            viewHolder.title_text = title_txt;
            viewHolder.content_text = content_txt;

            convertView.setTag(viewHolder);

        }else {
            // 재사용 할 때
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 데이터
        MemoItem memoItem = mData.get(position);


        // 화면에 뿌리기
        viewHolder.title_text.setText(memoItem.getTitle()); //제목
        viewHolder.content_text.setText(memoItem.getContent());//내용



        return convertView;
    }

    private static class ViewHolder {
        TextView title_text;
        TextView content_text;
    }
}
