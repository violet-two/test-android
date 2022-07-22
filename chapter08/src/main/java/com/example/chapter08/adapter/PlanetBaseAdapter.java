package com.example.chapter08.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter08.R;
import com.example.chapter08.entity.Planet;

import java.util.List;

public class PlanetBaseAdapter extends BaseAdapter {
    private Context mContext;
    private List<Planet> mPlanetList;

    public PlanetBaseAdapter(Context mContext, List<Planet> mPlanetList) {
        this.mContext = mContext;
        this.mPlanetList = mPlanetList;
    }

    @Override
    public int getCount() {
        return mPlanetList.size();
    }

    @Override
    public Object getItem(int i) {
        return mPlanetList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
            holder = new ViewHolder();
            holder.iv_icon = view.findViewById(R.id.iv_icon);
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_desc = view.findViewById(R.id.tv_desc);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Planet planet = mPlanetList.get(position);
        holder.iv_icon.setImageResource(planet.image);
        holder.tv_name.setText(planet.name);
        holder.tv_desc.setText(planet.desc);

        return view;
    }

    public final class ViewHolder {
        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_desc;
    }
}
