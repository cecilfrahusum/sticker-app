package com.example.stickerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CrewFragment extends Fragment {

    private TextView counterPrefix;
    private TextView counter;
    private TextView counterSuffix;

    private StickerDB stickerDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crew, container, false);
        stickerDB= new ViewModelProvider(requireActivity()).get(StickerDB.class);

        counterPrefix = v.findViewById(R.id.counterPrefix);
        counter = v.findViewById(R.id.counter);
        counterSuffix = v.findViewById(R.id.counterSuffix);

        counter.setText("" + stickerDB.getSize());

        return v;
    }
}