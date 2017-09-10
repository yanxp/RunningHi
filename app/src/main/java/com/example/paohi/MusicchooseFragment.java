package com.example.paohi;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicchooseFragment extends Fragment {
    private ListView musiclist;
    private Button mback;
    private Button mclear;
    private TextView mbmp;
    private File[] mfile;
    private TextView mmusicname;
    private Context context;
    private MusicIntent mi;
    private PlayerService ps;
    private CharSequence mMusicFilePath;


    @Override
    public void onAttach(Activity at) {
        context =getActivity().getApplicationContext();
        try {
            mi = new MusicIntent(context);
        }
        finally {
            if(ps==null)
                Log.d("bug","start");
        }
        super.onAttach(at);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        initFile();

        View v = inflater.inflate(R.layout.musicchoose, parent, false);
        Intent intent=getActivity().getIntent();
        musiclist = (ListView) v.findViewById(R.id.musiclist);
        mmusicname = (TextView) v.findViewById(R.id.musicname);
        mback = (Button) v.findViewById(R.id.back);
        mclear = (Button) v.findViewById(R.id.clear);
        List<Map<String, Object>> mlt = getListItems();
        if(intent.getCharSequenceExtra("path")!=null)
        {
            mmusicname.setText(intent.getCharSequenceExtra("name"));
            mMusicFilePath=intent.getCharSequenceExtra("path");
        }
        else{
        mmusicname.setText("未选择");}
        MusicAdapter madapter = new MusicAdapter(context, mlt,mi);
        madapter.setOnMusicChangeListener(new OnMusicChangeListener() {
            @Override
            public void ChangeName(String name,String path) {
                mmusicname.setText(name);
                mMusicFilePath=path;
            }
        });
        mclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmusicname.setText("未选择");
                mMusicFilePath=null;
                Intent it = new Intent();
                it.putExtra("MSG", AppConstant.PlayerMsg.STOP_MSG);
                it.setClass(context, PlayerService.class);

              mi.StartIntent(it);

            }
        });
        musiclist.setAdapter(madapter);
        MediaPlayer mp = new MediaPlayer();
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getActivity(), MainPage.class);
                if(mMusicFilePath!=null)
                {
                    b.putExtra("path",mMusicFilePath);
                    b.putExtra("name",mmusicname.getText());
                }
                getActivity().setResult(Activity.RESULT_OK, b);
                getActivity().finish();

            }
        });
        return v;
    }

    private List<Map<String, Object>> getListItems() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        getMusicFile(getSDPath() + File.separator + "paohi" + File.separator + "Music");
        for (File mf : mfile) {
            map = new HashMap<String, Object>();
            map.put("bmp", "160");
            map.put("name", mf.getName());
            map.put("path", mf.getPath());
            listItems.add(map);
        }
        return listItems;
    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
            Log.d("sdcard", sdDir.getPath());
        }
        for (File f : sdDir.listFiles()) {
            Log.i("file", f.getName());
        }
        return sdDir.toString();

    }

    private void initFile() {
        try {
            File filetemp = new File(getSDPath() + File.separator + "Paohi" + File.separator);

            if (!filetemp.exists())
                filetemp.mkdir();
            filetemp = new File(filetemp.getPath() + File.separator + "Music" + File.separator);
            if (!filetemp.exists())
                filetemp.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMusicFile(String Path) {
        try {
            File dir = new File(Path);
            if (!dir.exists()) {
                dir.mkdir();
            }
            FileFilter fft = new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().matches("^.*?\\.(mp3|wma)$");
                }
            };
            mfile = dir.listFiles(fft);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        mi.destroy();
        super.onDestroy();
    }


}