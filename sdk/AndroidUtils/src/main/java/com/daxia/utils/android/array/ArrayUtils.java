package com.daxia.utils.android.array;

/**
 * @author Dongping Wang
 * date 21-4-2
 */
public final class ArrayUtils {

    private ArrayUtils() {

    }

    public static <T> int getArrayLength(T[] arr) {
        return arr == null ? 0 : arr.length;
    }
}
