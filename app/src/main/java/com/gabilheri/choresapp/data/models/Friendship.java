package com.gabilheri.choresapp.data.models;


import android.content.ContentValues;
import android.database.Cursor;

import com.gabilheri.choresapp.data.ChoresContract;

/**
 * Created by kieran on 8/5/15.
 */


public class Friendship {

    Long id;

    String updatedAt;

    String createdAt;

    Long userId1;

    Long userId2;

    public Friendship(){}


    public Long getId() {
        return id;
    }

    public Friendship setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Friendship setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Friendship setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Long getUserId1() {
        return userId1;
    }

    public Friendship setUserId1(Long userId1) {
        this.userId1 = userId1;
        return this;
    }

    public Long getUserId2() {
        return userId2;
    }

    public Friendship setUserId2(Long userId2) {
        this.userId2 = userId2;
        return this;
    }

    public static Friendship fromCursor(Cursor cursor, boolean closeCursor) {
        if(cursor.getPosition() == -1) {
            cursor.moveToNext();
        }

        Friendship friendship = new Friendship();
        friendship.setCreatedAt(cursor.getString(cursor.getColumnIndex(ChoresContract.FriendshipEntry.COLUMN_DATE_CREATED)))
                    .setUpdatedAt(cursor.getString(cursor.getColumnIndex(ChoresContract.FriendshipEntry.COLUMN_UPDATED_AT)))
                    .setUserId1(cursor.getLong(cursor.getColumnIndex(ChoresContract.FriendshipEntry.COLUMN_USER_ID1)))
                    .setUserId2(cursor.getLong(cursor.getColumnIndex(ChoresContract.FriendshipEntry.COLUMN_USER_ID2)))
                    .setId(cursor.getLong(cursor.getColumnIndex(ChoresContract.LONG_ID)));


        if(closeCursor) {
            cursor.close();
        }

        return friendship;
    }

    public static ContentValues toContentValues(Friendship friendship) {
        ContentValues values = new ContentValues();
        values.put(ChoresContract.FriendshipEntry.COLUMN_USER_ID1, friendship.getUserId1());
        values.put(ChoresContract.FriendshipEntry.COLUMN_USER_ID2, friendship.getUserId2());
        values.put(ChoresContract.FriendshipEntry.COLUMN_DATE_CREATED, friendship.getCreatedAt());
        values.put(ChoresContract.FriendshipEntry.COLUMN_UPDATED_AT, friendship.getUpdatedAt());
        values.put(ChoresContract.FriendshipEntry._ID, friendship.getId());
        return values;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", userId1=" + userId1 +
                ", userId2=" + userId2 +
                '}';
    }
}
