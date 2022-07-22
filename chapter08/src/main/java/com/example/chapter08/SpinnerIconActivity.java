package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.chapter08.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerIconActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final static String[] startArray = {
            "水星",
            "金星",
            "地球",
            "火星",
            "木星",
            "土星"};
    private final static int[] iconArray = {
            R.drawable.shuixing,
            R.drawable.jinxing,
            R.drawable.diqiu,
            R.drawable.huoxing,
            R.drawable.muxing,
            R.drawable.tuxing};
    private Spinner sp_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_icon);
        initView();
        initEvent();
    }

    private void initEvent() {
        sp_icon.setOnItemSelectedListener(this);
    }

    private void initView() {
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < iconArray.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("icon",iconArray[i]);
            map.put("name",iconArray[i]);
            list.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.item_simple,
                new String[]{"icon","name"},
                new int[]{R.id.iv_icon,R.id.tv_name});
        sp_icon = findViewById(R.id.sp_icon);
        sp_icon.setAdapter(simpleAdapter);
        sp_icon.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int selection, long id) {
        ToastUtil.show(this,"您选择的是："+startArray[selection]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}