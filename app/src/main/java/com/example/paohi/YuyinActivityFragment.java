package com.example.paohi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class YuyinActivityFragment extends Fragment {
	private Spinner goalspinner,testspinner;
	private ImageButton mback;
	private Button mclear;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.yuyin, parent, false);
        goalspinner=(Spinner)v. findViewById(R.id.goalspinner);
        testspinner=(Spinner) v.findViewById(R.id.testspinner);
		mback=(ImageButton) v.findViewById(R.id.back);
		mclear=(Button) v.findViewById(R.id.clear);
		   List<String> list=new ArrayList<String>() ;
		   list.add("路程");
		   list.add("时间");
		   list.add("卡路里");

		   List<String> list2=new ArrayList<String>();
		   list2.add("20%");
		   list2.add("40%");
		   list2.add("60%");
		   list2.add("80%");
		   list2.add("100%");
		 ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,list);
		 goalspinner.setAdapter(adapter);
		   ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,list2);
		   testspinner.setAdapter(adapter2);
		   return v;		}
			
     
	}