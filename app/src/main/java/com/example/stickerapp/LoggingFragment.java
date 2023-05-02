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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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
import com.google.android.gms.maps.model.LatLng;

public class LoggingFragment extends Fragment {

    private TextView text;
    private Button foundButton;

    StickerDB stickerDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_logging, container, false);

        stickerDB= new ViewModelProvider(requireActivity()).get(StickerDB.class);

        text = v.findViewById(R.id.text);
        foundButton = v.findViewById(R.id.foundButton);

        foundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "Sticker saved.", Toast.LENGTH_LONG).show();
                stickerDB.addMarker(getRandomLatLng());
                Navigation.findNavController(view).navigate(R.id.action_loggingFragment_to_countUpFragment);
            }
        });

        return v;
    }

    private LatLng getRandomLatLng() {
        double lat = (double) ((Math.random() * (55.659225 - 55.652872)) + 55.652872);
        double lng = (double) ((Math.random() * (12.595497 - 12.581437)) + 12.581437);
        return new LatLng(lat, lng);
    }



}