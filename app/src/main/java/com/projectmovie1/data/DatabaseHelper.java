package com.projectmovie1.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.projectmovie1.data.model.Result;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "favoritesDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_FAVORITES = "favorites";

    //Favorite Table Columns
    private static final String KEY_FAVORITE_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_URL = "url";
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private static DatabaseHelper sInstance;

    public static synchronized DatabaseHelper getInstance(Context context){
        if (sInstance == null){
            sInstance = new DatabaseHelper(context.getApplicationContext(), null, null, 1);
        }
        return sInstance;
    }
    
    public DatabaseHelper(Context context, String dbname, Object o, int i) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES +
                "(" +
                KEY_FAVORITE_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_TITLE + " TEXT," +
                KEY_DESCRIPTION + " TEXT," +
                KEY_URL + " TEXT" +
                ")";


        sqLiteDatabase.execSQL(CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            // Simplest implementation is to drop all old tables and recreate them
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
            onCreate(sqLiteDatabase);
        }
    }

    /**
     *
     * @param post
     */
    public void addFavorite(Result post) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            ContentValues values = new ContentValues();
            values.put(KEY_FAVORITE_ID, post.getId());
            values.put(KEY_TITLE, post.getTitle());
            values.put(KEY_DESCRIPTION, post.getOverview());
            values.put(KEY_URL, post.getPosterPath());
            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_FAVORITES, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean removeFavorite(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        return db.delete(TABLE_FAVORITES, KEY_FAVORITE_ID +"=?", new String[]{Integer.toString(id)}) > 0;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean isFavorite(int id){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {KEY_FAVORITE_ID};
        String selection = KEY_FAVORITE_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        return db.query(TABLE_FAVORITES, columns, selection, selectionArgs,
                null, null, null, null).getCount() > 0;
    }

    public List<Result> getFavorites(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_FAVORITES,null);
        List<Result> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Result resultTemp = new Result();
                resultTemp.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                resultTemp.setId(cursor.getInt(cursor.getColumnIndex(KEY_FAVORITE_ID)));
                resultTemp.setOverview(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                resultTemp.setPosterPath(cursor.getString(cursor.getColumnIndex(KEY_URL)));
                list.add(resultTemp);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
