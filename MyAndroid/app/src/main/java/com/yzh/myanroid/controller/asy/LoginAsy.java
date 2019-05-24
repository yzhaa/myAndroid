/*
package com.yzh.myanroid.controller.asy;

import android.os.AsyncTask;

import com.yzh.myanroid.db.MyCookieStorge;
import com.yzh.myanroid.util.ActivityCollector;
import com.yzh.myanroid.util.CheakNetwork;
import com.yzh.myanroid.util.HttpURlConnectionUtill;
import com.yzh.myanroid.view.Activity.LoginActivity;
import com.yzh.myanroid.view.Activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginAsy extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... strings) {
        String[] keys = {"username", "password"};
        if (CheakNetwork.cheakNetAndSend()) {
            try {
                JSONObject jsonObject = new JSONObject(HttpURlConnectionUtill.getStringResultByPost
                        ("https://www.wanandroid.com/user/login", keys, strings));
                if (jsonObject.getInt("errorCode") != 0) {
                    return jsonObject.getString("errorMsg");
                } else {
                    MyCookieStorge.writeCookie(true, strings[0], strings[1]);
                    MyCookieStorge.IsState = true;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "登陆成功";
        }
        else return "登陆失败";
    }

    @Override
    protected void onPostExecute(String s) {
        if(s.equals("登陆成功")){
            ActivityCollector.getCurrentActivity().finish();
            ((MainActivity) ActivityCollector.getCurrentActivity()).showOperation(1,s);
        } else ((LoginActivity) ActivityCollector.getCurrentActivity()).showlogin(s);
    }
}*/
