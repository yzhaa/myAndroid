package com.yzh.myanroid.view.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.yzh.myanroid.R;
import com.yzh.myanroid.util.MyToast;
import com.yzh.myanroid.view.Fragment.HomeFragment;
import com.yzh.myanroid.view.Fragment.ProjectFragment;
import com.yzh.myanroid.view.Fragment.SystemFragment;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private static final int LOGIN=2;
    private static final int EXIT_ACCOUNT =3;
    private TextView mTextView;
    private Fragment mContent;
    private Fragment mHomeFragment;
    private Fragment mSystemFragment;
    private ProjectFragment mProjectFragment;
    private DrawerLayout mDrawerLayout;
    private TextView mTextViewWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.main_tv_navigation);
        Button button = findViewById(R.id.navigation_btn_back);
        Button btnSerach = findViewById(R.id.navigation_btn_serach);
        NavigationView navigationView = findViewById(R.id.activity_main_nv);
        mTextViewWelcome = navigationView.getHeaderView(0).findViewById(R.id.header_tv);
        mDrawerLayout = findViewById(R.id.activity_main_dl);
        mHomeFragment = new HomeFragment();
        mSystemFragment = new SystemFragment();
        mProjectFragment = new ProjectFragment();
        initContent();
        RadioGroup radioGroup = findViewById(R.id.rg_tab_bar);
        radioGroup.setOnCheckedChangeListener(this);
        button.setOnClickListener(this);
        btnSerach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        //  没写好的东西 废弃
      /*  navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case  R.id.nav_login:
                        if(!MyCookieStorge.IsState){
                            MyIntent.startIntent(LoginActivity.class,null);
                        } else MyToast.buildToast("已经登陆");
                        return true;
                    case R.id.nav_cancel:
                        BaseContrloler.exitAccount();
                        return true;
                    case R.id.nav_regist:
                        MyIntent.startIntent(RegistActivity.class,null);
                        return true;
                    case R.id.nav_clect:
                        return true;

                }
                return true;
            }
        });*/

    }

    @Override
    public void onClick(View v) {
        if(mContent instanceof SystemFragment){
            ((SystemFragment) mContent).back();
        }
        else if(mContent instanceof HomeFragment){
          mDrawerLayout.openDrawer(Gravity.START);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_home:
                    mTextView.setText("首页");
                    switchFrament(mHomeFragment);
                    break;
                case R.id.rb_system:
                    mTextView.setText("体系");
                    switchFrament(mSystemFragment);
                    break;
                case R.id.rb_project:
                    mTextView.setText("项目");
                    switchFrament(mProjectFragment);
                    break;
            }
        }

    private void initContent() {
        mContent = mHomeFragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container_frament, mContent).commit();
    }

    private void switchFrament(Fragment fragment){
        if(mContent!=fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if(!fragment.isAdded()){
                transaction.hide(mContent).add(R.id.container_frament, fragment).commit();
            }
            else {
                transaction.hide(mContent).show(fragment).commit();
            }
            mContent = fragment;
        }
    }

    public void showOperation(int choice,String contence){
        switch (choice)
       {
           case LOGIN:
            mTextViewWelcome.setText("欢迎你");
            break;
           case EXIT_ACCOUNT:
               mTextViewWelcome.setText("");
               break;
       }
        MyToast.buildToast(contence);
    }
}
