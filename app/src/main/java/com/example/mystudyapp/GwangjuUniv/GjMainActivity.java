package com.example.mystudyapp.GwangjuUniv;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.mystudyapp.activities.ViewPagerActivity;
import com.example.mystudyapp.adapters.ImageRecyclerAdapter;
import com.example.mystudyapp.models.getServerImage;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class GjMainActivity extends AppCompatActivity {

    private TextView board_txt,  mLast_login_txt, mMain_welcome;
    private Intent intent;

    private UserApi mUserApi;

    private String share="";
    private String id="";
    SharedPreferences setting;    //아이디 저장 기능
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gj_main);

        setting = getSharedPreferences("setting", Activity.MODE_PRIVATE);
        editor = setting.edit();

        share = setting.getString("ID","");
        if("".equals(share)){
            intent = getIntent();
            id = intent.getStringExtra("NickName");
        }else{
            id = share;
        }
        editor.putString("ID", id);
        editor.apply();
        init();


        mMain_welcome.setText(id+"님 환영합니다.");
        mMain_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectLoginActivity();
            }
        });
        mUserApi = new RetrofitUtil().getUserApi();


        getLastDate();

    }


    public void init() {
        board_txt = findViewById(R.id.board_txt);
        mLast_login_txt = findViewById(R.id.last_login_txt);
        mMain_welcome = findViewById(R.id.main_welcome);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.board_txt:

                startActivity(new Intent(GjMainActivity.this, GJBoardActivity.class));
                Toast.makeText(this, "게시판", Toast.LENGTH_SHORT).show();

                break;
            case R.id.food_txt:
                startActivity(new Intent(GjMainActivity.this, ViewPagerActivity.class));
                Toast.makeText(this, "학식", Toast.LENGTH_SHORT).show();
            case R.id.food_Img:
                startActivity(new Intent(GjMainActivity.this, ViewPagerActivity.class));
                Toast.makeText(this, "학식", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    private void redirectLoginActivity() {
        setting = getSharedPreferences("setting", Activity.MODE_PRIVATE);
        editor = setting.edit();
        editor.clear();
        //editor.commit();
        editor.apply();
        intent = new Intent(GjMainActivity.this, LoginUiActivity.class);
        startActivity(intent);
        finish();
    }

    private void getLastDate(){
        Log.d("TAG", "getServerData Start!!! " + id );
        Call<List<ResultModel>> call = mUserApi.getLastLoginDate(id);
        call.enqueue(new Callback<List<ResultModel>>() {

            @Override
            public void onResponse(Call<List<ResultModel>> call, retrofit2.Response<List<ResultModel>> response) {

                Log.d("TAG", "Login result " + response.toString());

                //정상 결과
                List<ResultModel> result = response.body();
                Log.d("TAG", "Login result " + result.toString());
                Log.d("TAG", "Login result " + result.get(0).getLasted_Date());
                String lastDate = result.get(0).getLasted_Date();

                mLast_login_txt.setText("최근로그인 : " + lastDate);


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
