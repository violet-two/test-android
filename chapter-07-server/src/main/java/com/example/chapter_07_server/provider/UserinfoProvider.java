package com.example.chapter_07_server.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.chapter_07_server.database.UserDBHelper;

public class UserinfoProvider extends ContentProvider {
    private UserDBHelper dbHelper;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int USERS = 1;
    private static final int USER = 2;
    static{
        URI_MATCHER.addURI(UserInfoContent.AUTHORITIES,"/user",USERS);
        URI_MATCHER.addURI(UserInfoContent.AUTHORITIES,"/user/#",USER);
    }
    public UserinfoProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (URI_MATCHER.match(uri)){
            //删除多行
            case USERS:
                SQLiteDatabase sqLiteDatabase1 = dbHelper.getWritableDatabase();
                count = sqLiteDatabase1.delete(UserDBHelper.TABLE_NAME,selection,selectionArgs);
                sqLiteDatabase1.close();
                break;
            //删除指定id
            case USER:
                String id = uri.getLastPathSegment();
                SQLiteDatabase sqLiteDatabase2 = dbHelper.getWritableDatabase();
                count = sqLiteDatabase2.delete(UserDBHelper.TABLE_NAME,"_id=?",new String[]{id});
                sqLiteDatabase2.close();
                break;
        }
        // Implement this to handle requests to delete one or more rows.
        return count;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d("shen","insert");
        // TODO: Implement this to handle requests to insert a new row.
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        writableDatabase.insert(UserDBHelper.TABLE_NAME,null,values);
        return uri;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        Log.d("shen","onCreate");
        dbHelper = UserDBHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d("shen","query");
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        return readableDatabase.query(UserDBHelper.TABLE_NAME,projection,selection,selectionArgs,null,null,null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}