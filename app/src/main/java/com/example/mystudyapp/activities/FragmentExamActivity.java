package com.example.mystudyapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mystudyapp.R;
import com.example.mystudyapp.fragments.TextFragment;

import java.util.Random;

public class FragmentExamActivity extends AppCompatActivity {

    public Button mFrag_Btn1, mFrag_Btn2, mFrag_Btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_exam);
        init();
    }

    private void init() {
        mFrag_Btn1 = findViewById(R.id.Frag_Btn1);
        mFrag_Btn2 = findViewById(R.id.Frag_Btn2);
        mFrag_Btn3 = findViewById(R.id.Frag_Btn3);


    }

    public void onClickFrag(View view) {
        switch (view.getId()) {
            case R.id.Frag_Btn1:
                addFragment(R.id.container_frame1, "1번프래그먼트");
                break;
            case R.id.Frag_Btn2:
                addFragment(R.id.container_frame2, "2번프래그먼트");
                break;
            case R.id.Frag_Btn3:
                addFragment(R.id.container_frame3, "3번프래그먼트");
                break;
            default:
                break;

        }
    }

    private void addFragment(int containerId, String text) {
        Random ran = new Random();
        int r = ran.nextInt(100);
        int g = ran.nextInt(100);
        int b = ran.nextInt(100);
        int color = Color.rgb(r, g, b);

        TextFragment examFragment = new TextFragment();
        getSupportFragmentManager().beginTransaction()
                .add(containerId, examFragment)
                .commit();
        examFragment.setColor(color);
        examFragment.setText(text);
    }
}
