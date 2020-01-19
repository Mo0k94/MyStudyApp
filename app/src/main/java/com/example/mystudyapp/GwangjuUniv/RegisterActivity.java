package com.example.mystudyapp.GwangjuUniv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mystudyapp.R;

public class RegisterActivity extends AppCompatActivity {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginTxt:
                intent = new Intent(getApplicationContext(),LoginUiActivity.class);
                finish();
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
