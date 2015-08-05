package com.gabilheri.choresapp.data.models;

import android.content.ContentValues;
import android.database.Cursor;

import static com.gabilheri.choresapp.data.ChoresContract.EventEntry;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/4/15.
 */
public class Event {

    String title;
    String date;
    String time;
    boolean isWant;
    int numFavorites;
    int numComments;
    int numGoing;
    int numShares;
    String userId;

    /**
     * Location can be of the following types
     *
     * addr:// represents an address
     * geo:// represents geo coordinates in the format LAT,LONG with no spaces in between
     */
    String location;

    public Event() {
    }

    public String getTitle() {
        return title;
    }

    public Event setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Event setDate(String date) {
        this.date = date;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Event setTime(String time) {
        this.time = time;
        return this;
    }

    public boolean isWant() {
        return isWant;
    }

    public Event setIsWant(boolean isWant) {
        this.isWant = isWant;
        return this;
    }

    public int getNumFavorites() {
        return numFavorites;
    }

    public Event setNumFavorites(int numFavorites) {
        this.numFavorites = numFavorites;
        return this;
    }

    public int getNumComments() {
        return numComments;
    }

    public Event setNumComments(int numComments) {
        this.numComments = numComments;
        return this;
    }

    public int getNumGoing() {
        return numGoing;
    }

    public Event setNumGoing(int numGoing) {
        this.numGoing = numGoing;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Event setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Event setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public int getNumShares() {
        return numShares;
    }

    public Event setNumShares(int numShares) {
        this.numShares = numShares;
        return this;
    }

    public static Event fromCursor(Cursor cursor, boolean closeCursor) {
        Event event = new Event();
        event.setDate(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_DATE)))
                .setTime(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_TIME)))
                .setTitle(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_TITLE)))
                .setIsWant(cursor.getInt(cursor.getColumnIndex(EventEntry.COLUMN_IS_WANT)) == 1)
                .setLocation(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_LOC)))
                .setNumComments(cursor.getInt(cursor.getColumnIndex(EventEntry.COLUMN_NUM_COMMENTS)))
                .setNumFavorites(cursor.getInt(cursor.getColumnIndex(EventEntry.COLUMN_NUM_FAV)))
                .setNumGoing(cursor.getInt(cursor.getColumnIndex(EventEntry.COLUMN_NUM_GOING)))
                .setNumShares(cursor.getInt(cursor.getColumnIndex(EventEntry.COLUMN_NUM_SHARES)))
                .setUserId(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_USER_ID)));

        if(closeCursor) {
            cursor.close();
        }

        return event;
    }

    public static ContentValues toContentValues(Event event) {
        ContentValues values = new ContentValues();
        values.put(EventEntry.COLUMN_DATE, event.getDate());
        values.put(EventEntry.COLUMN_TIME, event.getTime());
        values.put(EventEntry.COLUMN_TITLE, event.getTitle());
        values.put(EventEntry.COLUMN_IS_WANT, event.isWant());
        values.put(EventEntry.COLUMN_LOC, event.getLocation());
        values.put(EventEntry.COLUMN_NUM_COMMENTS, event.getNumComments());
        values.put(EventEntry.COLUMN_NUM_FAV, event.getNumFavorites());
        values.put(EventEntry.COLUMN_NUM_GOING, event.getNumGoing());
        values.put(EventEntry.COLUMN_USER_ID, event.getUserId());
        values.put(EventEntry.COLUMN_NUM_SHARES, event.getNumShares());
        return values;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", isWant=" + isWant +
                ", numFavorites=" + numFavorites +
                ", numComments=" + numComments +
                ", numGoing=" + numGoing +
                ", location='" + location + '\'' +
                '}';
    }
}
