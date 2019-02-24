package com.example.muhammadusama.locationbaseservicers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MainActivity extends Activity {
    private TextView latitude;
    private TextView longitude;
    private TextView choice;
    private CheckBox fineAcc;
    private Button choose;
    private TextView provText;
    private LocationManager locationManager;
    private String provider;
    // private MyLocationListener mylistener;
    private Criteria criteria;

    /**
     * Called when the activity is first created.
     */

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startLocationUpdates();
        FusedLocationProviderClient fusedLocationProviderClient = getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        } else {
            Toast.makeText(MainActivity.this, "Permission Already Granted!", Toast.LENGTH_LONG).show();
            startLocationUpdates();
        }

//        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null) {
//
//                    Toast.makeText(MainActivity.this, "Your location Successfully find ", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(MainActivity.this, "Longitude: " + location.getLongitude() + "\nLatitude: " + location.getLatitude() + "\nAccuracy: " + location.getAccuracy(), Toast.LENGTH_SHORT).show();
//
//                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.ENGLISH);
//                    try {
////                          List<Address> getLocation = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
////                          Toast.makeText(MainActivity.this,getLocation.toString(),Toast.LENGTH_SHORT).show();
//
//
//
//                        List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                        String result = null;
//                        if (list != null && list.size() > 0) {
//                            Address address = list.get(0);
//                            // sending back first address line and locality
//                            result = address.getAddressLine(0) + ", " + address.getLocality();
//                            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
//
//                        }
//
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
//                } else {
//                    Toast.makeText(MainActivity.this, "Your location contains nothing ", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });

//        fusedLocationProviderClient.getLastLocation().addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MainActivity.this, "ON Failure runs... ", Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2: {
                // Check if the permission is granted.
            }
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted.
                Toast.makeText(MainActivity.this, "Permission Granted!", Toast.LENGTH_LONG).show();

            } else {
                // Permission was denied...
                Toast.makeText(MainActivity.this, "Permission Denied!", Toast.LENGTH_LONG).show();
            }

        }
    }


    @SuppressLint("MissingPermission")
    protected void startLocationUpdates() {
        // Create the location request to start receiving updates
        @SuppressLint("RestrictedApi") LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(10 * 1000);
        mLocationRequest.setFastestInterval(2000);
//        if (ActivityCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
//                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
//            return;
//        }
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location location = locationResult.getLastLocation();
                // String msg = "Updated Location: " + Double.toString(location.getLatitude()) + "," + Double.toString(location.getLongitude());
                // Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.ENGLISH);
                try {
//                          List<Address> getLocation = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
//                          Toast.makeText(MainActivity.this,getLocation.toString(),Toast.LENGTH_SHORT).show();


                    List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    String result = null;
                    if (list != null && list.size() > 0) {
                        Address address = list.get(0);
                        // sending back first address line and locality
                        result = address.getAddressLine(0) + ", " + address.getLocality();
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };
        getFusedLocationProviderClient(this).requestLocationUpdates(
                mLocationRequest, locationCallback, null);


    }

    public void movetogps(View view) {
        Intent intent = new Intent(MainActivity.this, GPSClass.class);
        startActivity(intent);
        finish();
    }
}