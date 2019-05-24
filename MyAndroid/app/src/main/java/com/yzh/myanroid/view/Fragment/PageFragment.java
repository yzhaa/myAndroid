package com.yzh.myanroid.view.Fragment;


import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;


import com.yzh.myanroid.R;

import com.yzh.myanroid.adapter.PageListViewAda;

import com.yzh.myanroid.controller.BaseContrloler;
import com.yzh.myanroid.util.Constant;
import com.yzh.myanroid.widge.MyListView;

public class PageFragment extends Fragment {

    private MyListView mListView;
    private int mPageNumber;
    private  String mUrl;
    private View rootView;
    private PageListViewAda pageListViewAda;
    private String chapterName;

    public PageFragment() {
        this.mPageNumber = 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView!=null){
            return rootView;
        }else {
            View view = inflater.inflate(R.layout.fragment_page, container, false);
            mListView = view.findViewById(R.id.currency_lv_article);
            SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.currency_srl_article);
            pageListViewAda = new PageListViewAda();
            mListView.setSwipeRefreshLayout(swipeRefreshLayout);
            mListView.setAdapter(pageListViewAda);
            Bundle bundle = getArguments();
            if(bundle!=null){
                mUrl = bundle.getString("url");
                chapterName = bundle.getString("chapterName");
            }
            add();
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    update();
                }
            });
            rootView = view;
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL && mListView.getLastVisiblePosition() == mListView.getCount() - 1&&!mListView.isState()) {
                    mListView.showFootView();
                   add();
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        return rootView;
    }


    public MyListView getmListView() {
        return mListView;
    }

    public int getmPageNumber() {
        return mPageNumber;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void update() {
        BaseContrloler.loadProjectPage(this, Constant.UPDATE);
        mPageNumber++;
    }

    public void add() {
        BaseContrloler.loadProjectPage(this, Constant.ADD);
        mPageNumber++;
    }

}
