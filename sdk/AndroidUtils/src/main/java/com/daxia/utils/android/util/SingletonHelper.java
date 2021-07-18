package com.daxia.utils.android.util;

/**
 * @author dongping.wang
 * @date 2021/7/15 12:52
 * @description 单例工具类
 */
public abstract class SingletonHelper<T> {

    private volatile T instance;

    public final T get() {
        if (instance == null) {
            synchronized (SingletonHelper.class) {
                if (instance == null) {
                    instance = create();
                }
            }
        }
        return instance;
    }

    public abstract T create();
}
