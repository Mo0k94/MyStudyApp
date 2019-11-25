package com.example.mystudyapp.activities;


import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.mystudyapp.R;
import com.example.mystudyapp.Retrofit2.ImageApi;
import com.example.mystudyapp.Retrofit2.ResultModel;
import com.example.mystudyapp.Retrofit2.RetrofitImage;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class getServerImageActivity extends AppCompatActivity {

    ImageView imageView;
    Button mGetimage_Btn;
    String url = "";

    private ImageApi mImageApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_server_image);

        imageView = findViewById(R.id.image_view);

        mImageApi = new RetrofitImage().getImageApi();


        mGetimage_Btn = (Button) findViewById(R.id.getimage_Btn);
        mGetimage_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Call<List<ResultModel>> call = mImageApi.get_Image();
                call.enqueue(new Callback<List<ResultModel>>() {


                    @Override
                    public void onResponse(Call<List<ResultModel>> call, retrofit2.Response<List<ResultModel>> response) {

                        //정상 결과
                        List<ResultModel> result = response.body();

                        Log.d("TAG", "Login result " + result.toString());
                        Log.d("TAG", "Login result " + result.size());
                        Glide.with(getApplicationContext()).load(result.get(1).getPath()).into(imageView);
                    }

                    @Override
                    public void onFailure(Call<List<ResultModel>> call, Throwable t) {
                        // 네트워크 문제
                        Log.d("TAG", "Login onFailure " + t);
                    }
                });


            }
        });

    }
}
