package com.yzh.myanroid.util;

import android.widget.Toast;

public class MyToast {
    public static void buildToast(String contence){
        Toast.makeText(MyApplication.getContext(),contence,Toast.LENGTH_SHORT).show();
    }
}
