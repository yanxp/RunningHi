package com.example.paohi;

import android.support.v4.app.Fragment;

/**
 * Created by yxp on 2015/8/2.
 */
public class TimerActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return new TimerFragmentActivity();
    }
}