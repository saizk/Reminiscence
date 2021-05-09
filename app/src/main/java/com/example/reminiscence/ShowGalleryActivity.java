package com.example.reminiscence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ShowGalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter RvGalleryAdapter;
    RecyclerView.LayoutManager layoutmanager;

    String[] personNameList = {"nieta", "nieto", "amiga1", "amigo2", "brad"};
    int[] personPhoto = {R.drawable.nieta, R.drawable.nieto, R.drawable.amiga1, R.drawable.amigo2, R.drawable.brad};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_names);

        recyclerView = findViewById(R.id.rvGallery);
        recyclerView.setHasFixedSize(true);

        layoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanager);

        RvGalleryAdapter = new RvGalleryAdapter(this, personNameList, personPhoto );
        recyclerView.setAdapter(RvGalleryAdapter);

    }
}