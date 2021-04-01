package com.daxia.utils.android.unit;

/**
 * @author Dongping Wang
 * date 21-4-1
 */
public enum MemoryUnit {
    KILOBYTES { @Override public long toBytes(long v) { return v * 1_000; } },
    MEGABYTES { @Override public long toBytes(long v) { return v * 1_000_000; } },
    GIGABYTES { @Override public long toBytes(long v) { return v * 1_000_000_000; } },
    KIBIBYTES { @Override public long toBytes(long v) { return v * 1_024; } },
    MEBIBYTES { @Override public long toBytes(long v) { return v * 1_048_576; } },
    GIBIBYTES { @Override public long toBytes(long v) { return v * 1_073_741_824; } };

    public long toBytes(long v) {
        throw new AbstractMethodError();
    }
}
