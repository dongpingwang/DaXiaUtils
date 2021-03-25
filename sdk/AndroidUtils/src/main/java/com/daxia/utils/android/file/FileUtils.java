package com.daxia.utils.android.file;

import android.support.annotation.NonNull;

import com.daxia.utils.android.close.CloseableUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Dongping Wang
 * date 21-3-19
 * email wangdongping@flyaudio.cn
 */
public final class FileUtils {

    private FileUtils() {

    }

    public static String getContentFromFile(@NonNull File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            return getContentFromFile(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getContentFromFile(@NonNull InputStream is) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeSilently(reader);
        }
        return sb.toString();
    }

}
