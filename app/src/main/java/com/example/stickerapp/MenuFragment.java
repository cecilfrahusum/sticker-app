package com.example.stickerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MenuFragment extends Fragment {

    private TextView appTitle;
    private TextView tagLine;
    private Button crewButton;
    private Button logStickerButton;
    private Button mapButton;
    private Button safetyInfoButton;

    private PermissionsHandler permissionsHandler = new PermissionsHandler();
    private static final int REQUEST_PERMISSION = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        appTitle = v.findViewById(R.id.appTitle);
        tagLine = v.findViewById(R.id.tagLine);
        logStickerButton = v.findViewById(R.id.logStickerButton);
        mapButton = v.findViewById(R.id.mapButton);
        crewButton = v.findViewById(R.id.crewButton);
        safetyInfoButton = v.findViewById(R.id.safetyInfoButton);

        permissionsHandler.requestPermissions(getActivity(), REQUEST_PERMISSION);

        logStickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_loggingFragment);
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_mapFragment);
            }
        });

        crewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_crewFragment);
            }
        });

        safetyInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_safetyFragment);
            }
        });

        return v;
    }
}