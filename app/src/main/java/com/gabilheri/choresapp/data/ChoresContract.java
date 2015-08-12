package com.gabilheri.choresapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

import org.joda.time.LocalDateTime;

/**
 * Created by kieran on 7/24/15.
 */
public class ChoresContract {

    // Base authority + base uri
    public static final String CONTENT_AUTHORITY = "com.gabilheri.choresapp.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String LONG_ID = "long_id";

    // Tables
    public static final String PATH_USER = "users";
    public static final String PATH_EVENT = "events";
    public static final String PATH_RSVP = "rsvp";
    public static final String PATH_COMMENTS = "comments";
    public static final String PATH_FRIENDSHIP = "friendships";
    public static final String PATH_FAVORITES = "favorites";

    public static final class UserEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String TABLE_NAME = "users";

        public static final String COLUMN_FULL_NAME = "full_name";

        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_EMAIL = "email";

        public static final String COLUMN_DATE_REGISTERED = "date_registered";

        public static final String COLUMN_FACEBOOK_USERNAME = "facebook_username";
        public static final String COLUMN_TWITTER_USERNAME = "twitter_username";
        public static final String COLUMN_GOOGLE_USERNAME = "google_username";

        public static final String COLUMN_NUM_EVENTS = "num_events";
        public static final String COLUMN_NUM_FAVORITES = "num_favorites";

        public static final String COLUMN_PIC_URL = "pic_URL";

        public static final String COLUMN_UPDATED_AT = "updated_at";

        public static final String COLUMN_DATE_CREATED = "date_created";

        public static Uri buildUserUri() {
            return CONTENT_URI;
        }

        public static Uri buildUserUri(Long id) {
            return CONTENT_URI.buildUpon()
                    .appendPath("id")
                    .appendPath(String.valueOf(id))
                    .build();
        }

        public static Uri buildUserUri(String username) {
            return CONTENT_URI
                    .buildUpon()
                    .appendPath("us")
                    .appendPath(username)
                    .build();
        }

        public static Uri buildUsersForEvent(Long eventId) {
            return CONTENT_URI.buildUpon()
                    .appendPath("ei")
                    .appendPath(String.valueOf(eventId))
                    .build();
        }

        public static String getUsernameFromUri(Uri uri) {
            return uri.getPathSegments().get(2);
        }

