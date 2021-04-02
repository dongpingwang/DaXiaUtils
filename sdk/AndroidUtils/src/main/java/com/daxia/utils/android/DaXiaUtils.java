package com.daxia.utils.android;

import android.app.Application;

import com.daxia.utils.android.context.ContextUtils;
import com.daxia.utils.android.log.timber.DebugTree;
import com.daxia.utils.android.log.timber.Timber;

/**
 * @author Dongping Wang
 * date 21-4-1
 */
public final class DaXiaUtils {

    private DaXiaUtils() {

    }

    public static void init(Application application) {
        ContextUtils.init(application);
        Timber.plant(new DebugTree());
    }

    public static void deInit() {
        Timber.uprootAll();
    }
}
