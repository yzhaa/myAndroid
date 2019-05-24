/*
package com.yzh.myanroid.controller.asy;

import android.os.AsyncTask;

import com.yzh.myanroid.util.ActivityCollector;
import com.yzh.myanroid.util.CheakNetwork;
import com.yzh.myanroid.util.HttpURlConnectionUtill;
import com.yzh.myanroid.view.Activity.MainActivity;
import com.yzh.myanroid.view.Activity.RegistActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistAsy extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... strings) {
        if(CheakNetwork.cheakNetAndSend()) {
            String[] keys = {"username", "password", "repassword"};
            try {
                JSONObject jo = new JSONObject(HttpURlConnectionUtill.getStringResultByPost
                        ("https://www.wanandroid.com/user/register", keys, strings));
                if (jo.getInt("errorCode") != 0) {
                    return jo.getString("errorMsg");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }  return "注册成功";
        } else return "注册失败";
    }

    @Override
    protected void onPostExecute(String s) {
        if(s.equals("注册成功")){
            ActivityCollector.getCurrentActivity().finish();
            ((MainActivity)ActivityCollector.getCurrentActivity()).showOperation(2,s);
        }
        else {
            ((RegistActivity)ActivityCollector.getCurrentActivity()).showRegist(s);
        }

    }
}*/
