package com.example.mystudyapp;

import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Chronometer mChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                sound();
            }
        });

    }

    //초기화
    private void init() {
        mChronometer = (Chronometer) findViewById(R.id.m_chronometer);
    }


    //메뉴를 붙이는 부분
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    //메뉴클릭시!
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                Toast.makeText(this, "설정 아직 미구현!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_menu2:
                sound();
                return true;
            case R.id.action_menu3:
                startChronometer();
                return true;
            case R.id.action_menu4:
                stopChronometer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    //타이머스타트
    private void startChronometer() {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();

    }

    //타이머 스톱
    private void stopChronometer() {
        mChronometer.setBase(SystemClock.elapsedRealtime());//0초로 초기화
        mChronometer.stop();

    }

    //사운드
    private void sound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.button_sound);
        mediaPlayer.start();
    }
}
