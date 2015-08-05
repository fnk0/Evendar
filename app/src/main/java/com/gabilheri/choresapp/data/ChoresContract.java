package com.gabilheri.choresapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.text.format.Time;
import android.provider.BaseColumns;

/**
 * Created by kieran on 7/24/15.
 */
public class ChoresContract {

    // Base authority + base uri
    public static final String CONTENT_AUTHORITY = "com.gabilheri.choresapp.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Tables
    public static final String PATH_USER = "users";
    public static final String PATH_EVENT = "events";

    // To make it easy to query for the exact date, we normalize all dates that go into
    // the database to the start of the the Julian day at UTC.
    public static long normalizeDate(long startDate){
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }

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

        public static final String COLUMN_LONG_ID = "long_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";

        public static Uri buildUserUri() {
            return CONTENT_URI;
        }

        public static Uri buildUserUri(String username) {
            return CONTENT_URI.buildUpon().appendPath(username).build();
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




        public static Uri buildEventUri() {
            return CONTENT_URI;
        }

        /**
         *
         * @param startDate
         *      The beginning of the period to get events
         * @param endDate
         *      The ending of the period for the events
         * @param isWant
         *      represents if the events should be isWant or goingTo
         * @return
         *      The uri representing this query
         */
        public static Uri buildEventUri(String startDate, String endDate, boolean isWant) {
            return CONTENT_URI.buildUpon()
                    .appendPath("s")
                    .appendPath(startDate)
                    .appendPath("e")
                    .appendPath(endDate)
                    .appendPath("iw")
                    .appendPath(String.valueOf(isWant ? 1 : 0))
                    .build();
        }
    }

    public static final class FavoriteEntry implements BaseColumns{

        public static final String TABLE_NAME = "favorites";

        public static final String COLUMN_EVENT_ID = "event_id";

        public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_LONG_ID = "long_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";



    }

    public static final class FriendshipEntry implements BaseColumns{
        public static final String TABLE_NAME = "friendships";

        public static final String COLUMN_USER_ID1 = "user_id1";

        public static final String COLUMN_USER_ID2 = "user_id2";

        public static final String COLUMN_LONG_ID = "long_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";



    }

    public static final class CommentEntry implements BaseColumns{
        public static final String TABLE_NAME = "comments";

        public static final String COLUMN_TEXT = "text";

        public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_EVENT_ID = "event_id";

        public static final String COLUMN_TIME = "time";

        public static final String COLUMN_LONG_ID = "long_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";


    }

    public static final class RSVPEntry implements BaseColumns{
        public static final String TABLE_NAME = "RSVPs";

        public static final String COLUMN_EVENT_ID = "event_id";

        public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_LONG_ID = "long_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";


    }




}
