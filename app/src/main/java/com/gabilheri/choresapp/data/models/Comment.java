package com.gabilheri.choresapp.data.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.gabilheri.choresapp.data.ChoresContract;

import static com.gabilheri.choresapp.data.ChoresContract.CommentEntry;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/21/15.
 */
public class Comment {

    public static final int ID_PROJECTION = 0;
    public static final int TEXT_PROJECTION = 1;
    public static final int TIME_PROJECTION = 2;
    public static final int LONG_ID_PROJECTION = 3;
    public static final int UPDATED_AT_PROJECTION = 4;
    public static final int CREATED_AT_PROJECTION = 5;
    public static final int USER_ID_PROJECTION = 6;

    /**
     * Always use this projection to query the Comments table.
     * That ensures a faster parsing of the cursor
     */
    public static final String[] commentsProjection = {
            CommentEntry._ID,
            CommentEntry.COLUMN_TEXT,
            CommentEntry.COLUMN_TIME,
            ChoresContract.LONG_ID,
            CommentEntry.COLUMN_UPDATED_AT,
            CommentEntry.COLUMN_DATE_CREATED,
            CommentEntry.COLUMN_USER_ID
    };

    long autoID;
    Long id;
    String time;
    String text;
    String userId;
    String updatedAt;
    String createdAt;

    public Comment() {
    }

    public long getAutoID() {
        return autoID;
    }

    public Comment setAutoID(long autoID) {
        this.autoID = autoID;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Comment setTime(String time) {
        this.time = time;
        return this;
    }

    public String getText() {
        return text;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Comment setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Comment setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Comment setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public static Comment fromCursor(Cursor cursor, boolean shouldClose) {

        if(cursor.getPosition() == -1) {
            cursor.moveToNext();
        }

        Comment comment = new Comment();
        comment.setAutoID(cursor.getLong(cursor.getColumnIndex(CommentEntry._ID)));
        comment.setId(cursor.getLong(cursor.getColumnIndex(ChoresContract.LONG_ID)));
        comment.setText(cursor.getString(cursor.getColumnIndex(CommentEntry.COLUMN_TEXT)));
        comment.setUserId(cursor.getString(cursor.getColumnIndex(CommentEntry.COLUMN_USER_ID)));
        comment.setCreatedAt(cursor.getString(cursor.getColumnIndex(CommentEntry.COLUMN_DATE_CREATED)));
        comment.setUpdatedAt(cursor.getString(cursor.getColumnIndex(CommentEntry.COLUMN_UPDATED_AT)));
        comment.setTime(cursor.getString(cursor.getColumnIndex(CommentEntry.COLUMN_TIME)));

        if(shouldClose) {
            cursor.close();
        }

        return comment;
    }

    public static ContentValues toContentValues(Comment comment) {
        ContentValues values = new ContentValues();
        values.put(ChoresContract.LONG_ID, comment.getId());
        values.put(CommentEntry.COLUMN_TEXT, comment.getText());
        values.put(CommentEntry.COLUMN_TIME, comment.getTime());
        values.put(CommentEntry.COLUMN_UPDATED_AT, comment.getUpdatedAt());
        values.put(CommentEntry.COLUMN_DATE_CREATED, comment.getCreatedAt());
        values.put(CommentEntry.COLUMN_USER_ID, comment.getUserId());
        return values;
    }
}
