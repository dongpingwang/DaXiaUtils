package com.daxia.utils.android.broadcast;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import com.daxia.utils.android.context.ContextUtils;

/**
 * @author Dongping Wang
 * date 21-4-1
 */
public final class BroadcastUtils {

    private BroadcastUtils() {

    }

    public static void sendBroadcast(Intent intent) {
        ContextUtils.getContext().sendBroadcast(intent);
    }

    public static void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        ContextUtils.getContext().registerReceiver(receiver, filter);
    }

    public static void unregisterReceiver(BroadcastReceiver receiver) {
        ContextUtils.getContext().unregisterReceiver(receiver);
    }
}
