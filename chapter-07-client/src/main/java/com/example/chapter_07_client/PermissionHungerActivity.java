package com.example.chapter_07_client;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter_07_client.util.PermissionUtil;
import com.example.chapter_07_client.util.ToastUtil;

public class PermissionHungerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] PERMISSIONS= new String[]{
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS
    };
    private static final String[] PERMISSION_CONTACTS = new String[]{
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
    };
    private static final String[] PERMISSION_SMS = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS
    };
    private static final int REQUEST_CODE_ALL = 1;
    private static final int REQUEST_CODE_CONTACTS = 2;
    private static final int REQUEST_CODE_SMS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_lazy);
        findViewById(R.id.btn_contact).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        PermissionUtil.checkPermission(this,PERMISSIONS,REQUEST_CODE_ALL);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_contact:
                PermissionUtil.checkPermission(this,PERMISSION_CONTACTS,REQUEST_CODE_CONTACTS);
                break;
            case R.id.btn_sms:
                PermissionUtil.checkPermission(this,PERMISSION_SMS,REQUEST_CODE_SMS);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_ALL:
                if(PermissionUtil.checkGrant(grantResults)){
                    Log.d("shen","所有权限成功");
                }else{
                    for (int i = 0;i<grantResults.length;i++) {
                        if(grantResults[i]!= PackageManager.PERMISSION_GRANTED){
                            //判断什么权限没有获取
                            switch (permissions[i]){
                                case Manifest.permission.READ_CONTACTS:
                                case Manifest.permission.WRITE_CONTACTS:
                                    ToastUtil.show(this,"通讯录获取权限失败");
                                    break;
                                case Manifest.permission.SEND_SMS:
                                case Manifest.permission.READ_SMS:
                                    ToastUtil.show(this,"短信获取权限失败");
                                    break;

                            }
                        }
                    }
                }
                break;
            case REQUEST_CODE_CONTACTS:
                if(PermissionUtil.checkGrant(grantResults)){
                    Log.d("shen","通讯录获取权限成功");
                }else{
                    ToastUtil.show(this,"通讯录获取权限失败");
                    jumpToSetting();
                }
                break;
            case REQUEST_CODE_SMS:
                if(PermissionUtil.checkGrant(grantResults)){
                    Log.d("shen","短信获取权限成功");
                }else{
                    ToastUtil.show(this,"短信获取权限失败");
                    jumpToSetting();
                }
                break;
        }
    }

    private void jumpToSetting(){
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}