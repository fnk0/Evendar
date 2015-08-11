package com.gabilheri.choresapp.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import static com.gabilheri.choresapp.data.ChoresContract.EventEntry;
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
            case FRIENDS:
                return insertValuesIntoTable(FriendshipEntry.TABLE_NAME, uri, values);
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
        final SQLiteDatabase db = mChoresDbHelper.getWritableDatabase();
        int rowsDeleted;

        if (selection == null) {
            selection = "1";
        }

        switch (sUriMatcher.match(uri)) {
            case EVENTS:
                rowsDeleted = db.delete(EventEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case USER:
                rowsDeleted = db.delete(UserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case COMMENTS:
                rowsDeleted = db.delete(CommentEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case RSVP:
                rowsDeleted = db.delete(RSVPEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case FRIENDS:
                rowsDeleted = db.delete(FriendshipEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case FAVORITES:
                rowsDeleted = db.delete(FavoriteEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mChoresDbHelper.getWritableDatabase();
        int rowsUpdated;

        switch (sUriMatcher.match(uri)) {
            case EVENTS:
                rowsUpdated = db.update(EventEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case USER:
                rowsUpdated = db.update(UserEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case COMMENTS:
                rowsUpdated = db.update(CommentEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case RSVP:
                rowsUpdated = db.update(RSVPEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case FRIENDS:
                rowsUpdated = db.update(FriendshipEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case FAVORITES:
                rowsUpdated = db.update(FriendshipEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
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
