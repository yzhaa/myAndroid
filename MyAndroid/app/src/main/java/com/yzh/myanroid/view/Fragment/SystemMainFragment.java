package com.yzh.myanroid.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yzh.myanroid.R;
import com.yzh.myanroid.controller.BaseContrloler;
import com.yzh.myanroid.widge.FlowGroup;

public class SystemMainFragment extends Fragment {
    private FlowGroup mFlowGroup;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_system, container, false);
        mFlowGroup = view.findViewById(R.id.system_fg);
        BaseContrloler.loadSystem(this);
        return view;
    }

    public FlowGroup getmFlowGroup() {
        return mFlowGroup;
    }
}
