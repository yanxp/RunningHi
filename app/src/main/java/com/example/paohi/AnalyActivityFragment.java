package com.example.paohi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

public class AnalyActivityFragment extends Fragment {
   private ImageButton mback;
	//大写表示具体的数值内容的标签，小写表示提示标签
	private TextView mgrade,mfive,mdistance,mlongtime,mmatch,mKM,mTimeText,mMatchFast,mFastestTime;
	private DatePicker mdatepicker;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.analy_activity, parent, false);
		mback= (ImageButton) v.findViewById(R.id.back);
		mKM= (TextView) v.findViewById(R.id.KM);//最远距离具体数值标签
		mTimeText= (TextView) v.findViewById(R.id.textTime);//最长时间具体数值标签
		mMatchFast= (TextView) v.findViewById(R.id.matchsu);//最快配速具体数值标签
		mFastestTime= (TextView) v.findViewById(R.id.fastestTime);//五公里最快时间具体数值标签
		mback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), MainPage.class);
				startActivity(intent);
			}
		});

		return v;
	}
}

