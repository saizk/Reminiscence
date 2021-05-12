package com.example.reminiscence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MapDbAdapter {
    private static final String TAG = "APMOV: MapDbAdapter"; // Usado en los mensajes de Log

    private static final String DATABASE_NAME = "map";
    private static final String DATABASE_TABLE = "coordinates";
    private static final int DATABASE_VERSION = 2;

    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_ROWID = "_id";

    private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " (" +
            KEY_ROWID +" integer primary key autoincrement, " +
            KEY_LATITUDE +" text not null, " +
            KEY_LONGITUDE + " text not null);";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */

    public MapDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the coordinates database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public MapDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    /**
     * Create a new Map using the latitude and longitude provided. If the Map is
     * successfully created return the new rowId for that Map, otherwise return
     * a -1 to indicate failure.
     *
     * @param latitude the latitude of the Map
     * @param longitude the longitude of the Map
     * @return rowId or -1 if failed
     */
    public long createCoordinates(double latitude, double longitude) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_LATITUDE, latitude);
        initialValues.put(KEY_LONGITUDE, longitude);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the Map with the given rowId
     *
     * @param rowId id of Map to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteCoordinates(long rowId) {
        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all coordinates in the database
     *
     * @return Cursor over all coordinates
     */
    public Cursor fetchAllCoordinates() {
        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_LATITUDE,
                KEY_LONGITUDE}, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the Map that matches the given rowId
     *
     * @param rowId id of Map to retrieve
     * @return Cursor positioned to matching Map, if found
     * @throws SQLException if Map could not be found/retrieved
     */
    public Cursor fetchCoordinates(long rowId) throws SQLException {
        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_LATITUDE, KEY_LONGITUDE}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * Update the Map using the details provided. The Map to be updated is
     * specified using the rowId, and it is altered to use the latitude and longitude
     * values passed in
     *
     * @param rowId id of Map to update
     * @param latitude value to set Map latitude to
     * @param longitude value to set Map longitude to
     * @return true if the Map was successfully updated, false otherwise
     */
    public boolean updateCoordinates(long rowId, double latitude, double longitude) {
        ContentValues args = new ContentValues();
        args.put(KEY_LATITUDE, latitude);
        args.put(KEY_LONGITUDE, longitude);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
