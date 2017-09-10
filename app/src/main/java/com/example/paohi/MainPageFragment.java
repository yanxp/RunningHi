package com.example.paohi;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;

import static com.example.paohi.R.id.relative;

public class MainPageFragment  extends Fragment implements LocationSource,AMapLocationListener {
	private TextView musicname;
	private Button mstart,mset,mchoose,mrecord,mremained,mback;
	private  main_musicchoose mmuchoose;
	private ImageView mred,myellow,mgreen;
	private MapView mapView;
	private AMap aMap;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	private rundatakongjian mrdk;
	private RelativeLayout mll,gps,mgps,mmusic;
	private LinearLayout mmainsetrecord;
	private LatLng mlatlng;
    private CharSequence Path;


	@Override
	public void onResume() {
		super.onResume();
        ;
		mapView.onResume();
        aMap.setLocationSource(this);
	}

	/**
	 * 此方法需存在
	 */
	@Override
	public void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
		{

      if(requestCode==0) {
		  Log.d("result", "start");
		  if (intent.getCharSequenceExtra("path") != null) {
			  Log.d("path1", intent.getCharSequenceExtra("path") + "");
			  musicname.setText(intent.getCharSequenceExtra("name"));
			  Path = intent.getCharSequenceExtra("path");
		  } else {
			  Log.d("path1", "null");
			  musicname.setText("未选择音乐/歌曲");
			  Path = null;
		  }

	  }
}

		/**
         * 此方法需存在
         */
	@Override
	public void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		Intent intent=getActivity().getIntent();
        View v = inflater.inflate(R.layout.main_page, parent, false);
		mapView = (MapView)v.findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 必须要写
		aMap = mapView.getMap();
		UiSettings us=aMap.getUiSettings();
		aMap.setLocationSource(this);
//		aMap.setMyLocationEnabled(false);
		mmuchoose=(main_musicchoose)v.findViewById(R.id.mmco);
        mstart=(Button)v. findViewById(R.id.start);
        mrecord=(Button)v. findViewById(R.id.record);
		mremained= (Button)v.findViewById(R.id.remaind);
		mback= (Button)v.findViewById(R.id.back);
        mset=(Button) v.findViewById(R.id.set);
		gps= (RelativeLayout) v.findViewById(R.id.relativeLayout2);
		mgps= (RelativeLayout) v.findViewById(R.id.gpssign);
		//mchoose= (Button) v.findViewById(R.id.tomusicchoose);
		mll=(RelativeLayout)v.findViewById(R.id.root);
		musicname=(TextView)v.findViewById(R.id.musicofchoose);
		mred= (ImageView) v.findViewById(R.id.red);
		mmusic= (RelativeLayout) v.findViewById(relative);
		//mlinebt= (LinearLayout) v.findViewById(R.id.linebt);
		mmainsetrecord= (LinearLayout) v.findViewById(R.id.mainsetrecord);

		mrdk=new rundatakongjian(getActivity());
//		mmuchoose.setClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Log.d("click","ok");
//				Intent intent=new Intent(getActivity(),Musicchoose.class);
//				startActivity(intent);
//			}
//		});

//		String html="对于运动爱好者而言，<a href='http://www.baidu.com'>软件</a>是记录分析用户运动数据的大脑，而数据则需要硬件系统采集。目前市场上的运动类的App基本都依靠手机作为数据采集的来源，只靠手机软件来管理运动面对当下种类繁多的运动类型很多时候都会有些力不从心，运动数据的采集还是需要靠可穿戴智能终端来进行。";
//         CharSequence charSequence=Html.fromHtml(html);
//         music.setText(charSequence);
//         music.setMovementMethod(LinkMovementMethod.getInstance());

//		us.setCompassEnabled(true);
//		us.setScrollGesturesEnabled(true);
//		us.setAllGesturesEnabled(true);
		us.setZoomControlsEnabled(false);
//		us.setZoomGesturesEnabled(true);


		aMap.setLocationSource(this);//设置定位监听
		us.setMyLocationButtonEnabled(false);// 是否显示定位按钮
		aMap.setMyLocationEnabled(true);// 是否可触发定位并显示定位层

		mstart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ObjectAnimator animator=ObjectAnimator.ofFloat(mgps,"Y",mgps.getY(),580);
				animator.setDuration(500);
				animator.start();
				animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						mgps.postInvalidate();
						mgps.invalidate();

					}
				});


				ObjectAnimator anmired=ObjectAnimator.ofFloat(mred,"alpha",1f,0.2f);
				anmired.setDuration(1000);
				anmired.start();

					RelativeLayout.LayoutParams mlp=new RelativeLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
					mlp.addRule(RelativeLayout.BELOW, R.id.mmco);
					mll.addView(mrdk, mlp);

					ObjectAnimator anim=ObjectAnimator.ofFloat(mrdk,"alpha",0f,1f);
					anim.setDuration(1500);
					anim.start();
					mll.removeView(mstart);
				  //  mll.removeView(mmainsetrecord);
				  /* RelativeLayout.LayoutParams rlp=new RelativeLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
				    rlp.addRule(RelativeLayout.BELOW, R.id.back);
				    mll.addView(mlinebt, rlp);*/


			}
		});
		mrecord.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getActivity(),RunRecordActivity.class);
				startActivity(i);
			}
		});
		mset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in=new Intent(getActivity(),UserSetActivity.class);
				startActivity(in);
			}
		});
		mremained.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent a=new Intent(getActivity(),RunActivity.class);
				startActivity(a);
			}
		});

		mback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Toast.makeText(getActivity(),"正在定位",Toast.LENGTH_LONG).show();
                if(mlatlng!=null){
				CameraUpdate cu= CameraUpdateFactory.changeLatLng(mlatlng);
				aMap.animateCamera(cu);}
//				aMap.setLocationSource(this);
			}
		});

		mmusic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), Musicchoose.class);
				if (Path != null) {
					intent.putExtra("name", musicname.getText());
					intent.putExtra("path", Path);
				}
				startActivityForResult(intent, 0);
			}
		});

        	return v;
  }


	@Override
	public void activate(OnLocationChangedListener  listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(getActivity());
			//此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			//注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
			//在定位结束后，在合适的生命周期调用destroy()方法
			//其中如果间隔时间为-1，则定位只定一次
			mAMapLocationManager.requestLocationData(
					LocationProviderProxy.AMapNetwork, 10*1000, 10, this);
		}

	}
	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destroy();
		}
		mAMapLocationManager = null;

	}

	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (mListener != null && amapLocation != null) {
			if (amapLocation.getAMapException().getErrorCode() == 0) {
				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
					mlatlng=new LatLng(amapLocation.getLatitude(),amapLocation.getLongitude());
				Log.d("data",amapLocation.getLatitude()+" "+amapLocation.getLongitude() );
			}
		}

	}

	@Override
	public void onLocationChanged(Location location) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onProviderDisabled(String provider) {

	}
}
