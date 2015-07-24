package com.gabilheri.choresapp.data;

import android.text.format.Time;
import android.provider.BaseColumns;

/**
 * Created by kieran on 7/24/15.
 */
public class ChoresContract {

    // To make it easy to query for the exact date, we normalize all dates that go into
    // the database to the start of the the Julian day at UTC.
    public static long normalizeDate(long startDate){
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }

    public static final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";

        public static final String COLUMN_FULL_NAME = "fullname";

        public static final String COLUMN_EMAIL = "email";

        public static final String COLUMN_DATE_REGISTERED = "date_registered";

        public static final String COLUMN_FACEBOOK_USERNAME = "facebook_username";
        public static final String COLUMN_TWITTER_USERNAME = "twitter_username";
        public static final String COLUMN_GOOGLE_USERNAME = "google_username";



    }

    public static final class EventEntry implements BaseColumns {
        public static final String TABLE_NAME = "event";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_DATE = "date";

        public static final String COLUMN_USER_ID = "user_id";
    }

    public static final class FavoriteEntry implements BaseColumns{

        public static final String TABLE_NAME = "favorites";

        public static final String COLUMN_EVENT_ID = "event_id";

        public static final String COLUMN_USER_ID = "user_id";

    }

    public static final class FriendshipEntry implements BaseColumns{
        public static final String TABLE_NAME = "friendships";

        public static final String COLUMN_USER_ID1 = "user_id1";

        public static final String COLUMN_USER_ID2 = "user_id2";

    }




}
