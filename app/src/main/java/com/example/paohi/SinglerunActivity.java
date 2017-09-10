package com.example.paohi;

import android.support.v4.app.Fragment;

/**
 * Created by yxp on 2015/7/23.
 */
public class SinglerunActivity extends SingleFragmentActivity {
    protected Fragment createFragment() {
        return new SinglerunActivityFragment();
    }

}
