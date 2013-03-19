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
	double latitude; //����
	double longitude;//ά��
	
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
	        	latitude = location.getLatitude(); //����  
	        	longitude = location.getLongitude(); //γ��  
	        }  
        }else{  
	        LocationListener locationListener = new LocationListener() {  
		        public void onLocationChanged(Location location) { //������ı�ʱ�����˺��������Provider������ͬ�����꣬���Ͳ��ᱻ����  
			        if (location != null) {  
				        System.out.println("Location changed : Lat: "  
				        + location.getLatitude() + " Lng: "  
				        + location.getLongitude());  
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
		assertEquals(resourceString, (String)mView.getText());
    } 

}
