package com.yzh.myanroid.adapter;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yzh.myanroid.Bean.System;
import com.yzh.myanroid.Bean.SystemTag;
import com.yzh.myanroid.R;
import com.yzh.myanroid.util.Constant;
import com.yzh.myanroid.util.MyApplication;
import com.yzh.myanroid.util.MyBundle;
import com.yzh.myanroid.view.Fragment.SystemFragment;
import com.yzh.myanroid.view.Fragment.SystemMainFragment;
import com.yzh.myanroid.widge.FlowGroup;


import java.util.List;

/**
 * 体系下的布局
 */
public class SystemAda {
    private static SystemAda mSystemAda;

    private List<System> mSystemList;
    private List<List<SystemTag>> mSystemTagList;
    private SystemMainFragment mSystemMainFragment;

    public static SystemAda getmSystemAda(){
        if(mSystemAda ==null){
            synchronized (SystemAda.class){
                if(mSystemAda ==null){
                    mSystemAda = new SystemAda();
                }
            }
        }
        return mSystemAda;
    }



    public void set( List<System> systemList,List<List<SystemTag>> systemTagList,SystemMainFragment systemMainFragment){
        this.mSystemList = systemList;
        this.mSystemTagList = systemTagList;
        this.mSystemMainFragment = systemMainFragment;
    }

    public void initView() {
        LinearLayout.LayoutParams spra = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 3);
        LinearLayout.LayoutParams lptitle = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lpitag = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lptitle.setMargins(10, 10, 10, 10);
        lpitag.setMargins(10, 10, 10, 10);
        FlowGroup flowGroup = mSystemMainFragment.getmFlowGroup();
        for (int i = 0; i < mSystemList.size(); i++) {
            System system = mSystemList.get(i);
            TextView tvtitle = new TextView(MyApplication.getmContext());
            tvtitle.setText(system.getName());
            tvtitle.setTextSize(24);
            tvtitle.setGravity(Gravity.CENTER);
            tvtitle.setLayoutParams(lptitle);
            flowGroup.addView(tvtitle);
            List<SystemTag> systemTags = mSystemTagList.get(i);
            for (int j = 0; j < systemTags.size(); j++) {
                SystemTag systemTag = systemTags.get(j);
                final String tag = systemTag.getName();
                final String id = systemTag.getId();
                TextView tvtage = new TextView(MyApplication.getmContext());
                tvtage.setText(tag);
                tvtage.setTextSize(16);
                tvtage.setBackgroundResource(R.drawable.shape);
                tvtage.setLayoutParams(lpitag);
                tvtage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mSystemMainFragment.getParentFragment() != null) {
                            ((SystemFragment) mSystemMainFragment.getParentFragment()).replece(MyBundle.getMyBundle()
                                    .put("url", Constant.ARTICLE_ITEM_URL + id).put("tag", tag).build());
                        }
                    }
                });
                flowGroup.addView(tvtage);
            }
            View view = new View(MyApplication.getmContext());
            view.setLayoutParams(spra);
            flowGroup.addView(view);
        }
    }


}
