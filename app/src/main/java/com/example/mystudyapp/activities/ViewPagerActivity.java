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

    private static class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return ColorFragment.newInstance(Color.RED);
                case 1:
                    return ColorFragment.newInstance(Color.YELLOW);
                case 2:
                    return ColorFragment.newInstance(Color.GREEN);
                case 3:
                    return ColorFragment.newInstance(Color.CYAN);
                case 4:
                    return ColorFragment.newInstance(Color.MAGENTA);
            }
            return null;
        }

        //페이지가 몇 장인지
        @Override
        public int getCount() {
            return 5;
        }


        //탭레이아웃 제목 표시!
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Tab1";
                case 1:
                    return "Tab2";
                case 2:
                    return "Tab3";
                case 3:
                    return "Tab4";
                case 4:
                    return "Tab5";
            }
            return null;
        }
    }
}
