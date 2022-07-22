package com.example.chapter_07_client;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter_07_client.entity.User;
import com.example.chapter_07_client.util.ToastUtil;

public class ContentWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView et_name;
    private TextView et_age;
    private TextView et_height;
    private TextView et_weight;
    private CheckBox ck_married;
    private Button btn_save;
    private Button btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_write);
        initView();

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
    }

    private void initView() {
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        ck_married = findViewById(R.id.ck_married);
        btn_save = findViewById(R.id.btn_save);
        btn_read = findViewById(R.id.btn_read);
    }

    @SuppressLint("Range")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                ContentValues values = new ContentValues();
                values.put(UserInfoContent.USER_NAME, et_name.getText().toString());
                values.put(UserInfoContent.USER_AGE, Integer.parseInt(et_age.getText().toString()));
                values.put(UserInfoContent.USER_HEIGHT, Integer.parseInt(et_height.getText().toString()));
                values.put(UserInfoContent.USER_WEIGHT, Float.parseFloat(et_weight.getText().toString()));
                values.put(UserInfoContent.USER_MARRIED, ck_married.isChecked());
                // content://com.dongnaoedu.chapter07_server.provider.UserInfoProvider/user
                getContentResolver().insert(UserInfoContent.CONTENT_URI, values);
                ToastUtil.show(this, "保存成功");
                break;

            case R.id.btn_read:
                Cursor cursor = getContentResolver().query(UserInfoContent.CONTENT_URI, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        User info = new User();
                        info.id = cursor.getInt(cursor.getColumnIndex(UserInfoContent._ID));
                        info.name = cursor.getString(cursor.getColumnIndex(UserInfoContent.USER_NAME));
                        info.age = cursor.getInt(cursor.getColumnIndex(UserInfoContent.USER_AGE));
                        info.height = cursor.getInt(cursor.getColumnIndex(UserInfoContent.USER_HEIGHT));
                        info.weight = cursor.getFloat(cursor.getColumnIndex(UserInfoContent.USER_WEIGHT));
                        info.married = cursor.getInt(cursor.getColumnIndex(UserInfoContent.USER_MARRIED)) == 1 ? true : false;
                        Log.d("shen", info.toString());
                    }
                    cursor.close();
                }
                break;
            case R.id.btn_delete:
//                Uri uri = ContentUris.withAppendedId(UserInfoContent.CONTENT_URI,2);
//                int count = getContentResolver().delete(uri,null,null);
                int count = getContentResolver().delete(UserInfoContent.CONTENT_URI,"name=?",new String[]{"Jack"});
                if(count>0){
                    ToastUtil.show(this,"删除成功");
                }
                break;
        }
    }
}