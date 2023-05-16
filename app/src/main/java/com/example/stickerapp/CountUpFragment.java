package com.example.stickerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CountUpFragment extends Fragment {

    private TextView counterPrefix;
    private TextView counter;
    private TextView counterSuffix;
    private Button goButton;

    StickerDB stickerDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_count_up, container, false);

        stickerDB= new ViewModelProvider(requireActivity()).get(StickerDB.class);

        counterPrefix = v.findViewById(R.id.counterPrefix);
        counter = v.findViewById(R.id.counter);
        counterSuffix = v.findViewById(R.id.counterSuffix);
        goButton = v.findViewById(R.id.goButton);

        counter.setText("" + stickerDB.getSize());

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_countUpFragment_to_debriefFragment);
            }
        });

        return v;
    }
}
