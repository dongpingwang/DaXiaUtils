package com.daxia.utils.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.daxia.utils.android.density.DensityUtils;
import com.daxia.utils.android.res.ResUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "" + ResUtils.getDensity() + "--" + DensityUtils.dp2px(12.5F) + "--" + DensityUtils.px2dp(DensityUtils.dp2px(12.5F)));
    }

    public void test(View view) {

    }
}
