package com.example.chapter_07_client.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ToastUtil {

    public static void show(Context context,String desc){
        Toast.makeText(context,desc,Toast.LENGTH_SHORT).show();
    }
}
