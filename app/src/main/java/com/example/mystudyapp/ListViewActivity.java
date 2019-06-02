package com.example.mystudyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mystudyapp.activities.ColorFragmentActivity;
import com.example.mystudyapp.activities.FragmentExamActivity;
import com.example.mystudyapp.activities.LifeCycleActivity;
import com.example.mystudyapp.activities.ListViewExamActivity;
import com.example.mystudyapp.activities.MainActivity;
import com.example.mystudyapp.activities.MemoActivity;
import com.example.mystudyapp.activities.WeatherActivity;
import com.example.mystudyapp.activities.WebBrowserActivity;
import com.example.mystudyapp.adapters.MyAdapter;
import com.example.mystudyapp.models.ListItem;

import java.util.ArrayList;

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


        addItem("1) 사운드 & 스탑워치","05-18", MainActivity.class);
        addItem("2) WebView","05-18", WebBrowserActivity.class);
        addItem("3) 날씨앱(05-19)","모델클래스를 활용하여 BaseAdapter 연습", WeatherActivity.class);
        addItem("5) 메모앱(05-20)","BaseAdapter를 활용한 메모장 App", MemoActivity.class);
        addItem("6) 은행앱(05-22)","BaseAdapter를 활용한 은행 App",MemoActivity.class);
        addItem("7) LifeCycle(05-26)","Activity LifeCycle", LifeCycleActivity.class);
        addItem("8) 농구앱(05-26)","SharedPreferences이용해 데이터 저장", BasketBallActivity.class);
        addItem("9) Fragment(05-29)","Fragment연습", ColorFragmentActivity.class);
        addItem("10) 리스트뷰 연습","리스트뷰연습", ListViewExamActivity.class);
        addItem("11) 프래그먼트 연습","프래그먼트연습", FragmentExamActivity.class);
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
