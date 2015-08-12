package com.gabilheri.choresapp.data.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.gabilheri.choresapp.data.ChoresContract;

import static com.gabilheri.choresapp.data.ChoresContract.EventEntry;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/4/15.
 */
public class Event {

    String description;
    Long id;
    String title;
    String createdAt;
    String time;
    String date;
    boolean isWant;
    int numFavorites;
    int numComments;
    int numGoing;
    int numShares;
    String username;

    /**
     * Location can be of the following types
     *
     * addr:// represents an address
     * geo:// represents geo coordinates in the format LAT,LONG with no spaces in between
     */
    String location;

    String updatedAt;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public Event setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Event setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Event setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Event setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public Event setDescription(String description) {
        this.description = description;
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

    public String getUsername() {
        return username;
    }

    public Event setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getNumShares() {
        return numShares;
    }

    public Event setNumShares(int numShares) {
        this.numShares = numShares;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public Event setDate(String date) {
        this.date = date;
        return this;
    }

    public static Event fromCursor(Cursor cursor, boolean closeCursor) {
        if(cursor.getPosition() == -1) {
            cursor.moveToNext();
        }

        Event event = new Event();
        event.setCreatedAt(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_DATE_CREATED)))
                .setId(cursor.getLong(cursor.getColumnIndex(ChoresContract.LONG_ID)))
                .setDate(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_DATE)))
                .setTime(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_TIME)))
                .setTitle(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_TITLE)))
                .setIsWant(cursor.getInt(cursor.getColumnIndex(EventEntry.COLUMN_IS_WANT)) == 1)
                .setLocation(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_LOC)))
                .setNumComments(cursor.getInt(cursor.getColumnIndex(EventEntry.COLUMN_NUM_COMMENTS)))
                .setNumFavorites(cursor.getInt(cursor.getColumnIndex(EventEntry.COLUMN_NUM_FAV)))
                .setDescription(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_DESCRIPTION)))
                .setNumGoing(cursor.getInt(cursor.getColumnIndex(EventEntry.COLUMN_NUM_GOING)))
                .setNumShares(cursor.getInt(cursor.getColumnIndex(EventEntry.COLUMN_NUM_SHARES)))
                .setUsername(cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_USER_ID)));

        if(closeCursor) {
            cursor.close();
        }

        return event;
    }

    public static ContentValues toContentValues(Event event) {
        ContentValues values = new ContentValues();
        values.put(EventEntry.COLUMN_LONG_ID, event.getId());
        values.put(EventEntry.COLUMN_DATE, event.getDate());
        values.put(EventEntry.COLUMN_DATE_CREATED, event.getCreatedAt());
        values.put(EventEntry.COLUMN_TIME, event.getTime());
        values.put(EventEntry.COLUMN_TITLE, event.getTitle());
        values.put(EventEntry.COLUMN_IS_WANT, event.isWant());
        values.put(EventEntry.COLUMN_LOC, event.getLocation());
        values.put(EventEntry.COLUMN_NUM_COMMENTS, event.getNumComments());
        values.put(EventEntry.COLUMN_NUM_FAV, event.getNumFavorites());
        values.put(EventEntry.COLUMN_NUM_GOING, event.getNumGoing());
        values.put(EventEntry.COLUMN_USER_ID, event.getUsername());
        values.put(EventEntry.COLUMN_NUM_SHARES, event.getNumShares());
        values.put(EventEntry.COLUMN_DESCRIPTION, event.getDescription());
        return values;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", time='" + time + '\'' +
                ", isWant=" + isWant +
                ", numFavorites=" + numFavorites +
                ", numComments=" + numComments +
                ", numGoing=" + numGoing +
                ", location='" + location + '\'' +
                '}';
    }
}
