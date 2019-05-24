package com.yzh.myanroid.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yzh.myanroid.R;

import java.util.ArrayList;
import java.util.List;

public class SystemFragment extends Fragment {
    private View mRootView;
    private SystemMainFragment mSystemMainFragment;
    private Fragment mContent;
    private List<SystemAticleFragment> mFragmentList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView !=null){
            return mRootView;
        }
        else {
            View view = inflater.inflate(R.layout.frament_system,container,false);
            initFragment();
            mFragmentList = new ArrayList<>();
            mRootView = view;
        }
        return mRootView;
    }

    public void back(){
        if(!(mContent instanceof SystemMainFragment)){
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.hide(mContent).show(mSystemMainFragment).commit();
            mContent = mSystemMainFragment;
        }


    }

    private void initFragment(){
        mSystemMainFragment = new SystemMainFragment();
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().add(R.id.system_fl, mSystemMainFragment).commit();
        mContent = mSystemMainFragment;
    }


   public void replece(Bundle bundle){
       String tag = bundle.getString("tag");
       if(tag!=null) {
           FragmentManager fragmentManager = getChildFragmentManager();
           FragmentTransaction transaction = fragmentManager.beginTransaction();
           for (SystemAticleFragment saf : mFragmentList) {
               if (tag.equals(saf.getTag())) {
                   transaction.hide(mContent).show(saf).commit();
                   mContent = saf;
                   return;
               }
           }
           SystemAticleFragment systemAticleFragment = new SystemAticleFragment();
           systemAticleFragment.setArguments(bundle);
           transaction.hide(mContent).add(R.id.system_fl, systemAticleFragment).commit();
           mFragmentList.add(systemAticleFragment);
           mContent = systemAticleFragment;
       }

    }

}

