package com.daxia.utils.android.log.timber;

import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;

import com.daxia.utils.android.string.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dongping Wang
 * date 4/1/2021
 */
public class DebugTree extends Tree {

    private static final int MAX_LOG_LENGTH = 4000;
    private static final int MAX_TAG_LENGTH = 23;
    private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");
    private static final List<String> CLS_IGNORE = Arrays.asList(
            Timber.class.getName(),
            Tree.class.getName(),
            DebugTree.class.getName(),
            Forest.class.getName());

    @Override
    protected String getTag() {
        String tag = super.getTag();
        if (tag == null) {
            StackTraceElement[] elements = new Throwable().getStackTrace();
            for (StackTraceElement element : elements) {
                if (CLS_IGNORE.contains(element.getClassName())) {
                    continue;
                }
                return createStackElementTag(element);
            }
        }
        return tag;
    }

    @Override
    protected void log(int priority, @Nullable String tag, @Nullable String msg, @Nullable Throwable tr) {
        int length = StringUtils.getLen(msg);
        if (length < MAX_LOG_LENGTH) {
            log(priority, tag, msg);
            return;
        }
        int i = 0;
        while (i < length) {
            int newline = msg.indexOf('\n', i);
            if (newline == -1) {
                newline = length;
            }
            do {
                int end = Math.min(newline, i + MAX_LOG_LENGTH);
                String part = msg.substring(i, end);
                log(priority, tag, part);
                i = end;
            } while (i < newline);
            i++;
        }
    }

    private void log(int priority, String tag, String msg) {
        if (priority == Log.ASSERT) {
            Log.wtf(tag, msg);
        } else {
            Log.println(priority, tag, msg);
        }
    }

    private String createStackElementTag(StackTraceElement element) {
        String className = element.getClassName();
        String tag = className.substring(className.lastIndexOf('.') + 1);
        Matcher m = ANONYMOUS_CLASS.matcher(tag);
        if (m.find()) {
            tag = m.replaceAll("");
        }
        if (tag.length() > MAX_TAG_LENGTH && Build.VERSION.SDK_INT >= 24) {
            return tag.substring(0, MAX_TAG_LENGTH);
        }
        return tag;
    }
}
