package com.example.mystudyapp.activities;

import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mystudyapp.R;
import com.example.mystudyapp.fragments.ListViewFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerExamActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_exam);


        mTabLayout = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.pager);

        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(ListViewFragment.newInstance(createList('a','z')));
        fragmentList.add(ListViewFragment.newInstance(createList('A','Z')));
        fragmentList.add(ListViewFragment.newInstance(createList('ㄱ','ㅎ')));
        fragmentList.add(ListViewFragment.newInstance(createList('1','9')));

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(),
                fragmentList,
                new String[] {"영어 소문자", "영어 대문자", "한글", "숫자"});
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
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
    /*
    //소문자
    private List<String> createLowerCaseAlphabetList(){
        List<String> list = new ArrayList<>();
        char ch = 'a';
        for (char i= ch; i<='z'; i++){
            list.add(String.valueOf(i));//a부터 z까지 list에 넣음
        }
        return list;
    }
    //대문자
    private List<String> createUpperCaseAlphabetList(){
        List<String> list = new ArrayList<>();
        char ch = 'A';
        for (char i= ch; i<='Z'; i++){
            list.add(String.valueOf(i));//A부터 Z까지 list에 넣음
        }
        return list;
    }

    //한글
    private List<String> createHangulList(){
        List<String> list = new ArrayList<>();
        char ch = 'ㄱ';
        for (char i= ch; i<='ㅎ'; i++){
            list.add(String.valueOf(i));//ㄱ부터 ㅎ까지 list에 넣음
        }
        return list;
    }
    //숫자
    private List<String> createNumberList(){
        List<String> list = new ArrayList<>();
        char ch = '0';
        for (char i= ch; i<='9'; i++){
            list.add(String.valueOf(i));//ㄱ부터 ㅎ까지 list에 넣음
        }
        return list;
    }*/


    private static class MyAdapter extends FragmentPagerAdapter{

        private List<Fragment> mmFragmentList;
        private String[] mmPageTitles;
        public MyAdapter(FragmentManager fm,
                         List<Fragment> fragmentList,
                         String[] pageTitles){
            super(fm);
            mmFragmentList = fragmentList;
            mmPageTitles = pageTitles;
        }
        @Override
        public Fragment getItem(int position) {
            return mmFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mmFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return mmPageTitles[position];
        }
    }
}
