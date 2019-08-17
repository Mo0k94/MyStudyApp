package com.example.mystudyapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemoDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "memo.db";


    public MemoDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    // DB를 처음으로 사용할 때 호출
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Table 생성
        sqLiteDatabase.execSQL(MemoContract.SQL_CREATE_MEMO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // DB 업그레이드 처리
        sqLiteDatabase.execSQL(MemoContract.SQL_CREATE_MEMO_TABLE);
    }
}
