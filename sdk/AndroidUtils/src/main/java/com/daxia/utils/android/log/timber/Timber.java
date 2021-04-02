package com.daxia.utils.android.log.timber;


import java.util.List;

/**
 * @author Dongping Wang
 * date 4/1/2021
 */
public final class Timber {

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

}
