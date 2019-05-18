package com.example.mystudyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {



    private ListView mListView;
    private ArrayList<ListItem> mDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mListView = findViewById(R.id.list_view);

        //데이터터
        mDataList = new ArrayList<>();


        addItem("1) 사운드 & 스탑워치","05-18",MainActivity.class);
        addItem("2) WebView","05-18",WebBrowserActivity.class);

        MyAdapter adapter = new MyAdapter(mDataList);

        mListView.setAdapter(adapter);

        //이벤트
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),mDataList.get(position).getCls());
                Toast.makeText(ListViewActivity.this, mDataList.get(position).getTitle() + "로 이동!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    private void addItem(String title, String title2, Class cls){
        ListItem item = new ListItem(title,title2,cls);
        mDataList.add(item);
    }

}
