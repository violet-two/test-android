package com.example.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.chapter05.util.ViewUtil;

public class EditHideActivity extends AppCompatActivity {

    private EditText et_phone;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hide);
        et_phone = findViewById(R.id.phone);
        et_password = findViewById(R.id.password);
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone,11));
        et_password.addTextChangedListener(new HideTextWatcher(et_phone,6));
    }

    private class HideTextWatcher implements TextWatcher {
        private EditText mView;
        private int mMaxLength;
        public HideTextWatcher(EditText et_phone, int i) {
            this.mView = et_phone;
            this.mMaxLength = i;
        }
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            //获取已获得的字符串
            String str = editable.toString();
            if(str.length()==mMaxLength){
                //隐藏输入法的键盘
                ViewUtil.hideOneInputMethod(EditHideActivity.this,mView);
            }
        }

    }
}