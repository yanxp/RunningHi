package com.example.paohi;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by ’≈ˆŒ on 2015/8/17.
 */
public class RunService extends Service {
    private Boolean isRunning=false;
    public static final int startrun=1;
    public static final int stoprun=2;
    public static final int reset=3;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        start(intent);
       return  super.onStartCommand(intent,flags,startId);
    }
    public void start(Intent intent)
    {
            int command=intent.getIntExtra("command",0);
            switch (command)
            {
              case(RunService.startrun):


                        break;
                case(RunService.stoprun):

                        break;
                case(RunService.reset):

                        break;

                default:break;
            }
    }

}
