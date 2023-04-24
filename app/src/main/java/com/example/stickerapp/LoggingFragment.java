package com.example.stickerapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class LoggingFragment extends Fragment {

    private static final int REQUEST_PERMISSION = 1;
    PermissionsHandler permissionsHandler = new PermissionsHandler();
    FusedLocationProviderClient locationClient;

    private TextView foundWhereText;
    private Button hereButton; private Button somewhereElseButton;
    private TextView removedText;
    private Button yesButton; private Button noButton;
    private Button locationButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_logging, container, false);

        locationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        foundWhereText = v.findViewById(R.id.foundWhereText);
        hereButton = v.findViewById(R.id.hereButton);
        somewhereElseButton = v.findViewById(R.id.somewhereElseButton);
        removedText = v.findViewById(R.id.removedText);
        yesButton = v.findViewById(R.id.yesButton); noButton = v.findViewById(R.id.noButton);
        locationButton = v.findViewById(R.id.locationButton);

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionsHandler.requestPermissions(getActivity(), REQUEST_PERMISSION);
                if (permissionsHandler.hasPermissions(getActivity())) {
                    getLastLocation();
                }
            }
        });

        return v;
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager= (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @SuppressLint("MissingPermission")  // necessary
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {
            // check if location is enabled
            if (isLocationEnabled()) {
                locationClient.getLastLocation().addOnCompleteListener(task -> {
                    Location location = task.getResult();
                    if (location == null) {
                        requestNewLocationData();
                    } else {
                        //latitude.setText("Latitude.   : "+location.getLatitude());
                        //longitude.setText("Longitude.: "+location.getLongitude());
                        Toast.makeText(getActivity(), "Your latitude: " + location.getLatitude(), Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(getActivity(), "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        LocationRequest mLocationRequest= LocationRequest.create()
                .setInterval(5)
                .setFastestInterval(0)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(1);

        locationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        locationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private final LocationCallback mLocationCallback= new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation= locationResult.getLastLocation();
            //latitude.setText("Latitude: " + mLastLocation.getLatitude() + "");
            //longitude.setText("Longitude: " + mLastLocation.getLongitude() + "");
        }
    };

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
    }

    /*@Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }*/

    //TODO: Put this back in later after refactoring
   /* @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {  getLastLocation();   }
    }*/

}