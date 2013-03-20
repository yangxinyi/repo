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

		// ������ı�ʱ�����˺��������Provider������ͬ�����꣬���Ͳ��ᱻ����
		public void onLocationChanged(Location location) {
			if (location != null) {
				textLatitude.setText("Latitude2:" + location.getLatitude());
				textLongitude.setText("Longitude2:" + location.getLongitude());
			}
		}

		public void onProviderDisabled(String provider) {
			// Provider��disableʱ�����˺���������GPS���ر�
		}

		public void onProviderEnabled(String provider) {
			// Provider��enableʱ�����˺���������GPS����
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// Provider��ת̬�ڿ��á���ʱ�����ú��޷�������״ֱ̬���л�ʱ�����˺���
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
	    criteria.setAccuracy(Criteria.ACCURACY_FINE);// �߾���  
	    criteria.setCostAllowed(false);// ������������ʷ�  
	    criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);// �͹���  
	
	    String provider = locationManager.getBestProvider(criteria, true);// ��ȡGPS��Ϣ
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
