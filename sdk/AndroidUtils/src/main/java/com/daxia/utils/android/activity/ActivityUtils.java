package com.daxia.utils.android.activity;

import android.content.Intent;
import android.os.Bundle;

import com.daxia.utils.android.context.ContextUtils;

/**
 * @author Dongping Wang
 * date 21-4-1
 */
public final class ActivityUtils {

    private ActivityUtils() {

    }

    public static void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    public static void startActivity(Intent intent, Bundle options) {
        ContextUtils.getContext().startActivity(intent, options);
    }

    public static void startActivities(Intent[] intents) {
        startActivities(intents, null);
    }

    public static void startActivities(Intent[] intents, Bundle options) {
        ContextUtils.getContext().startActivities(intents, options);
    }

}
