package com.gabilheri.choresapp.data.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.DrawableRes;

import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.data.ChoresContract;

import static com.gabilheri.choresapp.data.ChoresContract.UserEntry;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/4/15.
 */
public class User {

    // Used by the endpoints
    Long id;

    String username;

    String fullName;

    String email;

    String dateRegistered;

    String facebookUsername;

    String twitterUsername;

    String googleUsername;

    String picUrl;

    int numEvents;

    int numFavorites;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public User setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
        return this;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }

    public User setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
        return this;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public User setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
        return this;
    }

    public String getGoogleUsername() {
        return googleUsername;
    }

    public User setGoogleUsername(String googleUsername) {
        this.googleUsername = googleUsername;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public User setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    public int getNumEvents() {
        return numEvents;
    }

    public User setNumEvents(int numEvents) {
        this.numEvents = numEvents;
        return this;
    }

    public int getNumFavorites() {
        return numFavorites;
    }

    public User setNumFavorites(int numFavorites) {
        this.numFavorites = numFavorites;
        return this;
    }

    public static ContentValues toContentValues(User user) {
        ContentValues userValues = new ContentValues();

        userValues.put(UserEntry.COLUMN_EMAIL, user.getEmail());
        userValues.put(UserEntry.COLUMN_USERNAME, user.getUsername());
        userValues.put(UserEntry.COLUMN_FULL_NAME, user.getFullName());
        userValues.put(UserEntry.COLUMN_PIC_URL, user.getPicUrl());
        userValues.put(UserEntry.COLUMN_DATE_REGISTERED, user.getDateRegistered());
        userValues.put(UserEntry.COLUMN_FACEBOOK_USERNAME, user.getFacebookUsername());
        userValues.put(UserEntry.COLUMN_GOOGLE_USERNAME, user.getGoogleUsername());
        userValues.put(UserEntry.COLUMN_TWITTER_USERNAME, user.getTwitterUsername());
        userValues.put(UserEntry.COLUMN_NUM_EVENTS, user.getNumEvents());
        userValues.put(UserEntry.COLUMN_NUM_FAVORITES, user.getNumFavorites());

        return userValues;
    }

    public static User fromCursor(Cursor cursor, boolean closeCursor) {
        if(cursor.moveToFirst()) {
            User user = new User();
            user.setUsername(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_USERNAME)))
                .setEmail(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_EMAIL)))
                .setFullName(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_FULL_NAME)))
                .setFacebookUsername(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_FACEBOOK_USERNAME)))
                .setTwitterUsername(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_TWITTER_USERNAME)))
                .setGoogleUsername(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_GOOGLE_USERNAME)))
                .setPicUrl(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_PIC_URL)))
                .setDateRegistered(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_DATE_REGISTERED)))
                .setNumEvents(cursor.getInt(cursor.getColumnIndex(UserEntry.COLUMN_NUM_EVENTS)))
                .setId(cursor.getLong(cursor.getColumnIndex(ChoresContract.LONG_ID)))
                .setNumFavorites(cursor.getInt(cursor.getColumnIndex(UserEntry.COLUMN_NUM_FAVORITES)));

            if(closeCursor) {
                cursor.close();
            }

            return user;
        } else {
            return null;
        }
    }

    public @DrawableRes int getSocialMedia() {
        if(googleUsername != null) {
            return R.drawable.ic_google;
        } else if(facebookUsername == null) {
            return R.drawable.ic_facebook;
        } else if(twitterUsername == null) {
            return R.drawable.ic_twitter;
        } else {
            return R.mipmap.ic_launcher;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dateRegistered='" + dateRegistered + '\'' +
                ", facebookUsername='" + facebookUsername + '\'' +
                ", twitterUsername='" + twitterUsername + '\'' +
                ", googleUsername='" + googleUsername + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", numEvents=" + numEvents +
                ", numFavorites=" + numFavorites +
                '}';
    }
}
