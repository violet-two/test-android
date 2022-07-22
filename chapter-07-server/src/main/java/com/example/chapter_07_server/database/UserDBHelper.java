package com.example.chapter_07_server.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "user.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "user_info";
    private static UserDBHelper mHelper = null;

    public UserDBHelper(Context context) {
        super(context,DB_NAME,null, DB_VERSION);
    }
    public  static  UserDBHelper getInstance(Context context){
        if(mHelper==null){
            mHelper = new UserDBHelper(context);
        }
        return mHelper;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " name VARCHAR NOT NULL," +
                " age INTEGER NOT NULL," +
                " height LONG NOT NULL," +
                " weight FLOAT NOT NULL," +
                " married INTEGER NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}
