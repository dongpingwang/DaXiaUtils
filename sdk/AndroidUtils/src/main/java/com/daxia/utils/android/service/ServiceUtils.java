package com.daxia.utils.android.service;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.daxia.utils.android.context.ContextUtils;

import java.util.List;

/**
 * @author Dongping Wang
 * date 21-4-1
 */
public final class ServiceUtils {

    private ServiceUtils() {

    }

    public static void startService(Class<?> cls) {
        startService(cls, "");
    }

    public static void startService(Class<?> cls, String action) {
        Intent intent = new Intent();
        intent.setClass(ContextUtils.getContext(), cls);
        if (!TextUtils.isEmpty(action)) {
            intent.setAction(action);
        }
        startService(intent);
    }

    public static void startService(Intent service) {
        ContextUtils.getContext().startService(service);
    }

    public static void stopServicee(Class<?> cls) {
        Intent intent = new Intent(ContextUtils.getContext(), cls);
        stopService(intent);
    }

    public static void stopService(Intent service) {
        ContextUtils.getContext().stopService(service);
    }

    public static boolean bindService(Intent service, @NonNull ServiceConnection conn, int flags) {
        return ContextUtils.getContext().bindService(service, conn, flags);
    }

    public static void unbindService(@NonNull ServiceConnection conn) {
        ContextUtils.getContext().unbindService(conn);
    }

    @TargetApi(26)
    public static void startForegroundService(Intent service) {
        ContextUtils.getContext().startForegroundService(service);
    }

    public static boolean isServiceRunning(Class<?> cls) {
        return isServiceRunning(cls.getName());
    }

    public static boolean isServiceRunning(String clsName) {
        ActivityManager am = (ActivityManager) ContextUtils.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = am.getRunningServices(50);
        for (ActivityManager.RunningServiceInfo service : runningServices) {
            if (service.service.getClassName().equals(clsName)) {
                return true;
            }
        }
        return false;
    }

}
