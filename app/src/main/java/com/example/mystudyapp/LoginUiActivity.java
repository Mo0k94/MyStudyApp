package com.example.mystudyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mystudyapp.Retrofit2.ResultModel;
import com.example.mystudyapp.Retrofit2.RetrofitUtil;
import com.example.mystudyapp.Retrofit2.UserApi;
import com.example.mystudyapp.activities.Memo2Activity;
import com.example.mystudyapp.activities.Retrofit2Activity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUiActivity extends AppCompatActivity {

    TextView LoginTxt, RegisterTxt;
    EditText mNickName, mPassword;

    private Gson mgSon;
    private UserApi mUserApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ui);

        init();

        mUserApi = new RetrofitUtil().getUserApi();
    }

    public void init() {
        mNickName = findViewById(R.id.nick_name);
        mPassword = findViewById(R.id.pass_word);


        LoginTxt = findViewById(R.id.Login_txt);
        RegisterTxt = findViewById(R.id.register);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Login_txt:
                Login();
                break;
            case R.id.register:
                break;
            default:
                break;
        }
    }

    public void Login(){
        String NickID = mNickName.getText().toString();
        String userPassword = mPassword.getText().toString();

        if(NickID.equals("") || NickID == null){
            Toast.makeText(this, "NickName을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }else if(userPassword.equals("") || userPassword == null){
            Toast.makeText(this, "Password를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<ResultModel> call = mUserApi.Nick_login(NickID,userPassword);
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                //정상 결과
                ResultModel result = response.body();

                Log.d("TAG","Login result " + result.getResult());
                if(response.body().getResult().equals("success")){
                    //로그인 성공

                    startActivity(new Intent(LoginUiActivity.this, Memo2Activity.class));
                    finish();
                    Toast.makeText(LoginUiActivity.this,"로그인 성공",Toast.LENGTH_SHORT).show();
                }else{
                    //로그인 실패
                    Toast.makeText(LoginUiActivity.this,"로그인 실패",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                // 네트워크 문제

            }
        });
    }
}
