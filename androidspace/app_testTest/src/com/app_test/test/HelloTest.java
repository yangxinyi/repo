package com.app_test.test;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import android.os.Bundle;

import com.app_test.MainActivity;

public class HelloTest extends ActivityInstrumentationTestCase2<MainActivity>{

	private Activity mActivity;
	private TextView mView;
	private String resourceString;
	double latitude; //经度
	double longitude;//维度
	
	public HelloTest() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
    protected void setUp() throws Exception {
        super.setUp();

        mActivity = this.getActivity();

        mView = (TextView) mActivity.findViewById(com.app_test.R.id.text_str);

        resourceString = mActivity.getString(com.app_test.R.string.hello_world);
    }

	public void testPreconditions() {

		assertNotNull(mView);

    }

	public void testText() {
		System.out.println("test starting");
		LocationManager locationManager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);  
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {  
        	System.out.println("enter gps_provider");
	        Location location = locationManager  
	        .getLastKnownLocation(LocationManager.GPS_PROVIDER);  
	        if(location != null){  
	        	latitude = location.getLatitude(); //经度  
	        	longitude = location.getLongitude(); //纬度  
	        }  
        }else{  
	        LocationListener locationListener = new LocationListener() {  
		        public void onLocationChanged(Location location) { //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发  
			        if (location != null) {  
				        System.out.println("Location changed : Lat: "  
				        + location.getLatitude() + " Lng: "  
				        + location.getLongitude());  
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
		assertEquals(resourceString, (String)mView.getText());
    } 

}
