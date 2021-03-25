package com.daxia.utils.android.res;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.support.annotation.XmlRes;
import android.util.DisplayMetrics;

import com.daxia.utils.android.context.ContextUtils;


/**
 * @author Dongping Wang
 * date 21-3-19
 */
public final class ResUtils {

    private static Resources resources;

    private ResUtils() {

    }

    public static String getString(@StringRes int id) {
        return getResources().getString(id);
    }

    public static String getString(@StringRes int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }

    public static String[] getStringArray(@ArrayRes int id) {
        return resources.getStringArray(id);
    }

    public static int getInteger(@IntegerRes int id) {
        return resources.getInteger(id);
    }

    public static int[] getIntArray(@ArrayRes int id) {
        return resources.getIntArray(id);
    }

    public static boolean getBoolean(@BoolRes int id) {
        return getResources().getBoolean(id);
    }

    public static Drawable getDrawable(@DrawableRes int id) {
        return getResources().getDrawable(id, null);
    }

    @ColorInt
    public static int getColor(@ColorRes int id) {
        return resources.getColor(id, null);
    }

    public static int getDimensionPixelOffset(@DimenRes int id) {
        return getResources().getDimensionPixelOffset(id);
    }

    public static float getDimension(@DimenRes int id) {
        return getResources().getDimension(id);
    }

    public static int getDimensionPixelSize(@DimenRes int id) {
        return getResources().getDimensionPixelSize(id);
    }

    public static XmlResourceParser getXml(@XmlRes int id) {
        return getResources().getXml(id);
    }

    public static AssetManager getAssets() {
        return getResources().getAssets();
    }

    public static float getDensity() {
        return getDisplayMetrics().density;
    }

    private static DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }

    private static Resources getResources() {
        if (resources == null) {
            resources = ContextUtils.getContext().getResources();
        }
        return resources;
    }

}
