package com.example.mystudyapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.mystudyapp.R;
import com.example.mystudyapp.fragments.ColorFragment;
import com.example.mystudyapp.fragments.Food_MenuFragment;
import com.example.mystudyapp.fragments.Food_WeekFragment;
import com.example.mystudyapp.fragments.ListViewFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mViewPager = findViewById(R.id.pager);
        mTabLayout = findViewById(R.id.tab);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        // 뷰페이저와 연결
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:

                    return Food_WeekFragment.newInstance();
            //ListViewFragment.newInstance(createList('a','z'));
                case 1:
                    return Food_MenuFragment.newInstance("kor");
                case 2:
                    return Food_MenuFragment.newInstance("west");
                case 3:
                    return Food_MenuFragment.newInstance("snack");
            }
            return null;
        }

        //페이지가 몇 장인지
        @Override
        public int getCount() {
            return 4;
        }


        //탭레이아웃 제목 표시!
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "정식";
                case 1:
                    return "한식";
                case 2:
                    return "양식";
                case 3:
                    return "푸드점(분식)";
            }
            return null;
        }
    }

    //메소드 정규화
    private List<String> createList(char start, char end){
        List<String> list = new ArrayList<>();
        char ch = start;
        for (char i= ch; i<=end; i++){
            list.add(String.valueOf(i));//a부터 z까지 list에 넣음
        }
        return list;
    }
}
