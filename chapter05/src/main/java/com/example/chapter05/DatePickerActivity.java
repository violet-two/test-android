package com.example.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private DatePicker dp_date;
    private TextView tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_date).setOnClickListener(this);
        dp_date = findViewById(R.id.dp_date);
        tv_date = findViewById(R.id.tv_date);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_ok:
                String desc = String.format("您选择的是%d年%d月%d日",dp_date.getYear(),dp_date.getMonth()+1,dp_date.getDayOfMonth());
                tv_date.setText(desc);
                break;
            case R.id.btn_date:
                Calendar calendar = Calendar.getInstance();
//
                DatePickerDialog dialog = new DatePickerDialog(this, this,2022,7,1);
                dialog.show();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String desc = String.format("您选择的是%d年%d月%d日",i,i1+1,i2);
        tv_date.setText(desc);
    }
}