package com.example.paohi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RunActivityFragment extends Fragment {
	private Button mstop,mnext,mback,mdata,mStop,mOver;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.run_activity_fragment, parent, false);
		mstop= (Button) v.findViewById(R.id.stop);
		mnext= (Button) v.findViewById(R.id.next);
		mback= (Button) v.findViewById(R.id.back);
		mdata= (Button) v.findViewById(R.id.data);
		mStop= (Button) v.findViewById(R.id.stop2);
		mOver= (Button) v.findViewById(R.id.over);
		mback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent=new Intent(getActivity(),MainPage.class);
				startActivity(intent);
			}
		});

		mdata.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),RunOverDataActivity.class);
				startActivity(intent);

			}
		});
		return v;
	}
}