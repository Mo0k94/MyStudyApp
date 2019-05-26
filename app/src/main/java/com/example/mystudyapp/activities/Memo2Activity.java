package com.example.mystudyapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mystudyapp.R;

public class Memo2Activity extends AppCompatActivity {

    private EditText mTitleEdit,mContentEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo2);
        init();
    }

    private void init() {
        mTitleEdit = findViewById(R.id.title_edit);
        mContentEdit = findViewById(R.id.content_edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_memo2,menu);
        return true;
    }

    //메뉴클릭시!
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_action:
                save();
                return true;
            case R.id.cancel_action:
                cancel();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void cancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

    //저장
    private void save(){
        Intent intent = new Intent();
        intent.putExtra("title",mTitleEdit.getText().toString());
        intent.putExtra("content",mContentEdit.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}
