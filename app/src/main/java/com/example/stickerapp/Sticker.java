package com.example.stickerapp;

import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Sticker {

    /* Note:
     I am sticking with the old 'Date' type instead of newer
     LocalDate, because LocalDate only works on sdk 26 and up. */

    private LatLng latLng;
    private String message;
    private Date date;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

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
