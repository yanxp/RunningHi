package com.example.paohi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by yxp on 2015/8/31.
 */
public class musictitle extends LinearLayout{
    private Context context;
    public musictitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.musictitle,this);
    }

    public musictitle(Context context) {
        super(context);
        this.context=context;
    }
}
