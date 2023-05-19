package com.example.stickerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public class LoggingFragment extends Fragment {

    private TextView text;
    private EditText shoutBox;
    private Button foundButton;

    private StickerDB stickerDB;
    private PermissionsHandler permissionsHandler = new PermissionsHandler();
    private FusedLocationProviderClient locationClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_logging, container, false);
        stickerDB= new ViewModelProvider(requireActivity()).get(StickerDB.class);
        locationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        text = v.findViewById(R.id.text);
        shoutBox = v.findViewById(R.id.shoutBox);
        foundButton = v.findViewById(R.id.foundButton);

        foundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shoutBox.getText().toString().length() > 37) {
                    Toast.makeText(getActivity(), "Please, no more than 37 characters.", Toast.LENGTH_LONG).show();
                } else {
                    stickerDB.addMarker(getRandomLatLng(), shoutBox.getText().toString(), new Date());
                    Navigation.findNavController(view).navigate(R.id.action_loggingFragment_to_countUpFragment);
                }
            }
        });
        return v;
    }

    /* Returns a random LatLng coordinate, specifically within a short distance
    * of the IT University. Used for playtesting purposes. */
    private LatLng getRandomLatLng() {
        double lat = (double) ((Math.random() * (55.659225 - 55.652872)) + 55.652872);
        double lng = (double) ((Math.random() * (12.595497 - 12.581437)) + 12.581437);
        return new LatLng(lat, lng);
    }

}