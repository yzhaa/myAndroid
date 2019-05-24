package com.yzh.myanroid.controller.asy;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.HeaderViewListAdapter;


import com.yzh.myanroid.Bean.ProjectArticle;
import com.yzh.myanroid.controller.Do.Storage;
import com.yzh.myanroid.controller.Do.StorageBitmap;
import com.yzh.myanroid.adapter.PageListViewAda;
import com.yzh.myanroid.db.BitmapStroge;
import com.yzh.myanroid.db.MyData;
import com.yzh.myanroid.util.CheakNetwork;
import com.yzh.myanroid.util.Constant;
import com.yzh.myanroid.util.HttpURlConnectionUtill;
import com.yzh.myanroid.util.JsonParase;
import com.yzh.myanroid.view.Fragment.PageFragment;
import com.yzh.myanroid.widge.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProjectPageAsy extends AsyncTask<String,Void,  Void> {
    private PageFragment pageFragment;
    private int choice;
    private String page;
    private List<ProjectArticle> list;
    private String url;
    private List<Map<String, Object>> mapList = new ArrayList<>();
    private boolean isStroage;


   public ProjectPageAsy(PageFragment pageFragment,int choice) {
        this.choice = choice;
        this.pageFragment = pageFragment;
    }

    @Override
    protected  Void doInBackground(String... strings) {
        page = pageFragment.getmPageNumber()+"";
        url = pageFragment.getmUrl().replace("/?", "/" + page);
        loadFromLocal();
        if(mapList.size()==0){
            if(CheakNetwork.cheakNetAndSend()){
                loadFromWeb();
                isStroage = true;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        if(mapList.size()>0) {
            loadPageListView(pageFragment.getmListView(), choice, mapList);
        }
        if(isStroage) {
            new DBAsy(new Storage(ProjectArticle.class,page,list)).execute();
            new DBAsy(new StorageBitmap(mapList, list, ProjectArticle.class, page)).execute();
        }
    }


    private void loadFromLocal(){
        String[] strings = {pageFragment.getChapterName(), page};
        list = MyData.read(ProjectArticle.class, "chapterName=? and curPage =?", strings);
        if (list.size() != 0) {
            for (ProjectArticle p : list) {
                Map<String, Object> map = new HashMap<>();
                Bitmap bitmap = BitmapStroge.getBitmap( p.getChapterName() + page + p.getId());
                if(bitmap!=null){
                    map.put("bitmap", bitmap);
                }
                map.put("title", p.getTitle());
                map.put("desc", p.getDesc());
                map.put("link", p.getLink());
                map.put("id", p.getId());
                map.put("page", pageFragment.getmPageNumber() + "");
                mapList.add(map);
            }
        }
    }

    private void loadFromWeb(){
       if(CheakNetwork.cheakNetAndSend()){
        try {
            JSONObject jsonObject = new JSONObject(HttpURlConnectionUtill.getStringResult(url));
            list = JsonParase.getResult(jsonObject.getString("data"), "datas", ProjectArticle.class);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    ProjectArticle projectArticle = list.get(i);
                    Map<String, Object> map = new HashMap<>();
                    String url = projectArticle.getEnvelopePic();
                    InputStream is = HttpURlConnectionUtill.getStreamResult(url);
                    map.put("bitmap", BitmapFactory.decodeStream(is));
                    map.put("title", projectArticle.getTitle());
                    map.put("desc", projectArticle.getDesc());
                    map.put("link", projectArticle.getLink());
                    map.put("id", projectArticle.getChapterName() + page + projectArticle.getId());
                    mapList.add(map);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
       }
    }

   private  void loadPageListView(MyListView myListView,int choice,List<Map<String ,Object>> maps){
       PageListViewAda adapter = (PageListViewAda) ((HeaderViewListAdapter) myListView.getAdapter()).getWrappedAdapter();
        List<Map<String, Object>> mapList = adapter.getMapList();
        if(choice== Constant.ADD){
            mapList.addAll(maps);
            adapter.notifyDataSetChanged();
            myListView.hideFootView();
        }
        else if(choice==Constant.UPDATE){
            mapList.clear();
            mapList.addAll(maps);
            adapter.notifyDataSetChanged();
            myListView.close();
        }
    }
}
