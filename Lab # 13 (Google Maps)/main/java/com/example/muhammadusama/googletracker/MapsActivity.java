package com.example.muhammadusama.googletracker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double longitude;
    private double latitude;
    Button currentLoc;

    LocationManager locationManager;
    LocationListener locationListener;

    private GoogleApiClient googleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // currentLoc = findViewById(R.id.moveToCurrentLocation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //    FusedLocationProviderClient fusedLocationProviderClient = getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);
//
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 2);
//        } else {
//            Toast.makeText(MapsActivity.this, "Permission Already Granted!", Toast.LENGTH_LONG).show();
//
//        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(MapsActivity.this, "onMapReady call", Toast.LENGTH_SHORT).show();
        mMap = googleMap;

        LatLng mySweetHome = new LatLng(25.3535828,68.36197439);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(mySweetHome)
                .zoom(16)
                .build();


        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }

//        mMap.setMyLocationEnabled(true);
//        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//            @Override
//            public void onMyLocationChange(Location location) {
//                Toast.makeText(MapsActivity.this,"onLocationChanged is called",Toast.LENGTH_SHORT).show();
//
//                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
//                CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);
//                mMap.clear();
//
//                MarkerOptions mp = new MarkerOptions();
//
//                mp.position(new LatLng(location.getLatitude(), location.getLongitude()));
//                mp.icon(BitmapDescriptorFactory.fromResource(R.drawable.home));
//                mp.title("my position");
//
//                mMap.addMarker(mp);
//                mMap.moveCamera(center);
//                mMap.animateCamera(zoom);
//
//            }
//        });


//        LatLng latifabad = new LatLng(25.354514, 68.358056);
//        MarkerOptions markerOptions = new MarkerOptions().position(latifabad).title("My sweet home");
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.home));
//        mMap.addMarker(markerOptions);


//        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_home_black_24dp)).position(latifabad).title("My Home"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latifabad));


//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(latifabad)
//                .zoom(15)
//                .build();
//        //move the map to selected marker
//        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


//        if (mMap == null) {
//            // Try to obtain the map from the SupportMapFragment.
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            mMap.setMyLocationEnabled(true);
//            // Check if we were successful in obtaining the map.
//            if (mMap != null) {
//
//
//                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//
//                    @Override
//                    public void onMyLocationChange(Location arg0) {
//                        // TODO Auto-generated method stub
//
//                        mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
//                    }
//                });
//
//            }

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {


                Toast.makeText(MapsActivity.this,"onLocationChanged",Toast.LENGTH_SHORT).show();
                mMap.clear();

                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

                mMap.addMarker(new MarkerOptions().position(userLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.homelocationmarker)).title("My Sweet Home"));


        //move the map to selected marker
             //  mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


               // mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));

                Toast.makeText(MapsActivity.this, userLocation.toString(), Toast.LENGTH_SHORT).show();
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


        };

        if (Build.VERSION.SDK_INT < 23) {


            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        }

//        else if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(MapsActivity.this,"else if ",Toast.LENGTH_SHORT).show();
//
//
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
//
//            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//            LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
//
//            mMap.clear();
//
//            mMap.addMarker(new MarkerOptions().position(userLocation).title("Marker"));
//
//
//
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
//
//            Toast.makeText(MapsActivity.this, userLocation.toString(), Toast.LENGTH_SHORT).show();
//
//
//        }

        else {

            Toast.makeText(MapsActivity.this,"else",Toast.LENGTH_SHORT).show();

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }
        }
    }




}
