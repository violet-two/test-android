package com.example.chapter08.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chapter08.entity.GoodsInfo;
import com.example.chapter08.fragment.DynamicFragment;

import java.util.List;

public class MobilePagerAdapter extends FragmentPagerAdapter {
    private final List<GoodsInfo> mGoodsInfo;

    public MobilePagerAdapter(@NonNull FragmentManager fm, List<GoodsInfo> goodsInfo) {
        //会将当前fragment设置为Resume的状态，把L:个fragment设置成Start的状态。
        //从而可以通过fragment 的onResume ()油懒加载数据。
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mGoodsInfo = goodsInfo;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        GoodsInfo info = mGoodsInfo.get(position);

        return DynamicFragment.newInstance(position,info.pic,info.description);
    }

    @Override
    public int getCount() {
        return mGoodsInfo.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mGoodsInfo.get(position).name;
    }
}
