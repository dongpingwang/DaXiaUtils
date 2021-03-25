package com.daxia.utils.android.density;

import com.daxia.utils.android.res.ResUtils;

/**
 * @author Dongping Wang
 * date 21-3-25
 * email wangdongping@flyaudio.cn
 */
public final class DensityUtils {

    private DensityUtils() {

    }

    public static int dp2px(float dp) {
        return (int) (dp * ResUtils.getDensity() + 0.5F);
    }

    public static float px2dp(int px) {
        return px / ResUtils.getDensity();
    }
}
