package com.daxia.utils.android.key;

import android.app.Instrumentation;

import com.daxia.utils.android.thread.ThreadUtils;


/**
 * @author Dongping Wang
 * date 21-3-17
 * email wangdongping@flyaudio.cn
 */
public final class KeyEventUtils {

    private static volatile Instrumentation instrumentation;

    private KeyEventUtils() {

    }

    private static void sendKey(final int key) {
        ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {
                getInstrumentation().sendKeyDownUpSync(key);
            }
        });
    }

    private static Instrumentation getInstrumentation() {
        if (instrumentation == null) {
            synchronized (KeyEventUtils.class) {
                if (instrumentation == null) {
                    instrumentation = new Instrumentation();
                }
            }
        }
        return instrumentation;
    }
}
