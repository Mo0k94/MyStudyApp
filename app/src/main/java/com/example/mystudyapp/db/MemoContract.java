package com.example.mystudyapp.db;

import android.provider.BaseColumns;

/*
 *  메모장의 스키마
 * */
public class MemoContract {


    /*
     * # 메모장 테이블 생성 #
     * CREATE TABLE memo
     * (
     *       _id INTEGER PRIMARY KEY AUTOINCREMENT,
     *       title TEXT,
     *       contents TEXT
     * );*/
    public static final String SQL_CREATE_MEMO_TABLE =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT);",
                    MemoEntry.TABLE_NAME,
                    MemoEntry._ID,
                    MemoEntry.COLUMN_NAME_TITLE,
                    MemoEntry.COLUMN_NAME_CONTENT);


    private MemoContract() {
    }

    public static class MemoEntry implements BaseColumns {
        public static final String TABLE_NAME = "memo";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
    }

    /*
    public static class AttachmentEntry implements BaseColumns {
        public static final String TABLE_NAME = "memo";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
    }
    */
}
