package com.example.chapter08.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chapter08.R;

public class DynamicFragment extends Fragment {
    private static final String TAG = "fragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DynamicFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DynamicFragment newInstance(int position,int image_id, String dsc) {
        DynamicFragment fragment = new DynamicFragment();
        //那参数打包
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("image_id", image_id);
        args.putString("dsc", dsc);
        fragment.setArguments(args);
        return fragment;
    }

    //从包裹取出位置号
    private int getPosition(){
        return getArguments().getInt("position");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach position="+getPosition());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate position="+getPosition());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        Bundle arguments = getArguments();
        if(arguments!=null){
            ImageView iv_pic = view.findViewById(R.id.iv_pic);
            TextView tv_desc = view.findViewById(R.id.tv_desc);
            iv_pic.setImageResource(arguments.getInt("image_id",0));
            tv_desc.setText(arguments.getString("dsc"));
        }
        Log.d(TAG,"onCreateView position="+getPosition());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG,"onStart position="+getPosition());
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG,"onResume position="+getPosition());
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG,"onPause position="+getPosition());
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG,"onStop position="+getPosition());
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG,"onDestroyView position="+getPosition());
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroyView position="+getPosition());
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG,"onDetach position="+getPosition());
        super.onDetach();
    }
}