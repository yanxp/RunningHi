package com.example.paohi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by ’≈ˆŒ on 2015/8/17.
 */
public class hongludeng extends RelativeLayout {

    private  Context context;
    public hongludeng(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.hongludeng,this);
    }

    public hongludeng(Context context) {
        super(context);
        this.context=context;
    }
}
