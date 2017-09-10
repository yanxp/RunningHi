package com.example.paohi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxp on 2015/7/23.
 */
public class SinglerunActivityFragment extends Fragment{
    private Button mshare;
    private ImageButton mback;
    private ListView mlist;
    private List<Map<String, Object>> listItems;
    private rundataAdapter adapter;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.singlerun, parent, false);
        mshare= (Button) v.findViewById(R.id.share);
        mback= (ImageButton) v.findViewById(R.id.back);
        mlist= (ListView) v.findViewById(R.id.list);
        listItems=setlistdata();
        adapter=new rundataAdapter(getActivity(),listItems);
        mlist.setAdapter(adapter);

        mback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainPage.class);
                startActivity(intent);
            }
        });
        mshare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ShareActivity.class);
                startActivity(intent);
            }
        });
        return v;

    }
    private List<Map<String, Object>> setlistdata() {
        listItems = new ArrayList<>();
        for(int i=0;i<10;i++)
        { Map<String,Object> map=new HashMap<String,Object>();
            map.put("digit",i+"");
            map.put("time","5:15'");
            map.put("overtime","10:56'");
            map.put("image",R.drawable.star);
            listItems.add(map);
        }
        return listItems;
    }
}
