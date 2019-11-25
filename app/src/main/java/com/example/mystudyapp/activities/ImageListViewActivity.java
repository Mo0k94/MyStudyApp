package com.example.mystudyapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.mystudyapp.BoardInsertActivity;
import com.example.mystudyapp.R;
import com.example.mystudyapp.Retrofit2.ImageApi;
import com.example.mystudyapp.Retrofit2.ResultModel;
import com.example.mystudyapp.Retrofit2.RetrofitImage;
import com.example.mystudyapp.adapters.ImageRecyclerAdapter;
import com.example.mystudyapp.models.getServerImage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ImageListViewActivity extends AppCompatActivity {

    private Button mInsertBtn;
    private RecyclerView mRecycle_view;
    private List<getServerImage> boardList;

    private ImageRecyclerAdapter mAdapter;
    private ImageApi mImageApi;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list_view);


        mInsertBtn = findViewById(R.id.insertBtn);
        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), BoardInsertActivity.class);
                finish();
                startActivity(intent);
            }
        });

        mRecycle_view = findViewById(R.id.recycle_view);

        boardList = new ArrayList<getServerImage>();

        mImageApi = new RetrofitImage().getImageApi();

        getServerData();


        Log.d("TAG", "getServerData mAdapter ====>  " + boardList.toString());



    }


    private void getServerData(){
        Log.d("TAG", "getServerData Start!!! " );
        Call<List<ResultModel>> call = mImageApi.getBoardList();
        call.enqueue(new Callback<List<ResultModel>>() {

            @Override
            public void onResponse(Call<List<ResultModel>> call, retrofit2.Response<List<ResultModel>> response) {

                //정상 결과
                List<ResultModel> result = response.body();

                Log.d("TAG", "Login result " + result.toString());
                Log.d("TAG", "Login result " + result.size());
                for(int i=0; i<result.size();i++){

                    String USER = result.get(i).getUser_id();
                    String TITLE = result.get(i).getTitle();
                    String CONTENT = result.get(i).getContent();
                    String DATE = result.get(i).getDate();
                    String PATH = result.get(i).getPath();

                    getServerImage getServerdata = new getServerImage(USER,TITLE,CONTENT,DATE,PATH);

                    boardList.add(getServerdata);
                    Log.d("TAG", "getServerData ====>  " + boardList.toString());
                    mAdapter = new ImageRecyclerAdapter(getApplicationContext(),boardList);

                    mRecycle_view.setAdapter(mAdapter);
                }

                //Glide.with(getApplicationContext()).load(result.get(1).getPath()).into(imageView);
            }

            @Override
            public void onFailure(Call<List<ResultModel>> call, Throwable t) {
                // 네트워크 문제
                Log.d("TAG", "Login onFailure " + t);
            }
        });

    }


}
