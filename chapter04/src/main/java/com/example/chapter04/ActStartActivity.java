package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActStartActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "shen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"ActStartActivity onCreate");
        setContentView(R.layout.activity_act_start);
        findViewById(R.id.btn_act_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,ActFinishActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"ActStartActivity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"ActStartActivity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"ActStartActivity onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"ActStartActivity onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"ActStartActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"ActStartActivity onStop");
    }
}