package com.yzh.myanroid.util;


import android.content.Intent;
import android.os.Bundle;

public  class MyIntent {
    public static void startIntent(Class targetActivity, Bundle bundle){
        Intent intent = new Intent(MyApplication.getContext(), targetActivity);
        if(bundle==null){
            ActivityCollector.getCurrentActivity().startActivity(intent);
        }
        else
        ActivityCollector.getCurrentActivity().startActivity( intent.putExtras(bundle));
    }


}
