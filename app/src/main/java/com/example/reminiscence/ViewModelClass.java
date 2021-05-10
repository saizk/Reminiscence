package com.example.reminiscence;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModelClass extends AndroidViewModel {

    private GalleryRepository repository;
    private LiveData<List<PhotoName>> allPhotos;


    public ViewModelClass(@NonNull Application application) {
        super(application);
        repository = new GalleryRepository(application);
        allPhotos = repository.getAllPhotos();
    }


    public void insert(PhotoName model) {
        repository.insert(model);
    }

    public LiveData<List<PhotoName>> getAllPhotos() {

        return (allPhotos);
    }


}
