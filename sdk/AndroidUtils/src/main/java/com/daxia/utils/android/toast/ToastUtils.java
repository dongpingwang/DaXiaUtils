package com.daxia.utils.android.toast;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.daxia.utils.android.context.ContextUtils;
import com.daxia.utils.android.res.ResUtils;
import com.daxia.utils.android.thread.UiThreadUtils;

/**
 * @author Dongping Wang
 * date 21-4-1
 */
public final class ToastUtils {

    private static Toast toast;

    private ToastUtils() {

    }

    public static void show(@StringRes int id) {
        show(ResUtils.getString(id));
    }

    public static void show(String str) {
        show(str, Toast.LENGTH_SHORT);
    }

    public static void showLong(String str) {
        show(str, Toast.LENGTH_LONG);
    }

    public static void showLong(@StringRes int id) {
        showLong(ResUtils.getString(id));
    }

    public synchronized static void show(String str, int duration) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(ContextUtils.getContext(), str, duration);
        UiThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                toast.show();
            }
        });
    }

}
