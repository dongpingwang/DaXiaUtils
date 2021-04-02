package com.daxia.utils.android.log.timber;

import android.support.annotation.Nullable;
import android.util.Log;

import com.daxia.utils.android.array.ArrayUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Dongping Wang
 * date 4/1/2021
 */
public abstract class Tree {

    private final ThreadLocal<String> explicitTag = new ThreadLocal<>();

    protected String getTag() {
        String tag = explicitTag.get();
        if (tag != null) {
            explicitTag.remove();
        }
        return tag;
    }

    protected void setTag(String tag) {
        explicitTag.set(tag);
    }

    public void v(String msg, Object... args) {
        prepareLog(Log.VERBOSE, null, msg, args);
    }

    public void v(Throwable tr) {
        prepareLog(Log.VERBOSE, tr, "");
    }

    public void v(Throwable t, String msg, Object... args) {
        prepareLog(Log.VERBOSE, t, msg, args);
    }

    public void d(String msg, Object... args) {
        prepareLog(Log.DEBUG, null, msg, args);
    }

    public void d(Throwable tr) {
        prepareLog(Log.DEBUG, tr, "");
    }

    public void d(Throwable tr, String msg, Object... args) {
        prepareLog(Log.DEBUG, tr, msg, args);
    }

    public void i(String msg, Object... args) {
        prepareLog(Log.INFO, null, msg, args);
    }

    public void i(Throwable tr) {
        prepareLog(Log.INFO, tr, "");
    }

    public void i(Throwable tr, String msg, Object... args) {
        prepareLog(Log.INFO, tr, msg, args);
    }

    public void w(String msg, Object... args) {
        prepareLog(Log.WARN, null, msg, args);
    }

    public void w(Throwable tr) {
        prepareLog(Log.WARN, tr, "");
    }

    public void w(Throwable tr, String msg, Object... args) {
        prepareLog(Log.WARN, tr, msg, args);
    }

    public void e(String msg, Object... args) {
        prepareLog(Log.ERROR, null, msg, args);
    }

    public void e(Throwable tr) {
        prepareLog(Log.ERROR, tr, "");
    }

    public void e(Throwable tr, String msg, Object... args) {
        prepareLog(Log.ERROR, tr, msg, args);
    }

    public void wtf(String msg, Object... args) {
        prepareLog(Log.ASSERT, null, msg, args);
    }

    public void wtf(Throwable tr) {
        prepareLog(Log.ASSERT, tr, "");
    }

    public void wtf(Throwable tr, String msg, Object... args) {
        prepareLog(Log.ASSERT, tr, msg, args);
    }

    public void log(int priority, String msg, Object... args) {
        prepareLog(priority, null, msg, args);
    }

    public void log(int priority, Throwable tr, String msg, Object... args) {
        prepareLog(priority, tr, msg, args);
    }

    public void log(int priority, Throwable tr) {
        prepareLog(priority, tr, "");
    }

    protected abstract void log(int priority, @Nullable String tag, @Nullable String msg, @Nullable Throwable tr);

    @Deprecated
    protected boolean isLoggable(int priority) {
        return true;
    }

    protected boolean isLoggable(@Nullable String tag, int priority) {
        return isLoggable(priority);
    }

    private void prepareLog(int priority, Throwable tr, String msg, Object... args) {
        String tag = getTag();
        if (!isLoggable(tag, priority)) {
            return;
        }
        if (msg == null) {
            return;
        }

        if (ArrayUtils.getArrayLength(args) > 0) {
            msg = String.format(msg, args);
        }
        if (tr != null) {
            msg += "\n" + getStackTraceString(tr);
        }
        log(priority, tag, msg, tr);
    }

    private String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter(256);
        PrintWriter pw = new PrintWriter(sw, false);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

}
