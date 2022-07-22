package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;

import com.example.chapter08.adapter.ImagePagerAdapter;
import com.example.chapter08.adapter.MobilePagerAdapter;
import com.example.chapter08.entity.GoodsInfo;

import java.util.ArrayList;

public class FragmentDynamicActivity extends AppCompatActivity {

    private ArrayList<GoodsInfo> mGoodsList;
    private ViewPager vp_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_dynamic);
        initView();
        initEvent();
        initPagerStrip();
    }
    //初始化标签栏
    private void initPagerStrip() {
        PagerTabStrip pts_tab = findViewById(R.id.pts_tab);
        //设置标签栏的文本大小
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        //文本颜色
        pts_tab.setTextColor(Color.BLACK);
    }

    //初始化事件
    private void initEvent() {

    }

    //初始化视图
    private void initView() {
        vp_content = findViewById(R.id.vp_content);
        mGoodsList = GoodsInfo.getDefaultList();
        MobilePagerAdapter adapter = new MobilePagerAdapter(getSupportFragmentManager(), mGoodsList);
        vp_content.setAdapter(adapter);
        vp_content.setCurrentItem(3);
    }
}