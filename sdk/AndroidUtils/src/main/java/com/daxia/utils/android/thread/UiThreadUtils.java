package com.daxia.utils.android.thread;


import android.os.Handler;
import android.os.Looper;

/**
 * @author Dongping Wang
 * date 21-3-25
 * email wangdongping@flyaudio.cn
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
                UI_HANDLER.post(run);
            }
        }
    }

    public static boolean isMainLooper() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

}
