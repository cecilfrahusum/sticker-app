package com.example.stickerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapFragment  extends Fragment {

    private String apiKey = BuildConfig.API_KEY;

    private StickerDB stickerDB;
    private PermissionsHandler permissionsHandler = new PermissionsHandler();
    private FusedLocationProviderClient locationClient;

    private LatLng defaultLocation = new LatLng(55.658619, 12.589548);
    private final static int DEFAULT_ZOOM = 15;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(GoogleMap googleMap) {
            setAllMarkers(googleMap);
            if (permissionsHandler.hasPermissions(getActivity())) {
                // This bit is based on: https://developers.google.com/maps/documentation/android-sdk/current-place-tutorial#java_7
                // Please refactor it later.
                Task<Location> locationResult = locationClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> lastLocationTask) {
                        if (lastLocationTask.isSuccessful()) {
                            Location lastKnownLocation = lastLocationTask.getResult();
                            if (lastKnownLocation != null) {
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lastKnownLocation.getLatitude(),
                                                lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            } //TODO: else-statement to ask for location ???
                        } else {
                            googleMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                        }
                    }
                });
                googleMap.setMyLocationEnabled(true);
            } else {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                //TODO: What happens if user does not give location permissions?
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        stickerDB = new ViewModelProvider(requireActivity()).get(StickerDB.class);
        locationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void setAllMarkers(GoogleMap googleMap) {
        for (Sticker sticker : stickerDB.getMarkers()) {
            String shout = "";
            if (sticker.getMessage().length() > 0) {
                shout = "A crew mate says: '" + sticker.getMessage() + "'";
            }
            googleMap.addMarker(new MarkerOptions().position(sticker.getLatLng()).title("Sticker removed on " + sticker.getDateString()).snippet(shout).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)).alpha(0.4f));
        }
    }

}