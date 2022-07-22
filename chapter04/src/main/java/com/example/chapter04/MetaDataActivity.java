package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class MetaDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_data);
        TextView tv_mate  = findViewById(R.id.tv_mate);
        PackageManager pm = getPackageManager();
        try {
            ActivityInfo activityInfo = pm.getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            Bundle bundle = activityInfo.metaData;
            String weather = bundle.getString("weather");
            tv_mate.setText(weather);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}