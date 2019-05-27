package com.yzh.myanroid.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
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

/**
 * 项目中碎片的listview
 */
public class PageListViewAda extends BaseAdapter {
    private List<Map<String, Object>> mMapList;


    private class HandleItem {
        ImageView mImageView;
        TextView mTextTitle;
        TextView mTextDesc;

        private HandleItem(ImageView imageView, TextView textTitle, TextView textDesc) {
            this.mImageView = imageView;
            this.mTextTitle = textTitle;
            this.mTextDesc = textDesc;
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public PageListViewAda() {
        mMapList = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.project_item, parent, false);
            ImageView imageView = convertView.findViewById(R.id.frame_iv);
            TextView textTitle = convertView.findViewById(R.id.frame_tv_title);
            TextView textDesc = convertView.findViewById(R.id.frame_tv_desc);
            HandleItem handleItem = new HandleItem(imageView, textTitle, textDesc);
            convertView.setTag(handleItem);
        }
        final Map<String, Object> map = mMapList.get(position);
        HandleItem handleItem = (HandleItem) convertView.getTag();
        handleItem.mImageView.setImageBitmap((Bitmap) map.get("bitmap"));
        handleItem.mTextTitle.setText((String) map.get("title"));
        handleItem.mTextDesc.setText((String) map.get("desc"));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getmContext(), WebActivity.class);
                intent.putExtra("url", (String) map.get("link"));
                ActivityCollector.getCurrentActivity().startActivity(intent);
            }
        });
        return convertView;
    }

    public List<Map<String, Object>> getMapList() {
        return mMapList;
    }

    @Override
    public int getCount() {
        return mMapList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}




