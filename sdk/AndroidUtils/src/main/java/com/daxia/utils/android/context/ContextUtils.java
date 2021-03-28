package com.daxia.utils.android.context;

import android.app.Application;
import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Dongping Wang
 * date 21-2-2
 */
public final class ContextUtils {

    private ContextUtils() {

    }

    private static volatile Application application;

    public static Context getContext() {
        return getApplicationInternal().getApplicationContext();
    }

    private static <T extends Application> T getApplicationInternal() {
        try {
            if (application == null) {
                synchronized (ContextUtils.class) {
                    if (application == null) {
                        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
                        Method method = activityThreadClass.getMethod("currentActivityThread");
                        Object localObject = method.invoke(null, (Object[]) null);
                        Field appField = activityThreadClass.getDeclaredField("mInitialApplication");
                        appField.setAccessible(true);
                        application = (Application) appField.get(localObject);
                        if (application == null) {
                            Method method2 = activityThreadClass.getMethod("getApplication");
                            application = (Application) method2.invoke(localObject, (Object[]) null);
                        }
                    }
                }
            }

            return (T) application;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Application getApplication() {
        return getApplicationInternal();
    }

}
