package com.yzh.myanroid.controller.asy;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import com.yzh.myanroid.Bean.Banner;
import com.yzh.myanroid.controller.Do.StorageBitmap;
import com.yzh.myanroid.adapter.BannerAdaper;
import com.yzh.myanroid.db.BitmapStroge;
import com.yzh.myanroid.db.MyData;
import com.yzh.myanroid.util.CheakNetwork;
import com.yzh.myanroid.util.HttpURlConnectionUtill;
import com.yzh.myanroid.util.JsonParase;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannAsy extends AsyncTask<Void, Void, List<Map<String, Object>>> {

    private ViewPager viewPager;
    private List<Banner> banners;

     public BannAsy(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    protected List<Map<String, Object>> doInBackground(Void... voids) {
        banners = MyData.read(Banner.class, null, null);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (banners != null && banners.size() > 0) {
            for (Banner b : banners) {
                Map<String, Object> map = new HashMap<>();
                map.put("url", b.getUrl());
                Bitmap bitmap = BitmapStroge.getBitmap(BitmapStroge.BANNER + b.getId());
                if (bitmap != null) {
                    map.put("bitmap", bitmap);
                }
                map.put("id", BitmapStroge.BANNER +b.getId());
                map.put("title", b.getTitle());
                mapList.add(map);
            }
        } else {
            if(CheakNetwork.cheakNetAndSend()) {
                banners = JsonParase.getResult(HttpURlConnectionUtill.getStringResult("https://www.wanandroid.com/banner/json"), "data", Banner.class);
                if (banners != null) {
                    for (Banner b : banners) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", BitmapStroge.BANNER + b.getId());
                        map.put("url", b.getUrl());
                        map.put("bitmap", BitmapFactory.decodeStream(HttpURlConnectionUtill.getStreamResult(b.getImagePath())));
                        map.put("title", b.getTitle());
                        mapList.add(map);
                    }
                }
            }
        }
        return mapList;
    }

    @Override
    protected void onPostExecute(final List<Map<String, Object>> maps) {
        BannerAdaper bannerAdaper = (BannerAdaper) viewPager.getAdapter();
        if (bannerAdaper != null) {
            bannerAdaper.setMapList(maps);
        }
        new DBAsy(new StorageBitmap(maps, banners, Banner.class, null)).execute();
    }
}
