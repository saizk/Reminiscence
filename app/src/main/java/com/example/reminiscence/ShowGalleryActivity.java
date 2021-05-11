package com.example.reminiscence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ListAdapter;

import android.os.Bundle;

import java.util.List;

public class ShowGalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter RvGalleryAdapter;
    RecyclerView.LayoutManager layoutmanager;
    ViewModelClass myViewModel;


    //String[] personNameList = {"nieta", "nieto", "amiga1", "amigo2", "brad"};
    //int[] personPhoto = {R.drawable.nieta, R.drawable.nieto, R.drawable.amiga1, R.drawable.amigo2, R.drawable.brad};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_names);

        recyclerView = findViewById(R.id.rvGallery);
        recyclerView.setHasFixedSize(true);

        layoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanager);


        final RvGalleryAdapter adapter = new RvGalleryAdapter();
        recyclerView.setAdapter(adapter);

        myViewModel = new ViewModelProvider(this).get(ViewModelClass.class);

        myViewModel.getAllPhotos().observe(this, new Observer<List<PhotoName>>() {
            @Override
            public void onChanged(List<PhotoName> models) {
                adapter.submitList(models);
            }
        });

        //RvGalleryAdapter = new RvGalleryAdapter(this, personNameList, personPhoto );
        //recyclerView.setAdapter(RvGalleryAdapter);


    }
}