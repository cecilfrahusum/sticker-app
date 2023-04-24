package com.example.stickerapp;

import androidx.appcompat.app.AppCompatActivity;
import com.parse.Parse;
import com.parse.ParseObject;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(BuildConfig.DB_APP_ID)
                .clientKey(BuildConfig.DB_CLIENT_KEY)
                .server(BuildConfig.DB_SERVER_URL)
                .build());

        ParseObject firstObject = new  ParseObject("FirstClass");
        firstObject.put("message","Hey ! First message from android. Parse is now connected");
        firstObject.saveInBackground(e -> {
            if (e != null){
                Log.e("MainActivity", e.getLocalizedMessage());
            }else{
                Log.d("MainActivity","Object saved.");
            }
        });
    }
}