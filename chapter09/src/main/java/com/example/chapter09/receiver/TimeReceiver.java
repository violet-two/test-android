package com.example.chapter09.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent!=null){
            Log.d("shen", "收到一个分钟广播");
        }
    }
}
