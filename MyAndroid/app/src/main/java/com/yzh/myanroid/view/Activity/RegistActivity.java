/*
package com.yzh.myanroid.view.Activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yzh.myanroid.R;

import com.yzh.myanroid.controller.Do.RegistAsy;
import com.yzh.myanroid.view.Activity.BaseActivity;
import com.yzh.myanroid.util.MyToast;

public class RegistActivity extends BaseActivity implements View.OnClickListener {
    private EditText mAccount;
    private EditText mPassword;
    private EditText mRePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        mAccount = findViewById(R.id.regist_et_id);
        mPassword = findViewById(R.id.regist_et_pw);
        mRePassword = findViewById(R.id.regist_et_repw);
        Button button = findViewById(R.id.regist_btn_regist);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new RegistAsy().execute(mAccount.getText().toString(), mPassword.getText().toString(), mRePassword.getText().toString());
    }

    public void showRegist(String contence){
        MyToast.buildToast(contence);
    }
}*/
