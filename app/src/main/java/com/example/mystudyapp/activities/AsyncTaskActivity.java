package com.example.mystudyapp.activities;

import android.os.AsyncTask;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mystudyapp.R;

public class AsyncTaskActivity extends AppCompatActivity {

    private static TextView mTextView;
    private static final String TAG = AsyncTaskActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        mTextView = findViewById(R.id.text_view);


        /*  # AsyncTask 제약사항 #
            1. AsyncTask 클래스는 반드시 UI 스레드에서 로드 해야 한다.
            2. AsyncTask 인스턴스는 반드시 UI 스레드에서 생성해야 한다.
            3. AsyncTask.execute() 도 반드시 UI 스레드에서 호출해야 한다.
            4. 모든 콜백(onPreExecute,doInBackground,onProgressUpdate,onPostExecute) 들은 직접 호출하면 안된다.
            5. AsyncTask 인스턴스는 한번만 실행할 수 있다.
            6. AsyncTask Cancel도 가능하다.
            MyAsyncTask task = new MyAsyncTask();
            task.execute(0);
            task.execute(0);   중복 실행 안됨
            task.cancel(true);
         */

        /*
            일반적인 사용법
            // 순차적으로 실행
            new MyAsyncTask().execute(0);
            new MyAsyncTask().execute(0);
            new MyAsyncTask().execute(0);

            // 병렬로 수행되는 AsyncTask
            new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTE,0);
        */

        new MyAsyncTask().execute(0);
    }


    /*
        # AsyncTask #
         <> 1. Parameter 타입
         -> Task가 실행되며 함께 전달해야하는 파라미터
         <> 2. Progress 타입
         -> 백그라운드 작업이 수행되는 동안에 발생되는 프로그레스 타입
         <> 3. Result 타입
         -> 백그라운드 작업이 끝나고 그 결과의 타입
    */
    private static class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {


        @Override
        protected void onPreExecute() {
            // 최초 실행 되는 부분
        }

        @WorkerThread
        @Override
        // ... =  [] 과 같다. ... 은 배열도 받을 수 있고 일반도 받을 수 있다. []은 무조건 배열만 받아야한다.
        protected Integer doInBackground(Integer... params) {
            int number = params[0];
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                number++;

                // UI 갱신
                publishProgress(number);
                // -> onProgressUpdate 메서드로 넘어감
            }

            // 오래 걸리는 처리
            return number;      // onPostExecute로 넘어감
        }

        @UiThread
        @Override
        protected void onProgressUpdate(Integer... values) {
            // UI 갱신
            mTextView.setText(values[0] + "");
        }

        @UiThread
        @Override
        protected void onPostExecute(Integer integer) {
            mTextView.setText(integer + "종료");
            Log.d(TAG, "onPostExecute : " + integer);
        }
    }

}
