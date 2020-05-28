package com.example.mystudyapp.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mystudyapp.R;

public class LifeCycleFragment extends Fragment {


    // 빈 생성자가 필요,   파라미터를 가질 수 없음 (액티비티의 라이프사이클에 맞게 동작하기 위해)
    public LifeCycleFragment() {
        // Required empty public constructor
    }


    // 액티비티에 붙을때
    @Override
    public void onAttach(Context context) {

        // context : 액티비티
        super.onAttach(context);
    }

    // 프래그먼트가 생성될때
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        // savedInstanceState 로 복원 처리 가능능
       super.onCreate(savedInstanceState);
    }

    // 액티비티의 onCreate()
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lifecycle, container, false);
    }


    // 뷰 소멸
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    // 프래그먼트 소멸
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    // 액티비티에서 떨어짐
    @Override
    public void onDetach() {
        super.onDetach();
    }
}
