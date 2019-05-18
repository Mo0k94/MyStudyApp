package com.example.mystudyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //메뉴를 붙이는 부분
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    //메뉴클릭시!
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                Toast.makeText(this, "설정 아직 미구현!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_menu2:
                return true;
            case R.id.action_menu3:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
