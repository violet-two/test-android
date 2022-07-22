package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener {

    private String mDatabaseName;
    private TextView tv_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        findViewById(R.id.btn_database_create).setOnClickListener(this);
        findViewById(R.id.btn_database_delete).setOnClickListener(this);
        tv_database = findViewById(R.id.tv_database);
        mDatabaseName = getFilesDir()+"/test.db";
    }

    @Override
    public void onClick(View view) {
        String desc = "";
        switch (view.getId()){
            case R.id.btn_database_create:
                //创建或者打开数据库。
                SQLiteDatabase db = openOrCreateDatabase(mDatabaseName, Context.MODE_PRIVATE, null);
                desc = String.format("数据库%s创建%s",db.getPath(),(db!=null)?"成功":"失败");
                tv_database.setText(desc);
                break;
            case R.id.btn_database_delete:
                boolean result = deleteDatabase(mDatabaseName);
                desc = String.format("数据库%s删除%s",mDatabaseName,(result)?"成功":"失败");
                tv_database.setText(desc);
                break;
        }
    }
}