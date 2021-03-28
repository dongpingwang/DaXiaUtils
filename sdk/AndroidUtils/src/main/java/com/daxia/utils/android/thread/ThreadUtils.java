package com.daxia.utils.android.thread;


import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author Dongping Wang
 * date 21-3-19
 */
public final class ThreadUtils {

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    public static void execute(@NonNull Runnable r) {
        EXECUTOR.execute(r);
    }

    public static <T> Future<T> execute(final ResultTask<T> callable) {
        UiThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                callable.onStart();
            }
        });
        final Future<T> future = EXECUTOR.submit(new Callable<T>() {
            @Override
            public T call() {
                return callable.doInBackground();
            }
        });
        UiThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                try {
                    callable.onFinish(future.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return future;
    }

    public static abstract class ResultTask<T> {
        public void onStart() {

        }

        public void onFinish(T result) {

        }

        public abstract T doInBackground();
    }

    private static boolean isMainLooper() {
        return Looper.myLooper() == Looper.getMainLooper();
    }


}
