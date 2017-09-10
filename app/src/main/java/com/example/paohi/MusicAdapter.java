package com.example.paohi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Map;


public class MusicAdapter extends BaseAdapter {
    private LayoutInflater listContainer;
    private Context context;
    private MusicIntent     mi;
    private OnMusicChangeListener omc;
    private List<Map<String, Object>> listItems;
    public final class ListItemView{
        TextView bmp;
       TextView name;
        Button mnext;
        Button mload;
        String path;
    }
    public  void setOnMusicChangeListener(OnMusicChangeListener omc)
    {
        this.omc=omc;
    }

    public MusicAdapter(Context context, List<Map<String, Object>> listItem,MusicIntent mi) {
        listContainer = LayoutInflater.from(context);
        this.mi=mi;
        this.context = context;
        this.listItems=listItem;

    }


    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ListItemView listItemView;
        if (convertView == null) {
            convertView=listContainer.inflate(R.layout.musiclist,null);
            listItemView =new ListItemView();
            listItemView.name=(TextView)convertView.findViewById(R.id.musiclistname);
            listItemView.bmp=(TextView) convertView.findViewById(R.id.numbmp);
            listItemView.mnext=(Button)convertView.findViewById(R.id.next);
            listItemView.mload=(Button)convertView.findViewById(R.id.load);

            convertView.setTag(listItemView);
        }
        else
        {
            listItemView=(ListItemView)convertView.getTag();

        }
        listItemView.bmp.setText((String) listItems.get(position).get("bmp"));
        listItemView.name.setText((String ) listItems.get(position).get("name"));
        listItemView.path= (String )listItems.get(position).get("path");
        listItemView.mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.putExtra("url", listItemView.path);Log.d("path", listItemView.path);
                it.putExtra("MSG", AppConstant.PlayerMsg.PLAY_MSG);
                it.setClass(context, PlayerService.class);
                if(omc!=null) {

                    omc.ChangeName(listItemView.name.getText().toString(),listItemView.path);
                }
                mi.StartIntent(it);
//                context.startService(it);       //启动服务
            }
        });

        return convertView;
    }
}
