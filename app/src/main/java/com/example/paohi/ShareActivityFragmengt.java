package com.example.paohi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import  android.view.View.OnClickListener;
public class ShareActivityFragmengt extends Fragment{
	private ImageButton mcamera,mdistace,mphoto;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.share_activity, parent, false);

		mcamera= (ImageButton)v.findViewById(R.id.camera);
		mdistace= (ImageButton)v.findViewById(R.id.distance);
		mphoto= (ImageButton)v.findViewById(R.id.photo);



		mcamera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),ShareRunActivity.class);
				startActivity(intent);
			}
		});
    return v;
	}
}
