package com.example.mystudyapp.GwangjuUniv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mystudyapp.R;

public class GjMainActivity extends AppCompatActivity {

    private TextView board_txt;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gj_main);


        init();
    }


    public void init() {
        board_txt = findViewById(R.id.board_txt);
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
}
