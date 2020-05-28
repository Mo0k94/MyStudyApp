package com.example.mystudyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mystudyapp.R;
import com.example.mystudyapp.fragments.TestFragment;

public class ImageFragmentActivity extends AppCompatActivity implements TestFragment.OnImageTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fragment);

        TestFragment testFragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.test_fragment);
        testFragment.setOnImageTouchListener(this);

    }

    //콜백을 쓰는 이유 :
    @Override
    public void onImageTouch(ImageView view, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
