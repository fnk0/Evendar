package com.gabilheri.choresapp.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import static com.gabilheri.choresapp.data.ChoresContract.EventEntry;
import static com.gabilheri.choresapp.data.ChoresContract.UserEntry;

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

    // User matcher ID's
    private static final int USER = 100;
    private static final int USER_WITH_ID = 101;
    private static final int USER_WITH_USERNAME = 102;

    // Event Mather ID's
    private static final int EVENTS = 200;

    // Comments Matcher ID's
    private static final int COMMENTS = 300;

    // Friendship matcher ID's
    private static final int FRIENDS = 400;

    // RSVP Matcher ID's
    private static final int RSVP = 500;

    // Favorites Matcher ID's
    private static final int FAVORITES = 600;

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
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
        switch (sUriMatcher.match(uri)) {
            case EVENTS:
                _id = db.insert(EventEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = EventEntry.buildLocalEventUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;

            case USER:
                _id = db.insert(UserEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = UserEntry.buildUserUri(_id);
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

        // Events URI's
        matcher.addURI(authority, ChoresContract.PATH_EVENT, EVENTS);

        // Comments URI's
        matcher.addURI(authority, ChoresContract.PATH_COMMENTS, COMMENTS);

        // Favorites URI's
        matcher.addURI(authority, ChoresContract.PATH_FAVORITES, FAVORITES);

        // Friends URI's
        matcher.addURI(authority, ChoresContract.PATH_FRIENDSHIP, FRIENDS);

        // RSVP Uri's
        matcher.addURI(authority, ChoresContract.PATH_RSVP, RSVP);



        return matcher;
    }
}
