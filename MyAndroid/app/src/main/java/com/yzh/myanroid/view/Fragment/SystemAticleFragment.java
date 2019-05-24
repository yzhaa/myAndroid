package com.yzh.myanroid.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yzh.myanroid.R;
import com.yzh.myanroid.util.Constant;
import com.yzh.myanroid.view.CurrencyView;


public class SystemAticleFragment extends Fragment implements ArticleFragment {
    private CurrencyView mCurrencyView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String mArtitleUrl = null;
        String chapterName = null;
        if (getArguments() != null) {
            mArtitleUrl = getArguments().getString("url");
            chapterName = getArguments().getString("tag");
        }
        View view = inflater.inflate(R.layout.artitle_currency, container, false);
        mCurrencyView = new CurrencyView();
        mCurrencyView.initView(view, Constant.GET, mArtitleUrl, null, chapterName);
        return view;
    }

    @Override
    public CurrencyView getmCurrencyView() {
        return mCurrencyView;
    }
}
