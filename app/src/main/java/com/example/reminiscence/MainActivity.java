package com.example.reminiscence;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onAlarmClick(View view){
        Intent intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);
    }
    public void onResourcesClick(View view){
        Intent intent = new Intent(this, ResourcesActivity.class);
        startActivity(intent);
    }
    public void onMapClick(View view){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
    public void onNotepadClick(View view){
        Intent intent = new Intent(this, NotepadActivity.class);
        startActivity(intent);
    }

    public void onGalleryClick(View view){
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

}