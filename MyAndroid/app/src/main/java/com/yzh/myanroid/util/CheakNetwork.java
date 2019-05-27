package com.yzh.myanroid.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



public class CheakNetwork {

    public static int getNetworkState() {
        Context context = MyApplication.getmContext();
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                    return Constant.NETWORK_WIFI;
                } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return Constant.NETWORK_MOBILE;
                }
            }
            else {
                return Constant. NETWORK_NONE;
            }
        }
        return Constant.NETWORK_NONE;
    }

    public static boolean cheakNetAndSend(){
        if(getNetworkState()==Constant.NETWORK_NONE){
            MyApplication.getmContext().sendBroadcast(new Intent().setAction(Constant.No_Net));
            return false;
        }
        return true;
    }
}
