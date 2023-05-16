package com.example.stickerapp;

import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sticker {

    private LatLng latLng;
    private String message;
    private Date date;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MM yyyy");

    public Sticker(LatLng latLng, String message, Date date) {
        this.latLng = latLng;
        this.message = message;
        this.date = date;
    }

    public LatLng getPos() {
        return latLng;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() { return date; }

    public String getDateString() {
        String dateString = DATE_FORMAT.format(getDate());
        return dateString;
    }

}
