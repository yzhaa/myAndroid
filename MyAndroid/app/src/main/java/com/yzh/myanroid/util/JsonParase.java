package com.yzh.myanroid.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JsonParase {
    public static <T> List<T> getResult(String contence,String sepa, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        try {
            JSONObject jsob = new JSONObject(contence);
            JSONArray jsonArray = jsob.getJSONArray(sepa);
            Field[] fields = tClass.getDeclaredFields();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T t = tClass.newInstance();
                for (Field f:fields) {
                    Method method = tClass.getMethod("set" + f.getName().substring(0,1).toUpperCase()+f.getName().substring(1), f.getType());
                    method.invoke(t, jsonObject.getString(f.getName()));
                }
                list.add(t);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
