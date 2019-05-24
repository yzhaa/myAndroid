package com.yzh.myanroid.controller.asy;


import android.os.AsyncTask;
import com.yzh.myanroid.Bean.HotKey;
import com.yzh.myanroid.adapter.HotSearchAda;
import com.yzh.myanroid.controller.Do.Storage;
import com.yzh.myanroid.db.MyData;
import com.yzh.myanroid.util.CheakNetwork;
import com.yzh.myanroid.util.HttpURlConnectionUtill;
import com.yzh.myanroid.util.JsonParase;

import java.util.List;

public class HotLoadAsy  extends AsyncTask<Void,Void, List<HotKey>> {
    private boolean mIsStorage;
    @Override
    protected List<HotKey> doInBackground(Void... voids) {
        List<HotKey> list = MyData.read(HotKey.class, null, null);
        if(list.size()==0){
           if(CheakNetwork.cheakNetAndSend()) {
               list = JsonParase.getResult(HttpURlConnectionUtill.getStringResult("https://www.wanandroid.com//hotkey/json"), "data", HotKey.class);
               mIsStorage = true;
           }
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<HotKey> hotKeys) {
        HotSearchAda.setList(hotKeys);
        HotSearchAda.init();
        if(mIsStorage) {
            new DBAsy(new Storage(HotKey.class, null, hotKeys)).execute();
        }
    }
}
