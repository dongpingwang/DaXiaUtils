package com.daxia.utils.android.unit;

/**
 * @author Dongping Wang
 * date 21-4-1
 */
public enum TimeUnit {
    SECOND { @Override public long toMillis(long v) { return v * 1000; } },
    MINUTE { @Override public long toMillis(long v) { return v * 60_000; } },
    HOUR   { @Override public long toMillis(long v) { return v * 3_600_000; } },
    DAY    { @Override public long toMillis(long v) { return v * 86_400_000; } };

    public long toMillis(long v) { throw new AbstractMethodError(); }
}
