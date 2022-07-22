package com.example.chapter_07_client;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.example.chapter_07_client.util.PermissionUtil;
import com.example.chapter_07_client.util.ToastUtil;

import java.io.File;

public class ProviderApkActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_apk);
        findViewById(R.id.btn_install).setOnClickListener(this);
    }

    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    public void onClick(View view) {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.R){
            Log.d("shenjia","android 11+");
            checkAndInstall();
            
        }
        installApk();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void checkAndInstall() {
        //检查有没有MANAGE_EXTERNAL_STORAGE
        if (!Environment.isExternalStorageManager()) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.fromParts("package",getPackageName(),null));
            startActivity(intent);
        }else{
            //如果有权限，直接安装，没有权限获取权限
            if(PermissionUtil.checkPermission(this,PERMISSIONS,PERMISSION_REQUEST_CODE)){
                installApk();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PERMISSION_REQUEST_CODE&& PermissionUtil.checkGrant(grantResults)){
            installApk();
        }
    }

    private void installApk() {
        String apkPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+"/chapter06-release.apk";
        Log.d("shenjia","apkPath"+apkPath);
        PackageManager pm = getPackageManager();
        PackageInfo packageArchiveInfo = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if(packageArchiveInfo==null){
            ToastUtil.show(this,"文件损坏");
            return;
        }
        //installer
        Uri uri = Uri.parse(apkPath);
        //通过FileProvider获取uri
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            uri = FileProvider.getUriForFile(this,getString(R.string.file_provider),new File(apkPath));
            Log.d("shenjia","uri"+uri);
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //设置uri的数据类型为apk文件
        intent.setDataAndType(uri,"application/vnd.android.package-archive");
        startActivity(intent);
    }
}