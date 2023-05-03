package com.example.stickerapp;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class StickerDB extends ViewModel {
    private final List<Sticker> markers = new ArrayList<>();

    public StickerDB() {
        LatLng icecreamSticker = new LatLng(55.660505, 12.591268);
        LatLng metroSticker = new LatLng(55.655954,12.589270);
        LatLng concertSticker = new LatLng(55.657839,12.589377);
        LatLng jurSticker = new LatLng(55.661571,12.586713);
        markers.add(new Sticker(icecreamSticker, "test"));
        markers.add(new Sticker(metroSticker, "test"));
        markers.add(new Sticker(concertSticker, "test"));
        markers.add(new Sticker(jurSticker, "test"));
    }

    public List<Sticker> getMarkers() {
        return markers;
    }

    public void addMarker(LatLng latLng, String message) {
        Sticker sticker = new Sticker(latLng, message);
        markers.add(sticker);
    }

    public int getSize() {
        return markers.size();
    }

}
