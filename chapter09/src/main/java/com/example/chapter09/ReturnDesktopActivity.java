package com.example.chapter09;

import android.app.PictureInPictureParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Rational;

import androidx.appcompat.app.AppCompatActivity;

public class ReturnDesktopActivity extends AppCompatActivity {


    private DesktopReceiver desktopReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_desktop);
        desktopReceiver = new DesktopReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(desktopReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(desktopReceiver);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        if(isInPictureInPictureMode){
            Log.d("shen", "进入画中画模式");
        }else{
            Log.d("shen", "退出画中画模式");
        }
    }

    private class DesktopReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null&&intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)){
                String reason = intent.getStringExtra("reason");
                if(!TextUtils.isEmpty(reason)&&reason.equals("homekey")||reason.equals("recentapps")){
                    //8.0后才有画中画
                    if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O
                    &&!isInPictureInPictureMode()){
                        //画中画参数的构建起
                        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
                        Rational rational = new Rational(10,5);
                        builder.setAspectRatio(rational);
                        enterPictureInPictureMode(builder.build());
                    }
                }
            }
        }
    }
}