package com.example.chapter05;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter05.util.ViewUtil;

import java.util.Random;

public class LoginMainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private TextView tv_password;
    private EditText et_password;
    private Button btn_forget;
    private Button btn_login;
    private CheckBox ck_remember;
    private EditText et_phone;
    private RadioButton rb_verifycode;
    private RadioButton rb_password;
    private ActivityResultLauncher<Intent> register;
    private String mpassword = "123456";
    private String verifyCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        RadioGroup rg_login = findViewById(R.id.rg_login);
        rg_login.setOnCheckedChangeListener(this);
        tv_password = findViewById(R.id.tv_password);
        et_password = findViewById(R.id.et_password);
        btn_forget = findViewById(R.id.btn_forget);
        btn_forget.setOnClickListener(this);
        ck_remember = findViewById(R.id.ck_remember);
        et_phone = findViewById(R.id.et_phone);
        rb_verifycode = findViewById(R.id.rb_verifycode);
        rb_password = findViewById(R.id.rb_password);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone,11));
        et_password.addTextChangedListener(new HideTextWatcher(et_password,6));

        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Intent intent = result.getData();
            if(intent!=null&&result.getResultCode()== Activity.RESULT_OK){
                mpassword = intent.getStringExtra("new_password");
            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            //密码登录
            case R.id.rb_password:
                tv_password.setText(getString(R.string.login_password));
                et_password.setHint(getString(R.string.input_password));
                btn_forget.setText(getString(R.string.forget_password));
                ck_remember.setVisibility(View.VISIBLE);
                break;
            //验证码登录
            case R.id.rb_verifycode:
                tv_password.setText(getString(R.string.verifycode));
                et_password.setHint(getString(R.string.input_verifycode));
                et_password.setInputType(InputType.TYPE_CLASS_NUMBER);
                btn_forget.setText(getString(R.string.get_verifycode));
                ck_remember.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public void onClick(View view) {
        String phone = et_phone.getText().toString();
        if(phone.length()<11){
            Toast.makeText(this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
            return;
        }
        switch (view.getId()){
            case R.id.btn_forget:
                if(rb_password.isChecked()){
                    Intent intent = new Intent(this,LoginForgetActivity.class);
                    intent.putExtra("phone",phone);
                    register.launch(intent);
                }else if(rb_verifycode.isChecked()){
                    verifyCode = String.format("%06d",new Random().nextInt(999999));
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("请记住验证码");
                    builder.setMessage("手机号"+phone+",本次验证码是"+ verifyCode);
                    builder.setPositiveButton("好的", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                break;
            case R.id.btn_login:
                if(rb_password.isChecked()){
                    if(!mpassword.equals(et_password.getText().toString())){
                        Toast.makeText(this,"请输入正确的密码",Toast.LENGTH_SHORT).show();
                        return ;
                    }
                    loginSuccess();
                }else{
                    if(!verifyCode.equals(et_password.getText().toString())){
                        Toast.makeText(this,"请输入正确的验证码",Toast.LENGTH_SHORT).show();
                        return ;
                    }
                    loginSuccess();
                }
        }
    }

    private void loginSuccess() {
        String desc = String.format("您的手机号码是%s，恭喜你通过验证，点击“确定按钮返回上一个页面”",et_phone.getText().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定返回",(dialog,which)->{
           finish();
        });
        builder.setNegativeButton("我在看看",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private class HideTextWatcher implements TextWatcher {
        private EditText mView;
        private int mMaxLength;
        public HideTextWatcher(EditText et, int maxLength) {
            this.mView = et;
            this.mMaxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(editable.toString().length()==mMaxLength){
                ViewUtil.hideOneInputMethod(LoginMainActivity.this,mView);
            }
        }
    }
}