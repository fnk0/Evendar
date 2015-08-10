package com.gabilheri.choresapp.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

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
        return null;
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

        // Event's URI's
        matcher.addURI(authority, ChoresContract.PATH_EVENT, EVENTS);


        return matcher;
    }
}
