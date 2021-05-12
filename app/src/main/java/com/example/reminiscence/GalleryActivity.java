package com.example.reminiscence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
    }

    public void onSeeGalleryClick(View view){
        Intent intent = new Intent(this, GalleryShowActivity.class);
        startActivity(intent);
    }

    public void onAddPhotoClick(View view){
        Intent intent = new Intent(this, GalleryAddPhotoActivity.class);
        startActivity(intent);
    }

}