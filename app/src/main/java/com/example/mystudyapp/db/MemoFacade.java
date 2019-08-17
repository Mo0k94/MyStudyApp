package com.example.mystudyapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.mystudyapp.models.MemoItem;

import java.util.ArrayList;
import java.util.List;

public class MemoFacade {

    private MemoDBHelper mDBHelper;


    public MemoFacade(Context context) {
        this.mDBHelper = new MemoDBHelper(context);
    }


    /*
     *  메모 Insert
     *  @param title 제목
     *  @param content 내용
     *  @return 추가된 row의 id, 만약 에러가 발생하면 -1 리턴
     * */
    public long insert(String title, String content) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        // 이거 한줄로도 가능 대신 return 값이 없어서 에러인지 확인 불가능
        //db.execSQL("INSERT INTO memo(title,content) VALUES('" + title + "', '" + content + "')");
        ContentValues values = new ContentValues();
        values.put(MemoContract.MemoEntry.COLUMN_NAME_TITLE, title);
        values.put(MemoContract.MemoEntry.COLUMN_NAME_CONTENT, content);

        long newRowId = db.insert(MemoContract.MemoEntry.TABLE_NAME, null, values);

        return newRowId;
    }


    /*
    *   전체 메모 Select
    *   @return 전체 메모 데이터 리턴
    * */
    public List<MemoItem> getMemoList() {

        ArrayList<MemoItem> mMemoList = new ArrayList<>();
        // DB에서 읽어오기
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String[] projection = {
                MemoContract.MemoEntry._ID,
                MemoContract.MemoEntry.COLUMN_NAME_TITLE,
                MemoContract.MemoEntry.COLUMN_NAME_CONTENT
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = MemoContract.MemoEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = {"My Title"};

        // Order 정렬
        String sortOrder = MemoContract.MemoEntry._ID + " DESC";

        Cursor c = db.query(
                MemoContract.MemoEntry.TABLE_NAME,              // table명
                null,                                     // return할 컬럼
                null,                                          // 조건
                null,                                      //
                null,                                     // groupby
                null,                                       // having
                sortOrder                                           // 정렬

        );

        if (c != null) {    // select 결과 값이 null이 아니면 실행
            // Cursor -> Memo로 변환
            //c.moveToFirst();
            while (c.moveToNext()) { // Data 있으면 계속 돈다

                String title = c.getString(c.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_NAME_TITLE));
                String content = c.getString(c.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_NAME_CONTENT));
                long id = c.getLong(c.getColumnIndexOrThrow(MemoContract.MemoEntry._ID));
                MemoItem memo = new MemoItem(id,title, content);
                mMemoList.add(memo);
            }

            //커서 닫기
            c.close();
        }
        return mMemoList;
    }

    /*
    *   메모 Delete
    *   @param id 삭제할 메모 id
    *   @return 삭제된 행의 수
    * */
    public int delete(long id){

        String selection = MemoContract.MemoEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id)};

        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int deleted = db.delete(MemoContract.MemoEntry.TABLE_NAME,
                selection,
                selectionArgs);

        /* 이 방법으로도 가능능
        int deleted = db.dlete(MemoContract.MemoEntry.TABLE_NAME,
                "_id=" + id,
                null);*/
        return deleted;
    }

}
