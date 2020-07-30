package com.example.mystudyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mystudyapp.utils.FileUtils;
import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity;
import com.opensooq.supernova.gligar.GligarPicker;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GligerActivity extends AppCompatActivity {

    final int PICKER_REQUEST_CODE = 101;

    TextView imgText;
    Button imgBtn;
    ImageView imageView1, imageView2, imageView3;
    LinearLayout mImageLinear;
    String[] pathsList;
    int count=0;
    private ArrayList<String> uriList;

    HashMap<Integer, String> imagePathMap = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gliger);



        uriList = new ArrayList<>();

       mImageLinear = findViewById(R.id.imageLinear);

       imgText = findViewById(R.id.imageTxt);
       imgBtn = findViewById(R.id.imgBtn);

       imageView1 = findViewById(R.id.image1);
       imageView2 = findViewById(R.id.image2);
       imageView3 = findViewById(R.id.image3);


       imgBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(pathsList != null){
                   Toast.makeText(GligerActivity.this, "현재 이미지 개수 "+count, Toast.LENGTH_SHORT).show();
                   if(count ==3){
                       Toast.makeText(GligerActivity.this, "이미지는 최대 3장까지 선택가능합니다.", Toast.LENGTH_SHORT).show();
                   }else{

                       new GligarPicker()
                               .requestCode(PICKER_REQUEST_CODE)
                               .limit(3-count)// 최대 이미지 수
                               .withActivity(GligerActivity.this)   //Activity
                               //.withFragment -> Fragment
                               // .disableCamera(false) -> 카메라 캡처를 사용할지
                               // .cameraDirect(true) -> 바로 카메라를 실행할지
                               .show();
                   }
               }else{
                   imagePathMap.clear();
                   new GligarPicker()
                           .requestCode(PICKER_REQUEST_CODE)
                           .limit(3)// 최대 이미지 수
                           .withActivity(GligerActivity.this)   //Activity
                           //.withFragment -> Fragment
                           // .disableCamera(false) -> 카메라 캡처를 사용할지
                           // .cameraDirect(true) -> 바로 카메라를 실행할지
                           .show();
               }

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


                Log.d("TAG", "pathList : " + Arrays.toString(pathsList));




                for(int i=0;i<pathsList.length;i++){

                    uriList.add(pathsList[i]);
                    //imagePathMap.put(i, pathsList[i]);
                    count++;
                    setImage(pathsList[i]);
                }


                //Log.d("TAG","imagePathMap : " + imagePathMap.toString());
                Log.d("TAG","ArrayList  : " + uriList.toString());
                imgText.setText("Number of selected Images: " + count);

                Uri uri = Uri.parse(pathsList[0]);
                File originalFile = FileUtils.getFile(GligerActivity.this, uri);


                break;
            }
        }
    }

    // 파일 경로를 받아와 Bitmap 으로 변환후 ImageView 적용
    public void setImage(final String imagePath){

        File imgFile = new  File(imagePath);
        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            final LinearLayout statLayoutItem = (LinearLayout) inflater.inflate(R.layout.addimage, null);
            final ImageView addImg = statLayoutItem.findViewById(R.id.addImage);
            final ImageView delImg = statLayoutItem.findViewById(R.id.delImage);



            delImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG","동일한 이미지가 있는지 확인 : " + imagePathMap.containsValue(imagePath));
                    Log.d("TAG","동일한 이미지가 있는지 확인 : " + getKey(imagePathMap,imagePath));
                    if(uriList.contains(imagePath)){
                        uriList.remove(imagePath);
                        count--;
                        mImageLinear.removeView(statLayoutItem);
                        Log.d("TAG","동일한 이미지가 있는지 확인 ArrayList : " + uriList.toString());
                    }
                    /*if(imagePathMap.containsValue(imagePath)){  //동일한 이미지가 있는 경우
                        uriList.remove(imagePath);
                        imagePathMap.remove(getKey(imagePathMap,imagePath));
                        mImageLinear.removeView(statLayoutItem);
                        count--;
                        delyn = true;
                        Log.d("TAG","동일한 이미지가 있는지 확인 Map : " + imagePathMap.toString());
                        Log.d("TAG","동일한 이미지가 있는지 확인 ArrayList : " + uriList.toString());

                    }*/
                    Toast.makeText(GligerActivity.this, "ImageView "+ imagePath, Toast.LENGTH_SHORT).show();

                }
            });
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

    public Object getKey(HashMap<Integer, String> m, Object value) {
        for(Object o: m.keySet()) {
            if(m.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }



    private void onImageClickAction(ArrayList<String> uriString, int pos) {
        Intent fullImageIntent = new Intent(GligerActivity.this, FullScreenImageViewActivity.class);
        fullImageIntent.putExtra(FullScreenImageViewActivity.URI_LIST_DATA, uriString);
        fullImageIntent.putExtra(FullScreenImageViewActivity.IMAGE_FULL_SCREEN_CURRENT_POS, pos);
        startActivity(fullImageIntent);
    }


}