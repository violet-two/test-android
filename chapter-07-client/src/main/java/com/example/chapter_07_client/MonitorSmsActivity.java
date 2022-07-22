package com.example.chapter_07_client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MonitorSmsActivity extends AppCompatActivity {


    private SmsGetObserver smsGetObserver;
    private static TextView tv_text;
    private static Cursor cursor;

    @Override
    protected void onDestroy() {
        getContentResolver().unregisterContentObserver(smsGetObserver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_sms);
        //给指定uri注册内容观察期，一旦发生数据变化，就会触发观察期的onChange方法
        Uri uri = Uri.parse("content://sms");
        SmsGetObserver smsGetObserver = new SmsGetObserver(this);
        tv_text = findViewById(R.id.tv_text);
        getContentResolver().registerContentObserver(uri, true,smsGetObserver);
    }


    private static class SmsGetObserver extends ContentObserver {
        private final Context mConText;

        public SmsGetObserver(Context context) {
            super(new Handler((Looper.getMainLooper())));
            this.mConText = context;
        }

        @Override

        @SuppressLint("Range")
        public void onChange(boolean selfChange, @Nullable Uri uri) {
            super.onChange(selfChange, uri);
            if (uri == null) {
                return;
            }
            if (uri.toString().contains("content://sms/raw")||
            uri.toString().equals("content://sms")) {
                return;
            }

            //通过内容解析器获取符合条件的结果集游标
            cursor = mConText.getContentResolver().query(uri,new String[]{"address","body","date"},null,null,"date DESC");
            if(cursor.moveToNext()){
                //短信的发送号码
                String sender = cursor.getString((cursor.getColumnIndex("address")));
                //短信内容
                String content = cursor.getString((cursor.getColumnIndex("body")));
                String date = cursor.getString((cursor.getColumnIndex("date")));
                Log.d("shen",String.format("sender:%s,content:%s,date:%s",sender,content,date));
                showText(content);
            }
            cursor.close();

        }

        private static void showText(String content) {
            tv_text.setText(content);
        }
    }

}