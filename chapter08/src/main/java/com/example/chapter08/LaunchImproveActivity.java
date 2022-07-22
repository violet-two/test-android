package com.example.chapter08;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.chapter08.adapter.LaunchImproveAdapter;

public class LaunchImproveActivity extends AppCompatActivity {
    private int[] launchImageArray = {
            R.drawable.guide_bg1,
            R.drawable.guide_bg2,
            R.drawable.guide_bg3,
            R.drawable.guide_bg4,
    };
    private ViewPager vp_launch;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_improve);
        initView();
        initEvent();
    }

    private void initEvent() {
    }

    private void initView() {
        vp_launch = findViewById(R.id.vp_launch);
        LaunchImproveAdapter adapter = new LaunchImproveAdapter(getSupportFragmentManager(),launchImageArray);
        vp_launch.setAdapter(adapter);
    }
}