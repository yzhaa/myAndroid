/*
package com.yzh.myanroid.view.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yzh.myanroid.R;

import com.yzh.myanroid.controller.asy.LoginAsy;
import com.yzh.myanroid.util.BaseActivity;
import com.yzh.myanroid.util.MyToast;


public class LoginActivity extends BaseActivity {
    private EditText mEditTextId;
    private EditText mEditTextPw;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        Button login = findViewById(R.id.login_btn_login);
        mEditTextId = findViewById(R.id.login_et_id);
       mEditTextPw = findViewById(R.id.login_et_pw);
        super.onCreate(savedInstanceState);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginAsy().execute(mEditTextId.getText().toString(), mEditTextPw.getText().toString());
            }
        });
    }

    public void showlogin(String contence){
        MyToast.buildToast(contence);
    }
}*/
