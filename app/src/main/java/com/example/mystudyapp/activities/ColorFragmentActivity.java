package com.example.mystudyapp.activities;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mystudyapp.R;
import com.example.mystudyapp.fragments.ColorFragment;

import java.util.Random;

public class ColorFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_fragment);

        // XML에서 프래그먼트 가져오기
        ColorFragment colorFragment = (ColorFragment) getSupportFragmentManager().findFragmentById(R.id.color_frag);
        colorFragment.setColor(Color.BLUE);

        // 동적으로 프래그먼트 추가
        ColorFragment colorFragment2 = new ColorFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("color",Color.YELLOW);
        bundle.putString("text","글자");
        colorFragment2.setArguments(bundle);

        // 팩토리 패턴을 활용한 프래그먼트 생성 (가장많이 사용)
        ColorFragment colorFragment3 = ColorFragment.newInstance(Color.GREEN);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_frame,colorFragment2)
                .commit();
    }

    public void onClick(View view) {
        ColorFragment newColorFragment = new ColorFragment();
        Random ran = new Random();
        int r = ran.nextInt(100);
        int g = ran.nextInt(100);
        int b = ran.nextInt(100);
        int color = Color.rgb(r,g,b);
        newColorFragment.setColor(color);
        // 기존의 프래그먼트를 교체
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_frame,newColorFragment)
                .commit();
    }
}
