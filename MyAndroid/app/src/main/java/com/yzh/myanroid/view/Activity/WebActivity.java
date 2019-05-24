package com.yzh.myanroid.view.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.yzh.myanroid.R;


public class WebActivity extends AppCompatActivity {
    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWebView = findViewById(R.id.contence_wv_show);
        mWebView.setWebViewClient(new WebViewClient());
        mProgressBar = findViewById(R.id.contence_pb_show);
        if(getIntent().getExtras()!=null){
            mWebView.loadUrl(getIntent().getExtras().getString("url"));
        }
        WebSettings settings = mWebView.getSettings();
        settings.setDisplayZoomControls(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                        mProgressBar.setVisibility(View.GONE);
                }
                else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });


        Button btnSerach = findViewById(R.id.navigation_btn_serach);
        Button btnBack = findViewById(R.id.navigation_btn_back);
        btnSerach.setVisibility(View.INVISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mWebView.canGoBack()){
                    mWebView.goBack();
                }
                else finish();
            }
        });
    }

}
