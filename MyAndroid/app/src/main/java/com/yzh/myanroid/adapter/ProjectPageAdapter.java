package com.yzh.myanroid.adapter;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.yzh.myanroid.util.MyBundle;
import com.yzh.myanroid.view.Fragment.PageFragment;
import com.yzh.myanroid.view.Fragment.ProjectFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目模块
 */

public class ProjectPageAdapter extends FragmentPagerAdapter {
    private static ProjectPageAdapter mProjectPageAdapter;
    private List<PageFragment> mPageFragments;
    private ProjectFragment mProjectFragment;

    public static ProjectPageAdapter getInstance( ProjectFragment mProjectFragment) {
        if(mProjectPageAdapter ==null){
            synchronized (ProjectPageAdapter.class){
                if(mProjectPageAdapter ==null){
                    mProjectPageAdapter = new ProjectPageAdapter( mProjectFragment);
                }
            }
        }
        return mProjectPageAdapter;
    }

    private ProjectPageAdapter( ProjectFragment mProjectFragment) {
        super(mProjectFragment.getChildFragmentManager());
        mPageFragments = new ArrayList<>();
        this.mProjectFragment = mProjectFragment;
    }



    public void initView(int number){
        for(int i=0;i<number;i++){
            PageFragment pageFragment = new PageFragment();
            pageFragment.setArguments(MyBundle.getMyBundle().put("url", mProjectFragment.getmUrls().get(i))
                    .put("chapterName", mProjectFragment.getmTabNames().get(i)).build());
            mPageFragments.add(pageFragment);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return mPageFragments.get(i);
    }

    @Override
    public int getCount() {
        return mPageFragments.size();
    }

}


