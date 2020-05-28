package com.example.mystudyapp.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mystudyapp.R;
import com.example.mystudyapp.fragments.Food_MenuFragment;
import com.example.mystudyapp.fragments.Food_WeekFragment;

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

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        // 뷰페이저와 연결
        mTabLayout.setupWithViewPager(mViewPager);


    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public boolean tag = true;
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {

            switch (position){
            //ListViewFragment.newInstance(createList('a','z'));
                case 0:
                    return Food_WeekFragment.newInstance(tag);
                case 1:
                    tag = false;
                    return Food_MenuFragment.newInstance("kor");

                case 2:
                    tag = false;
                    return Food_MenuFragment.newInstance("west");
                case 3:
                    tag = false;
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
                    return "분식";
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
