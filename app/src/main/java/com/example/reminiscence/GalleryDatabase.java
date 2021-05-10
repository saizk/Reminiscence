package com.example.reminiscence;


import android.content.Context;
import android.os.AsyncTask;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {PhotoName.class}, version = 1)
public abstract class GalleryDatabase extends RoomDatabase {

    public abstract PhotoNameDao photoNameDao();
    private static GalleryDatabase instance;



    public static synchronized GalleryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    GalleryDatabase.class, "gallery_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {

        private PhotoNameDao photoNameDao;

        PopulateDBAsyncTask(GalleryDatabase db){
            photoNameDao = db.photoNameDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            photoNameDao.insert(new PhotoName("content://com.android.providers.downloads.documents/document/msf%3A34", "brad"));
            photoNameDao.insert(new PhotoName("content://com.android.providers.downloads.documents/document/msf%3A33", "amigo1"));
            photoNameDao.insert(new PhotoName("content://com.android.providers.downloads.documents/document/msf%3A32", "amiga2"));
            photoNameDao.insert(new PhotoName("content://com.android.providers.downloads.documents/document/msf%3A31", "nieto"));
            photoNameDao.insert(new PhotoName("content://com.android.providers.downloads.documents/document/msf%3A35", "nieta"));

            return null;
        }





    }

}


