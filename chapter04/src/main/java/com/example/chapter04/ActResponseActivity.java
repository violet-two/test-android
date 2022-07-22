package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chapter04.utils.DateUtil;

public class ActResponseActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String mResponse = "你好呀";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_response);
        TextView tv_request = findViewById(R.id.tv_request);
        Bundle bundle = getIntent().getExtras();
        String request_time = bundle.getString("request_time");
        String request_content = bundle.getString("request_content");
        String desc = String.format("收到请求消息：\n请求时间为%s\n请求内容为%s",request_time,request_content);
        tv_request.setText(desc);

        findViewById(R.id.btn_response).setOnClickListener(this);

        TextView tv_response = findViewById(R.id.tv_response);
        tv_response.setText("待返回的消息为："+mResponse);
    }

    @Override
    public void onClick(View view) {
        Intent intent =new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("response_time", DateUtil.getNowDate());
        bundle.putString("response_content",mResponse);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}