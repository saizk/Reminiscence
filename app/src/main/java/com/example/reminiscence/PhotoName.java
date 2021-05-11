package com.example.reminiscence;

import android.net.Uri;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;


@Entity(tableName = "photo_table")
public class PhotoName {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String imageUri;
    public String name;


    public PhotoName(String imageUri, String name) {
        this.imageUri = imageUri;
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return imageUri;
    }

    public void setUri(String imageUri) {
        this.imageUri = imageUri;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}


