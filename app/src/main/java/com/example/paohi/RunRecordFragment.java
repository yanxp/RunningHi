package com.example.paohi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by yxp on 2015/8/4.
 */
public class RunRecordFragment extends Fragment {
    private ImageButton mback,mleft,mright,mright_one,mright_two;
    private Button mdata;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.run_recorde, parent, false);
        mback= (ImageButton) v.findViewById(R.id.back);
        mright_one= (ImageButton) v.findViewById(R.id.right_one);
        mright_two= (ImageButton) v.findViewById(R.id.right_two);
        mdata= (Button) v.findViewById(R.id.completedata);
        mback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MainPage.class);
                startActivity(intent);
            }
        });
        mright_one.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SinglerunActivity.class);
                startActivity(intent);
            }
        });
        mright_two.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SinglerunActivity.class);
                startActivity(intent);
            }
        });

        mdata.setOnClickListener(new  OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),AnalyActivity.class);
                startActivity(i);

            }
        });

        return v;
    }
}