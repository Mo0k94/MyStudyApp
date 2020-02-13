package com.example.mystudyapp.activities;

import android.Manifest;
import android.app.Activity;
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

import com.bumptech.glide.Glide;
import com.example.mystudyapp.GwangjuUniv.GJBoardActivity;
import com.example.mystudyapp.R;
import com.example.mystudyapp.Retrofit2.ImageApi;
import com.example.mystudyapp.Retrofit2.RetrofitImage;
import com.example.mystudyapp.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Uri updateuri;
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
    private EditText titleTxt, contentTxt, userTxt;

    private Intent intent;
    private int gbdata = 0;

    SharedPreferences setting;    //아이디 저장 기능
    SharedPreferences.Editor editor;

    String id ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_insert);


        setting = getSharedPreferences("setting", Activity.MODE_PRIVATE);
        editor = setting.edit();

        id = setting.getString("ID","");

        Log.d("TAG","ID ===========> " + id);

        titleTxt = findViewById(R.id.titleTxt);
        contentTxt = findViewById(R.id.contentTxt);
        dateTxt = findViewById(R.id.dateTxt);
        userTxt = findViewById(R.id.userTxt);


        userTxt.setText(id);
        mImg = findViewById(R.id.imageView);


        intent = getIntent();
        if (intent != null) {
            if (intent.getStringExtra("TITLE") == null || intent.getIntExtra("Seq", 0) == 0) {
                Log.d("TAG", "Insert!!!!!!!");
                gbdata = 0;
            } else {
                Log.d("TAG", "Update!!!!!!!");
                Log.d("TAG", "getIntent ========> " + intent.getStringExtra("TITLE"));
                Log.d("TAG", "getIntent ========> " + intent.getIntExtra("Seq", 0));
                Log.d("TAG", "getIntent ========> " + intent.getStringExtra("WRITER"));
                Log.d("TAG", "getIntent ========> " + intent.getStringExtra("PATH"));

                String image_path = intent.getStringExtra("PATH");
                //Uri fileUri = Uri.parse(image_path);
                updateuri = Uri.parse(image_path);
//                String filePath = fileUri.getPath();
//                Cursor c = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,"_data= '" + filePath + "'",null,null);
//                c.moveToNext();
//                int img_id = c.getInt(c.getColumnIndex("_id"));
//                updateuri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,img_id);

                titleTxt.setText(intent.getStringExtra("TITLE"));
                contentTxt.setText(intent.getStringExtra("CONTENT"));
                userTxt.setText(intent.getStringExtra("WRITER"));
                dateTxt.setText(intent.getStringExtra("DATE"));
                Glide.with(getApplicationContext())
                        .load(intent.getStringExtra("PATH"))
                        .override(500,200)
                        .fitCenter()
                        .into(mImg);
                mImg.setVisibility(View.VISIBLE);
                gbdata = 1;
            }

        }


        dateTxt.setText(formatDate);

        mImageApi = new RetrofitImage().getImageApi();

        mInsertBtn = findViewById(R.id.insertBtn);
        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "mInsertBtn 클릭 =======> " + gbdata);
                if (gbdata == 0) {
                    uploadImage(path);
                }
                {
                    updateBoard(path);
                }

            }
        });

        mInsert_img = findViewById(R.id.insert_img);
        mInsert_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            //설명을 보여줄 것인가
            if (ActivityCompat.shouldShowRequestPermissionRationale(this
                    , Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // 사용자 응답을 기다리는 설명을 비동기로 보여주기
                // 권한 체크를 안 하면 이 기능을 사용할 수 없다고 어필하고

                // 다이얼로그 표시
                // 이 권한을 수락하지 않으면 이 기능을 사용할 수 없습니다.ㅣ
                // 권한을 설정하시려면 설정 > 애플리케이션 > 앱이름 가서 설정하시오

                // 다시 권한 요청

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        , 1000);
            } else {
                //권한을 요청
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1000);
            }
        } else {

            // 이미 권한이 존재할 때
            //selectImage();
        }

    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);

    }


    private void updateBoard(Uri filePath) {
        String USER = userTxt.getText().toString();
        String TITLE = titleTxt.getText().toString();
        String CONTENT = contentTxt.getText().toString();
        String DATE = dateTxt.getText().toString();
        String SEQ = String.valueOf(intent.getIntExtra("Seq", 0));

        RequestBody seqPart = RequestBody.create(MultipartBody.FORM, SEQ);
        RequestBody userPart = RequestBody.create(MultipartBody.FORM, USER);
        RequestBody titlePart = RequestBody.create(MultipartBody.FORM, TITLE);
        RequestBody contentPart = RequestBody.create(MultipartBody.FORM, CONTENT);
        RequestBody datePart = RequestBody.create(MultipartBody.FORM, DATE);

        Log.d("TAG", "imageUpload NoImage filePath ======> " + filePath);
        Call<ResponseBody> call;
        if (filePath == null) {
            call = mImageApi.UpdateBoard_NoImage(seqPart, userPart, titlePart, contentPart, datePart);
        } else {
            File originalFile = FileUtils.getFile(this, filePath);

            RequestBody imagePart = RequestBody.create(
                    //MediaType.parse(getContentResolver().getType(filePath)),
                    MediaType.parse("multipart/form-data"),
                    originalFile);

            MultipartBody.Part file = MultipartBody.Part.createFormData("image", originalFile.getName(), imagePart);
            call = mImageApi.UpdateBoard(seqPart, userPart, titlePart, contentPart, datePart, file);
        }

        // Call<ResponseBody> call = apiInterface.uploadImage(titlePart,file);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG", "imageUpload Success.." + response.body());
                intent = new Intent(getApplicationContext(), GJBoardActivity.class);
                finish();
                startActivity(intent);
                Toast.makeText(BoardInsertActivity.this, "success", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(BoardInsertActivity.this, "faild", Toast.LENGTH_LONG).show();
                Log.d("TAG", "imageUpload Faild.." + t.getMessage());
            }
        });

    }

    private void uploadImage(Uri filePath) {

        //String Image = imageToString();
        String USER = userTxt.getText().toString();
        String TITLE = titleTxt.getText().toString();
        String CONTENT = contentTxt.getText().toString();
        String DATE = dateTxt.getText().toString();


        RequestBody userPart = RequestBody.create(MultipartBody.FORM, USER);
        RequestBody titlePart = RequestBody.create(MultipartBody.FORM, TITLE);
        RequestBody contentPart = RequestBody.create(MultipartBody.FORM, CONTENT);
        RequestBody datePart = RequestBody.create(MultipartBody.FORM, DATE);

        Call<ResponseBody> call;

        if(filePath != null){
            File originalFile = FileUtils.getFile(this, filePath);


            RequestBody imagePart = RequestBody.create(
                    //MediaType.parse(getContentResolver().getType(filePath)),
                    MediaType.parse("multipart/form-data"),
                    originalFile);

            MultipartBody.Part file = MultipartBody.Part.createFormData("image", originalFile.getName(), imagePart);

             call = mImageApi.InsertBoard(userPart, titlePart, contentPart, datePart, file);

        }else{
            call = mImageApi.InsertBoard_NoImage(userPart, titlePart, contentPart, datePart);

        }


        // Call<ResponseBody> call = apiInterface.uploadImage(titlePart,file);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG", "imageUpload Success.." + response.body());
                intent = new Intent(getApplicationContext(), GJBoardActivity.class);
                finish();
                startActivity(intent);
                Toast.makeText(BoardInsertActivity.this, "success", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(BoardInsertActivity.this, "faild", Toast.LENGTH_LONG).show();
                Log.d("TAG", "imageUpload Faild.." + t.getMessage());
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //승인 됨
                    Toast.makeText(this, "권한 승인됨", Toast.LENGTH_LONG).show();
                    selectImage();
                } else {


                    // 앱을 종료함
                    //승인 거부됨
                    Toast.makeText(this, "권한 거부됨", Toast.LENGTH_LONG).show();
                    BoardInsertActivity.this.finish();
                }
                return;
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {

            path = data.getData();

            //uploadImage(path);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                mImg.setImageBitmap(bitmap);
                mImg.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
