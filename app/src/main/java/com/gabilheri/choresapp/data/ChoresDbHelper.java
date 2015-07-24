package com.gabilheri.choresapp.data;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import android.content.Context;

/**
 * Created by kieran on 7/24/15.
 */
public class ChoresDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "chores.db";

    public ChoresDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //@TODO: finish creation of the other tables
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + ChoresContract.UserEntry.TABLE_NAME + " (" +
                ChoresContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ChoresContract.UserEntry.COLUMN_FULL_NAME + " TEXT NOT NULL, "

                ;

        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        onCreate(sqLiteDatabase);
    }


























}
