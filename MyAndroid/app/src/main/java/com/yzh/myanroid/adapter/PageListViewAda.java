package com.yzh.myanroid.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yzh.myanroid.R;
import com.yzh.myanroid.util.ActivityCollector;
import com.yzh.myanroid.util.MyApplication;
import com.yzh.myanroid.view.Activity.WebActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageListViewAda extends BaseAdapter {
    private List<Map<String, Object>> mapList;


    private class HandleItem {
        ImageView imageView;
        TextView textTitle;
        TextView textDesc;

        private HandleItem(ImageView imageView, TextView textTitle, TextView textDesc) {
            this.imageView = imageView;
            this.textTitle = textTitle;
            this.textDesc = textDesc;
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public PageListViewAda() {
        mapList = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.project_item, parent, false);
            ImageView imageView = convertView.findViewById(R.id.frame_iv);
            TextView textTitle = convertView.findViewById(R.id.frame_tv_title);
            TextView textDesc = convertView.findViewById(R.id.frame_tv_desc);
            HandleItem handleItem = new HandleItem(imageView, textTitle, textDesc);
            convertView.setTag(handleItem);
        }
        final Map<String, Object> map = mapList.get(position);
        HandleItem handleItem = (HandleItem) convertView.getTag();
        handleItem.imageView.setImageBitmap((Bitmap) map.get("bitmap"));
        handleItem.textTitle.setText((String) map.get("title"));
        handleItem.textDesc.setText((String) map.get("desc"));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getContext(), WebActivity.class);
                intent.putExtra("url", (String) map.get("link"));
                ActivityCollector.getCurrentActivity().startActivity(intent);
            }
        });
        return convertView;
    }

    public List<Map<String, Object>> getMapList() {
        return mapList;
    }

    @Override
    public int getCount() {
        return mapList.size();
    }

    @Override
    public Object getItem(int position) {
        return mapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}




