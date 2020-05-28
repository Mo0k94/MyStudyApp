package com.example.mystudyapp.activities;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mystudyapp.R;
import com.example.mystudyapp.fragments.GalleryFragment;

public class GalleryActivity extends AppCompatActivity implements GalleryFragment.OnFragmentInteractionListener {


    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mFragment = getSupportFragmentManager().findFragmentById(R.id.fragment);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      mFragment.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
