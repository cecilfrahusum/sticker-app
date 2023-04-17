package com.example.stickerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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