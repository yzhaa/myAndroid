package com.yzh.myanroid.util;

import android.app.Activity;
import java.util.LinkedList;
import java.util.List;

public class ActivityCollector {
    private static List<Activity> mActivityList = new LinkedList<>();

    public static void addActivity(Activity activity) {
        mActivityList.add(activity);
    }

    public static Activity getCurrentActivity(){
        return mActivityList.get(mActivityList.size() - 1);
    }

    public static void removeActivity(Activity activity) {
        mActivityList.remove(activity);
    }


}
