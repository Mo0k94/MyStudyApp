package com.example.mystudyapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mystudyapp.R;
import com.example.mystudyapp.Retrofit2.ApiClient;
import com.example.mystudyapp.Retrofit2.ResultModel;
import com.example.mystudyapp.Retrofit2.RetrofitUtil;
import com.example.mystudyapp.Retrofit2.UserApi;
import com.example.mystudyapp.models.Login;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Retrofit2Activity extends AppCompatActivity {


    EditText idText, passwordText;
    Button loginButton, registerButton;

    String NickID = "";
    String userPassword = "";

    TextView resultText;
    private Gson mgSon;


    private UserApi mUserApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);


        idText = (EditText) findViewById(R.id.NickNameText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        resultText = findViewById(R.id.result_text);

        loginButton = (Button) findViewById(R.id.loginButton);

        mUserApi = new RetrofitUtil().getUserApi();




        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String NickID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();

                //UserApi apiInterface = ApiClient.getApiClient().create(UserApi.class);


                Retrofit retrofit = ApiClient.getApiClient();


                Call<ResultModel> call = mUserApi.Nick_login(NickID,userPassword);
                call.enqueue(new Callback<ResultModel>() {
                    @Override
                    public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                        //정상 결과
                        ResultModel result = response.body();

                        Log.d("TAG","Login result " + result.getResult());
                        if(response.body().getResult().equals("success")){
                            //로그인 성공

                            startActivity(new Intent(Retrofit2Activity.this,Memo2Activity.class));
                            finish();
                            Toast.makeText(Retrofit2Activity.this,"로그인 성공",Toast.LENGTH_SHORT).show();
                        }else{
                            //로그인 실패
                            Toast.makeText(Retrofit2Activity.this,"로그인 실패",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultModel> call, Throwable t) {
                        // 네트워크 문제

                    }
                });





            }
        });


    }
}
