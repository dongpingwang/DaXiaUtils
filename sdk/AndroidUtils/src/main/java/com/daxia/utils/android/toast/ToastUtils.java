package com.daxia.utils.android.toast;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
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

    public static void show(String msg) {
        show(msg, Toast.LENGTH_SHORT);
    }

    public static void showLong(String msg) {
        show(msg, Toast.LENGTH_LONG);
    }

    public static void showLong(@StringRes int id) {
        showLong(ResUtils.getString(id));
    }

    public synchronized static void show(String msg, int duration) {
        show(null, msg, duration);
    }

    public synchronized static void show(Decorator decorator) {
        show(decorator, null, Toast.LENGTH_SHORT);
    }

    public synchronized static void showLong(Decorator decorator) {
        show(decorator, null, Toast.LENGTH_LONG);
    }

    private synchronized static void show(final Decorator decorator,final String msg, final int duration) {
        UiThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    toast.cancel();
                    toast = null;
                }
                if (decorator != null) {
                    if (decorator.getView() == null) {
                        return;
                    }
                    toast = new Toast(ContextUtils.getContext());
                    toast.setView(decorator.getView());
                    Decorator.Gravity gravity = decorator.getGravity();
                    if (gravity != null) {
                        toast.setGravity(gravity.gravity, gravity.xOffset, gravity.yOffset);
                    }
                    toast.setDuration(duration);
                } else {
                    toast = Toast.makeText(ContextUtils.getContext(), msg, duration);
                }
                if (toast.getView() != null) {
                    toast.show();
                }
            }
        });
    }

    public abstract static class Decorator {

        @NonNull
        public abstract View getView();

        @Nullable
        public Gravity getGravity() {
            return null;
        }

        public class Gravity {
            private int gravity;
            private int xOffset;
            private int yOffset;

            public Gravity(int gravity, int xOffset, int yOffset) {
                this.gravity = gravity;
                this.xOffset = xOffset;
                this.yOffset = yOffset;
            }
        }
    }
}
