package com.example.mystudyapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudyapp.R;

public class LifeCycleActivity extends AppCompatActivity {

    private ImageView mLifeImg;
    private TextView mGametxt;
    private Button mLifeBtn;
    private EditText mTestedit;
    private static final String TAG = LifeCycleActivity.class.getCanonicalName();

    private int mScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        mLifeImg = findViewById(R.id.life_img);

        mGametxt = findViewById(R.id.game_txt);
        mTestedit = findViewById(R.id.test_edit);
        // EditText 는 화면이 돌아가도 데이터 저장여부는 ID값이 있느냐 없느냐

        mLifeBtn = findViewById(R.id.life_btn);
        mLifeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScore += 100;
                setScore(mScore);
            }
        });


        setScore(mScore);
        Log.d(TAG,"onCreate : Activity 생성!" );

        //복원 방법 1 (onCreate 에서)
       if (savedInstanceState != null){ // onSaveInstanceState에 저장된게 있다면
            mScore = savedInstanceState.getInt("score");
            setScore(mScore);
        }
    }


    // 복원 방법 2 (onRestoreInstanceState 에서 ) 둘다 됨
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

//        if (savedInstanceState != null){ // onSaveInstanceState에 저장된게 있다면
//            mScore = savedInstanceState.getInt("score");
//            setScore(mScore);
//        }
    }

    private void setScore(int score){
        mGametxt.setText("점수 : " + score + "점");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart : Activity 시작!" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop : Activity 홈키(일시정지), 종료" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause : Activity 홈키(일시정지)" );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart : Activity 재시작" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestory : Activity 종료" );
    }

    // 인스턴스의 상태를 저장
    /*
    * 화면을 돌렸을 시
    * onPause -> onSaveInstanceState -> onStop -> onDestory -> onCretae -> onStart -> onResume
    *            (이때 값을 저장해야함)
    *
    * ## 화면 세로로 고정 하는 법
    * Manifest 고정하려는 Activity 에
    * android:screenOrientation="portrait" 추가
    * */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState : Activity 가로/세로 전환" );

        outState.putInt("score",mScore); //스코어 값을 저장
    }
}
