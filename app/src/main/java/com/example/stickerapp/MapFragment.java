package com.example.stickerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        String apiKey = BuildConfig.API_KEY;

        LatLng startPos = new LatLng(55.658619, 12.589548);

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            setAllPins(googleMap);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startPos, 15));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
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

    public void setAllPins(GoogleMap googleMap) {
        LatLng icecreamSticker = new LatLng(55.660505, 12.591268);
        googleMap.addMarker(new MarkerOptions().position(icecreamSticker).title("A removed sticker").snippet("Removed on April 20th 2023").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).alpha(0.6f));

        LatLng metroSticker = new LatLng(55.655954,12.589270);
        googleMap.addMarker(new MarkerOptions().position(metroSticker).title("A removed sticker").snippet("Removed on February 3rd 2023").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).alpha(0.6f));

        LatLng jurSticker = new LatLng(55.661571,12.586713);
        googleMap.addMarker(new MarkerOptions().position(jurSticker).title("A sticker").snippet("Please help remove this sticker if you can.").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
    }

    public void placePin(GoogleMap googleMap, double lat, double lng, String title, String colour) {

    }
}