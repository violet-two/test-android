package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chapter08.adapter.PlanetBaseAdapter;
import com.example.chapter08.entity.Planet;
import com.example.chapter08.util.ToastUtil;

import java.util.List;

public class BaseAdapterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner sp_planet;
    private List<Planet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);
        initView();
        initEvent();
    }

    private void initEvent() {
        sp_planet.setOnItemSelectedListener(this);
    }

    private void initView() {
        sp_planet = findViewById(R.id.sp_planet);
        planetList = Planet.getDefaultList();
        PlanetBaseAdapter adapter = new PlanetBaseAdapter(this, planetList);
        sp_planet.setAdapter(adapter);
        sp_planet.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ToastUtil.show(this,"您选择的是"+planetList.get(i).name);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}