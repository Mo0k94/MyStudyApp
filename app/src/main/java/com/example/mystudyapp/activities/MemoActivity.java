package com.example.mystudyapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mystudyapp.R;
import com.example.mystudyapp.adapters.MemoAdapter;
import com.example.mystudyapp.db.MemoFacade;
import com.example.mystudyapp.models.MemoItem;

import java.util.List;

public class MemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final int REQUEST_CODE_NEW_MEMO = 1000;
    public static final int REQUEST_CODE_UPDATE_MEMO = 1001;
    private static final String TAG = MemoActivity.class.getCanonicalName();

    private List<MemoItem> mMemoList;

    private MemoAdapter mAdapter;
    private ListView mList_view;

    private SearchView mSearchView;
    private Toolbar mToolbar;


    //private MemoDBHelper mDbHelper;

    private MemoFacade mMemofacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //DB Helper
        //mDbHelper = new MemoDBHelper(this);

        // 메모 Facade
        mMemofacade = new MemoFacade(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Memo2Activity.class);
                startActivityForResult(intent, REQUEST_CODE_NEW_MEMO);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mList_view = findViewById(R.id.memo_list);
        // 데이터
        mMemoList =  mMemofacade.getMemoList();

        mAdapter = new MemoAdapter(mMemoList);
        mList_view.setAdapter(mAdapter);

        //클릭이벤트
        mList_view.setOnItemClickListener(this);

        //  ContextMenu
        registerForContextMenu(mList_view);


        mToolbar = findViewById(R.id.toolbar);
        mSearchView = findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { // 결과
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { //검색 내용
                // 새로운 쿼리의 결과 뿌리기

                return true;
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        MemoItem memo = mMemoList.get(position);
        Intent intent = new Intent(this, Memo2Activity.class);
        intent.putExtra("id", id);
        //intent.putExtra("memo",memo);

        startActivityForResult(intent, REQUEST_CODE_UPDATE_MEMO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            if (requestCode == REQUEST_CODE_NEW_MEMO) {
                // 새 메모
                long newRowId = mMemofacade.insert(title,content);

                if (newRowId == -1) {
                    // Insert 에러
                    Toast.makeText(this, "저장실패했습니다", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert 성공
                    mMemoList = mMemofacade.getMemoList();

                    Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show();
                }

            } else if (requestCode == REQUEST_CODE_UPDATE_MEMO) {
                long id = data.getLongExtra("id", -1);
                //수정
                MemoItem memo = mMemoList.get((int) id);
                memo.setTitle(title);
                memo.setContent(content);

            }
            //mAdapter.notifyDataSetChanged();

            // TODO  위코드가 안되어 땜빵 코드
            mAdapter = new MemoAdapter(mMemoList);
            mList_view.setAdapter(mAdapter);

            Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "취소되었습니다", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_memo, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_delete:
                //삭제를 누르면 확인을 받고 싶을 때! AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("확인"); //상단 제목
                builder.setMessage("정말 삭제하시겠습니까?"); //내용
                builder.setIcon(R.mipmap.ic_launcher); //아이콘
                //긍정버튼
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteMemo(info.id);
                        Toast.makeText(getApplicationContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
                    }
                });
                //부정 버튼
                builder.setNegativeButton("취소", null);
                builder.show();
                return true;
            case R.id.action_custom_dialog:
                showCustomDialog();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void showCustomDialog() {

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_login, null, false);
        final EditText id_edit = view.findViewById(R.id.id_edit);
        final EditText pw_edit = view.findViewById(R.id.pw_edit);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("확인"); //상단 제목
        builder.setMessage("정말 삭제하시겠습니까?"); //내용
        builder.setIcon(R.mipmap.ic_launcher); //아이콘
        //긍정버튼
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String id = id_edit.getText().toString();
                String pw = pw_edit.getText().toString();
                Toast.makeText(MemoActivity.this, "id : " + id + " /  pw : " + pw, Toast.LENGTH_SHORT).show();
            }
        });
        //부정 버튼
        builder.setNegativeButton("취소", null);
        //내가 만든 레이아웃을 붙일 수 있음
        builder.setView(view);
        builder.show();
    }

    private void DeleteMemo(long id) {
        int deleted = mMemofacade.delete(id);
        if(deleted != 0){
            mMemoList = mMemofacade.getMemoList();
            //mAdapter.notifyDataSetChanged();
            // TODO  위코드가 안되어 땜빵 코드
            mAdapter = new MemoAdapter(mMemoList);
            mList_view.setAdapter(mAdapter);
        }
    }


}
