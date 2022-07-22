package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chapter08.adapter.PlanetListWithButtonAdapter;
import com.example.chapter08.entity.Planet;
import com.example.chapter08.util.ToastUtil;

import java.util.List;

public class ListFocusActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lv_planet;
    private List<Planet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_focus);
        initView();
        initEvent();
        
    }

    private void initEvent() {
        lv_planet.setOnItemClickListener(this);
    }

    private void initView() {
        lv_planet = findViewById(R.id.lv_planet);
        planetList = Planet.getDefaultList();
        PlanetListWithButtonAdapter adapter = new PlanetListWithButtonAdapter(this, planetList);
        lv_planet.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ToastUtil.show(this,"条目被点击了"+planetList.get(i).name);
    }
}