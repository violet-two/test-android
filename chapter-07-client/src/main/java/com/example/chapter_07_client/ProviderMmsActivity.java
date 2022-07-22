package com.example.chapter_07_client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.chapter_07_client.util.FileUtil;
import com.example.chapter_07_client.util.ImageInfo;
import com.example.chapter_07_client.util.PermissionUtil;
import com.example.chapter_07_client.util.ToastUtil;
import com.example.chapter_07_client.util.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProviderMmsActivity extends AppCompatActivity {

    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private static final int PERMISSION_REQUEST_CODE = 1;

    private List<ImageInfo> mImageList = new ArrayList<>();

    private GridLayout gl_appendix;
    private EditText et_message;
    private EditText et_phone;
    private EditText et_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_mms);
        initView();
        //手动让MediaStore扫描入库
        MediaScannerConnection.scanFile(this,
                new String[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()},
                null,null);
        if( PermissionUtil.checkPermission(this,PERMISSIONS,PERMISSION_REQUEST_CODE)){
            //加载图片列表
            loadImageList();
            //显示图像列表
            showImageGrid();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PERMISSION_REQUEST_CODE&& PermissionUtil.checkGrant(grantResults)){
            //加载图片列表
            loadImageList();
            //显示图像列表
            showImageGrid();
        }

    }

    private void showImageGrid() {
        BitmapFactory.Options opts = null;
        gl_appendix.removeAllViews();
        for (ImageInfo imageInfo : mImageList) {
            //image->ImageView
            ImageView iv_appendix = new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeFile(imageInfo.path.toString());
            iv_appendix.setImageBitmap(bitmap);
            //设置缩放类型
            iv_appendix.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //设置图像视图的布局参数
            int px = Utils.dip2px(this, 110);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px, px);
            iv_appendix.setLayoutParams(params);
            int padding = Utils.dip2px(this, 5);
            iv_appendix.setPadding(padding, padding, padding, padding);
            iv_appendix.setOnClickListener(v -> {
                sendMms(et_phone.getText().toString(),
                        et_title.getText().toString(),
                        et_message.getText().toString(),
                        imageInfo.path);
            });
            //把图像视图添加至网格控件
            gl_appendix.addView(iv_appendix);
        }
    }

    private void sendMms( String phone, String title, String message,String path) {
        //根据指定路劲构建Uri对象
        Uri uri = Uri.parse(path);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            //通过FIleProvider获得文件的Uri访问方式
            uri = FileProvider.getUriForFile(this, getString(R.string.file_provider), new File(path));
            Log.d("shenjia",path);
            Log.d("shenjia",uri.toString());
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.putExtra("address", phone);
        intent.putExtra("subject", title);
        intent.putExtra("sms_body", message);

        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.setType("image/*");
        startActivity(intent);
        ToastUtil.show(this,"请选择发送短信的应用");

    }

    private void initView() {
        gl_appendix = findViewById(R.id.gl_appendix);
        et_phone = findViewById(R.id.et_phone);
        et_message  = findViewById(R.id.et_message);
        et_title = findViewById(R.id.et_title);
    }

    @SuppressLint("Range")
    private void loadImageList() {
        //MediaStore
        String[] columns = new String[]{
                MediaStore.Audio.Media._ID,//编号
                MediaStore.Audio.Media.TITLE,//标题
                MediaStore.Audio.Media.SIZE,//文件大小
                MediaStore.Audio.Media.DATA//文件路径
        };

        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                "_size < 307200",
                null,
                "_size DESC"
        );
        int count = 0;
        if (cursor != null) {
            while (cursor.moveToNext() && count < 6) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                imageInfo.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                imageInfo.name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                imageInfo.path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                if(FileUtil.checkFileUri(this,imageInfo.path)){
                    count++;
                    mImageList.add(imageInfo);
                    Log.d("shen", "image:" + imageInfo.toString());
                }
            }
        }
    }
}