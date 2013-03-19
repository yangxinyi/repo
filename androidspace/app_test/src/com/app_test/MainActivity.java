package com.app_test;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("8023","loginfo");
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        	System.out.println("enter gps_provider");
	        Location location = locationManager  
	        .getLastKnownLocation(LocationManager.GPS_PROVIDER);  
	        if(location != null){  
	        	System.out.println("gps:" + location.getLatitude() + " , " + location.getLongitude());
	        }  
        }else{  
        	System.out.println("enter network_provider");
	        LocationListener locationListener = new LocationListener() {  
		        public void onLocationChanged(Location location) { //������ı�ʱ�����˺��������Provider������ͬ�����꣬���Ͳ��ᱻ����  
			        if (location != null) {  
			        	System.out.println("network:" + location.getLatitude() + " , " + location.getLongitude());
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
	        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 0,locationListener); 
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
