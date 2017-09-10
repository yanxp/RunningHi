package com.example.paohi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ’≈ˆŒ on 2015/8/16.
 */
public class main_musicchoose extends RelativeLayout {
    private TextView tv;
    private Button btn;
    private Context context;

    public main_musicchoose(Context context) {
        super(context);
        this.context=context;
        init();
    }
    public main_musicchoose(Context context,AttributeSet attrs)
    {
        super(context,attrs);
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.main_musickongjian, this, true);

        init();
    }
    public void init()
    {
        View v=LayoutInflater.from(context).inflate(R.layout.main_musickongjian,null);
       // btn=(Button)v.findViewById(R.id.tomusicchoose);
        tv=(TextView)v.findViewById(R.id.musicofchoose);
//        btn.setFocusable(true);
//        btn.requestFocus();
    }
    public void setText(CharSequence s)
    {
        tv.setText(s);
    }
    public void setClickListener(OnClickListener on)
    {
        btn.setOnClickListener(on);


    }


}
