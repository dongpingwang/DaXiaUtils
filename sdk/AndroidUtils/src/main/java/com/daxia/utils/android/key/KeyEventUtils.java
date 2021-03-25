package com.daxia.utils.android.key;

import android.app.Instrumentation;

import com.daxia.utils.android.thread.ThreadUtils;

import java.io.IOException;


/**
 * @author Dongping Wang
 * date 21-3-17
 */
public final class KeyEventUtils {

    private static volatile Instrumentation instrumentation;

    private KeyEventUtils() {

    }

    public static void sendKeyDownUpSync(final int key) {
        ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {
                getInstrumentation().sendKeyDownUpSync(key);
            }
        });
    }

    public static void exec(final int key) {
        try {
            Runtime.getRuntime().exec("input keyevent " + key);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
