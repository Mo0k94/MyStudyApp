package com.example.mystudyapp.activities;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mystudyapp.R;

public class ThreadActivity extends AppCompatActivity {

    private static final String TAG = ThreadActivity.class.getSimpleName();
    private TextView mTextView;
    private int mNumber = 0;

    private Handler mHandler = new Handler();

    private Handler mHandler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            mTextView.setText(msg.what + "");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        mTextView = findViewById(R.id.text_view);


        // 메인 스레드에서는 UI 갱신이 가능


        new Thread(new Runnable() {
            @Override
            public void run() {
                // 복잡한 오래걸리는 처리
                for (int i=0; i<10; i++){
                    mNumber++;
                    try {
                        Thread.sleep(1000);
                        // 1). 스레드에서는 UI 갱신이 안된다.
                        // 2). 스레드에서는 Handler를 생성할 수 없다.
                        // # Handler를 사용해서 UI 갱신을 해야 됨
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mTextView.setText(mNumber + "");
                            }
                        });

                        // 위에 코드와 같은 코드2
                        //mHandler2.sendEmptyMessage(mNumber);

                        // 위에 코드와 같은 코드3
                        // 모든 View는 Handler를 가지고 있다.
//                        mTextView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                mTextView.setText(mNumber + "");
//                            }
//                        });

                        // 위에 코드와 같은 코드 4
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                mTextView.setText(mNumber + "");
//                            }
//                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG,"run : " + i );

                }
            }
        }).start();
    }
}
