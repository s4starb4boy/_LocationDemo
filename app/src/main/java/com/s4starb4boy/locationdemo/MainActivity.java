package com.s4starb4boy.locationdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);

        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {

            Log.i("Location Info: ", "Location achieved");
        } else {

            Log.i("Location Info: ", "No Location :(");
        }
    }


    //This method runs when app is running in backgroung and invocked when app is reactivated and location is requested again.
    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();


        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    //Following method runs when app is stopped or put into background.
    @Override
    protected void onPause() {
        super.onPause();

        locationManager.removeUpdates(this);
    }

    //following methid is used to get location as soon as it is updated.if you want to track the user location on map this method will be used.
    @Override
    public void onLocationChanged(Location location) {

        Double lat = location.getLatitude();
        Double lng = location.getLongitude();

        Log.i("Latitude: ", lat.toString());
        Log.i("Longitude: ", lng.toString());

    }

    //Following methoid is used when location is re available after a short while of it wasn't.
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    //Following method is used if GPS location is available.
    @Override
    public void onProviderEnabled(String s) {

    }

    //Following method is used if GPS location is not available.
    @Override
    public void onProviderDisabled(String s) {

    }
}
