package com.example.mystudyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mystudyapp.utils.FileUtils;
import com.opensooq.supernova.gligar.GligarPicker;

import java.io.File;
import java.util.Arrays;

public class GligerActivity extends AppCompatActivity {

    final int PICKER_REQUEST_CODE = 101;

    TextView imgText;
    Button imgBtn;
    ImageView imageView1, imageView2, imageView3;
    LinearLayout mImageLinear;
    String[] pathsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gliger);



       mImageLinear = findViewById(R.id.imageLinear);

       imgText = findViewById(R.id.imageTxt);
       imgBtn = findViewById(R.id.imgBtn);

       imageView1 = findViewById(R.id.image1);
       imageView2 = findViewById(R.id.image2);
       imageView3 = findViewById(R.id.image3);


       imgBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new GligarPicker()
                       .requestCode(PICKER_REQUEST_CODE)
                       .limit(3)// 최대 이미지 수
                       .withActivity(GligerActivity.this)   //Activity
                       //.withFragment -> Fragment
                       // .disableCamera(false) -> 카메라 캡처를 사용할지
                       // .cameraDirect(true) -> 바로 카메라를 실행할지
                       .show();
           }
       });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode){
            case PICKER_REQUEST_CODE : {
                pathsList= data.getExtras().getStringArray(GligarPicker.IMAGES_RESULT); // return list of selected images paths.
                imgText.setText("Number of selected Images: " + pathsList.length);
                Log.d("TAG", "pathList : " + Arrays.toString(pathsList));
                for(int i=0;i<pathsList.length;i++){
                    Log.d("TAG", "pathList "+i + "번째 : " + pathsList[i]);
                    setImage(pathsList[i]);
                }

                Uri uri = Uri.parse(pathsList[0]);
                File originalFile = FileUtils.getFile(GligerActivity.this, uri);


                break;
            }
        }
    }

    // 파일 경로를 받아와 Bitmap 으로 변환후 ImageView 적용
    public void setImage(String imagePath){
        File imgFile = new  File(imagePath);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            LinearLayout statLayoutItem = (LinearLayout) inflater.inflate(R.layout.addimage, null);
            ImageView addImg = statLayoutItem.findViewById(R.id.addImage);

            Glide.with(getApplicationContext())
                    .load(myBitmap)
                    .override(300,300)
                    .fitCenter()
                    .into(addImg);

            //addImg.setImageBitmap(myBitmap);
            mImageLinear.addView(statLayoutItem);

            //ImageView myImage = (ImageView) findViewById(R.id.Images1);



        }
    }


}