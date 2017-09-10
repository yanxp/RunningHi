package com.example.paohi;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.jar.Attributes;

/**
 * Created by 张鑫 on 2015/8/12.
 */
public class huadongjiesuo extends FrameLayout implements View.OnClickListener {
    public huadongjiesuo(Context context) {
        super(context);
    }
    public huadongjiesuo(Context context,AttributeSet attrs)
    {
        super(context,attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.huadongjiesuo);


    }


    @Override
    public void onClick(View v) {

    }
}
