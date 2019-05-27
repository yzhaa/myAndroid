package com.yzh.myanroid.view.Activity;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.yzh.myanroid.R;
import com.yzh.myanroid.util.Constant;
import com.yzh.myanroid.view.CurrencyView;

public class SearchResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.activity_serarch_result, null);
        setContentView(view);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            new CurrencyView().initView(view, Constant.POST,Constant.SEARCH_URL, bundle.getString("keyWord"),null);
        }
        Button button = findViewById(R.id.search_result_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        super.onCreate(savedInstanceState);
    }

}
