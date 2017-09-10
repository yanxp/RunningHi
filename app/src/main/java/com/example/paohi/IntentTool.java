package com.example.paohi;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

/**
 * Created by 张鑫 on 2015/8/10.
 */
public class IntentTool {
    private Context context;
    private ServiceConnection cnn;
    public IntentTool(Context context)
    {
        this.context=context;
    }
    public void BindService(Intent intent)
    {
        context.bindService(intent,cnn,Context.BIND_AUTO_CREATE);

    }
}
