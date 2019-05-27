package com.yzh.myanroid.util;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Set;

public class MyBundle {
    private HashMap<String,Object> mHashMap =new HashMap<>();

    public  MyBundle put(String key,Object object){
        mHashMap.put(key, object);
        return this;
    }

    public Bundle build(){
        Set<String> keySet = mHashMap.keySet();
        Bundle bundle = new Bundle();
        for (String key:keySet) {
            Object object = mHashMap.get(key);
            if( object instanceof String){
                bundle.putString(key,(String) object);
            }
            else if(object instanceof Integer)
            {
                bundle.putInt(key,(Integer) object);
            }
        }
        return bundle;
    }
   public static MyBundle  getMyBundle(){
       return new MyBundle();
   }

}
