package com.example.stickerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CrewFragment extends Fragment {

    private TextView counterPrefix;
    private TextView counter;
    private TextView counterSuffix;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crew, container, false);

        counterPrefix = v.findViewById(R.id.counterPrefix);
        counter = v.findViewById(R.id.counter);
        counterSuffix = v.findViewById(R.id.counterSuffix);

        return v;
    }
}