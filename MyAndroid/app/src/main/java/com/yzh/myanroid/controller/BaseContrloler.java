package com.yzh.myanroid.controller;

import android.support.v4.view.ViewPager;
import com.yzh.myanroid.controller.Do.loadArticle;
import com.yzh.myanroid.controller.Do.loadProjectFragment;
import com.yzh.myanroid.controller.Do.loadSystem;
import com.yzh.myanroid.controller.Do.DBCInterface;
import com.yzh.myanroid.controller.asy.BannAsy;
import com.yzh.myanroid.controller.asy.DBAsy;
import com.yzh.myanroid.controller.asy.HotLoadAsy;
import com.yzh.myanroid.controller.asy.ProjectPageAsy;
import com.yzh.myanroid.view.CurrencyView;
import com.yzh.myanroid.view.Fragment.PageFragment;
import com.yzh.myanroid.view.Fragment.ProjectFragment;
import com.yzh.myanroid.view.Fragment.SystemMainFragment;


public class BaseContrloler {

    public static void loadHotKey(){
        new HotLoadAsy().execute();
    }
    public static void loadBanner(ViewPager viewPager) {
        new BannAsy(viewPager).execute();
    }

    public static void loadArticle(int choice, int request, CurrencyView currencyView, int mPageNumber, String chapterName, String... strings) {
        DBCInterface dbcInterface = new loadArticle(choice, request,currencyView,  mPageNumber, chapterName, strings);
        new DBAsy(dbcInterface).execute();
    }

    public static void loadSystem(SystemMainFragment systemMainFragment) {
        DBCInterface dbcInterface = new loadSystem(systemMainFragment);
        new DBAsy(dbcInterface).execute();
    }


    public static void loadProjectFrament(ProjectFragment projectFragment) {
        DBCInterface dbcInterface = new loadProjectFragment(projectFragment);
        new DBAsy(dbcInterface).execute();
    }

    public static void loadProjectPage(PageFragment pageFragment,int choice) {
        new ProjectPageAsy(pageFragment,choice).execute();
    }
/*
    public static void exitAccount() {
        if (!MyCookieStorge.IsState) {
            ((MainActivity) ActivityCollector.getCurrentActivity()).showOperation(3, "无账号登陆");
        } else {
            MyCookieStorge.writeCookie(false, null, null);
            MyCookieStorge.IsState = false;
            ((MainActivity) ActivityCollector.getCurrentActivity()).showOperation(3, "注销成功");
        }
    }
*/

}

