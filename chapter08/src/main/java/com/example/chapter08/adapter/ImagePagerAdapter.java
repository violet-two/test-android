package com.example.chapter08.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.chapter08.entity.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<GoodsInfo> mGoodsList;
    private ViewPager vp_content;
    // 声明一个图像视图列表
    private List<ImageView> mViewList = new ArrayList<>();

    public ImagePagerAdapter(Context mContext, ArrayList<GoodsInfo> mGoodsList) {
        this.mContext = mContext;
        this.mGoodsList = mGoodsList;
        // 给每个商品分配一个专用的图像视图
        for (GoodsInfo info : mGoodsList) {
            ImageView view = new ImageView(mContext);
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            view.setImageResource(info.pic);
            mViewList.add(view);
        }
    }


    @Override
    public int getCount() {
        return mGoodsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    // 实例化指定位置的页面，并将其添加到容器中
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //能够是view自身或者其他对象
        //关键在途isViewFromObject可以将view和这个object可以关联起来
        ImageView item = mViewList.get(position);
        container.addView(item);
        return item;

    }
    // 从容器中销毁指定位置的页面
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mGoodsList.get(position).name;
    }
}
