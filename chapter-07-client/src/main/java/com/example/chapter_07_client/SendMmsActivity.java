package com.example.chapter_07_client;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chapter_07_client.util.ToastUtil;

public class SendMmsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_appendix;
    private ActivityResultLauncher<Intent> mRegisterLauncher;
    private EditText et_phone;
    private EditText et_title;
    private EditText et_message;
    private Uri picUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mms);
        initView();
        initEvent();
        //跳转到系统相册并返回
        mRegisterLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                picUri = intent.getData();
                if (picUri != null) {
                    //显示刚刚选择的图片
                    iv_appendix.setImageURI(picUri);
                    Log.d("shen", "picUri" + picUri.toString());
                }
            }
        });
    }

    private void initEvent() {
        iv_appendix.setOnClickListener(this);
        findViewById(R.id.btn_send_mms).setOnClickListener(this);
    }

    private void initView() {
        iv_appendix = findViewById(R.id.iv_appendix);
        et_phone = findViewById(R.id.et_phone);
        et_title = findViewById(R.id.et_title);
        et_message = findViewById(R.id.et_message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_appendix:
                //跳转到系统相册并返回
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                mRegisterLauncher.launch(intent);
                break;
            case R.id.btn_send_mms:
                sendMms(et_phone.getText().toString(),
                        et_title.getText().toString(),
                        et_message.getText().toString());
                break;
        }
    }

    private void sendMms(String phone, String title, String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //读取我携带的URI
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //彩信的目标号码
        intent.putExtra("address", phone);
        //彩信的标题
        intent.putExtra("subject", title);
        //彩信的内容
        intent.putExtra("sms_body", message);
        //彩信的图片
        intent.putExtra(Intent.EXTRA_STREAM, picUri);
        intent.setType("image/*");
        startActivity(intent);
        ToastUtil.show(this, "请在弹窗中选择短信或者信息应用");
    }
}