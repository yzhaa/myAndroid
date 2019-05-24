package com.yzh.myanroid.adapter;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yzh.myanroid.Bean.System;
import com.yzh.myanroid.Bean.SystemTag;
import com.yzh.myanroid.R;
import com.yzh.myanroid.util.MyApplication;
import com.yzh.myanroid.util.MyBundle;
import com.yzh.myanroid.view.Fragment.SystemFragment;
import com.yzh.myanroid.view.Fragment.SystemMainFragment;
import com.yzh.myanroid.widge.FlowGroup;


import java.util.List;

public class SystemAda {
    private static SystemAda systemAda;

    private List<System> systemList;
    private List<List<SystemTag>> systemTagList;
    private SystemMainFragment systemMainFragment;

    public static SystemAda getSystemAda(){
        if(systemAda==null){
            synchronized (SystemAda.class){
                if(systemAda==null){
                    systemAda = new SystemAda();
                }
            }
        }
        return systemAda;
    }



    public void set( List<System> systemList,List<List<SystemTag>> systemTagList,SystemMainFragment systemMainFragment){
        this.systemList = systemList;
        this.systemTagList = systemTagList;
        this.systemMainFragment = systemMainFragment;
    }

    public void initView() {
        LinearLayout.LayoutParams spra = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 3);
        LinearLayout.LayoutParams lptitle = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lpitag = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lptitle.setMargins(10, 10, 10, 10);
        lpitag.setMargins(10, 10, 10, 10);
        FlowGroup flowGroup = systemMainFragment.getmFlowGroup();
        for (int i = 0; i < systemList.size(); i++) {
            System system = systemList.get(i);
            TextView tvtitle = new TextView(MyApplication.getContext());
            tvtitle.setText(system.getName());
            tvtitle.setTextSize(24);
            tvtitle.setGravity(Gravity.CENTER);
            tvtitle.setLayoutParams(lptitle);
            flowGroup.addView(tvtitle);
            List<SystemTag> systemTags = systemTagList.get(i);
            for (int j = 0; j < systemTags.size(); j++) {
                SystemTag systemTag = systemTags.get(j);
                final String tag = systemTag.getName();
                final String id = systemTag.getId();
                TextView tvtage = new TextView(MyApplication.getContext());
                tvtage.setText(tag);
                tvtage.setTextSize(16);
                tvtage.setBackgroundResource(R.drawable.shape);
                tvtage.setLayoutParams(lpitag);
                tvtage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (systemMainFragment.getParentFragment() != null) {
                            ((SystemFragment) systemMainFragment.getParentFragment()).replece(MyBundle.getMyBundle()
                                    .put("url", "https://www.wanandroid.com/article/list/?/json?cid=" + id).put("tag", tag).build());
                        }
                    }
                });
                flowGroup.addView(tvtage);
            }
            View view = new View(MyApplication.getContext());
            view.setLayoutParams(spra);
            flowGroup.addView(view);
        }
    }


}
