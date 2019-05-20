package com.example.mystudyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mystudyapp.adapters.MemoAdapter;
import com.example.mystudyapp.models.MemoItem;

import java.util.ArrayList;
import java.util.List;

public class MemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final int REQUEST_CODE_NEW_MEMO = 1000;
    public static final int REQUEST_CODE_UPDATE_MEMO = 1001;
    private static final String TAG = MemoActivity.class.getCanonicalName();

    private List<MemoItem> mMemoList;
    private MemoAdapter mAdapter;
    private ListView mList_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Memo2Activity.class);
                startActivityForResult(intent,REQUEST_CODE_NEW_MEMO);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mList_view = findViewById(R.id.memo_list);
        // 데이터
        mMemoList = new ArrayList<>();

        mAdapter = new MemoAdapter(mMemoList);
        mList_view.setAdapter(mAdapter);

        //클릭이벤트
        mList_view.setOnItemClickListener(this);

        //  ContextMenu
        registerForContextMenu(mList_view);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        MemoItem memo = mMemoList.get(position);
        Intent intent = new Intent(this, Memo2Activity.class);
        intent.putExtra("id",id);
        //intent.putExtra("memo",memo);

        startActivityForResult(intent,REQUEST_CODE_UPDATE_MEMO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            if (requestCode == REQUEST_CODE_NEW_MEMO) {
                mMemoList.add(new MemoItem(title, content));
                //Log.d(TAG,"onActivityResult : " + title + " , " + content);
                Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show();
            } else if (requestCode == REQUEST_CODE_UPDATE_MEMO) {
                long id = data.getLongExtra("id", -1);
                //수정
                MemoItem memo = mMemoList.get((int) id);
                memo.setTitle(title);
                memo.setContent(content);

            }
            mAdapter.notifyDataSetChanged();
            Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "취소되었습니다", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_memo,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.action_delete:
                DeleteMemo(info.id);
                return true;
//            case R.id.delete:
//                deleteNote(info.id);
//                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void DeleteMemo(long id) {
        mMemoList.remove(id);
        mAdapter.notifyDataSetChanged();
    }


}
