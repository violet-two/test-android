package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chapter09.receiver.StandardReceiver;

public class BroadStandardActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String STANDARD_ACTION = "com.example.chapter09.standard";
    private Button btn_standard;
    private StandardReceiver standardReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_standard);
        initView();
        initEvent();
    }

    private void initEvent() {
        btn_standard.setOnClickListener(this);
    }

    private void initView() {
        btn_standard = findViewById(R.id.btn_standard);
    }

    @Override
    public void onClick(View view) {
        //发送标准广播
        Intent intent = new Intent(STANDARD_ACTION);
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        standardReceiver = new StandardReceiver();
        IntentFilter filter = new IntentFilter(standardReceiver.STANDARD_ACTION);
        registerReceiver(standardReceiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(standardReceiver);
    }
}