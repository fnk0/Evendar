package com.example.kieran.myapplication.backend;

public class ChoresContract {

    // Base authority + base uri
    public static final String CONTENT_AUTHORITY = "com.gabilheri.choresapp.app";

    // Tables
    public static final String PATH_USER = "users";
    public static final String PATH_EVENT = "events";

    public static final class UserEntry {


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

        public static final String COLUMN_DATE_CREATED = "date_created";


    }

    public static final class EventEntry {




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




    }

    public static final class FavoriteEntry{

        public static final String TABLE_NAME = "favorites";

        public static final String COLUMN_EVENT_ID = "event_id";

        public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_LONG_ID = "long_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";

        public static final String COLUMN_DATE_CREATED = "date_created";

    }

    public static final class FriendshipEntry {
        public static final String TABLE_NAME = "friendships";

        public static final String COLUMN_USER_ID1 = "user_id1";

        public static final String COLUMN_USER_ID2 = "user_id2";

        public static final String COLUMN_LONG_ID = "long_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";

        public static final String COLUMN_DATE_CREATED = "date_created";

    }

    public static final class CommentEntry {
        public static final String TABLE_NAME = "comments";

        public static final String COLUMN_TEXT = "text";

        public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_EVENT_ID = "event_id";

        public static final String COLUMN_TIME = "time";

        public static final String COLUMN_LONG_ID = "long_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";

        public static final String COLUMN_DATE_CREATED = "date_created";

    }

    public static final class RSVPEntry {
        public static final String TABLE_NAME = "RSVPs";

        public static final String COLUMN_EVENT_ID = "event_id";

        public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_LONG_ID = "long_id";

        public static final String COLUMN_UPDATED_AT = "updated_at";

        public static final String COLUMN_DATE_CREATED = "date_created";

    }




}
