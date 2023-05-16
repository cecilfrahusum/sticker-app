package com.example.stickerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
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

import java.sql.Date;

public class MapFragment  extends Fragment {

    StickerDB stickerDB;

    String apiKey = BuildConfig.API_KEY;
    private static final int REQUEST_PERMISSION = 1;
    PermissionsHandler permissionsHandler = new PermissionsHandler();
    FusedLocationProviderClient locationClient;

    LatLng startPos = new LatLng(55.658619, 12.589548);

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(GoogleMap googleMap) {
            permissionsHandler.requestPermissions(getActivity(), REQUEST_PERMISSION);
            if (permissionsHandler.hasPermissions(getActivity())) {
                googleMap.setMyLocationEnabled(true);
                setAllMarkers(googleMap);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startPos, 15));
            } else {
                //TODO: What happens if user does not give location permissions?
            }
            //TODO: change startPos to be the current location.
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
            googleMap.addMarker(new MarkerOptions().position(sticker.getPos()).title("Sticker removed on " + sticker.getDateString()).snippet(shout).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)).alpha(0.4f));
        }
    }

}