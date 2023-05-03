package com.example.stickerapp;

import com.google.android.gms.maps.model.LatLng;

public class Sticker {

    private LatLng latLng;
    private String message;

    public Sticker(LatLng latLng, String message) {
        this.latLng = latLng;
        this.message = message;
    }

    public LatLng getPos() {
        return latLng;
    }

    public String getMessage() {
        return message;
    }


}
