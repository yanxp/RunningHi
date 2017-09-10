package com.example.paohi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TimerFragmentActivity extends Fragment {
    private long mlCount = 0;
    private long mlTimerUnit = 100;
    private TextView tvTime;
    private ImageButton btnStartPause;
    private ImageButton btnStop;
    private Timer timer = null;
    private TimerTask task = null;
    private Handler handler = null;
    private Message msg = null;
    private boolean bIsRunningFlg = false;
    private static final String MYTIMER_TAG = "MYTIMER_LOG";

    private static final int SETTING_TIMER_UNIT_ID = Menu.FIRST;
    private static final int SETTING_SECOND_ID = Menu.FIRST + 101;
    private static final int SETTING_100MILLISECOND_ID = Menu.FIRST + 102;

    // Setting timer unit flag
    private int settingTimerUnitFlg = SETTING_100MILLISECOND_ID;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.timer, parent, false);
        tvTime = (TextView)v.findViewById(R.id.tvTime);
        btnStartPause = (ImageButton)v.findViewById(R.id.btnStartPaunse);
        btnStop = (ImageButton)v.findViewById(R.id.btnStop);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mytimer_unit", Context.MODE_PRIVATE);

        mlTimerUnit = sharedPreferences.getLong("time_unit", 100);

        if (1000 == mlTimerUnit) {
            // second
            settingTimerUnitFlg = SETTING_SECOND_ID;
            tvTime.setText(R.string.init_time_second);
        } else if (100 == mlTimerUnit) {
            // 100 millisecond
            settingTimerUnitFlg = SETTING_100MILLISECOND_ID;
            tvTime.setText(R.string.init_time_100millisecond);
        }

        // Handle timer message
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                switch(msg.what) {
                    case 1:
                        mlCount++;
                        int totalSec = 0;
                        int yushu = 0;

                        if (SETTING_SECOND_ID == settingTimerUnitFlg) {
                            // second
                            totalSec = (int)(mlCount);
                        } else if (SETTING_100MILLISECOND_ID == settingTimerUnitFlg) {
                            // 100 millisecond
                            totalSec = (int)(mlCount / 10);
                            yushu = (int)(mlCount % 10);
                        }

                        // Set time display
                        int min = (totalSec / 60);
                        int sec = (totalSec % 60);
                        try{
                            if (SETTING_SECOND_ID == settingTimerUnitFlg) {
                                // second(1000ms)
                                tvTime.setText(String.format("%1$02d:%2$02d", min, sec));
                            } else if (SETTING_100MILLISECOND_ID == settingTimerUnitFlg) {
                                // 100 millisecond
                                tvTime.setText(String.format("%1$02d:%2$02d:%3$d", min, sec, yushu));
                            }
                        } catch(Exception e) {
                            tvTime.setText("" + min + ":" + sec + ":" + yushu);
                            e.printStackTrace();

                        }
                        break;

                    default:
                        break;
                }

                super.handleMessage(msg);
            }

        };

        btnStartPause.setOnClickListener(startPauseListener);
        btnStop.setOnClickListener(stopListener);
        return v;
    }

    // Start and pause
    View.OnClickListener startPauseListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //  Log.i(MYTIMER_TAG, "Start/Pause is clicked.");

            if (null == timer) {
                if (null == task) {
                    task = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            if (null == msg) {
                                msg = new Message();
                            } else {
                                msg = Message.obtain();
                            }
                            msg.what = 1;
                            handler.sendMessage(msg);
                        }

                    };
                }

                timer = new Timer(true);
                timer.schedule(task, mlTimerUnit, mlTimerUnit); // set timer duration
            }

            // start
            if (!bIsRunningFlg) {
                bIsRunningFlg = true;
                btnStartPause.setImageResource(R.drawable.pausetimer);
            } else { // pause
                try{
                    bIsRunningFlg = false;
                    task.cancel();
                    task = null;
                    timer.cancel(); // Cancel timer
                    timer.purge();
                    timer = null;
                    handler.removeMessages(msg.what);
                    btnStartPause.setImageResource(R.drawable.starttimer);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

    };
    // Stop
    View.OnClickListener stopListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            if (null != timer) {
                task.cancel();
                task = null;
                timer.cancel(); // Cancel timer
                timer.purge();
                timer = null;
                handler.removeMessages(msg.what);
            }
            mlCount = 0;
            bIsRunningFlg = false;
            btnStartPause.setImageResource(R.drawable.starttimer);

            if (SETTING_SECOND_ID == settingTimerUnitFlg) {
                // second
                tvTime.setText(R.string.init_time_second);
            } else if (SETTING_100MILLISECOND_ID == settingTimerUnitFlg) {
                // 100 millisecond
                tvTime.setText(R.string.init_time_100millisecond);
            }
        }

    };
}