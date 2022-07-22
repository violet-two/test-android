package com.example.chapter05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class LoginForgetActivity extends AppCompatActivity implements View.OnClickListener {

    private String mPhone;
    private String verifyCode;
    private EditText et_password_first;
    private EditText et_password_second;
    private EditText et_verifyCode;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);
        et_password_first = findViewById(R.id.et_password_first);
        et_password_second = findViewById(R.id.et_password_second);
        et_verifyCode = findViewById(R.id.et_verifyCode);
        //从上个界面获取要修改密码的手机号
        mPhone = getIntent().getStringExtra("phone");
        findViewById(R.id.btn_verifyCode).setOnClickListener(this);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_verifyCode:
                verifyCode = String.format("%06d", new Random().nextInt(999999));
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请记住验证码");
                builder.setMessage("手机号"+mPhone+",本次验证码是"+ verifyCode);
                builder.setPositiveButton("好的", null);
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.btn_confirm:
                String password_first = et_password_first.getText().toString();
                String password_second = et_password_second.getText().toString();
                if(password_first.length()<6){
                    Toast.makeText(this,"请输入正确的密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password_first.equals(password_second)){
                    Toast.makeText(this,"2次密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!verifyCode.equals(et_verifyCode.getText().toString())){
                    Toast.makeText(this,"请输入正确的验证码",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this,"面膜修改成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("new_password",password_first);
                setResult(Activity.RESULT_OK,intent);
                finish();
                break;
        }
    }
}