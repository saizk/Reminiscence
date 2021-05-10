package com.example.reminiscence;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.net.URI;
import java.util.List;

@Dao
public interface PhotoNameDao {


    @Insert
    void insert(PhotoName photoName);

    @Query("SELECT * FROM photo_table")
    LiveData<List<PhotoName>> getAllPhotos();

}
