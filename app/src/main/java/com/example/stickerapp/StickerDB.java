package com.example.stickerapp;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StickerDB extends ViewModel {
    private final List<Sticker> markers = new ArrayList<>();

    public StickerDB() {
        LatLng icecreamSticker = new LatLng(55.660505, 12.591268);
        LatLng metroSticker = new LatLng(55.655954,12.589270);
        LatLng concertSticker = new LatLng(55.657839,12.589377);
        LatLng jurSticker = new LatLng(55.661571,12.586713);
        markers.add(new Sticker(icecreamSticker, "Go ITU anti-fascist sticker patrol!", new Date()));
        markers.add(new Sticker(metroSticker, "Smash the patriarchy", new Date()));
        markers.add(new Sticker(concertSticker, "Let's remove this nazi crap please", new Date()));
        markers.add(new Sticker(jurSticker, "Another gross sticker, boo!", new Date()));
    }

    public List<Sticker> getMarkers() {
        return markers;
    }

    public void addMarker(LatLng latLng, String message, Date date) {
        Sticker sticker = new Sticker(latLng, message, date);
        markers.add(sticker);
    }

    public int getSize() {
        return markers.size();
    }

}
