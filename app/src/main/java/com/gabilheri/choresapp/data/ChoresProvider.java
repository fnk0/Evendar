package com.gabilheri.choresapp.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

import static com.gabilheri.choresapp.data.ChoresContract.EventEntry;
import static com.gabilheri.choresapp.data.ChoresContract.PATH_USER;
import static com.gabilheri.choresapp.data.ChoresContract.UserEntry;
import static com.gabilheri.choresapp.data.ChoresContract.CommentEntry;
import static com.gabilheri.choresapp.data.ChoresContract.RSVPEntry;
import static com.gabilheri.choresapp.data.ChoresContract.FriendshipEntry;
import static com.gabilheri.choresapp.data.ChoresContract.FavoriteEntry;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/10/15.
 */
public class ChoresProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private ChoresDbHelper mChoresDbHelper;

    private static final SQLiteQueryBuilder sChores

    // User matcher ID's
    private static final int USER = 100;
    private static final int USER_WITH_ID = 101;
    private static final int USER_WITH_USERNAME = 102;
    private static final int USER_WITH_EVENT_ID = 103;

    // Event Mather ID's
    private static final int EVENTS = 200;
    private static final int EVENT_WITH_STARTDATE = 201;
    private static final int EVENT_WITH_ENDDATE = 202;
    private static final int EVENT_WITH_IS_WANT = 203;

    // Comments Matcher ID's
    private static final int COMMENTS = 300;
    private static final int COMMENT_WITH_ID = 301;

    // Friendship matcher ID's
    private static final int FRIENDS = 400;
    private static final int FRIEND_WITH_ID = 401;
    private static final int FRIEND_WITH_USER_ID = 402;

    // RSVP Matcher ID's
    private static final int RSVP = 500;
    private static final int RSVP_WITH_ID = 501;
    private static final int RSVP_WITH_EVENT_ID = 502;

    // Favorites Matcher ID's
    private static final int FAVORITES = 600;
    private static final int FAVORITE_WITH_ID = 601;

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
       Cursor retCursor;
        switch(sUriMatcher.match(uri)){
            case USER_WITH_EVENT_ID:
            {
                retCursor =;
                break;
            }

            case USER_WITH_USERNAME:
            {
                retCursor = ;
                break;
            }
            case USER_WITH_ID:
            {
                retCursor = ;
                break;

            }
            case USER:
            {
                retCursor = null;
                break;
            }

            case EVENTS:
            {
                retCursor = ;
                break;
            }
            case EVENT_WITH_ENDDATE:
            {
                retCursor = ;
                break;

            }
        }

        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mChoresDbHelper.getWritableDatabase();
        Uri returnUri;
        long _id;

        Long appEngineId = values.getAsLong(CommentEntry.COLUMN_LONG_ID);

        switch (sUriMatcher.match(uri)) {
            case EVENTS:
                _id = db.insert(EventEntry.TABLE_NAME, null, values);
                if (_id > 0 && appEngineId != null) {
                    returnUri = EventEntry.buildEventUri(appEngineId);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;

            case USER:
                _id = db.insert(UserEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = UserEntry.buildUserUri(appEngineId);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;

            case COMMENTS:
                _id = db.insert(CommentEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = CommentEntry.buildCommentsUri(appEngineId);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            case RSVP:
                _id = db.insert(RSVPEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = RSVPEntry.buildRSVP(appEngineId);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            case FRIENDS:
                _id = db.insert(FriendshipEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = FriendshipEntry.buildFriendship(appEngineId);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;

            case FAVORITES:
                _id = db.insert(FavoriteEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = FavoriteEntry.buildFavorite(appEngineId);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return returnUri;
    }

    @Override
    public int bulkInsert(Uri uri, @NonNull ContentValues[] values) {

        switch (sUriMatcher.match(uri)) {
            case EVENTS:
                return insertValuesIntoTable(EventEntry.TABLE_NAME, uri, values);
            case USER:
                return insertValuesIntoTable(UserEntry.TABLE_NAME, uri, values);
            case COMMENTS:
                return insertValuesIntoTable(CommentEntry.TABLE_NAME, uri, values);
            case RSVP:
                return insertValuesIntoTable(RSVPEntry.TABLE_NAME, uri, values);
            case FAVORITES:
                return insertValuesIntoTable(FavoriteEntry.TABLE_NAME, uri, values);
            default:
                return super.bulkInsert(uri, values);
        }
    }

    private int insertValuesIntoTable(String tableName, Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mChoresDbHelper.getWritableDatabase();
        int returnCount = 0;
        db.beginTransaction();
        try {
            for (ContentValues value : values) {
                long _id = db.insert(tableName, null, value);
                if (_id != -1) {
                    returnCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnCount;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * This URI matcher will match each table of the Database and return the right one
     * based on the query
     * @return The UriMatcher object containing the desired query table
     */
    public static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ChoresContract.CONTENT_AUTHORITY;

        // User URI's
        matcher.addURI(authority, ChoresContract.PATH_USER, USER);
        matcher.addURI(authority, ChoresContract.PATH_USER + "/id/#", USER_WITH_ID);
        matcher.addURI(authority, ChoresContract.PATH_USER + "/us/*", USER_WITH_USERNAME);
        matcher.addURI(authority, ChoresContract.PATH_USER + "/ei/#", USER_WITH_EVENT_ID);

        // Events URI's
        matcher.addURI(authority, ChoresContract.PATH_EVENT, EVENTS);
        matcher.addURI(authority, ChoresContract.PATH_EVENT + "/s/*", EVENT_WITH_STARTDATE);
        matcher.addURI(authority, ChoresContract.PATH_EVENT + "/e/*", EVENT_WITH_ENDDATE);
        matcher.addURI(authority, ChoresContract.PATH_EVENT + "/iw/#", EVENT_WITH_IS_WANT);


        // Comments URI's
        matcher.addURI(authority, ChoresContract.PATH_COMMENTS, COMMENTS);
        matcher.addURI(authority, ChoresContract.PATH_COMMENTS + "/commentId/#", COMMENT_WITH_ID);

        // Favorites URI's
        matcher.addURI(authority, ChoresContract.PATH_FAVORITES, FAVORITES);
        matcher.addURI(authority, ChoresContract.PATH_FAVORITES + "/favId/#", FAVORITE_WITH_ID);

        // Friends URI's
        matcher.addURI(authority, ChoresContract.PATH_FRIENDSHIP, FRIENDS);
        matcher.addURI(authority, ChoresContract.PATH_FRIENDSHIP + "/fId/#", FRIEND_WITH_ID);
        matcher.addURI(authority, ChoresContract.PATH_FRIENDSHIP + "/userId/#", FRIEND_WITH_USER_ID);


        // RSVP Uri's
        matcher.addURI(authority, ChoresContract.PATH_RSVP, RSVP);
        matcher.addURI(authority, ChoresContract.PATH_RSVP + "/rsvpId/#", RSVP_WITH_ID);
        matcher.addURI(authority, ChoresContract.PATH_RSVP + "/eId/#", RSVP_WITH_EVENT_ID);

        return matcher;
    }
}
