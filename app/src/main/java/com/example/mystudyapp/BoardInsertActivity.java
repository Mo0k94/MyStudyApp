package com.example.mystudyapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mystudyapp.Retrofit2.ImageApi;
import com.example.mystudyapp.Retrofit2.ResultModel;
import com.example.mystudyapp.Retrofit2.RetrofitImage;
import com.example.mystudyapp.activities.ImageListViewActivity;
import com.example.mystudyapp.adapters.ImageRecyclerAdapter;
import com.example.mystudyapp.models.getServerImage;
import com.example.mystudyapp.utils.FileUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardInsertActivity extends AppCompatActivity {

    private static final int IMG_REQUEST = 777;

    private Bitmap bitmap;
    private Uri path;

    Intent intent;
    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date nowdate = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("MM/dd hh:mm");  //06/19 13:50
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(nowdate);

    private ImageView mImg;
    private ImageButton mInsert_img;
    private Button mInsertBtn;

    private ImageApi mImageApi;

    private TextView dateTxt;
    private EditText titleTxt,contentTxt,userTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_insert);


        titleTxt = findViewById(R.id.titleTxt);
        contentTxt =  findViewById(R.id.contentTxt);
        dateTxt =  findViewById(R.id.dateTxt);
        userTxt =  findViewById(R.id.userTxt);

        mImg = findViewById(R.id.imageView);

        dateTxt.setText(formatDate);

        mImageApi = new RetrofitImage().getImageApi();

        mInsertBtn = findViewById(R.id.insertBtn);
        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadImage(path);
            }
        });

        mInsert_img = findViewById(R.id.insert_img);
        mInsert_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });





        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            //설명을 보여줄 것인가
            if(ActivityCompat.shouldShowRequestPermissionRationale(this
                    ,Manifest.permission.READ_EXTERNAL_STORAGE)){

                // 사용자 응답을 기다리는 설명을 비동기로 보여주기
                // 권한 체크를 안 하면 이 기능을 사용할 수 없다고 어필하고

                // 다이얼로그 표시
                // 이 권한을 수락하지 않으면 이 기능을 사용할 수 없습니다.ㅣ
                // 권한을 설정하시려면 설정 > 애플리케이션 > 앱이름 가서 설정하시오

                // 다시 권한 요청

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        ,1000);
            }else{
                //권한을 요청
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1000);
            }
        }else{

            // 이미 권한이 존재할 때
            selectImage();
        }

    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);

    }

    private void uploadImage(Uri filePath) {

        //String Image = imageToString();
        String USER = userTxt.getText().toString();
        String TITLE = titleTxt.getText().toString();
        String CONTENT = contentTxt.getText().toString();
        String DATE = dateTxt.getText().toString();




        File originalFile = FileUtils.getFile(this, filePath);
        RequestBody userPart = RequestBody.create(MultipartBody.FORM,USER);
        RequestBody titlePart = RequestBody.create(MultipartBody.FORM,TITLE);
        RequestBody contentPart = RequestBody.create(MultipartBody.FORM,CONTENT);
        RequestBody datePart = RequestBody.create(MultipartBody.FORM,DATE);

        RequestBody imagePart = RequestBody.create(
                //MediaType.parse(getContentResolver().getType(filePath)),
                MediaType.parse("multipart/form-data"),
                originalFile);

        MultipartBody.Part file = MultipartBody.Part.createFormData("image",originalFile.getName(),imagePart);

        Call<ResponseBody> call = mImageApi.InsertBoard(userPart,titlePart,contentPart,datePart,file);
       // Call<ResponseBody> call = apiInterface.uploadImage(titlePart,file);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG","imageUpload Success.." + response.body());
                intent = new Intent(getApplicationContext(), ImageListViewActivity.class);
                finish();
                startActivity(intent);
                Toast.makeText(BoardInsertActivity.this,"success",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(BoardInsertActivity.this,"faild",Toast.LENGTH_LONG).show();
                Log.d("TAG","imageUpload Faild.." + t.getMessage());
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1000:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    //승인 됨
                    Toast.makeText(this,"권한 승인됨", Toast.LENGTH_LONG).show();
                    selectImage();
                }else{


                    // 앱을 종료함
                    //승인 거부됨
                    Toast.makeText(this,"권한 거부됨", Toast.LENGTH_LONG).show();
                    BoardInsertActivity.this.finish();
                }
                return;
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){

            path = data.getData();

            //uploadImage(path);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                mImg.setImageBitmap(bitmap);
                mImg.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
