package com.daxia.utils.android.asset;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;

import com.daxia.utils.android.close.CloseableUtils;
import com.daxia.utils.android.context.ContextUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Dongping Wang
 * date 2021/4/6
 */
public final class AssetUtils {

    private AssetUtils() {

    }

    private static AssetManager getAssets() {
        AssetManager assets = ContextUtils.getContext().getAssets();
        return assets;
    }

    public static InputStream open(String fileName) throws IOException {
        return getAssets().open(fileName);
    }

    public static InputStream open(String fileName, int accessMode) throws IOException {
        return getAssets().open(fileName, accessMode);
    }

    public static AssetFileDescriptor openFd(String fileName) throws IOException {
        return getAssets().openFd(fileName);
    }

    public static AssetFileDescriptor openNonAssetFd(String fileName) throws IOException {
        return getAssets().openNonAssetFd(fileName);
    }

    public static AssetFileDescriptor open(int cookie, String fileName) throws IOException {
        return getAssets().openNonAssetFd(cookie, fileName);
    }

    public static XmlResourceParser openXmlResourceParser(String fileName) throws IOException {
        return getAssets().openXmlResourceParser(fileName);
    }

    public static XmlResourceParser openXmlResourceParser(int cookie, String fileName) throws IOException {
        return getAssets().openXmlResourceParser(cookie, fileName);
    }

    public static void close(AssetManager am) {
        CloseableUtils.closeSilently(am);
    }

}
