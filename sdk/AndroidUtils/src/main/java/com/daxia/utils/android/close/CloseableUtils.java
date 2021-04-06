package com.daxia.utils.android.close;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Dongping Wang
 * date 21-3-19
 */
public final class CloseableUtils {

    private CloseableUtils() {

    }

    public static void closeSilently(Closeable... closes) {
        try {
            for (Closeable close : closes) {
                if (close != null) {
                    close.close();
                }
            }
        } catch (IOException e) {
            // silently
        }
    }

    public static void closeSilently(AutoCloseable... closes) {
        try {
            for (AutoCloseable close : closes) {
                if (close != null) {
                    close.close();
                }
            }
        } catch (Exception e) {
            // silently
        }
    }
}
