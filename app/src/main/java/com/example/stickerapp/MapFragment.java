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

    LatLng startPos = new LatLng(55.658619, 12.589548);

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(GoogleMap googleMap) {
            permissionsHandler.requestPermissions(getActivity(), REQUEST_PERMISSION);
            if (permissionsHandler.hasPermissions(getActivity())) {
                //googleMap.setMyLocationEnabled(true);
            } else {
                // TODO: A toast saying they can interact with map without location turned on.
            }
            setAllMarkers(googleMap);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startPos, 15));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        stickerDB = new ViewModelProvider(requireActivity()).get(StickerDB.class);
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
            googleMap.addMarker(new MarkerOptions().position(sticker.getPos()).title("Sticker removed on May 4th 2023").snippet(shout).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)).alpha(0.4f));
        }
    }

   /* public void placeMarker(GoogleMap googleMap, double lat, double lng, Date date, boolean removed) {
        LatLng stickerPos = new LatLng(lat, lng);
        String title = "A sticker";
        String snippet = "Please help remove this sticker if you can.";
        float opacity = 1;
        if (removed) {
            title = "A removed sticker";
            snippet = "Sticker removed on XX date"; //TODO: add actual date
            opacity = 0.6f;
        }
        googleMap.addMarker(new MarkerOptions().position(stickerPos).title(title).snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)).alpha(opacity));
    }*/
}