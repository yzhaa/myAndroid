package com.yzh.myanroid.controller.Do;

import com.yzh.myanroid.Bean.System;
import com.yzh.myanroid.Bean.SystemTag;
import com.yzh.myanroid.controller.asy.DBAsy;
import com.yzh.myanroid.adapter.SystemAda;
import com.yzh.myanroid.db.MyData;
import com.yzh.myanroid.util.CheakNetwork;
import com.yzh.myanroid.util.HttpURlConnectionUtill;
import com.yzh.myanroid.util.JsonParase;
import com.yzh.myanroid.view.Fragment.SystemMainFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class loadSystem implements DBCInterface {
    private SystemMainFragment mSystemMainFragment;
    private List<List<SystemTag>> mListList;
    private List<System> mSystemList;
    private boolean mIsStorage;

    public loadSystem(SystemMainFragment mSystemMainFragment) {
        this.mSystemMainFragment = mSystemMainFragment;
    }

    @Override
    public void doSome() {
        loadFromLocal();
        if(mSystemList.size()==0|| mListList.size()==0){
            loadFromWeb();
            mIsStorage = true;
        }
    }

    @Override
    public void result() {
        if (mSystemList.size() > 0 && mListList != null && mListList.size() > 0) {
            SystemAda.getmSystemAda().set(mSystemList, mListList, mSystemMainFragment);
            SystemAda.getmSystemAda().initView();
        }
        if(mIsStorage){
            new DBAsy(new Storage(System.class, null, mSystemList)).execute();
            for (List<SystemTag> tagList:mListList) {
                new DBAsy(new Storage(SystemTag.class, null,tagList)).execute();
            }
        }
    }

    private void loadFromLocal(){
        mSystemList = MyData.read(System.class, null, null);
        mListList = new ArrayList<>();
        if (mSystemList.size() != 0) {
            for (System s : mSystemList) {
                String[] strings = {s.getId()};
                mListList.add(MyData.read(SystemTag.class, " parentChapterId=?", strings));
            }
        }
    }

    private void loadFromWeb() {
        if (CheakNetwork.cheakNetAndSend()) {
            String contence = HttpURlConnectionUtill.getStringResult("https://www.wanandroid.com/tree/json");
            try {
                mSystemList = JsonParase.getResult(contence, "data", System.class);
                JSONArray jsonArray = new JSONObject(contence).getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    mListList.add(JsonParase.getResult(jsonArray.getJSONObject(i).toString(), "children", SystemTag.class));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
