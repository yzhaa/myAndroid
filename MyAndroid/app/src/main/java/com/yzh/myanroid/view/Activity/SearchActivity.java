package com.yzh.myanroid.view.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yzh.myanroid.R;


import com.yzh.myanroid.adapter.HotSearchAda;
import com.yzh.myanroid.controller.BaseContrloler;
import com.yzh.myanroid.controller.asy.HotLoadAsy;
import com.yzh.myanroid.util.MyBundle;
import com.yzh.myanroid.util.MyIntent;
import com.yzh.myanroid.widge.FlowGroup;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        Button btnBack = findViewById(R.id.search_btn_back);
        Button btnSearch = findViewById(R.id.search_btn_search);
        FlowGroup flowGroup = findViewById(R.id.serach_fg_hot);
        HotSearchAda.setFlowGroup(flowGroup);
        final EditText editText = findViewById(R.id.search_et);
        HotSearchAda.setFlowGroup(flowGroup);
        BaseContrloler.loadHotKey();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIntent.startIntent(SearchResultActivity.class,
                        MyBundle.getMyBundle().put("keyWord",editText.getText().toString()).build());
            }
        });

        super.onCreate(savedInstanceState);
    }
}
