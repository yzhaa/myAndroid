package com.yzh.myanroid.view.Activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yzh.myanroid.broadcastReceiver.NetBroadcastReceiver;
import com.yzh.myanroid.util.ActivityCollector;
import com.yzh.myanroid.util.CheakNetwork;
import com.yzh.myanroid.util.Constant;
import com.yzh.myanroid.util.MyToast;


public class BaseActivity extends AppCompatActivity implements NetBroadcastReceiver.HanderNet{

    private IntentFilter mIntentFilter;
    private NetBroadcastReceiver mNetBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActivityCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mIntentFilter.addAction(Constant.No_Net);
        mNetBroadcastReceiver = new NetBroadcastReceiver(this);
        registerReceiver(mNetBroadcastReceiver, mIntentFilter);
        hander(CheakNetwork.getNetworkState());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        unregisterReceiver(mNetBroadcastReceiver);
    }


    @Override
    public void finish() {
        super.finish();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void hander(int state) {
        if (state == Constant.NETWORK_NONE) {
            MyToast.buildToast("没网");
        }
    }

}

