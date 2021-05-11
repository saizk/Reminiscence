package com.example.reminiscence;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import java.util.List;

public class GalleryRepository {

    private PhotoNameDao dao;
    private LiveData<List<PhotoName>> allPhotos;

    public GalleryRepository(Application application) {
        GalleryDatabase database = GalleryDatabase.getInstance(application);
        dao = database.photoNameDao();
        allPhotos = dao.getAllPhotos();
    }



    //insert
    public void insert(PhotoName model) {
        new InsertPhotoAsyncTask(dao).execute(model);
    }

    //get all photos
    public LiveData<List<PhotoName>> getAllPhotos() {
        return allPhotos;
    }


    private static class InsertPhotoAsyncTask extends AsyncTask<PhotoName, Void, Void> {
        private PhotoNameDao dao;

        private InsertPhotoAsyncTask(PhotoNameDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PhotoName... model) {
            dao.insert(model[0]);
            return null;
        }
    }

}
