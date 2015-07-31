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


    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + ChoresContract.UserEntry.TABLE_NAME + " (" +
                ChoresContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChoresContract.UserEntry.COLUMN_FULL_NAME + " TEXT NOT NULL, " +
                ChoresContract.UserEntry.COLUMN_USERNAME + " TEXT NOT NULL, " +
                ChoresContract.UserEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                ChoresContract.UserEntry.COLUMN_DATE_REGISTERED + " TEXT NOT NULL, " +
                ChoresContract.UserEntry.COLUMN_FACEBOOK_USERNAME + " TEXT NOT NULL, " +
                ChoresContract.UserEntry.COLUMN_TWITTER_USERNAME + " TEXT NOT NULL, " +
                ChoresContract.UserEntry.COLUMN_GOOGLE_USERNAME + " TEXT NOT NULL, " +
                ChoresContract.UserEntry.COLUMN_NUM_EVENTS + " INTEGER NOT NULL, " +
                ChoresContract.UserEntry.COLUMN_NUM_FAVORITES + " INTEGER NOT NULL, " +
                ChoresContract.UserEntry.COLUMN_PIC_URL + " TEXT NOT NULL );" ;


        final String SQL_CREATE_EVENT_TABLE = "CREATE TABLE " + ChoresContract.EventEntry.TABLE_NAME + " (" +
                ChoresContract.EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChoresContract.EventEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                ChoresContract.EventEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                ChoresContract.EventEntry.COLUMN_TIME + " TEXT NOT NULL, " +
                ChoresContract.EventEntry.COLUMN_IS_WANT + " INTEGER NOT NULL, " +
                ChoresContract.EventEntry.COLUMN_NUM_FAV + " INTEGER NOT NULL, " +
                ChoresContract.EventEntry.COLUMN_NUM_COMMENTS + " INTEGER NOT NULL, " +
                ChoresContract.EventEntry.COLUMN_NUM_GOING + " INTEGER NOT NULL, " +
                ChoresContract.EventEntry.COLUMN_LOC + " TEXT NOT NULL, " +
                " FOREIGN KEY (" + ChoresContract.EventEntry.COLUMN_USER_ID + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.UserEntry._ID + ") );"

                ;


        final String SQL_CREATE_FAVORITES_TABLE = "CREATE TABLE " + ChoresContract.FavoriteEntry.TABLE_NAME + " (" +
                ChoresContract.FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " FOREIGN KEY (" + ChoresContract.FavoriteEntry.COLUMN_EVENT_ID + ") REFERENCES " +
                ChoresContract.EventEntry.TABLE_NAME + " (" + ChoresContract.EventEntry._ID + "), " +
                " FOREIGN KEY (" + ChoresContract.FavoriteEntry.COLUMN_USER_ID + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.UserEntry._ID + ") );"
                ;

        final String SQL_CREATE_FRIENDSHIPS_TABLE = "CREATE TABLE " + ChoresContract.FriendshipEntry.TABLE_NAME + " (" +
                ChoresContract.FriendshipEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " FOREIGN KEY (" + ChoresContract.FriendshipEntry.COLUMN_USER_ID1 + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.UserEntry._ID + "), " +
                " FOREIGN KEY (" + ChoresContract.FriendshipEntry.COLUMN_USER_ID2 + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.UserEntry._ID + ") );"
                ;


        final String SQL_CREATE_COMMENTS_TABLE = "CREATE TABLE " + ChoresContract.CommentEntry.TABLE_NAME + " (" +
                ChoresContract.CommentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChoresContract.CommentEntry.COLUMN_TEXT + " TEXT NOT NULL, " +
                ChoresContract.CommentEntry.COLUMN_TIME + " TEXT NOT NULL, " +
                " FOREIGN KEY (" + ChoresContract.CommentEntry.COLUMN_USER_ID + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.UserEntry._ID + "), " +
                " FOREIGN KEY (" + ChoresContract.CommentEntry.COLUMN_EVENT_ID + ") REFERENCES " +
                ChoresContract.EventEntry.TABLE_NAME + " (" + ChoresContract.EventEntry._ID + ") );"
                ;

        final String SQL_CREATE_RSVP_TABLE = "CREATE TABLE " + ChoresContract.RSVPEntry.TABLE_NAME + " (" +
                ChoresContract.RSVPEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " FOREIGN KEY (" + ChoresContract.RSVPEntry.COLUMN_EVENT_ID + ") REFERENCES " +
                ChoresContract.EventEntry.TABLE_NAME + " (" + ChoresContract.EventEntry._ID + "), " +
                " FOREIGN KEY (" + ChoresContract.RSVPEntry.COLUMN_USER_ID + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.UserEntry._ID + ") );"
                ;


        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_EVENT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FRIENDSHIPS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_COMMENTS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_RSVP_TABLE);




    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        onCreate(sqLiteDatabase);
    }








}
