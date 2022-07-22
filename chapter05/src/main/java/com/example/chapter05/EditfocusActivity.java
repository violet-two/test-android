package com.example.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditfocusActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editfocus);
        et_phone = findViewById(R.id.phone);
        EditText password = findViewById(R.id.password);
        password.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(b){
            String phone = et_phone.getText().toString();
            if(phone.length()<11||phone.isEmpty()){
                et_phone.requestFocus();
                Toast.makeText(this,"请输入11位手机号",Toast.LENGTH_SHORT).show();
            }

        }
    }
}