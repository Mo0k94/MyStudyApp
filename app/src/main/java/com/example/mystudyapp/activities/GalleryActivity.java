package com.example.mystudyapp.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
