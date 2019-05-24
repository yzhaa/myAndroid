package com.yzh.myanroid.controller.Do;

import com.yzh.myanroid.Bean.Project;
import com.yzh.myanroid.controller.asy.DBAsy;
import com.yzh.myanroid.adapter.ProjectPageAdapter;
import com.yzh.myanroid.adapter.TabLayoutAdapter;
import com.yzh.myanroid.db.MyData;
import com.yzh.myanroid.util.CheakNetwork;
import com.yzh.myanroid.util.HttpURlConnectionUtill;
import com.yzh.myanroid.util.JsonParase;
import com.yzh.myanroid.view.Fragment.ProjectFragment;

import java.util.ArrayList;
import java.util.List;

public class loadProjectFragment implements DBCInterface {
    private ProjectFragment mProjectFragment;
    private List<Project> mList;
    private boolean mIsStorage;

    public loadProjectFragment(ProjectFragment mProjectFragment) {
        this.mProjectFragment = mProjectFragment;
    }

    @Override
    public void doSome() {
       mList = MyData.read(Project.class, null, null);
       if(mList.size()==0){
           if(CheakNetwork.cheakNetAndSend()) {
               mList = JsonParase.getResult(HttpURlConnectionUtill.getStringResult("https://www.wanandroid.com/project/tree/json"), "data", Project.class);
               mIsStorage = true;
           }
       }
       setUrlAndName(mProjectFragment, mList);
    }

    @Override
    public void result() {
        if (mList !=null&& mList.size() != 0) {
            TabLayoutAdapter.getInstance().set(mProjectFragment.getmTabLayout(), mList);
            TabLayoutAdapter.getInstance().initTabLayout();
            ProjectPageAdapter.getInstance(mProjectFragment).initView(mList.size());
        } else throw new RuntimeException("哪里都加载不到");
        if(mIsStorage){
            new DBAsy(new Storage(Project.class,null, mList)).execute();
        }
    }

    private   void setUrlAndName(ProjectFragment projectFragment, List<Project> list) {
        if (projectFragment != null && list != null && list.size() > 0) {
            List<String> urls = new ArrayList<>();
            List<String> chapterName = new ArrayList<>();
            for (Project p : list) {
                urls.add("https://www.wanandroid.com/project/list/?/json?cid=" + p.getId());
                chapterName.add(p.getName());
            }
            projectFragment.setmUrls(urls);
            projectFragment.setTabNames(chapterName);
        }
    }
}
