package com.app_test;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textLatitude;
	private TextView textLongitude;

	private LocationListener locationListener = new LocationListener() {

		// 当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
		public void onLocationChanged(Location location) {
			if (location != null) {
				textLatitude.setText("Latitude2:" + location.getLatitude());
				textLongitude.setText("Longitude2:" + location.getLongitude());
			}
		}

		public void onProviderDisabled(String provider) {
			// Provider被disable时触发此函数，比如GPS被关闭
		}

		public void onProviderEnabled(String provider) {
			// Provider被enable时触发此函数，比如GPS被打开
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// Provider的转态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textLatitude = (TextView) findViewById(R.id.lati_str);
		textLongitude = (TextView) findViewById(R.id.longi_str);
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		/*for(String provider:locationManager.getAllProviders()) {
			System.out.println("providers:"+provider);
		}*/
		findBestProvider(locationManager);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Location location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location != null) {
				System.out.println("last gps:" + location.getLatitude() + " , "
						+ location.getLongitude());
				textLatitude.setText("Latitude1:" + location.getLatitude());
				textLongitude.setText("Longitude1:" + location.getLongitude());
			}
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		} else {
			Location location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if (location != null) {
				System.out.println("last net:" + location.getLatitude() + " , "
						+ location.getLongitude());
				textLatitude.setText("Latitude3:" + location.getLatitude());
				textLongitude.setText("Longitude3:" + location.getLongitude());
			}
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		}
	}

	private String findBestProvider(LocationManager locationManager) {
		Criteria criteria = new Criteria();  
	    criteria.setAccuracy(Criteria.ACCURACY_FINE);// 高精度  
	    criteria.setCostAllowed(false);// 设置允许产生资费  
	    criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);// 低功耗  
	
	    String provider = locationManager.getBestProvider(criteria, true);// 获取GPS信息
	    System.out.println("best provider is " + provider);
	    return provider;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
