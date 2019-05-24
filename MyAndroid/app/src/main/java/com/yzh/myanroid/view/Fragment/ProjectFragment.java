package com.yzh.myanroid.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzh.myanroid.R;
import com.yzh.myanroid.adapter.ProjectPageAdapter;
import com.yzh.myanroid.controller.BaseContrloler;

import java.util.List;


public class ProjectFragment extends Fragment {
    private List<String> mUrls;
    private TabLayout mTabLayout;
    private ViewPager mViewPage;
    private List<String> mTabNames;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_project, container, false);
        mTabLayout = view.findViewById(R.id.project_tl);
        mViewPage = view.findViewById(R.id.project_vp);
        mViewPage.setAdapter(ProjectPageAdapter.getInstance(this));
        BaseContrloler.loadProjectFrament(this);
        super.onCreate(savedInstanceState);
        mViewPage.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPage));
        return view;

    }



    public void setmUrls(List<String> mUrls) {
        this.mUrls = mUrls;
    }

    public void setTabNames(List<String> mTabNames){
        this.mTabNames = mTabNames;
    }

    public List<String> getmTabNames() {
        return mTabNames;
    }


    public TabLayout getmTabLayout() {
        return mTabLayout;
    }

    public List<String> getmUrls() {
        return mUrls;
    }

    public ViewPager getmViewPage() {
        return mViewPage;
    }
}
