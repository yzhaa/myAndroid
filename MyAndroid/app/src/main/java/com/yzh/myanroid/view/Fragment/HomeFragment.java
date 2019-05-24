package com.yzh.myanroid.view.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yzh.myanroid.R;
import com.yzh.myanroid.adapter.BannerAdaper;
import com.yzh.myanroid.controller.BaseContrloler;
import com.yzh.myanroid.util.Constant;
import com.yzh.myanroid.view.CurrencyView;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements ArticleFragment {
    private ViewPager mViewPager;
    private int mCurrentItemId;
    private Timer mTimer;
    private int mViewPagerLength;
    private Handler mHandler;
    private CurrencyView mCurrencyView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHandler = new Myhandler();
        View view = inflater.inflate(R.layout.framgent_home, container, false);
        mCurrencyView = new CurrencyView();
        mCurrencyView.initView(view, Constant.GET, "https://www.wanandroid.com/article/list/?/json", null, null);
        mViewPager = view.findViewById(R.id.vp);
        mViewPager.setAdapter(new BannerAdaper());
        BaseContrloler.loadBanner(mViewPager);
        return view;
    }


    private class Myhandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (mCurrentItemId < mViewPagerLength-1) {
                    mCurrentItemId += 1;
                    mViewPager.setCurrentItem(mCurrentItemId);
                } else mCurrentItemId = 0;
                mViewPager.setCurrentItem(mCurrentItemId);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(mViewPager.getAdapter()!=null){
                    mViewPagerLength = mViewPager.getAdapter().getCount();
                    mCurrentItemId = mViewPager.getCurrentItem();
                }
                mHandler.sendEmptyMessage(1);
            }
        }, 0, 2000);
    }


    @Override
    public void onPause() {
        super.onPause();
        mTimer.cancel();
    }

    @Override
    public void onStop() {
        super.onStop();
        mTimer.cancel();
    }

    public CurrencyView getmCurrencyView() {
        return mCurrencyView;
    }
}
