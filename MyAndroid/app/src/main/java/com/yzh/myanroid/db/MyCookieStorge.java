package com.yzh.myanroid.db;

import android.content.Context;
import android.content.SharedPreferences;


import com.yzh.myanroid.util.MyApplication;

import java.util.HashMap;
import java.util.Map;

public class MyCookieStorge {
    public static boolean IsState;


  public static void writeCookie(boolean isStroge, String  name,String password){
      SharedPreferences sp = MyApplication.getContext().getSharedPreferences("cookie", Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sp.edit();
      if(!isStroge){
          editor.clear();
          editor.putBoolean("status", false);
          editor.apply();
      }
      else {
          editor.putBoolean("state", true);
          editor.putString("name", name);
          editor.putString("password", password);
          editor.apply();
      }
  }


  public static Map<String,?> readCookie(){
      Map<String, Object> map = new HashMap<>();
      SharedPreferences sp = MyApplication.getContext().getSharedPreferences("cookie", Context.MODE_PRIVATE);
      boolean state = sp.getBoolean("state", false);
      map.put("state", false);
      if(state){
          String name = sp.getString("name", "");
          String password = sp.getString("password", "");
          if(name!=null&&password!=null){
              map.put("name", name);
              map.put("password", password);
          }
      }
      return map;
  }

}
