package com.example.mystudyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.mystudyapp.R;

public class addViewActivity extends AppCompatActivity {

    private LinearLayout mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);

        mView = findViewById(R.id.main_view);

        Button mAdd_btn = findViewById(R.id.add_btn);
        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button button = new Button(getApplicationContext());
                CheckBox checkBox = new CheckBox(getApplicationContext());
                mView.addView(button);
                mView.addView(checkBox);


            }
        });



    }
}
