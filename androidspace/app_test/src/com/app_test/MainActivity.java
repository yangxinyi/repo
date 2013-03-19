package com.app_test;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        	System.out.println("enter gps_provider");
	        Location location = locationManager  
	        .getLastKnownLocation(LocationManager.GPS_PROVIDER);  
	        if(location != null){  
	        	printLocation(location);
	        }  
        }else{  
        	System.out.println("enter network_provider");
	        LocationListener locationListener = new LocationListener() {  
		        public void onLocationChanged(Location location) { //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发  
			        if (location != null) {  
			        	printLocation(location);
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
	        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 0,locationListener); 
       }
    }

    private void printLocation(Location location) {
    	System.out.println("Location changed : Lat: "  
		        + location.getLatitude() + " Lng: "  
		        + location.getLongitude());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
