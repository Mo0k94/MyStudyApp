package com.example.mystudyapp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mystudyapp.R;
import com.example.mystudyapp.Retrofit2.ImageApi;
import com.example.mystudyapp.Retrofit2.ResultFood;
import com.example.mystudyapp.Retrofit2.ResultModel;
import com.example.mystudyapp.Retrofit2.RetrofitImage;
import com.example.mystudyapp.adapters.Food_Adapter;
import com.example.mystudyapp.adapters.Food_Menu_Adapter;
import com.example.mystudyapp.adapters.ImageRecyclerAdapter;
import com.example.mystudyapp.models.FoodMenu;
import com.example.mystudyapp.models.getServerImage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class Food_MenuFragment extends Fragment {

    private RecyclerView mRecycle_view;
    private TextView mMenu_div;
    List<ResultFood> boardList;
    private List<ResultFood> saveList;

    private Food_Menu_Adapter mAdapter;
    private ImageApi mImageApi;


    public static Food_MenuFragment newInstance(String data){

        Log.d("TAG","메뉴구분 ========>" + data);
        Food_MenuFragment fragment = new Food_MenuFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("data",(Serializable) data);

        fragment.setArguments(bundle);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        return view;
    }

    //onCreateView   와     onActivityCreated 사이에 존재 생명주기에는 없음
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();

        Log.d("TAG","메뉴구분 ========>" + bundle.getSerializable("data"));

        String str="";
        str = (String) bundle.getSerializable("data");
        //mData = (List<String>) bundle.getSerializable("data");

        mRecycle_view = view.findViewById(R.id.recycle_view);

        mMenu_div = view.findViewById(R.id.menu_div);


        boardList = new ArrayList<>();
        saveList = new ArrayList<ResultFood>();
        mImageApi = new RetrofitImage().getImageApi();

        getServerData(str);

        //MyAdapter mAdapter = new MyAdapter(mData);
        //listView.setAdapter(mAdapter);

    }
    private void getServerData(String str){
        Log.d("TAG", "getServerData Start!!! " );




        RequestBody divPart = RequestBody.create(MultipartBody.FORM, str);

        Call<List<ResultFood>> call = mImageApi.getFoodList(divPart);
        call.enqueue(new Callback<List<ResultFood>>() {

            @Override
            public void onResponse(Call<List<ResultFood>> call, retrofit2.Response<List<ResultFood>> response) {

                //정상 결과
                List<ResultFood> result = response.body();

                Log.d("TAG", "Login result " + result.toString());
                Log.d("TAG", "Login result " + result.size());
                for(int i=0; i<result.size();i++){

                    int SEQ      = result.get(i).getSeq();
                    String div = result.get(i).getFood_div();
                    String name = result.get(i).getFood_name();
                    String price = result.get(i).getFood_price();
                    String img_path = result.get(i).getFood_path();

                    ResultFood getFoodList = new ResultFood(SEQ,div,name,price,img_path);
                    boardList.add(getFoodList);
                    Log.d("TAG", "getServerData ====>  " + boardList.toString());
                    mAdapter = new Food_Menu_Adapter(getActivity(),boardList);

                }

                if("kor".equals(boardList.get(0).getFood_div())){
                    mMenu_div.setText("한식");
                }else if("west".equals(boardList.get(0).getFood_div())){
                    mMenu_div.setText("양식");
                }else if("snack".equals(boardList.get(0).getFood_div())){
                    mMenu_div.setText("분식");
                }
                mRecycle_view.setAdapter(mAdapter);
                //Glide.with(getApplicationContext()).load(result.get(1).getPath()).into(imageView);
            }

            @Override
            public void onFailure(Call<List<ResultFood>> call, Throwable t) {
                // 네트워크 문제
                Log.d("TAG", "Login onFailure " + t);
            }
        });

    }
}
