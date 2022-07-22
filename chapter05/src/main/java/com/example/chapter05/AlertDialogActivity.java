package com.example.chapter05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        Button btn_alert = findViewById(R.id.btn_alert);
        tv_alert = findViewById(R.id.tv_alert);
        btn_alert.setOnClickListener(this);
        tv_alert.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //构造对话框的建造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置标题
        builder.setTitle("尊敬的用户");
        //设置内容
        builder.setMessage("您真的要卸载我吗？");
        //设置肯定按钮文本及监听器
        builder.setPositiveButton("残忍卸载",(dialog,which)->{
            tv_alert.setText("你走吧");
        });
        //设置否定按钮文本及监听器
        builder.setNegativeButton("我再想想",(dialog,which)->{
            tv_alert.setText("留下来");
        });

        //根据建造器提醒对话框对象
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}