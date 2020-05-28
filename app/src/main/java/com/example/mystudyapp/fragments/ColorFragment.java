package com.example.mystudyapp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

public class ColorFragment extends Fragment {

    private Button mInsertBtn;
    EditText editTextFilter;

    private RecyclerView mRecycle_view;
    private List<getServerImage> boardList;
    private List<getServerImage> saveList;

    private ImageRecyclerAdapter mAdapter;
    private ImageApi mImageApi;

    private Intent intent;

    private FloatingActionButton fab;

    SharedPreferences setting;    //아이디 저장 기능
    SharedPreferences.Editor editor;

    private int mColor = Color.RED;
    public ColorFragment() {
    }

    //팩토리 패턴
    public static ColorFragment newInstance(int color){
        ColorFragment colorFragment = new ColorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("color",color);
        colorFragment.setArguments(bundle);
        return colorFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lifecycle,container,false);

        Bundle bundle = getArguments();
        int color = bundle.getInt("color");
        mColor = color;
        view.setBackgroundColor(mColor);

        mRecycle_view = view.findViewById(R.id.recycle_view);

        boardList = new ArrayList<getServerImage>();
        saveList = new ArrayList<getServerImage>();
        mImageApi = new RetrofitImage().getImageApi();

        getServerData();
        return view;
    }

    public void setColor(int color){
        mColor = color;
        if (getView() !=null){
            getView().setBackgroundColor(mColor);
        }
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

                    int SEQ      = result.get(i).getSeq();
                    String USER = result.get(i).getUser_id();
                    String TITLE = result.get(i).getTitle();
                    String CONTENT = result.get(i).getContent();
                    String DATE = result.get(i).getDate();
                    String PATH = result.get(i).getPath();

                    getServerImage getServerdata = new getServerImage(SEQ,USER,TITLE,CONTENT,DATE,PATH);

                    boardList.add(getServerdata);
                    saveList.add(getServerdata);
                    Log.d("TAG", "getServerData ====>  " + boardList.toString());
                    mAdapter = new ImageRecyclerAdapter(getContext(),boardList,saveList);

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
