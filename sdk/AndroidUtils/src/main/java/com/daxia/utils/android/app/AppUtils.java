package com.daxia.utils.android.app;


import com.daxia.utils.android.context.ContextUtils;

/**
 * @author Dongping Wang
 * date 21-3-25
 * email wangdongping@flyaudio.cn
 */
public final class AppUtils {

    private AppUtils() {

    }

    public static String getPackageName() {
        return ContextUtils.getContext().getPackageName();
    }
}
