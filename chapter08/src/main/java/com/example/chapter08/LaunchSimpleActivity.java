package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.chapter08.adapter.LaunchSimpleAdapter;

public class LaunchSimpleActivity extends AppCompatActivity {

    private int[] launchImageArray = {
            R.drawable.guide_bg1,
            R.drawable.guide_bg2,
            R.drawable.guide_bg3,
            R.drawable.guide_bg4,
    };
    private ViewPager vp_launch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_simple);
        initView();
        LaunchSimpleAdapter adapter = new LaunchSimpleAdapter(this,launchImageArray);
        vp_launch.setAdapter(adapter);

    }

    private void initView() {
        vp_launch = findViewById(R.id.vp_launch);
    }
}