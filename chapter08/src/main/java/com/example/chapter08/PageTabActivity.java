package com.example.chapter08;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.example.chapter08.adapter.ImagePagerAdapter;
import com.example.chapter08.entity.GoodsInfo;

import java.util.ArrayList;

public class PageTabActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ArrayList<GoodsInfo> mGoodsList;
    private ViewPager vp_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_tab);
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
        vp_content.addOnPageChangeListener(this);

    }

    //初始化视图
    private void initView() {
        vp_content = findViewById(R.id.vp_content);
        mGoodsList = GoodsInfo.getDefaultList();
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, mGoodsList);
        vp_content.setAdapter(adapter);
        vp_content.setCurrentItem(3);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}