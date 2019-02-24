package com.example.muhammadusama.locationbaseservicers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPSClass extends Activity implements LocationListener {

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsclass);
        txtLat = (TextView) findViewById(R.id.textview1);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


    }
    public void movetoHome(View view)
    {
        Intent intent = new Intent(GPSClass.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLocationChanged(Location location) {
        txtLat = (TextView) findViewById(R.id.textview1);
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());

        Geocoder geocoder = new Geocoder(GPSClass.this, Locale.ENGLISH);
        try {
//                          List<Address> getLocation = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
//                          Toast.makeText(MainActivity.this,getLocation.toString(),Toast.LENGTH_SHORT).show();


            List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String result = null;
            if (list != null && list.size() > 0) {
                Address address = list.get(0);
                // sending back first address line and locality
                result = address.getAddressLine(0) + ", " + address.getLocality();
                Toast.makeText(GPSClass.this, result, Toast.LENGTH_SHORT).show();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

        Toast.makeText(GPSClass.this,"onStatusChanged",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(GPSClass.this,"onProviderEnabled",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(GPSClass.this,"onProviderDisabled",Toast.LENGTH_SHORT).show();

    }
}


