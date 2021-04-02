package com.daxia.utils.demo;

import android.app.Application;

import com.daxia.utils.android.DaXiaUtils;

/**
 * @author Dongping Wang
 * date 21-4-2
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaXiaUtils.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DaXiaUtils.deInit();
    }
}
