package com.example.googlemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapaFragment fr = new MapaFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.contenerdor,fr).commit();

    }
}
