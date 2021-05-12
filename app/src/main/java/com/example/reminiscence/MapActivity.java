package com.example.reminiscence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public void onMapClick(View view){
        Intent intent = new Intent(this, MapCoordinatesActivity.class);
        startActivity(intent);
    }

    public void onCoordinatesSet(View view){
        Intent intent = new Intent(this, MapCoordinatesActivity.class);
        startActivity(intent);
    }

}