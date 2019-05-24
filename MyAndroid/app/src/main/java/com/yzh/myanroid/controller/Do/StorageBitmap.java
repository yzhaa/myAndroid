package com.yzh.myanroid.controller.Do;

import android.graphics.Bitmap;
import com.yzh.myanroid.db.BitmapStroge;
import com.yzh.myanroid.db.MyData;
import java.util.List;
import java.util.Map;

public class StorageBitmap implements DBCInterface {
    private List<Map<String, Object>> mMaps;
    private List mList;
    private Class mClass;
    private String mPage;


    public StorageBitmap(List<Map<String, Object>> mMaps, List mList, Class mClass, String mPage) {
        this.mMaps = mMaps;
        this.mList = mList;
        this.mClass = mClass;
        this.mPage = mPage;
    }

    @Override
    public void doSome() {
        if(mList !=null&& mClass !=null) {
            MyData.write(mList, mClass, mPage);
        }
        if(mMaps !=null){
            for (Map m: mMaps) {
                BitmapStroge.readASFile((Bitmap) m.get("bitmap"),(String) m.get("id"));
            }
        }
    }

    @Override
    public void result() {

    }
}
