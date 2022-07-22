package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionBarPolicy;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.chapter08.adapter.PlanetGridAdapter;
import com.example.chapter08.adapter.PlanetListWithButtonAdapter;
import com.example.chapter08.entity.Planet;
import com.example.chapter08.util.ToastUtil;

import java.util.List;

public class GridViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gv_planet;
    private List<Planet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        initView();
        initEvent();
    }

    private void initEvent() {
        gv_planet.setOnItemClickListener(this);
    }

    private void initView() {
        gv_planet = findViewById(R.id.gv_planet);
        planetList = Planet.getDefaultList();
        PlanetGridAdapter adapter = new PlanetGridAdapter(this, planetList);
        gv_planet.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ToastUtil.show(this, "您选择了" + planetList.get(i).name);
    }
}