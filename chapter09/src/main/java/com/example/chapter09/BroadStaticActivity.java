package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chapter09.receiver.ShockReceiver;

public class BroadStaticActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SHOCK_ACTION = "com.example.chapter09.shock";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_static);
        findViewById(R.id.btn_send_shock).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ShockReceiver.SHOCK_ACTION);
        String fullName = "com.example.chapter09.receiver.ShockReceiver";
        ComponentName componentName = new ComponentName(this,fullName);
        intent.setComponent(componentName);
        sendBroadcast(intent);
    }
}