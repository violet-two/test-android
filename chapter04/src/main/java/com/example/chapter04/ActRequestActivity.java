package com.example.chapter04;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.chapter04.utils.DateUtil;

public class ActRequestActivity extends AppCompatActivity implements View.OnClickListener {
    private String mRequest = "你好";
    private ActivityResultLauncher<Intent> register;
    private TextView tv_response;

    @Override
    protected void onStart() {
        super.onStart();
        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result != null) {
                Intent intent = result.getData();
                if (intent != null && result.getResultCode() == Activity.RESULT_OK) {
                    Bundle bundle = intent.getExtras();
                    String response_time = bundle.getString("response_time");
                    String response_content = bundle.getString("response_content");
                    String desc = String.format("收到请求消息：\n应答时间为%s\n应答内容为%s", response_time, response_content);
                    tv_response.setText(desc);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_request);
        TextView tv_request = findViewById(R.id.tv_request);
        tv_request.setText("待发送的消息：" + mRequest);

        tv_response = findViewById(R.id.tv_response);
        findViewById(R.id.btn_request).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ActResponseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("request_time", DateUtil.getNowDate());
        bundle.putString("request_content", mRequest);
        intent.putExtras(bundle);
        register.launch(intent);
    }
}