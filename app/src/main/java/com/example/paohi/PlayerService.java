package com.example.paohi;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

public class PlayerService extends Service {
    private MediaPlayer mediaPlayer =  new MediaPlayer();
    private String path;
    private boolean isPause;

    public void onCreate() {
        Toast.makeText(this, "MusicSevice onCreate()"
                , Toast.LENGTH_SHORT).show();
        Log.d("service", "MusicSerice onCreate()");
            mediaPlayer = new MediaPlayer();
        mediaPlayer.setLooping(true);
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return new MsgBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       onStartMusic(intent);
        return super.onStartCommand(intent, flags, startId);
    }
    public void onStartMusic(Intent intent) {
        int msg = intent.getIntExtra("MSG", 0);
        Log.d("msg:",msg+"");
        if(mediaPlayer.isPlaying()) {

            stop();
        }
        if(msg == AppConstant.PlayerMsg.STOP_MSG) {
            stop();
            return;
        }
        Log.d("service","start");
        path = intent.getStringExtra("url");

        if(msg == AppConstant.PlayerMsg.PLAY_MSG) {
           play(0);
        } else if(msg == AppConstant.PlayerMsg.PAUSE_MSG) {
            pause();
        }

//        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * ��������
     * @param position
     */
    private void play(int position) {
        try {
            mediaPlayer.reset();//�Ѹ�������ָ�����ʼ״̬
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            mediaPlayer.setDataSource(fis.getFD());
            Log.d("music", path);
            mediaPlayer.prepare();	//���л���
            mediaPlayer.setOnPreparedListener(new PreparedListener(position));//ע��һ��������

// mediaPlayer.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ͣ����
     */
    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPause = true;
        }
    }

    /**
     * ֹͣ����
     */
    private void stop(){
        if(mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare(); // �ڵ���stop�������Ҫ�ٴ�ͨ��start���в���,��Ҫ֮ǰ����prepare����
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onDestroy() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
    /**
     *
     * ʵ��һ��OnPrepareLister�ӿ�,������׼���õ�ʱ��ʼ����
     *
     */
    private final class PreparedListener implements OnPreparedListener {
        private int positon;

        public PreparedListener(int positon) {
            this.positon = positon;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.start();
            if(positon > 0) {
                mediaPlayer.seekTo(positon);
            }
        }
    }
    public class MsgBinder extends Binder {
        public PlayerService getService(){
            return  PlayerService.this;
        }
    }


}
