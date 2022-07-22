package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chapter08.util.ToastUtil;

public class SpinnerDropdownActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    private final static String[] startArray = {
            "水星",
            "金星",
            "地球",
            "火星",
            "木星",
            "土星"};
    private Spinner sp_dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_dropdown);
        initView();
        initEvent();
        //声明一个下拉列表的数组适配器
        ArrayAdapter<String> startAdapter = new ArrayAdapter<>(this,R.layout.item_select,startArray);

        sp_dropdown.setAdapter(startAdapter);
        //设置下拉框默认显示第一项
        sp_dropdown.setSelection(0);
        
    }

    private void initEvent() {
        sp_dropdown.setOnItemSelectedListener(this);
    }

    private void initView() {
        sp_dropdown = findViewById(R.id.sp_dropdown);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        ToastUtil.show(this,"您选择的是"+startArray[position]);
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}