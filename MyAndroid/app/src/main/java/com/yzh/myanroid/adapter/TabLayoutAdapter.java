package com.yzh.myanroid.adapter;

import android.support.design.widget.TabLayout;

import com.yzh.myanroid.Bean.Project;

import java.util.List;

public class TabLayoutAdapter {
    private static TabLayoutAdapter tabLayoutAdapter;
    private TabLayout tabLayout;
    private List<Project> list;

    public static TabLayoutAdapter getInstance() {
        if(tabLayoutAdapter==null){
            synchronized (TabLayoutAdapter.class){
                if(tabLayoutAdapter==null){
                    tabLayoutAdapter = new TabLayoutAdapter();
                }
            }
        }
        return tabLayoutAdapter;
    }

    public void set(TabLayout tabLayout, List<Project> list){
        this.tabLayout = tabLayout;
        this.list = list;
    }

    public void initTabLayout() {
        for (Project p : list) {
            tabLayout.addTab(tabLayout.newTab().setText(p.getName()));
        }

    }
}
