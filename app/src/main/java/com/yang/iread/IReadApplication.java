package com.yang.iread;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author: jianhong
 * @createDate: 2018/9/6 12:13
 * @description:
 */
public class IReadApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
