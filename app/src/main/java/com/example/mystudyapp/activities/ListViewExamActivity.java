package com.example.mystudyapp.activities;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mystudyapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListViewExamActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Integer> mdata = null;

    private MyAdapter2 mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_exam);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
//
//        if (getIntent().getSerializableExtra("data") != null) {
//            mdata = (List<Integer>) getIntent().getSerializableExtra("data");
//        } else {
//            mdata = new ArrayList<>();
//            for (int i = 1; i <= 100; i++) {
//                mdata.add(i);
//            }
//        }
        mdata = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            mdata.add(i);
        }
        mAdapter = new MyAdapter2(mdata);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        //데이터 뒤집기
        Collections.reverse(mdata);

        mAdapter.notifyDataSetChanged();
    }

    private static class MyAdapter2 extends BaseAdapter {

        private final List<Integer> mData;

        public MyAdapter2(List<Integer> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();


                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_text, parent, false);
                viewHolder.textView = convertView.findViewById(R.id.text_view);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //데이터 표시
            int data = mData.get(position);
            viewHolder.textView.setText("" + data);
            return convertView;
        }

    }

    private static class ViewHolder {
        TextView textView;
    }
}
