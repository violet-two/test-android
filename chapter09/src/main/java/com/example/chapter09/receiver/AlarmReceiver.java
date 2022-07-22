package com.example.chapter09.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    public static String ALARM_ACTION = "com.example.chapter09.alarm";
    private final Context mContext;

    public AlarmReceiver(Context context){
        super();
        this.mContext = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent!=null){
            Log.d("shen", "收到闹钟广播");
            sendAlarm();
        }
    }

    public void sendAlarm(){
        Intent intent = new Intent(ALARM_ACTION);
        //创建延迟意图
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext,0,intent,PendingIntent.FLAG_MUTABLE);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,1000,pendingIntent);
        }else{
            alarmManager.set(AlarmManager.RTC_WAKEUP,1000,pendingIntent);
        }
    }
}