package com.yzh.myanroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yzh.myanroid.Bean.Article;
import com.yzh.myanroid.R;
import com.yzh.myanroid.util.MyApplication;
import java.util.ArrayList;
import java.util.List;

public class ArticleListViewAda extends BaseAdapter {
    private List<Article> mList;


    private  class HandleItem{
        TextView tvAuthor;
        TextView tvTitle;
        TextView textType;
        TextView textTime;
        private HandleItem(TextView tvAuthor , TextView tvTitle,TextView textType,TextView textTime) {
            this.tvTitle = tvTitle;
            this.textTime = textTime;
            this.textType = textType;
            this.tvAuthor = tvAuthor;
        }
    }

    public List<Article> getmList() {
        return mList;
    }

    public ArticleListViewAda() {
        mList = new ArrayList<>();
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        Article article = mList.get(position);
        if(convertView!=null){
            view = convertView;
        }
        else {
            view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.article_item, parent, false);
            TextView tvTitle = view.findViewById(R.id.aritcle_tv_title);
            TextView tvAuthor = view.findViewById(R.id.aritcle_tv_author);
            TextView textType = view.findViewById(R.id.aritcle_tv_type);
            TextView textTime = view.findViewById(R.id.aritcle_tv_time);
            view.setTag(new HandleItem(tvAuthor, tvTitle, textType, textTime));
        }
        Context context=MyApplication.getContext();
        HandleItem handleItem = (HandleItem) view.getTag();
        handleItem.tvAuthor.setText(context.getString(R.string.ArticleAuthor, article.getAuthor()));
        handleItem.textType.setText(context.getString(R.string.ArticleType, article.getChapterName()));
        handleItem.textTime.setText(context.getString(R.string.ArticleTime,article.getNiceDate()));
        handleItem.tvTitle.setText(context.getString(R.string.ArticleTitle,article.getTitle()));
        return view;
    }

}
