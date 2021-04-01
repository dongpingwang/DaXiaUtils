package com.daxia.utils.android.thread;


import android.os.Handler;
import android.os.Looper;

/**
 * @author Dongping Wang
 * date 21-3-25
 */
public final class UiThreadUtils {

    private UiThreadUtils() {

    }

    private static final Handler UI_HANDLER = new Handler(Looper.getMainLooper());

    public static void runOnUIThread(Runnable run) {
        if (run != null) {
            if (isMainLooper()) {
                run.run();
            } else {
                runOnUIThread(run);
            }
        }
    }

    public static void runOnUIThread(Runnable run, long delay) {
        UI_HANDLER.postDelayed(run, delay);
    }

    public static boolean isMainLooper() {
        return Looper.myLooper() == Looper.getMainLooper();
    }


}
