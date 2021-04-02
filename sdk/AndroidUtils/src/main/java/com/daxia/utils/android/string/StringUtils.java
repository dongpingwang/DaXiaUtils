package com.daxia.utils.android.string;

/**
 * @author Dongping Wang
 * date 21-4-2
 */
public final class StringUtils {

    private StringUtils() {

    }

    public static int getLen(String str) {
        return str == null ? 0 : str.length();
    }
}
