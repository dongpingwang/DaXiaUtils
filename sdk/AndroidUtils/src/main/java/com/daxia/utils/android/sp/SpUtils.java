package com.daxia.utils.android.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.daxia.utils.android.app.AppUtils;
import com.daxia.utils.android.context.ContextUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Dongping Wang
 * date 21-3-26
 */
public final class SpUtils {

    private static final String DEFAULT_NAME = AppUtils.getPackageName();

    private static volatile Map<String, SpUtils> spUtilsMap;
    private static SpUtils spUtils;
    private final SharedPreferences sp;
    private Map<OnValueChangeListener, SharedPreferences.OnSharedPreferenceChangeListener> listenerMap;

    private SpUtils(String name) {
        sp = ContextUtils.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static SpUtils getInstance() {
        return getInstance("");
    }

    public static SpUtils getInstance(String name) {
        if (spUtilsMap == null) {
            synchronized (SpUtils.class) {
                if (spUtilsMap == null) {
                    spUtilsMap = new HashMap<>();
                }
            }
        }
        if (TextUtils.isEmpty(name)) {
            name = DEFAULT_NAME;
        }
        spUtils = spUtilsMap.get(name);
        if (spUtils == null) {
            spUtils = new SpUtils(name);
            spUtilsMap.put(name, spUtils);
        }
        return spUtils;
    }

    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public float getFloat(String key) {
        return getFloat(key, 0.0F);
    }

    public float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return getLong(key, 0L);
    }

    public long getLong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    @Nullable
    public Set<String> getStringSet(String key) {
        return getStringSet(key, null);
    }

    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }

    private SharedPreferences.Editor edit() {
        return sp.edit();
    }

    public void clear() {
        edit().clear().apply();
    }

    public void remove(String key) {
        edit().remove(key).apply();
    }

    public void put(String key, boolean value) {
        putBoolean(key, value);
    }

    public void put(String key, float value) {
        putFloat(key, value);
    }

    public void put(String key, int value) {
        putInt(key, value);
    }

    public void put(String key, long value) {
        putLong(key, value);
    }

    public void put(String key, String value) {
        putString(key, value);
    }

    public void put(String key, Set<String> value) {
        putStringSet(key, value);
    }

    public void putBoolean(String key, boolean value) {
        edit().putBoolean(key, value).apply();
    }

    public void putFloat(String key, float value) {
        edit().putFloat(key, value).apply();
    }

    public void putInt(String key, int value) {
        edit().putInt(key, value).apply();
    }

    public void putLong(String key, long value) {
        edit().putLong(key, value).apply();
    }

    public void putString(String key, String value) {
        edit().putString(key, value).apply();
    }

    public void putStringSet(String key, Set<String> value) {
        edit().putStringSet(key, value).apply();
    }

    public void registerOnValueChangeListener(final OnValueChangeListener valueChangeListener) {
        if (listenerMap == null) {
            listenerMap = new HashMap<>();
        }
        sp.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                listenerMap.put(valueChangeListener, this);
                Iterator<OnValueChangeListener> iterator = listenerMap.keySet().iterator();
                while (iterator.hasNext()) {
                    iterator.next().onValueChange(key);
                }
            }
        });
    }

    public void unregisterOnValueChangeListener(OnValueChangeListener listener) {
        sp.unregisterOnSharedPreferenceChangeListener(listenerMap.remove(listener));
    }

    public interface OnValueChangeListener {
        void onValueChange(String key);
    }
}
