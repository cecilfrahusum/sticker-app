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

    private TextView foundWhereText;
    private Button hereButton; private Button somewhereElseButton;
    private TextView removedText;
    private Button yesButton; private Button noButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_logging, container, false);

        foundWhereText = v.findViewById(R.id.foundWhereText);
        hereButton = v.findViewById(R.id.hereButton);
        somewhereElseButton = v.findViewById(R.id.somewhereElseButton);
        removedText = v.findViewById(R.id.removedText);
        yesButton = v.findViewById(R.id.yesButton); noButton = v.findViewById(R.id.noButton);


        return v;
    }



}