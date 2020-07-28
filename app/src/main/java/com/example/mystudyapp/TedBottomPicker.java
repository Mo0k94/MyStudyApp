package com.example.mystudyapp;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TedBottomPicker extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ted_bottom_picker);

        Button pickImage = findViewById(R.id.pickImage);

        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(TedBottomPicker.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(TedBottomPicker.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            100);
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            final ImageView imageView = findViewById(R.id.pickImages1);
            final ImageView imageView2 = findViewById(R.id.pickImages2);
            final ImageView imageView3 = findViewById(R.id.pickImages3);

            final List<Bitmap> bitmaps = new ArrayList<>();
            ClipData clipData = data.getClipData();

            if(clipData != null){

                if(clipData.getItemCount() >3){
                    Toast.makeText(this, "사진은 3장까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                }else if(clipData.getItemCount() == 3) {
                        Uri imageUri = clipData.getItemAt(0).getUri();
                        Uri imageUri2 = clipData.getItemAt(1).getUri();
                        Uri imageUri3 = clipData.getItemAt(2).getUri();
                        try{
                            InputStream is = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            bitmaps.add(bitmap);

                                imageView.setImageBitmap(bitmaps.get(0));
                                imageView2.setImageBitmap(bitmaps.get(1));
                                imageView3.setImageBitmap(bitmaps.get(2));
                        }catch (FileNotFoundException e){
                            e.printStackTrace();
                        }
                }else if(clipData.getItemCount() == 1){
                    Uri imageUri = clipData.getItemAt(0).getUri();
                    try{
                        InputStream is = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        bitmaps.add(bitmap);

                        imageView.setImageBitmap(bitmaps.get(0));
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }

                /*for(int i=0; i<clipData.getItemCount();i++){
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    try{
                        InputStream is = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        bitmaps.add(bitmap);

                        for(int j=0; j<bitmaps.size(); j++){
                            imageView.setImageBitmap(bitmaps.get(j));
                        }
                        for(final Bitmap b : bitmaps){
                            imageView.setImageBitmap(b);
                        }
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }*/
            }else{
                Uri imageUri = data.getData();
                try{
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    bitmaps.add(bitmap);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }

            /*new Thread(new Runnable(){

                @Override
                public void run() {
                    for(final Bitmap b : bitmaps){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(b);
                            }
                        });

                        try{
                            Thread.sleep(3000);

                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }).start();*/
        }
    }
}