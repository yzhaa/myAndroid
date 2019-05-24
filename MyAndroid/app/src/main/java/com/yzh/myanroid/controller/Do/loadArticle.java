package com.yzh.myanroid.controller.Do;

import android.widget.HeaderViewListAdapter;

import com.yzh.myanroid.Bean.Article;
import com.yzh.myanroid.Bean.MainArticle;
import com.yzh.myanroid.Bean.SystemArticle;
import com.yzh.myanroid.adapter.ArticleListViewAda;
import com.yzh.myanroid.controller.asy.DBAsy;
import com.yzh.myanroid.db.MyData;
import com.yzh.myanroid.util.CheakNetwork;
import com.yzh.myanroid.util.Constant;
import com.yzh.myanroid.util.HttpURlConnectionUtill;
import com.yzh.myanroid.util.JsonParase;
import com.yzh.myanroid.view.CurrencyView;
import com.yzh.myanroid.widge.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class loadArticle implements DBCInterface {
    private List<Article> mList;
    private int mChoice;
    private int mRequest;
    private CurrencyView mCurrencyView;
    private int mPageNumber;
    private String mChapterName;
    private String[] mStrings;
    private int mType;
    private boolean mIsStorage;


    public loadArticle(int mChoice, int mRequest, CurrencyView mCurrencyView, int mPageNumber, String chapterName, String... mStrings) {
        this.mChoice = mChoice;
        this.mRequest = mRequest;
        this.mCurrencyView = mCurrencyView;
        this.mPageNumber = mPageNumber;
        this.mChapterName = chapterName;
        this.mStrings = mStrings;
    }

    @Override
    public void doSome() {
         mType = judge(mRequest, mChapterName);
        mList = loadFromLocal();
        if(mList.size()==0){
            if(CheakNetwork.cheakNetAndSend()){
                mList = loadFromWeb();
                mIsStorage = true;
            }
        }
    }


    @Override
    public void result() {
        setArticleListView(mCurrencyView, mChoice, mList);
        if(mIsStorage) {
            switch (mType) {
                case Constant.MAIN_ARTICLE:
                    new DBAsy(new Storage(MainArticle.class, mPageNumber + "", mList)).execute();
                    break;
                case Constant.SYSTEM_ARTICLE:
                    new DBAsy(new Storage(SystemArticle.class, mPageNumber + "", mList)).execute();
                    break;
            }
        }
    }

    private int judge(int request, String chapterName) {
        if (chapterName == null && request == Constant.GET) {
            return Constant.MAIN_ARTICLE;
        } else if (chapterName != null) {
            return Constant.SYSTEM_ARTICLE;
        } else {
            return Constant.SEARCH_ARTICLE;
        }
    }

    private List<Article> loadFromLocal(){
        List<Article>list=new ArrayList<>();
        switch (mType) {
            case Constant.MAIN_ARTICLE:
                String[] s1 = {mPageNumber + ""};
                if (mChoice == Constant.UPDATE) {
                    String[] s2 = {" %置顶%"};
                    list.addAll( MyData.read(MainArticle.class, "chapterName like ?", s2));
                    list.addAll(MyData.read(MainArticle.class, "curPage=?", s1));
                } else {
                    list.addAll(MyData.read(MainArticle.class, " curPage=?", s1)) ;
                }
                break;
            case Constant.SYSTEM_ARTICLE:
                String[] s = {mPageNumber + "", mChapterName};
                list.addAll(MyData.read(SystemArticle.class, " curPage=? and chapterName=?", s));
                break;
        }
        return list;
    }

    private List<Article> loadFromWeb(){
        String url = mStrings[0].replace("/?", "/" + mPageNumber);
        try {
            List<Article> articleList;
            JSONObject jsonObject;
            List<Article> articles;
            if (mType ==Constant.SEARCH_ARTICLE) {
                String[] k = {"k"};
                String[] v = {mStrings[1]};
                jsonObject = new JSONObject(HttpURlConnectionUtill.getStringResultByPost(url,k,v));
                return JsonParase.getResult(jsonObject.getString("data"), "datas", Article.class);
            }
            else {
                jsonObject = new JSONObject(HttpURlConnectionUtill.getStringResult(url));
                articles = JsonParase.getResult(jsonObject.getString("data"), "datas", Article.class);
                if(mType ==Constant.MAIN_ARTICLE) {
                    if (mChoice == Constant.UPDATE) {
                        articleList = JsonParase.getResult((HttpURlConnectionUtill.getStringResult(mStrings[2])), "data", Article.class);
                        if (articleList != null) {
                            for (int i = 0; i < articleList.size(); i++) {
                                Article article = articleList.get(i);
                                article.setChapterName("置顶文章/" + article.getChapterName());
                            }
                            if (articles != null) {
                                articleList.addAll(articles);
                                return articleList;
                            }
                        }
                    } else return articles;
                } else return articles;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void setArticleListView(CurrencyView currencyViewt, int choice, List<? extends Article> articles) {
        MyListView myListView = currencyViewt.getmMyListView();
        if (myListView != null&&articles.size()>0) {
            ArticleListViewAda adapter = (ArticleListViewAda) ((HeaderViewListAdapter) myListView.getAdapter()).getWrappedAdapter();
            if (choice == Constant.ADD) {
                adapter.getmList().addAll(articles);
                adapter.notifyDataSetChanged();
                myListView.hideFootView();
            } else if (choice == Constant.UPDATE) {
                adapter.getmList().clear();
                adapter.getmList().addAll(articles);
                adapter.notifyDataSetChanged();
                myListView.close();
            }
        }
    }
}
