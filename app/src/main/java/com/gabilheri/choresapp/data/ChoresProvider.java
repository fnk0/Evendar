package com.gabilheri.choresapp.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import static com.gabilheri.choresapp.data.ChoresContract.CommentEntry;
import static com.gabilheri.choresapp.data.ChoresContract.EventEntry;
import static com.gabilheri.choresapp.data.ChoresContract.FavoriteEntry;
import static com.gabilheri.choresapp.data.ChoresContract.FriendshipEntry;
import static com.gabilheri.choresapp.data.ChoresContract.RSVPEntry;
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
    public static final int USER = 100;
    private static final int USER_WITH_ID = 101;
    private static final int USER_WITH_USERNAME = 102;
    private static final int USER_WITH_EVENT_ID = 103;

    // Event Mather ID's

    private static final int EVENTS = 200;
    private static final int EVENT_WITH_STARTDATE = 201;
    private static final int EVENT_WITH_ID = 202;
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
    private static final int FAVORITE_WITH_USER_ID = 602;
    private static final int FAVORITES_FOR_USER = 603;

    @Override
    public boolean onCreate() {
        mChoresDbHelper = new ChoresDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {

            case USER_WITH_EVENT_ID: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        EventEntry.TABLE_NAME,
                        projection,
                        ("" + EventEntry.TABLE_NAME + "." + EventEntry.COLUMN_USER_ID + " = ?"),
                        new String[]{EventEntry.getUserIdFromUri(uri)},
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case USER_WITH_USERNAME: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        UserEntry.TABLE_NAME,
                        projection,
                        ("" + UserEntry.TABLE_NAME + "." + UserEntry.COLUMN_USERNAME + " = ?"),
                        new String[]{UserEntry.getUsernameFromUri(uri)},
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case USER_WITH_ID: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        UserEntry.TABLE_NAME,
                        projection,
                        UserEntry.TABLE_NAME + "." + ChoresContract.LONG_ID + " = ?",
                        new String[]{String.valueOf(UserEntry.getIdFromUri(uri))},
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case USER: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        UserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case EVENTS: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        EventEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case EVENT_WITH_ID: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        EventEntry.TABLE_NAME,
                        projection,
                        ("" + EventEntry.TABLE_NAME + "." + EventEntry.COLUMN_LONG_ID + " = ?"),
                        new String[]{uri.getPathSegments().get(2)},
                        null,
                        null,
                        sortOrder
                );
                break;

            }
            case EVENT_WITH_STARTDATE: {
                String startDate = EventEntry.getStartDateFromUri(uri);
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        EventEntry.TABLE_NAME,
                        projection,
                        "CAST(" + EventEntry.TABLE_NAME + "." + EventEntry.COLUMN_DATE_CREATED + " AS INTEGER)" + " >= ? AND " + EventEntry.COLUMN_IS_WANT + " = ?",
                        new String[]{startDate, EventEntry.getIsWantFromUri(uri)},
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case EVENT_WITH_IS_WANT: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        EventEntry.TABLE_NAME,
                        projection,
                        ("" + EventEntry.TABLE_NAME + "." + EventEntry.COLUMN_IS_WANT + " = ?"),
                        new String[]{EventEntry.getIsWantFromUri(uri) + ""},
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case COMMENT_WITH_ID: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        CommentEntry.TABLE_NAME,
                        projection,
                        ("" + CommentEntry.TABLE_NAME + "." + CommentEntry._ID + " = ?"),
                        new String[]{CommentEntry.getIdFromUri(uri) + ""},
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case COMMENTS: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        CommentEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case FRIEND_WITH_ID: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        FriendshipEntry.TABLE_NAME,
                        projection,
                        ("" + FriendshipEntry.TABLE_NAME + "." + FriendshipEntry._ID + " = ?"),
                        new String[]{FriendshipEntry.getIdFromUri(uri) + ""},
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case FRIEND_WITH_USER_ID: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        FriendshipEntry.TABLE_NAME,
                        projection,
                        ("" + FriendshipEntry.TABLE_NAME + "." + FriendshipEntry.COLUMN_USER_ID1 + " = ? OR " + FriendshipEntry.COLUMN_USER_ID2 + " = ?"),
                        new String[]{FriendshipEntry.getId1FromUri(uri) + "", FriendshipEntry.getId2FromUri(uri) + ""},
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case FRIENDS: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        UserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case RSVP_WITH_EVENT_ID: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        RSVPEntry.TABLE_NAME,
                        projection,
                        ("" + RSVPEntry.TABLE_NAME + "." + RSVPEntry.COLUMN_EVENT_ID + " = ?"),
                        new String[]{RSVPEntry.getEventIdFromUri(uri)},
                        null,
                        null,
                        null
                );
                break;
            }

            case RSVP_WITH_ID: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        RSVPEntry.TABLE_NAME,
                        projection,
                        ("" + RSVPEntry.TABLE_NAME + "." + RSVPEntry._ID + " = ?"),
                        new String[]{RSVPEntry.getIdFromUri(uri)},
                        null,
                        null,
                        sortOrder
                );
                break;


            }

            case RSVP: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        RSVPEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case FAVORITE_WITH_USER_ID: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        FavoriteEntry.TABLE_NAME,
                        projection,
                        ("" + FavoriteEntry.TABLE_NAME + "." + FavoriteEntry.COLUMN_USER_ID + " = ?"),
                        new String[]{FavoriteEntry.getUserIdFromUri(uri) + ""},
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case FAVORITE_WITH_ID: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        FavoriteEntry.TABLE_NAME,
                        projection,
                        FavoriteEntry.TABLE_NAME + "." + FavoriteEntry._ID + " = ?",
                        new String[]{FavoriteEntry.getIdFromUri(uri)},
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case FAVORITES: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        FavoriteEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case FAVORITES_FOR_USER: {
                retCursor = mChoresDbHelper.getReadableDatabase().query(
                        FavoriteEntry.TABLE_NAME,
                        projection,
                        FavoriteEntry.TABLE_NAME + "." + FavoriteEntry.COLUMN_USER_ID + " = ?",
                        new String[]{uri.getPathSegments().get(2)},
                        null,
                        null,
                        FavoriteEntry.COLUMN_DATE_CREATED + " DESC"
                );
                break;

            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {

        switch (sUriMatcher.match(uri)) {
            case EVENTS:
            case EVENT_WITH_STARTDATE:
                return EventEntry.CONTENT_TYPE;

            case EVENT_WITH_ID:
                return EventEntry.CONTENT_ITEM_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mChoresDbHelper.getWritableDatabase();
        Uri returnUri;
        long _id;

        Long appEngineId = values.getAsLong(ChoresContract.LONG_ID);

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

        getContext().getContentResolver().notifyChange(uri, null);
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
     *
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
        matcher.addURI(authority, ChoresContract.PATH_EVENT + "/s/*/e/#/iw/#", EVENT_WITH_STARTDATE);
        matcher.addURI(authority, ChoresContract.PATH_EVENT + "/id/#", EVENT_WITH_ID);
        matcher.addURI(authority, ChoresContract.PATH_EVENT + "/iw/#", EVENT_WITH_IS_WANT);


        // Comments URI's
        matcher.addURI(authority, ChoresContract.PATH_COMMENTS, COMMENTS);
        matcher.addURI(authority, ChoresContract.PATH_COMMENTS + "/commentId/#", COMMENT_WITH_ID);

        // Favorites URI's
        matcher.addURI(authority, ChoresContract.PATH_FAVORITES, FAVORITES);
        matcher.addURI(authority, ChoresContract.PATH_FAVORITES + "/userId/#", FAVORITES_FOR_USER);
        matcher.addURI(authority, ChoresContract.PATH_FAVORITES + "/favId/#", FAVORITE_WITH_ID);
        matcher.addURI(authority, ChoresContract.PATH_FAVORITES + "/favId/uId/#", FAVORITE_WITH_USER_ID);

        // Friends URI's
        matcher.addURI(authority, ChoresContract.PATH_FRIENDSHIP, FRIENDS);
        matcher.addURI(authority, ChoresContract.PATH_FRIENDSHIP + "/fId/#", FRIEND_WITH_ID);
        matcher.addURI(authority, ChoresContract.PATH_FRIENDSHIP + "/fId/userId1/#/userId2/#", FRIEND_WITH_USER_ID);


        // RSVP Uri's
        matcher.addURI(authority, ChoresContract.PATH_RSVP, RSVP);
        matcher.addURI(authority, ChoresContract.PATH_RSVP + "/rsvpId/#", RSVP_WITH_ID);
        matcher.addURI(authority, ChoresContract.PATH_RSVP + "/eId/#", RSVP_WITH_EVENT_ID);

        return matcher;
    }
}
