package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.chapter08.adapter.ImagePagerAdapter;
import com.example.chapter08.entity.GoodsInfo;
import com.example.chapter08.util.ToastUtil;

import java.util.ArrayList;

public class ViewPageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager vp_content;
    private ArrayList<GoodsInfo> mGoodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        initView();
        initEvent();

    }

    private void initEvent() {
        vp_content.addOnPageChangeListener(this);
    }

    private void initView() {
        vp_content = findViewById(R.id.vp_content);
        mGoodsList = GoodsInfo.getDefaultList();
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, mGoodsList);
        vp_content.setAdapter(adapter);
    }

    //翻页状态改变时触发。status取值为:0表示静止，1表示在滑动，2表示滑动完毕
    //在翻页过程中，状态变化依次为：正在滑动--滑动完毕-静止
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //在翻页过程中触发。该方法的三个参数取值说明为:第一个参数表示当前页面的序号
    //第二个参数表示页面偏移的百分比，取值为0到1;第三个参数表示页面的偏移距离
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    // 在翻页结束后触发。position表示当前滑到了哪一 个页面
    @Override
    public void onPageSelected(int position) {
        ToastUtil.show(this,"您翻到的手机品牌是："+mGoodsList.get(position).name);
    }
}