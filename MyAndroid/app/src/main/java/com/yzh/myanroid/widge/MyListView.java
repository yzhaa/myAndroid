package com.yzh.myanroid.widge;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.yzh.myanroid.R;

public class MyListView extends ListView {
    private View mFootView;
    private Context context;
    private boolean state;
    private SwipeRefreshLayout swipeRefreshLayout;


    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isState() {
        return state;
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initFootView();
    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public void hideFootView(){
        mFootView.measure(0,0);
        mFootView.setPadding(0,0,0,-mFootView.getMeasuredHeight());
        state = false;
    }
    public void showFootView(){
        mFootView.measure(0,0);
        mFootView.setPadding(0,0,0,mFootView.getMeasuredHeight());
        state = true;
    }


    private void initFootView() {
        mFootView = LayoutInflater.from(context).inflate(R.layout.listview_foot, null);
        addFooterView(mFootView);

    }
    public void close(){
        swipeRefreshLayout.setRefreshing(false);
    }

}
