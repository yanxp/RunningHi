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
	//��д��ʾ�������ֵ���ݵı�ǩ��Сд��ʾ��ʾ��ǩ
	private TextView mgrade,mfive,mdistance,mlongtime,mmatch,mKM,mTimeText,mMatchFast,mFastestTime;
	private DatePicker mdatepicker;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.analy_activity, parent, false);
		mback= (ImageButton) v.findViewById(R.id.back);
		mKM= (TextView) v.findViewById(R.id.KM);//��Զ���������ֵ��ǩ
		mTimeText= (TextView) v.findViewById(R.id.textTime);//�ʱ�������ֵ��ǩ
		mMatchFast= (TextView) v.findViewById(R.id.matchsu);//������پ�����ֵ��ǩ
		mFastestTime= (TextView) v.findViewById(R.id.fastestTime);//�幫�����ʱ�������ֵ��ǩ
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

