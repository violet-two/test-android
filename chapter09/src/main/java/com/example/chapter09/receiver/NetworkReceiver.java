package com.example.chapter09.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.chapter09.util.NetworkUtil;

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            NetworkInfo networkInfo = intent.getParcelableExtra("networkInfo");
            String text = String.format("收到一个网络广播，网络大类为%s,"+
                    "网络小类为%s,网络制式为%s,网络状态为%s",
                    networkInfo.getTypeName(),
                    networkInfo.getSubtypeName(),
                    NetworkUtil.getNetworkClass(networkInfo.getSubtype()),
                    networkInfo.getState().toString()
                    );
            Log.d("shen",text );
        }
    }
}