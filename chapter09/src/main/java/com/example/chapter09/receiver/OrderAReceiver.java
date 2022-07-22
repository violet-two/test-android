package com.example.chapter09.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.chapter09.BroadOrderActivity;

public class OrderAReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent!=null&&intent.getAction().equals(BroadOrderActivity.ORDER_ACTION)){
            Log.d("shen","A接收到一个有序广播");
        }
    }
}
