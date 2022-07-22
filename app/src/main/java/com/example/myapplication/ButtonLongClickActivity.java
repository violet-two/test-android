package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.utils.DateUtil;

public class ButtonLongClickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_long_click);
        TextView tv_result = findViewById(R.id.tv_result);
        Button btn_long_click = findViewById(R.id.btn_long_click);
        btn_long_click.setOnLongClickListener(view -> {
            String desc = String.format("%s 您点击了按钮： %s",
                    DateUtil.getNowDate(),
                    ((Button)view).getText()
                    );
            tv_result.setText(desc);
            return true;
        });
    }
}