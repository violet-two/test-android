package com.example.chapter08.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.chapter08.ListFocusActivity;
import com.example.chapter08.MainActivity;
import com.example.chapter08.R;
import com.example.chapter08.util.ToastUtil;


public class LaunchFragment extends Fragment {


    public static LaunchFragment newInstance(int position, int image_id,int count) {
        LaunchFragment fragment = new LaunchFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("image_id", image_id);
        args.putInt("count", count);
        fragment.setArguments(args);
        return fragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        int position = arguments.getInt("position", 0);
        int image_id = arguments.getInt("image_id", 0);
        int count = arguments.getInt("count", 0);
        View view = LayoutInflater.from(context).inflate(R.layout.item_launch,container,false);
        ImageView iv_launch = view.findViewById(R.id.iv_launch);
        RadioGroup rg_indicate = view.findViewById(R.id.rg_indicate);
        Button btn_start = view.findViewById(R.id.btn_start);

        iv_launch.setImageResource(image_id);
        //每一面分配对应的一组单选框按钮
        for (int j = 0; j < count; j++) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            radioButton.setPadding(10, 10, 10, 10);
            rg_indicate.addView(radioButton);
        }
        //当前位 置的单选按钮要高亮显示，比如第二个引导页就高亮第二个单选按钮

        for (int i = 0; i < count; i++) {
            if(i==position){
                ((RadioButton) rg_indicate.getChildAt(position)).setChecked(true);
            }else{
                ((RadioButton) rg_indicate.getChildAt(i)).setEnabled(false);
            }

        }

        //如果是最后一-个引导页，则显示入口按钮，以便用户点击按钮进入主页
        if (position == count - 1) {
            btn_start.setVisibility(View.VISIBLE);
            btn_start.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);

            });
        }
        return view;
    }
}