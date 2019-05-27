package com.yzh.myanroid.adapter;

import android.support.design.widget.TabLayout;

import com.yzh.myanroid.Bean.Project;

import java.util.List;

/**
 * 项目模块中的tab
 */

public class TabLayoutAdapter {
    private static TabLayoutAdapter mTabLayoutAdapter;
    private TabLayout mTabLayout;
    private List<Project> mList;

    public static TabLayoutAdapter getInstance() {
        if(mTabLayoutAdapter ==null){
            synchronized (TabLayoutAdapter.class){
                if(mTabLayoutAdapter ==null){
                    mTabLayoutAdapter = new TabLayoutAdapter();
                }
            }
        }
        return mTabLayoutAdapter;
    }

    public void set(TabLayout tabLayout, List<Project> list){
        this.mTabLayout = tabLayout;
        this.mList = list;
    }

    public void initTabLayout() {
        for (Project p : mList) {
            mTabLayout.addTab(mTabLayout.newTab().setText(p.getName()));
        }

    }
}
