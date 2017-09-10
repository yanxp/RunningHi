package com.example.paohi;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class RunOverActivityFragment extends Fragment{
	private Button mshare,msave,mdelete,mdata,mpen;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.run_over, parent, false);
		msave= (Button) v.findViewById(R.id.save);
		mshare= (Button) v.findViewById(R.id.share);
		mdata= (Button) v.findViewById(R.id.completedata);
		mdelete= (Button) v.findViewById(R.id.delete);
		mpen= (Button) v.findViewById(R.id.pen);
		msave.setOnClickListener(new  OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),MainPage.class);
				startActivity(intent);
			}
		});
		mshare.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getActivity(),ShareActivity.class);
				startActivity(i);
			}
		});
		mdelete.setOnClickListener(new OnClickListener() {
			@TargetApi(Build.VERSION_CODES.CUPCAKE)
			@Override
			public void onClick(View v) {
				AlertDialog ad=new AlertDialog.Builder(getActivity()).create();
				ad.setTitle("是否退出");
				ad.setIcon(R.drawable.ic_launcher);
				ad.setMessage("确认要删除本次跑步记录吗?");

				ad.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
				ad.setButton(DialogInterface.BUTTON_NEGATIVE,"取消",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});

				ad.show();
			}
		});

		mdata.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),DataActivity.class);
				startActivity(intent);
			}
		});
		return v;
	}
}
