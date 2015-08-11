package com.gabilheri.choresapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kieran on 7/24/15.
 */
public class ChoresDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "chores.db";

    public ChoresDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + ChoresContract.UserEntry.TABLE_NAME + " (" +
                ChoresContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChoresContract.UserEntry.COLUMN_FULL_NAME + " TEXT, " +
                ChoresContract.UserEntry.COLUMN_USERNAME + " TEXT, " +
                ChoresContract.UserEntry.COLUMN_EMAIL + " TEXT, " +
                ChoresContract.UserEntry.COLUMN_DATE_REGISTERED + " TEXT, " +
                ChoresContract.UserEntry.COLUMN_FACEBOOK_USERNAME + " TEXT, " +
                ChoresContract.UserEntry.COLUMN_TWITTER_USERNAME + " TEXT, " +
                ChoresContract.UserEntry.COLUMN_GOOGLE_USERNAME + " TEXT, " +
                ChoresContract.UserEntry.COLUMN_NUM_EVENTS + " INTEGER, " +
                ChoresContract.UserEntry.COLUMN_NUM_FAVORITES + " INTEGER, " +
                ChoresContract.UserEntry.COLUMN_UPDATED_AT + " TEXT, " +
                ChoresContract.LONG_ID + " TEXT, " +
                ChoresContract.UserEntry.COLUMN_PIC_URL + " TEXT, " +
                "UNIQUE (" + ChoresContract.UserEntry.COLUMN_USERNAME + ", " + ChoresContract.LONG_ID + ") ON CONFLICT REPLACE);";


        final String SQL_CREATE_EVENT_TABLE = "CREATE TABLE " + ChoresContract.EventEntry.TABLE_NAME + " (" +
                ChoresContract.EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChoresContract.EventEntry.COLUMN_USERNAME + " TEXT, " +
                ChoresContract.EventEntry.COLUMN_TITLE + " TEXT, " +
                ChoresContract.EventEntry.COLUMN_DESCRIPTION + " TEXT, " +
                ChoresContract.EventEntry.COLUMN_DATE + " TEXT, " +
                ChoresContract.EventEntry.COLUMN_TIME + " TEXT, " +
                ChoresContract.EventEntry.COLUMN_IS_WANT + " INTEGER, " +
                ChoresContract.EventEntry.COLUMN_NUM_FAV + " INTEGER, " +
                ChoresContract.EventEntry.COLUMN_NUM_COMMENTS + " INTEGER, " +
                ChoresContract.EventEntry.COLUMN_NUM_GOING + " INTEGER, " +
                ChoresContract.EventEntry.COLUMN_NUM_SHARES + " INTEGER, " +
                ChoresContract.EventEntry.COLUMN_LOC + " TEXT, " +
                ChoresContract.EventEntry.COLUMN_LONG_ID + " TEXT, " +
                ChoresContract.EventEntry.COLUMN_UPDATED_AT + " TEXT, " +
                ChoresContract.EventEntry.COLUMN_DATE_CREATED + " TEXT, " +
                ChoresContract.EventEntry.COLUMN_USER_ID + " TEXT, " +

                " FOREIGN KEY (" + ChoresContract.EventEntry.COLUMN_USER_ID + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.LONG_ID + "), " +
                "UNIQUE (" +  ChoresContract.LONG_ID + ") ON CONFLICT REPLACE);";


        final String SQL_CREATE_FAVORITES_TABLE = "CREATE TABLE " + ChoresContract.FavoriteEntry.TABLE_NAME + " (" +
                ChoresContract.FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChoresContract.LONG_ID + " TEXT, " +
                ChoresContract.FavoriteEntry.COLUMN_UPDATED_AT + " TEXT, " +
                ChoresContract.FavoriteEntry.COLUMN_DATE_CREATED + " TEXT, " +
                ChoresContract.FavoriteEntry.COLUMN_EVENT_ID + " TEXT, " +
                ChoresContract.FavoriteEntry.COLUMN_USER_ID + " TEXT, " +

                " FOREIGN KEY (" + ChoresContract.FavoriteEntry.COLUMN_EVENT_ID + ") REFERENCES " +
                ChoresContract.EventEntry.TABLE_NAME + " (" + ChoresContract.LONG_ID + "), " +
                " FOREIGN KEY (" + ChoresContract.FavoriteEntry.COLUMN_USER_ID + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.LONG_ID + "), " +
                "UNIQUE (" +  ChoresContract.LONG_ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_FRIENDSHIPS_TABLE = "CREATE TABLE " + ChoresContract.FriendshipEntry.TABLE_NAME + " (" +
                ChoresContract.FriendshipEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChoresContract.LONG_ID + " TEXT, " +
                ChoresContract.FriendshipEntry.COLUMN_UPDATED_AT + " TEXT, " +
                ChoresContract.FriendshipEntry.COLUMN_DATE_CREATED + " TEXT, " +
                ChoresContract.FriendshipEntry.COLUMN_USER_ID1 + " TEXT, " +
                ChoresContract.FriendshipEntry.COLUMN_USER_ID2 + " TEXT, " +

                " FOREIGN KEY (" + ChoresContract.FriendshipEntry.COLUMN_USER_ID1 + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.LONG_ID + "), " +
                " FOREIGN KEY (" + ChoresContract.FriendshipEntry.COLUMN_USER_ID2 + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.LONG_ID + "), " +
                "UNIQUE (" +  ChoresContract.LONG_ID + ") ON CONFLICT REPLACE);";


        final String SQL_CREATE_COMMENTS_TABLE = "CREATE TABLE " + ChoresContract.CommentEntry.TABLE_NAME + " (" +
                ChoresContract.CommentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChoresContract.CommentEntry.COLUMN_TEXT + " TEXT, " +
                ChoresContract.CommentEntry.COLUMN_TIME + " TEXT, " +
                ChoresContract.LONG_ID + " TEXT, " +
                ChoresContract.CommentEntry.COLUMN_UPDATED_AT + " TEXT, " +
                ChoresContract.CommentEntry.COLUMN_DATE_CREATED + " TEXT, " +
                ChoresContract.CommentEntry.COLUMN_USER_ID + " TEXT, " +
                ChoresContract.CommentEntry.COLUMN_EVENT_ID + " TEXT, " +

                " FOREIGN KEY (" + ChoresContract.CommentEntry.COLUMN_USER_ID + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.LONG_ID + "), " +
                " FOREIGN KEY (" + ChoresContract.CommentEntry.COLUMN_EVENT_ID + ") REFERENCES " +
                ChoresContract.EventEntry.TABLE_NAME + " (" + ChoresContract.LONG_ID + "), " +
                "UNIQUE (" +  ChoresContract.LONG_ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_RSVP_TABLE = "CREATE TABLE " + ChoresContract.RSVPEntry.TABLE_NAME + " (" +
                ChoresContract.RSVPEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ChoresContract.LONG_ID + " TEXT, " +
                ChoresContract.RSVPEntry.COLUMN_UPDATED_AT + " TEXT, " +
                ChoresContract.RSVPEntry.COLUMN_DATE_CREATED + " TEXT, " +
                ChoresContract.RSVPEntry.COLUMN_EVENT_ID + " TEXT, " +
                ChoresContract.RSVPEntry.COLUMN_USER_ID + " TEXT, " +
                ChoresContract.RSVPEntry.COLUMN_FULL_NAME + " TEXT, " +


                " FOREIGN KEY (" + ChoresContract.RSVPEntry.COLUMN_EVENT_ID + ") REFERENCES " +
                ChoresContract.EventEntry.TABLE_NAME + " (" + ChoresContract.LONG_ID + "), " +
                " FOREIGN KEY (" + ChoresContract.RSVPEntry.COLUMN_USER_ID + ") REFERENCES " +
                ChoresContract.UserEntry.TABLE_NAME + " (" + ChoresContract.LONG_ID + "), " +
                "UNIQUE (" +  ChoresContract.LONG_ID + ") ON CONFLICT REPLACE);";


        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_EVENT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FRIENDSHIPS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_COMMENTS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_RSVP_TABLE);


    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase);
    }


}
