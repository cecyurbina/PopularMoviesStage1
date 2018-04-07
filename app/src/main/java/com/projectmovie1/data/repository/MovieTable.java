package com.projectmovie1.data.repository;

/**
 * Created by s.urbina.coronado on 4/4/2018.
 */

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MovieTable {
    // Database table
    public static final String TABLE_MOVIE = "movie";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MOVIE_ID = "id_movie";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_VOTE = "vote";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MOVIE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_MOVIE_ID + " integer, "
            + COLUMN_TITLE + " text,"
            + COLUMN_DESCRIPTION + " text,"
            + COLUMN_URL + " text,"
            + COLUMN_DATE + " text,"
            + COLUMN_VOTE + " text" + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(MovieTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(database);
    }
}