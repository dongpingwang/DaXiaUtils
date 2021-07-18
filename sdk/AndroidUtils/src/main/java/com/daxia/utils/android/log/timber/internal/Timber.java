package com.daxia.utils.android.log.timber.internal;


import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dongping Wang
 * date 4/1/2021
 */
final class Timber {

    private static Forest forest = Forest.asTree();

    private Timber() {

    }

    public static void v(String msg, Object... args) {
        forest.v(msg, args);
    }

    public static void v(Throwable tr) {
        forest.v(tr);
    }

    public static void v(Throwable tr, String msg, Object... args) {
        forest.v(tr, msg, args);
    }

    public static void d(String msg, Object... args) {
        forest.d(msg, args);
    }

    public static void d(Throwable tr) {
        forest.d(tr);
    }

    public static void d(Throwable tr, String msg, Object... args) {
        forest.d(tr, msg, args);
    }

    public static void i(String msg, Object... args) {
        forest.i(msg, args);
    }

    public static void i(Throwable tr) {
        forest.i(tr);
    }

    public static void i(Throwable tr, String msg, Object... args) {
        forest.i(tr, msg, args);
    }

    public static void w(String msg, Object... args) {
        forest.w(msg, args);
    }

    public static void w(Throwable tr) {
        forest.w(tr);
    }

    public static void w(Throwable tr, String msg, Object... args) {
        forest.w(tr, msg, args);
    }

    public static void e(String msg, Object... args) {
        forest.e(msg, args);
    }

    public static void e(Throwable tr) {
        forest.e(tr);
    }

    public static void e(Throwable tr, String msg, Object... args) {
        forest.e(tr, msg, args);
    }

    public static void wtf(String msg, Object... args) {
        forest.wtf(msg, args);
    }

    public static void wtf(Throwable tr) {
        forest.wtf(tr);
    }


    public static void wtf(Throwable tr, String msg, Object... args) {
        forest.wtf(tr, msg, args);
    }


    public static void log(int priority, String msg, Object... args) {
        forest.log(priority, msg, args);
    }

    public static void log(int priority, Throwable tr, String msg, Object... args) {
        forest.log(priority, tr, msg, args);
    }


    public static void log(int priority, Throwable tr) {
        forest.log(priority, tr);
    }

    public static Forest asTree() {
        return forest;
    }

    public static Tree tag(String tag) {
        return forest.tag(tag);
    }

    public static void plant(Tree tree) {
        forest.plant(tree);
    }

    public static void plant(Tree... trees) {
        forest.plant(trees);
    }

    public static void uproot(Tree tree) {
        forest.uproot(tree);
    }

    public static void uprootAll() {
        forest.uprootAll();
    }

    public static List<Tree> getTrees() {
        return forest.forest();
    }

    public static int getTreeCount() {
        return forest.getTreeCount();
    }

    public static abstract class Tree {
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

            int len = args == null ? 0 : args.length;
            if (len > 0) {
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

    public static class DebugTree extends Tree {

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
            int length = msg == null ? 0 : msg.length();
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

    private static class Forest extends Tree {

        private static Forest forest = new Forest();
        private final List<Tree> trees = new ArrayList<>();
        private Tree[] treeArray = new Tree[0];

        private Forest() {

        }

        @Override
        protected void log(int priority, @Nullable String tag, @Nullable String msg, @Nullable Throwable tr) {

        }

        @Override
        public void v(String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.v(msg, args);
            }
        }

        @Override
        public void v(Throwable tr) {
            for (Tree tree : treeArray) {
                tree.v(tr);
            }
        }

        @Override
        public void v(Throwable tr, String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.v(tr, msg, args);
            }
        }

        @Override
        public void d(String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.d(msg, args);
            }
        }

        @Override
        public void d(Throwable tr) {
            for (Tree tree : treeArray) {
                tree.d(tr);
            }
        }

        @Override
        public void d(Throwable tr, String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.d(tr, msg, args);
            }
        }

        @Override
        public void i(String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.i(msg, args);
            }
        }

        @Override
        public void i(Throwable tr) {
            for (Tree tree : treeArray) {
                tree.i(tr);
            }
        }

        @Override
        public void i(Throwable tr, String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.i(tr, msg, args);
            }
        }

        @Override
        public void w(String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.w(msg, args);
            }
        }

        @Override
        public void w(Throwable tr) {
            for (Tree tree : treeArray) {
                tree.w(tr);
            }
        }

        @Override
        public void w(Throwable tr, String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.w(tr, msg, args);
            }
        }

        @Override
        public void e(String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.e(msg, args);
            }
        }

        @Override
        public void e(Throwable tr) {
            for (Tree tree : treeArray) {
                tree.e(tr);
            }
        }

        @Override
        public void e(Throwable tr, String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.e(tr, msg, args);
            }
        }

        @Override
        public void wtf(String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.wtf(msg, args);
            }
        }

        @Override
        public void wtf(Throwable tr) {
            for (Tree tree : treeArray) {
                tree.wtf(tr);
            }
        }

        @Override
        public void wtf(Throwable tr, String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.wtf(tr, msg, args);
            }
        }

        @Override
        public void log(int priority, String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.log(priority, msg, args);
            }
        }

        @Override
        public void log(int priority, Throwable tr, String msg, Object... args) {
            for (Tree tree : treeArray) {
                tree.log(priority, tr, msg, args);
            }
        }

        @Override
        public void log(int priority, Throwable tr) {
            for (Tree tree : treeArray) {
                tree.log(priority, tr);
            }
        }

        private static Forest asTree() {
            return forest;
        }

        Tree tag(String tag) {
            for (Tree tree : trees) {
                tree.setTag(tag);
            }
            return forest;
        }

        private void plant(Tree tree) {
            synchronized (trees) {
                if (!trees.contains(tree)) {
                    trees.add(tree);
                    treeArray = trees.toArray(treeArray);
                }
            }
        }

        private void plant(Tree... trees) {
            synchronized (this.trees) {
                Collections.addAll(this.trees, trees);
                treeArray = this.trees.toArray(treeArray);
            }
        }

        private void uproot(Tree tree) {
            synchronized (trees) {
                trees.remove(tree);
                treeArray = this.trees.toArray(treeArray);
            }
        }

        private void uprootAll() {
            synchronized (trees) {
                trees.clear();
                treeArray = new Tree[0];
            }
        }

        private List<Tree> forest() {
            synchronized (trees) {
                return Collections.unmodifiableList(trees);
            }
        }

        private int getTreeCount() {
            return treeArray.length;
        }
    }

}
