package com.yzh.myanroid.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import com.yzh.myanroid.util.CheakNetwork;
import com.yzh.myanroid.util.Constant;

public class NetBroadcastReceiver extends BroadcastReceiver {
    private HanderNet mHanderNet;

    public NetBroadcastReceiver() {
    }

    public NetBroadcastReceiver(HanderNet mHanderNet) {
        this.mHanderNet = mHanderNet;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction()!=null){
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                mHanderNet.hander(CheakNetwork.getNetworkState());
            }
            else if(intent.getAction().equals(Constant.No_Net)){
                mHanderNet.hander(Constant.NETWORK_NONE);
            }
        }
    }
    public interface HanderNet{
        void hander(int state);
    }


}
