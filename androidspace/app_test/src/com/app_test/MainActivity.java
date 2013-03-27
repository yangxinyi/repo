package com.app_test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textLatitude;
	private TextView textLongitude;

	private LocationListener locationListener = new LocationListener() {

		public void onLocationChanged(Location location) {
			if (location != null) {
				textLatitude.setText("Latitude2:" + location.getLatitude());
				textLongitude.setText("Longitude2:" + location.getLongitude());
			}
		}

		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*textLatitude = (TextView) findViewById(R.id.lati_str);
		textLongitude = (TextView) findViewById(R.id.longi_str);
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		for(String provider:locationManager.getAllProviders()) {
			System.out.println("providers:"+provider);
		}
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
		}*/
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
	}

	private String findBestProvider(LocationManager locationManager) {
		Criteria criteria = new Criteria();  
	    criteria.setAccuracy(Criteria.ACCURACY_FINE);
	    criteria.setCostAllowed(false);
	    criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);//中文注释能看吗
	
	    String provider = locationManager.getBestProvider(criteria, true);
	    System.out.println("best provider is " + provider);
	    return provider;
	}
	
	public void sendMessage(View view) throws Exception {
		System.out.println("http requeset begin");
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://192.168.1.104:8080/findUserTest");
	    List<NameValuePair> parameters = new ArrayList<NameValuePair>();  
	    parameters.add(new BasicNameValuePair("id", "-1"));  
	    post.setEntity(new UrlEncodedFormEntity(parameters)); 
	    HttpResponse response = client.execute(post);
	    System.out.println("http_res:" + EntityUtils.toString(response.getEntity()));;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
