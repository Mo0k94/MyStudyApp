package com.example.mystudyapp.GwangjuUniv;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mystudyapp.ListViewActivity;
import com.example.mystudyapp.R;
import com.example.mystudyapp.Retrofit2.ResultModel;
import com.example.mystudyapp.Retrofit2.RetrofitUtil;
import com.example.mystudyapp.Retrofit2.UserApi;
import com.example.mystudyapp.activities.Memo2Activity;
import com.example.mystudyapp.utils.Common;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUiActivity extends AppCompatActivity {

    TextView LoginTxt, RegisterTxt;
    EditText mNickName, mPassword;
    CheckBox chk_auto;

    SharedPreferences setting;    //아이디 저장 기능
    SharedPreferences.Editor editor;

    private AlertDialog dialog;


    private Gson mgSon;
    private UserApi mUserApi;

    private Intent intent;

    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date nowdate = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("YYYY-MM-dd hh:mm");  //06/19 13:50
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(nowdate);

    String NickID="";
    String userPassword ="";

    public ProgressDialog mProgressDialog = null;
    public Common.ProgressDialogHandler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ui);

        init();

        setting = getSharedPreferences("setting", Activity.MODE_PRIVATE);

        editor = setting.edit();

        mUserApi = new RetrofitUtil().getUserApi();



        if (setting.getBoolean("chk_auto", false)) {

            //Toast.makeText(getApplicationContext(), "데이터 셋팅!", Toast.LENGTH_LONG).show();
            String NickID = setting.getString("ID", "");
            String userPassword = setting.getString("PW", "");
            mNickName.setText(NickID);
            mPassword.setText(userPassword); //패스워드



            String uuid = GetDevicesUUID(this);

            Log.d("TAG", "UUID ==============> " + uuid);

            String lasted_date = formatDate;

            Log.d("TAG", "LastedDate ==============> " + lasted_date);

            if (NickID.equals("") || NickID == null) {
                Toast.makeText(this, "NickName을 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            } else if (userPassword.equals("") || userPassword == null) {
                Toast.makeText(this, "Password를 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            Call<ResultModel> call = mUserApi.Nick_loginLog(NickID, userPassword,lasted_date,uuid);



            call.enqueue(new Callback<ResultModel>() {
                @Override
                public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                    //정상 결과
                    ResultModel result = response.body();

                    Log.d("TAG", "Login result " + result.getResult());
                    if (response.body().getResult().equals("success")) {
                        //로그인 성공

                        startActivity(new Intent(LoginUiActivity.this, GjMainActivity.class));
                        finish();
                        Toast.makeText(LoginUiActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    } else {
                        //로그인 실패
                        Toast.makeText(LoginUiActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResultModel> call, Throwable t) {
                    // 네트워크 문제

                }
            });


            chk_auto.setChecked(true);

        }


        chk_auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chk_auto.isChecked()) {
                    String ID = mNickName.getText().toString();
                    String PW = mPassword.getText().toString();
                    //Toast.makeText(Login2Activity.this, "ID PW 값 " + ID + PW, Toast.LENGTH_SHORT).show();
                    if (ID.equals("") && PW.equals("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginUiActivity.this);
                        dialog = builder.setMessage("닉네임과 패스워드를 먼저 입력해주세요")
                                .setNegativeButton("다시 시도", null)
                                .create();
                        dialog.show();
                        chk_auto.setChecked(false);
                    }
                    editor.putString("ID", ID);
                    editor.putString("PW", PW);
                    editor.putBoolean("chk_auto", true);
                    editor.commit();

                } else {
                    setting = getSharedPreferences("setting", Activity.MODE_PRIVATE);
                    editor = setting.edit();
                    editor.clear();
                    editor.commit();
                }
            }
        }); // 아이디 저장 기능

        handler = new Common.ProgressDialogHandler(this);
    }

    public void init() {
        mNickName = findViewById(R.id.nick_name);
        mPassword = findViewById(R.id.pass_word);


        LoginTxt = findViewById(R.id.Login_txt);
        RegisterTxt = findViewById(R.id.register);

        chk_auto = findViewById(R.id.chk_auto);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Login_txt:
                Login();
                break;
            case R.id.register:
                Register();
                break;
            default:
                break;
        }
    }


    public void Login() {

        handler.handleMessage(100);
        NickID = mNickName.getText().toString();
        userPassword = mPassword.getText().toString();

        String uuid = GetDevicesUUID(this);

        Log.d("TAG", "UUID ==============> " + uuid);

        String lasted_date = formatDate;

        Log.d("TAG", "LastedDate ==============> " + lasted_date);
        if (NickID.equals("") || NickID == null) {
            Toast.makeText(this, "NickName을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        } else if (userPassword.equals("") || userPassword == null) {
            Toast.makeText(this, "Password를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<ResultModel> call = mUserApi.Nick_loginLog(NickID, userPassword,lasted_date,uuid);



        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                //정상 결과
                ResultModel result = response.body();

                Log.d("TAG", "Login result " + result.getResult());
                if (response.body().getResult().equals("success")) {
                    handler.handleMessage(101);
                    //로그인 성공
                    intent = new Intent(LoginUiActivity.this, GjMainActivity.class);
                    intent.putExtra("NickName",NickID);
                    startActivity(intent);
                    finish();

                    Toast.makeText(LoginUiActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                } else {
                    //로그인 실패
                    Toast.makeText(LoginUiActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                // 네트워크 문제

            }
        });

    }


    public void Register() {
        intent = new Intent(getApplicationContext(), RegisterActivity.class);
        finish();
        startActivity(intent);
    }


    // Device UUID 가져오기
    @SuppressLint("HardwareIds")
    private String GetDevicesUUID(Context mContext) {


        final TelephonyManager tm = (TelephonyManager) mContext

                .getSystemService(Context.TELEPHONY_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            //설명을 보여줄 것인가
            if (ActivityCompat.shouldShowRequestPermissionRationale(this
                    , Manifest.permission.READ_PHONE_STATE)) {

                // 사용자 응답을 기다리는 설명을 비동기로 보여주기
                // 권한 체크를 안 하면 이 기능을 사용할 수 없다고 어필하고

                // 다이얼로그 표시
                // 이 권한을 수락하지 않으면 이 기능을 사용할 수 없습니다.ㅣ
                // 권한을 설정하시려면 설정 > 애플리케이션 > 앱이름 가서 설정하시오

                // 다시 권한 요청

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE}
                        , 1000);
            } else {
                //권한을 요청
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        1000);
            }

        } else {


            // 이미 권한이 존재할 때
            //getPicture();
        }
        final String tmDevice, tmSerial, androidId;

        tmDevice = "" + tm.getDeviceId();

        tmSerial = "" + tm.getSimSerialNumber();

        androidId = ""

                + android.provider.Settings.Secure.getString(

                getContentResolver(),

                android.provider.Settings.Secure.ANDROID_ID);


        UUID deviceUuid = new UUID(androidId.hashCode(),

                ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());

        String deviceId = deviceUuid.toString();


        return deviceId;

    }





}
