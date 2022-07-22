package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chapter08.adapter.PlanetBaseAdapter;
import com.example.chapter08.entity.Planet;
import com.example.chapter08.util.ToastUtil;

import java.util.List;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lv_planet;
    private List<Planet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initView();
        initEvent();


    }

    private void initEvent() {
        lv_planet.setOnItemClickListener(this);
    }

    private void initView() {
        lv_planet = findViewById(R.id.lv_planet);
        planetList = Planet.getDefaultList();
        PlanetBaseAdapter adapter = new PlanetBaseAdapter(this, planetList);
        lv_planet.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ToastUtil.show(this,"您选择的是："+planetList.get(i).name);
    }
}