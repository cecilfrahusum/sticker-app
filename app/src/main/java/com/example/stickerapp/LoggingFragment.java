package com.example.stickerapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoggingFragment extends Fragment {

    private static final int REQUEST_PERMISSION = 1;
    PermissionsHandler permissionsHandler = new PermissionsHandler();

    private TextView foundWhereText;
    private Button hereButton; private Button somewhereElseButton;
    private TextView removedText;
    private Button yesButton; private Button noButton;
    private Button getLocationButton;

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
        getLocationButton = v.findViewById(R.id.getLocationButton);

        getLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionsHandler.requestPermissions(getActivity(), REQUEST_PERMISSION);
                Toast.makeText(getActivity(), "testing.. " + permissionsHandler.hasPermissions(getActivity()), Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }


}