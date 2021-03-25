package com.daxia.utils.android.res;

import android.content.res.Resources;
import android.support.annotation.StringRes;

import com.daxia.utils.android.context.ContextUtils;


/**
 * @author Dongping Wang
 * date 21-3-19
 * email wangdongping@flyaudio.cn
 */
public final class ResUtils {

    private static Resources resources;

    private ResUtils() {

    }


    public static String getString(@StringRes int id) {
        return getResources().getString(id);
    }

    private static Resources getResources() {
        if (resources == null) {
            resources = ContextUtils.getContext().getResources();
        }
        return resources;
    }


}
