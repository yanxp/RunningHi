package com.example.paohi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ’≈ˆŒ on 2015/8/17.
 */
public class rundatakongjian extends RelativeLayout {
    private Context context;
    private TextView lucheng, kaluoli, shijian;
    private View v;

    public rundatakongjian(Context context) {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.rundatakongjian, this);
        init();
    }

    public rundatakongjian(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.rundatakongjian, this);
        init();
    }

    private void init() {
        v = LayoutInflater.from(context).inflate(R.layout.rundatakongjian, null);
       lucheng=(TextView)v.findViewById(R.id.rundatalucheng);
        kaluoli=(TextView)v.findViewById(R.id.rundatakaluoli);
        shijian=(TextView)v.findViewById(R.id.rundatashijian);
    }
   public void setTextTime(CharSequence cs)
   {
       shijian.setText(cs);
   }
    public void setTextKaluoli(CharSequence cs)
    {
        kaluoli.setText(cs);

    }
    public void setTextlucheng(CharSequence cs)
    {
        lucheng.setText(cs);
    }
}
