package com.example.mystudyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mystudyapp.activities.AsyncTaskActivity;
import com.example.mystudyapp.activities.CardViewActivity;
import com.example.mystudyapp.activities.ColorFragmentActivity;
import com.example.mystudyapp.activities.FragmentExamActivity;
import com.example.mystudyapp.activities.GalleryActivity;
import com.example.mystudyapp.activities.GeoIpActivity;
import com.example.mystudyapp.activities.HTMLParserActivity;
import com.example.mystudyapp.activities.ImageFragmentActivity;
import com.example.mystudyapp.activities.ImageListViewActivity;
import com.example.mystudyapp.activities.LifeCycleActivity;
import com.example.mystudyapp.activities.ListViewExamActivity;
import com.example.mystudyapp.activities.MainActivity;
import com.example.mystudyapp.activities.MapsActivity;
import com.example.mystudyapp.activities.MemoActivity;
import com.example.mystudyapp.activities.RecyclerViewActivity;
import com.example.mystudyapp.activities.Retrofit2Activity;
import com.example.mystudyapp.activities.Selector_ImageActivity;
import com.example.mystudyapp.activities.ThreadActivity;
import com.example.mystudyapp.activities.ViewPagerActivity;
import com.example.mystudyapp.activities.ViewPagerExamActivity;
import com.example.mystudyapp.activities.WeatherActivity;
import com.example.mystudyapp.activities.WebBrowserActivity;
import com.example.mystudyapp.activities.addViewActivity;
import com.example.mystudyapp.activities.getServerImageActivity;
import com.example.mystudyapp.adapters.MyAdapter;
import com.example.mystudyapp.models.ListItem;

import java.util.ArrayList;
import java.util.Collections;

public class ListViewActivity extends AppCompatActivity {


    private ListView mListView;
    private ArrayList<ListItem> mDataList;
    private ArrayList<ListItem> mDataList_backup;
    private MyAdapter mAdapter;


    private EditText mSearch_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mListView = findViewById(R.id.list_view);




        //데이터터
        mDataList = new ArrayList<>();






        addItem("1) 사운드 & 스탑워치", "05-18", MainActivity.class);
        addItem("2) WebView", "05-18", WebBrowserActivity.class);
        addItem("3) 날씨앱(05-19)", "모델클래스를 활용하여 BaseAdapter 연습", WeatherActivity.class);
        addItem("5) 메모앱(05-20)", "BaseAdapter를 활용한 메모장 App", MemoActivity.class);
        addItem("6) 은행앱(05-22)", "BaseAdapter를 활용한 은행 App", MemoActivity.class);
        addItem("7) LifeCycle(05-26)", "Activity LifeCycle", LifeCycleActivity.class);
        addItem("8) 농구앱(05-26)", "SharedPreferences이용해 데이터 저장", BasketBallActivity.class);
        addItem("9) Fragment(05-29)", "Fragment연습", ColorFragmentActivity.class);
        addItem("10) 리스트뷰 연습", "리스트뷰연습", ListViewExamActivity.class);
        addItem("11) 프래그먼트 연습", "프래그먼트연습", FragmentExamActivity.class);
        addItem("12) 프래그먼트 콜백 연습", "콜백연습", ImageFragmentActivity.class);
        addItem("13) ViewPager", "FragmentPagerAdapter 연습", ViewPagerActivity.class);
        addItem("14) ViewPager 연습", "TabLayout + ViewPager 연습", ViewPagerExamActivity.class);
        addItem("15) Retrofit 통신 연습", "Retrofit2 + gson", GeoIpActivity.class);
        addItem("16) Google Map ", "Google Map 베타", MapsActivity.class);
        addItem("17) Gallery CursorAdapter ", "Gallery & Permission 권한 요청", GalleryActivity.class);
        addItem("18) 쓰레드 ", "Thread연습 ", ThreadActivity.class);
        addItem("18) AsyncTask ", "AsyncTask연습 ", AsyncTaskActivity.class);
        addItem("19) AsyncTask ", "Progress연습 ", AsyncTaskActivity.class);
        addItem("20) RecyclerView & EventBus ", "2019-09-08 ", RecyclerViewActivity.class);
        addItem("21) AddView ", "2019-09-08 ", addViewActivity.class);
        addItem("22) CardViewDesign ", "2019-09-24 ", CardViewActivity.class);
        addItem("23) Retrofit2", "2019-09-28 ", Retrofit2Activity.class);
        addItem("24) HTMLParser", "2019-10-05 ", HTMLParserActivity.class);
        addItem("25) CheckListView", "2019-10-09", CheckListViewActivity.class);
        addItem("26) Room라이브러리", "2019-10-13", RoomTestActivitiy.class);
        addItem("27) XML_Selector(버튼눌림)", "2019-11-16", Selector_ImageActivity.class);
        //addItem("29) 이미지 서버에 Upload", "2019-11-23", ImageListViewActivity.class);
        addItem("28) Server 이미지 가져오기", "2019-11-20", getServerImageActivity.class);
        addItem("29) 이미지 리사이클러뷰", "2019-11-23", ImageListViewActivity.class);


        Collections.reverse(mDataList);
        mAdapter = new MyAdapter(mDataList);

        mDataList_backup = new ArrayList<ListItem>();

        mDataList_backup.addAll(mDataList);
        mListView.setAdapter(mAdapter);


        mAdapter.notifyDataSetChanged();
        //이벤트
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), mDataList.get(position).getCls());
                Toast.makeText(ListViewActivity.this, mDataList.get(position).getTitle() + "로 이동!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });



        mSearch_edit = findViewById(R.id.edit_search);
        mSearch_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input 창에 문자를 입력할때마다 호출된다
                // search메소드를 호출
                String text = mSearch_edit.getText().toString();
                search(text);
            }
        });


    }

    private void addItem(String title, String title2, Class cls) {
        ListItem item = new ListItem(title, title2, cls);
        mDataList.add(item);
    }


    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        mDataList.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            mDataList.addAll(mDataList_backup);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < mDataList_backup.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (mDataList_backup.get(i).getTitle().toLowerCase().contains(charText) || mDataList_backup.get(i).getTitle().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    mDataList.add(mDataList_backup.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        mAdapter.notifyDataSetChanged();
    }


}
