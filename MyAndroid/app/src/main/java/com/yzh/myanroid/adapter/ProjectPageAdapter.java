package com.yzh.myanroid.adapter;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.yzh.myanroid.util.MyBundle;
import com.yzh.myanroid.view.Fragment.PageFragment;
import com.yzh.myanroid.view.Fragment.ProjectFragment;
import java.util.ArrayList;
import java.util.List;



public class ProjectPageAdapter extends FragmentPagerAdapter {
    private static ProjectPageAdapter projectPageAdapter;
    private List<PageFragment> pageFragments;
    private ProjectFragment mProjectFragment;

    public static ProjectPageAdapter getInstance( ProjectFragment mProjectFragment) {
        if(projectPageAdapter==null){
            synchronized (ProjectPageAdapter.class){
                if(projectPageAdapter==null){
                    projectPageAdapter = new ProjectPageAdapter( mProjectFragment);
                }
            }
        }
        return projectPageAdapter;
    }

    private ProjectPageAdapter( ProjectFragment mProjectFragment) {
        super(mProjectFragment.getChildFragmentManager());
        pageFragments = new ArrayList<>();
        this.mProjectFragment = mProjectFragment;
    }



    public void initView(int number){
        for(int i=0;i<number;i++){
            PageFragment pageFragment = new PageFragment();
            pageFragment.setArguments(MyBundle.getMyBundle().put("url", mProjectFragment.getmUrls().get(i))
                    .put("chapterName", mProjectFragment.getmTabNames().get(i)).build());
            pageFragments.add(pageFragment);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return pageFragments.get(i);
    }

    @Override
    public int getCount() {
        return pageFragments.size();
    }

}


