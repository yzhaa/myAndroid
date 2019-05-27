package com.yzh.myanroid.view;


import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.yzh.myanroid.Bean.Article;
import com.yzh.myanroid.R;
import com.yzh.myanroid.adapter.ArticleListViewAda;
import com.yzh.myanroid.controller.BaseContrloler;
import com.yzh.myanroid.util.Constant;
import com.yzh.myanroid.util.MyBundle;
import com.yzh.myanroid.util.MyIntent;
import com.yzh.myanroid.view.Activity.WebActivity;
import com.yzh.myanroid.widge.MyListView;

public class CurrencyView  {

    private MyListView mMyListView;
    private int mPageNumber;
    private  String mArticleUrl;
    private int mRequest;
    private String mKeyWord;
    private String mChapterName;

    public void initView(View view,int request,String url,String keyWord,String chapterName){
        this.mRequest = request;
        this.mKeyWord = keyWord;
        this.mArticleUrl = url;
        this.mChapterName = chapterName;
        mMyListView = view.findViewById(R.id.currency_lv_article);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.currency_srl_article);
        final ArticleListViewAda adapter = new ArticleListViewAda();
        mMyListView.setAdapter(adapter);
        mMyListView.setSwipeRefreshLayout(swipeRefreshLayout);
        update();
        mMyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyIntent.startIntent(WebActivity.class,
                    MyBundle.getMyBundle().put("url",((Article) adapter.getItem(position)).getLink()).build());
        }
    });

        mMyListView.setOnScrollListener(new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == SCROLL_STATE_TOUCH_SCROLL && mMyListView.getLastVisiblePosition() == mMyListView.getCount() - 1&&!mMyListView.isState()) {
                mMyListView.showFootView();
                add();
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            update();
        }
    });

    }

    private void update(){
        BaseContrloler.loadArticle(Constant.UPDATE, mRequest, this, mPageNumber, mChapterName,
                mArticleUrl, mKeyWord,Constant.TOP_ARTICLE_URL);
        mPageNumber++;
    }

    public void add(){
        BaseContrloler.loadArticle(Constant.ADD, mRequest, this, mPageNumber, mChapterName,
                mArticleUrl, mKeyWord,Constant.TOP_ARTICLE_URL);

        mPageNumber++;
    }

    public MyListView getmMyListView() {
        return mMyListView;
    }
}
