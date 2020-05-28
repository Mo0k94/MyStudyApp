package com.example.mystudyapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.core.content.SharedPreferencesCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BasketBallActivity extends AppCompatActivity {

    private TextView mScoreTxt;
    private Button mBasket_btn;
    private int mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket_ball);

        mScoreTxt = findViewById(R.id.scoreTxt);
        mBasket_btn = findViewById(R.id.basket_btn);


        //저장한값 읽어오기
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mScore = sharedPreferences.getInt("score",0);
        setScore(mScore);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //저장
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("score",mScore);
        //editor.commit();    //동기 (sync)
        editor.apply();     //비동기 (async)
    }


    public void onClickButton(View view) {
        mScore += 100;
        setScore(mScore);
    }
    private void setScore(int score){
        mScoreTxt.setText("점수 : " + score + "점");
    }
}
