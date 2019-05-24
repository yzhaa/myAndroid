package com.yzh.myanroid.adapter;


import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.yzh.myanroid.R;
import com.yzh.myanroid.util.ActivityCollector;
import com.yzh.myanroid.util.MyApplication;
import com.yzh.myanroid.view.Activity.WebActivity;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BannerAdaper extends PagerAdapter {
    private List<Map<String,Object>> mapList=new ArrayList<>();

    public void setMapList(List<Map<String, Object>> mapList) {
        this.mapList = mapList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mapList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return o==view;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Map map = mapList.get(position);
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.activity_main_banner, container, false);
        final ImageView imageView = view.findViewById(R.id.banner_iv);
        TextView textView = view.findViewById(R.id.banner_tv);
        container.addView(view);
        textView.setText((String)map.get("title"));
        imageView.setImageBitmap((Bitmap) map.get("bitmap"));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getContext(), WebActivity.class);
                intent.putExtra("url",(String) map.get("url"));
                ActivityCollector.getCurrentActivity().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      container.removeView((View) object);
    }
}
