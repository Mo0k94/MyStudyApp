package com.example.mystudyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mystudyapp.R;

public class Selector_ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector__image);

        final TextView selector_selected = (TextView) findViewById(R.id.selector_selected);
        final TextView selector_endabled = (TextView) findViewById(R.id.selector_enabled);
        final EditText selector_focused = (EditText) findViewById(R.id.selector_focused);


        // 클릭시 포커스를 준다.
        selector_focused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selector_focused.requestFocus();
            }
        });

        // 클릭시 비사용 모드로 바꾼다.
        selector_endabled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selector_endabled.setEnabled(false);
            }
        });

        // 클릭시 선택된다.
        selector_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selector_selected.setSelected(true);
            }
        });
    }
}
