package com.example.mystudyapp.GwangjuUniv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mystudyapp.R;
import com.example.mystudyapp.Retrofit2.ImageApi;
import com.example.mystudyapp.Retrofit2.ResultModel;
import com.example.mystudyapp.Retrofit2.RetrofitImage;
import com.example.mystudyapp.Retrofit2.RetrofitUtil;
import com.example.mystudyapp.Retrofit2.UserApi;
import com.example.mystudyapp.adapters.ImageRecyclerAdapter;
import com.example.mystudyapp.models.getServerImage;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class GjMainActivity extends AppCompatActivity {

    private TextView board_txt,  mLast_login_txt;
    private Intent intent;

    private UserApi mUserApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gj_main);


        init();

        mUserApi = new RetrofitUtil().getUserApi();


        getLastDate();

    }


    public void init() {
        board_txt = findViewById(R.id.board_txt);
        mLast_login_txt = findViewById(R.id.last_login_txt);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.board_txt:

                startActivity(new Intent(GjMainActivity.this, GJBoardActivity.class));
                finish();
                Toast.makeText(this, "게시판", Toast.LENGTH_SHORT).show();

                break;

            default:
                break;
        }
    }


    private void getLastDate(){
        Log.d("TAG", "getServerData Start!!! " );
        Call<List<ResultModel>> call = mUserApi.getLastLoginDate();
        call.enqueue(new Callback<List<ResultModel>>() {

            @Override
            public void onResponse(Call<List<ResultModel>> call, retrofit2.Response<List<ResultModel>> response) {

                //정상 결과
                List<ResultModel> result = response.body();

                

                //Glide.with(getApplicationContext()).load(result.get(1).getPath()).into(imageView);
            }

            @Override
            public void onFailure(Call<List<ResultModel>> call, Throwable t) {
                // 네트워크 문제
                Log.d("TAG", "Login onFailure " + t);
            }
        });

    }
}
