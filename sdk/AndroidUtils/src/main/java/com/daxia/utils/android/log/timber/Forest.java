package com.daxia.utils.android.log.timber;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dongping Wang
 * date 21-4-2
 */
public class Forest extends Tree {

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

    static Forest asTree() {
        return forest;
    }

    Tree tag(String tag) {
        for (Tree tree : trees) {
            tree.setTag(tag);
        }
        return forest;
    }

    void plant(Tree tree) {
        synchronized (trees) {
            if (!trees.contains(tree)) {
                trees.add(tree);
                treeArray = trees.toArray(treeArray);
            }
        }
    }

    void plant(Tree... trees) {
        synchronized (this.trees) {
            Collections.addAll(this.trees, trees);
            treeArray = this.trees.toArray(treeArray);
        }
    }

    void uproot(Tree tree) {
        synchronized (trees) {
            trees.remove(tree);
            treeArray = this.trees.toArray(treeArray);
        }
    }

    void uprootAll() {
        synchronized (trees) {
            trees.clear();
            treeArray = new Tree[0];
        }
    }

    List<Tree> forest() {
        synchronized (trees) {
            return Collections.unmodifiableList(trees);
        }
    }

    int getTreeCount() {
        return treeArray.length;
    }
}
