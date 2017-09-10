package com.example.paohi;

import android.support.v4.app.Fragment;

public class DataActivity  extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return   new DataActivityFragment();
    }
}
