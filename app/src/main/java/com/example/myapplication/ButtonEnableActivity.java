package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.utils.DateUtil;

public class ButtonEnableActivity extends AppCompatActivity implements View.OnClickListener {

    private Button test;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_enable);
        Button enable = findViewById(R.id.enable);
        Button disable = findViewById(R.id.disable);
        test = findViewById(R.id.test);
        result = findViewById(R.id.result);

        enable.setOnClickListener(this);
        disable.setOnClickListener(this);
        test.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.enable:
                test.setEnabled(true);
                test.setBackgroundColor(Color.WHITE);
                break;
            case R.id.disable:
                test.setEnabled(false);
                test.setBackgroundColor(Color.GRAY);
                break;
            case R.id.test:
                String desc = String.format("%s 您点击了按钮： %s",
                        DateUtil.getNowDate(),
                        ((Button)view).getText()
                );
                result.setText(desc);
                break;
        }
    }
}