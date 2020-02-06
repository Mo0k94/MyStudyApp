package com.example.mystudyapp.GwangjuUniv;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mystudyapp.R;

public class GJBoardActivity extends AppCompatActivity {


    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gjboard);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GJBoardActivity.this, "글쓰기 클릭", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