        public static Long getIdFromUri(Uri uri) {
            return Long.parseLong(uri.getPathSegments().get(2));
        }


    }

    public static final class EventEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENT).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;

        public static final String TABLE_NAME = "event";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_DATE = "date";

        public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_USERNAME = "username";

        public static final String COLUMN_TIME = "time";

        public static final String COLUMN_IS_WANT = "is_want";

        public static final String COLUMN_NUM_FAV = "num_fav";

        public static final String COLUMN_NUM_COMMENTS = "num_comments";

        public static final String COLUMN_NUM_GOING = "num_going";

        public static final String COLUMN_NUM_SHARES = "num_shares";

        public static final String COLUMN_LOC = "loc";

        public static final String COLUMN_LONG_ID = "long_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";

        public static final String COLUMN_DATE_CREATED = "date_created";

        public static final String COLUMN_DESCRIPTION = "description";

        public static Uri buildEventUri() {
            return CONTENT_URI;
        }

        public static Uri buildLocalEventUri(long id) {
            return CONTENT_URI.buildUpon()
                    .appendPath("localId")
                    .appendPath(String.valueOf(id))
                    .build();
        }

        public static Uri buildEventUri(Long id) {
            return CONTENT_URI.buildUpon()
                    .appendPath("id")
                    .appendPath(String.valueOf(id))
                    .build();
        }

        public static String getIsWantFromUri(Uri uri) {
            return uri.getPathSegments().get(6);
        }

        /**
         * @param startDate The beginning of the period to get events
         * @param endDate   The ending of the period for the events
         * @param isWant    represents if the events should be isWant or goingTo
         * @return The uri representing this query
         */
        public static Uri buildEventUri(String startDate, @Nullable String endDate, boolean isWant) {

            if (endDate == null) {
                endDate = String.valueOf(LocalDateTime.now().plusDays(365).toDate().getTime());
            }

            return CONTENT_URI.buildUpon()
                    .appendPath("s")
                    .appendPath(startDate)
                    .appendPath("e")
                    .appendPath(endDate)
                    .appendPath("iw")
                    .appendPath(String.valueOf(isWant ? 1 : 0))
                    .build();
        }

        public static String getDateFromUri(Uri uri) {
            return uri.getPathSegments().get(4);
        }

        public static String getStartDateFromUri(Uri uri) {
            return uri.getPathSegments().get(2);
        }

        public static String getUserIdFromUri(Uri uri) {
            return uri.getPathSegments().get(2);
        }
    }

    public static final class FavoriteEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITES).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVORITES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVORITES;

        public static final String TABLE_NAME = "favorites";

        public static final String COLUMN_EVENT_ID = "event_id";

        public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";

        public static final String COLUMN_DATE_CREATED = "date_created";

        public static Uri buildFavorite(Long favId) {
            return CONTENT_URI.buildUpon()
                    .appendPath("favId")
                    .appendPath(String.valueOf(favId))
                    .build();
        }

        public static Uri buildFavoritesForUser(Long userId) {
            return CONTENT_URI.buildUpon()
                    .appendPath("userId")
                    .appendPath(String.valueOf(userId))
                    .build();
        }

        public static String getUserIdFromUri(Uri uri) {
            return uri.getPathSegments().get(4);
        }

        public static String getIdFromUri(Uri uri) {
            return uri.getPathSegments().get(2);
        }

    }

    public static final class FriendshipEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FRIENDSHIP).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FRIENDSHIP;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FRIENDSHIP;

        public static final String TABLE_NAME = "friendships";

        public static final String COLUMN_USER_ID1 = "user_id1";

        public static final String COLUMN_USER_ID2 = "user_id2";

        public static final String COLUMN_UPDATED_AT = "updated_at";

        public static final String COLUMN_DATE_CREATED = "date_created";

        public static Uri buildAllFriends() {
            return CONTENT_URI;
        }

        public static Uri buildFriendship(Long friendshipID) {
            return CONTENT_URI.buildUpon()
                    .appendPath("fId")
                    .appendPath(String.valueOf(friendshipID))
                    .build();
        }

        public static Uri buildFriendsForUser(Long userId) {
            return CONTENT_URI.buildUpon()
                    .appendPath("userId")
                    .appendPath(String.valueOf(userId))
                    .build();
        }

        public static String getIdFromUri(Uri uri) {
            return uri.getPathSegments().get(2);
        }

        public static String getId1FromUri(Uri uri) {
            return uri.getPathSegments().get(4);
        }

        public static String getId2FromUri(Uri uri) {
            return uri.getPathSegments().get(6);
        }

    }

    public static final class CommentEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_COMMENTS).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMMENTS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMMENTS;

        public static final String TABLE_NAME = "comments";

        public static final String COLUMN_TEXT = "text";

        public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_EVENT_ID = "event_id";

        public static final String COLUMN_TIME = "time";

        public static final String COLUMN_UPDATED_AT = "updated_at";

        public static final String COLUMN_DATE_CREATED = "date_created";

        public static Uri buildCommentsUri(Long commentId) {
            return CONTENT_URI.buildUpon()
                    .appendPath("commentId")
                    .appendPath(String.valueOf(commentId))
                    .build();
        }

        public static Uri buildCommentsUriForEvent(Long eventId) {
            return CONTENT_URI.buildUpon()
                    .appendPath("eventId")
                    .appendPath(String.valueOf(eventId))
                    .build();
        }

        public static String getIdFromUri(Uri uri) {
            return uri.getPathSegments().get(2);
        }
    }

    public static final class RSVPEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RSVP).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RSVP;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RSVP;

        public static final String TABLE_NAME = "RSVPs";

        public static final String COLUMN_EVENT_ID = "event_id";

        public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";

        public static final String COLUMN_DATE_CREATED = "date_created";

        public static final String COLUMN_FULL_NAME = "full_name";


        public static Uri buildRSVP(Long rsvpID) {
            return CONTENT_URI.buildUpon()
                    .appendPath("rsvpId")
                    .appendPath(String.valueOf(rsvpID))
                    .build();
        }

        public static Uri buildUsersForEvent(Long eventId) {
            return CONTENT_URI.buildUpon()
                    .appendPath("eId")
                    .appendPath(String.valueOf(eventId))
                    .build();
        }

        public static String getEventIdFromUri(Uri uri) {
            return uri.getPathSegments().get(2);
        }

        public static String getIdFromUri(Uri uri) {
            return uri.getPathSegments().get(2);
        }
    }
}
