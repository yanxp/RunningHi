package com.example.paohi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ShareRunActivityFragment extends Fragment{
	private  ImageButton mback,mwechat,mweibo;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.share_run, parent, false);
		mback= (ImageButton) v.findViewById(R.id.back);
		mwechat= (ImageButton) v.findViewById(R.id.wechat);
		mweibo= (ImageButton) v.findViewById(R.id.weibo);
		mback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),RunActivity.class);
				startActivity(intent);

			}
		});
    return v;
	}
}