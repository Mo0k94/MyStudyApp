package com.example.mystudyapp.GwangjuUniv;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mystudyapp.R;
import com.example.mystudyapp.Retrofit2.ImageApi;
import com.example.mystudyapp.Retrofit2.ResultModel;
import com.example.mystudyapp.Retrofit2.RetrofitImage;
import com.example.mystudyapp.activities.BoardInsertActivity;
import com.example.mystudyapp.adapters.ImageRecyclerAdapter;
import com.example.mystudyapp.models.getServerImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class GJBoardActivity extends AppCompatActivity {

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

    String id="";

    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date nowdate = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("MM/dd hh:mm");  //06/19 13:50
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(nowdate);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gjboard);

        setting = getSharedPreferences("setting", Activity.MODE_PRIVATE);

        editor = setting.edit();

        id = setting.getString("ID","");

        Log.d("TAG","ID ===========> " + id);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), BoardInsertActivity.class);
                startActivity(intent);
            }
        });

        editTextFilter = findViewById(R.id.EditTextFilter);
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUser(charSequence.toString());
                Log.d("Test","게시물 검색 값 : " + charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable edit) {

               /* String filterText = edit.toString();

                ((Dept_Adapter)listview1.getAdapter()).getFilter().filter(filterText);*/
            }
        });


        mRecycle_view = findViewById(R.id.recycle_view);

        boardList = new ArrayList<getServerImage>();
        saveList = new ArrayList<getServerImage>();
        mImageApi = new RetrofitImage().getImageApi();

        getServerData();


        Log.d("TAG", "getServerData mAdapter ====>  " + boardList.toString());



    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // 보낸이 : MemoRecyclerAdapter
    @SuppressLint("RestrictedApi")
    @Subscribe
    public void onItemClick(ImageRecyclerAdapter.ItemClickEvent event) {
        getServerImage BoardData = boardList.get(event.position);

        //Memo memo2 = newMemoList.get(event.position);
        Intent intent = new Intent(getApplicationContext(), BoardInsertActivity.class);

        //intent.putExtra("id", event.id);
        intent.putExtra("Seq", boardList.get(event.position).getSeq());
        intent.putExtra("TITLE", boardList.get(event.position).getTitle());
        intent.putExtra("WRITER", boardList.get(event.position).getUser_id());
        intent.putExtra("CONTENT", boardList.get(event.position).getContent());
        intent.putExtra("DATE",boardList.get(event.position).getDate());
        intent.putExtra("PATH",boardList.get(event.position).getPath());
        startActivity(intent);
        finish();
    }


    private void searchUser(String search) {
        boardList.clear();
        for(int i=0; i<saveList.size();i++){
            if(saveList.get(i).getTitle().contains(search)
                    || saveList.get(i).getUser_id().contains(search)){
                boardList.add(saveList.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
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

                    String msg ="";

                    msg = formatTimeString(DATE);

                    getServerImage getServerdata = new getServerImage(SEQ,USER,TITLE,CONTENT,msg,PATH);

                    boardList.add(getServerdata);
                    saveList.add(getServerdata);
                    Log.d("TAG", "getServerData ====>  " + boardList.toString());
                    mAdapter = new ImageRecyclerAdapter(getApplicationContext(),boardList,saveList);

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

    // 몇분 전, 방금 전
    private static class TIME_MAXIMUM{
        public static final int SEC = 60;       //초
        public static final int MIN = 60;       //분
        public static final int HOUR = 24;      //시
        public static final int DAY = 30;       //일
        public static final int MONTH = 12;     //월
    }

    public static String formatTimeString(String regTime){

        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date temdate = null;
        long diffTime = 0;
        long time = 0;
        long curTime = 0;
        String msg = null;
        try {
            temdate = tempDate.parse(regTime);
            time = temdate.getTime();

            curTime = System.currentTimeMillis();
            diffTime = (curTime - time) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(diffTime <TIME_MAXIMUM.SEC){
            msg = "방금 전";
        }else if((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN){
            msg = diffTime +"분 전";
        }else if((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR){
            msg = diffTime +"시간 전";
        }/*else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
            msg = (diffTime) + "달 전";
        }*/
        else {
            msg = regTime.substring(0,16) +"";
            Log.d("board","날짜 ===> " + msg);
        }
        return msg;
    }

}

