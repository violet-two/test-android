package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ActionuriActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionuri);
        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_my).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String phoneNo = "123456";
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btn_dial:
                intent.setAction(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:"+phoneNo);
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.btn_sms:
                intent.setAction(Intent.ACTION_SENDTO);
                Uri uri2 = Uri.parse("smsto:"+phoneNo);
                intent.setData(uri2);
                startActivity(intent);
                break;
            case R.id.btn_my:
                intent.setAction("android.intent.action.SHEN");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);
                break;
        }
    }
}