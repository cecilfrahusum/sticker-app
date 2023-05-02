package com.example.stickerapp;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class StickerDB extends ViewModel {
    private final List<LatLng> markers = new ArrayList<>();

    public StickerDB() {
        LatLng icecreamSticker = new LatLng(55.660505, 12.591268);
        LatLng metroSticker = new LatLng(55.655954,12.589270);
        LatLng concertSticker = new LatLng(55.657839,12.589377);
        LatLng jurSticker = new LatLng(55.661571,12.586713);
        markers.add(icecreamSticker);
        markers.add(metroSticker);
        markers.add(concertSticker);
        markers.add(jurSticker);
    }

    public List<LatLng> getMarkers() {
        return markers;
    }

    public void addMarker(LatLng latLng) {
        markers.add(latLng);
    }

    public int size() {
        return markers.size();
    }

}
