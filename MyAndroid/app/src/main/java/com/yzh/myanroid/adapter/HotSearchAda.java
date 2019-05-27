package com.yzh.myanroid.adapter;




import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzh.myanroid.Bean.HotKey;
import com.yzh.myanroid.R;

import com.yzh.myanroid.util.MyApplication;
import com.yzh.myanroid.util.MyBundle;
import com.yzh.myanroid.util.MyIntent;
import com.yzh.myanroid.view.Activity.SearchResultActivity;
import com.yzh.myanroid.widge.FlowGroup;

import java.util.List;

/**
 * 搜索中的流标签
 */
public class HotSearchAda {
    private static FlowGroup mFlowGroup;
    private static List<HotKey> mList;

    public static void setmFlowGroup(FlowGroup mFlowGroup) {
        HotSearchAda.mFlowGroup = mFlowGroup;
    }

    public static void setmList(List<HotKey> mList) {
        HotSearchAda.mList = mList;
    }
    public static void init(){
        if(mList ==null|| mFlowGroup ==null){
            throw new NullPointerException("存在空指针");
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20,10,20,10);
        for (final HotKey name: mList) {
            final TextView textView = new TextView(MyApplication.getmContext());
            textView.setLayoutParams(layoutParams);
            textView.setText(name.getName());
            textView.setTextSize(18);
            textView.setBackgroundResource(R.drawable.shape);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyIntent.startIntent(SearchResultActivity.class,   MyBundle.getMyBundle().put("keyWord", name).build());
                }
            });
            mFlowGroup.addView(textView);
        }
    }

}
