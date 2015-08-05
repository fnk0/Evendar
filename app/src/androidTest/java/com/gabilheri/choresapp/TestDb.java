package com.gabilheri.choresapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.ChoresDbHelper;

import java.util.HashSet;

/**
 * Created by kieran on 7/24/15.
 */
public class TestDb extends AndroidTestCase {
    //@TODO: complete the test cases to ensure DB is working


    public static final String LOG_TAG = TestDb.class.getSimpleName();

    void deleteTheDatabase(){
        mContext.deleteDatabase(ChoresDbHelper.DATABASE_NAME);
    }

    public void setUp(){
        deleteTheDatabase();
    }



    //tests the creation of the different tables
    public void testCreateDB() throws Throwable {
        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(ChoresContract.UserEntry.TABLE_NAME);
        tableNameHashSet.add(ChoresContract.EventEntry.TABLE_NAME);
        tableNameHashSet.add(ChoresContract.FavoriteEntry.TABLE_NAME);
        tableNameHashSet.add(ChoresContract.FriendshipEntry.TABLE_NAME);
        tableNameHashSet.add(ChoresContract.CommentEntry.TABLE_NAME);
        tableNameHashSet.add(ChoresContract.RSVPEntry.TABLE_NAME);

        mContext.deleteDatabase(ChoresDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new ChoresDbHelper(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());

        //check if tables have been created
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error: The database has not been created correctly", c.moveToFirst());

        do{
            tableNameHashSet.remove(c.getString(0));
        } while(c.moveToNext());

        assertTrue("Error: The database was created without the proper tables", tableNameHashSet.isEmpty());

        //check if tables have proper columns
        c = db.rawQuery("PRAGMA table_info(" + ChoresContract.UserEntry.TABLE_NAME + ")", null);
        assertTrue("Error: Unable to query the database for table info", c.moveToFirst());

        final HashSet<String> userColumnHashSet = new HashSet<String>();
        userColumnHashSet.add(ChoresContract.UserEntry._ID);
        userColumnHashSet.add(ChoresContract.UserEntry.COLUMN_FULL_NAME);
        userColumnHashSet.add(ChoresContract.UserEntry.COLUMN_USERNAME);
        userColumnHashSet.add(ChoresContract.UserEntry.COLUMN_EMAIL);
        userColumnHashSet.add(ChoresContract.UserEntry.COLUMN_DATE_REGISTERED);
        userColumnHashSet.add(ChoresContract.UserEntry.COLUMN_FACEBOOK_USERNAME);
        userColumnHashSet.add(ChoresContract.UserEntry.COLUMN_TWITTER_USERNAME);
        userColumnHashSet.add(ChoresContract.UserEntry.COLUMN_GOOGLE_USERNAME);
        userColumnHashSet.add(ChoresContract.UserEntry.COLUMN_NUM_EVENTS);
        userColumnHashSet.add(ChoresContract.UserEntry.COLUMN_NUM_FAVORITES);
        userColumnHashSet.add(ChoresContract.UserEntry.COLUMN_PIC_URL);

        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            userColumnHashSet.remove(columnName);
        } while(c.moveToNext());

        assertTrue("Error: The database doesn't contain all the user table columns", userColumnHashSet.isEmpty());
        db.close();








    }


    public void testUserTable() {

        SQLiteDatabase db = new ChoresDbHelper(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());



    }




}
