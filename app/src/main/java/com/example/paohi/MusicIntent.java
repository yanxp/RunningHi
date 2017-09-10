package com.example.paohi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import java.util.prefs.AbstractPreferences;

/**
 * Created by 张鑫 on 2015/8/10.
 */
public class MusicIntent extends IntentTool {
    private PlayerService ps;
    private Context context;
    private Boolean isBind=false;

    public MusicIntent(Context context) {
        super(context);
        Intent it = new Intent(context,PlayerService.class);
        context.bindService(it,cnn,Context.BIND_AUTO_CREATE);
        this.context=context;
        isBind=true;
        if(ps==null)
        {
            Log.d("service", "null");
        }
    }
    public Boolean isBound()
    {
        return isBind;

    }
    public void StartIntent(Intent intent)
    {
        ps.onStartMusic(intent);
    }
    private ServiceConnection cnn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ps = ((PlayerService.MsgBinder) service).getService();
            Log.d("service", "init");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind=false;
        }
    };
    public void destroy()
    {
        if(isBind)
            context.unbindService(cnn);
    }

}
