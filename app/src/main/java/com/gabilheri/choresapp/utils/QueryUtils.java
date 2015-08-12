package com.gabilheri.choresapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.util.SparseArrayCompat;

import com.gabilheri.choresapp.ChoresApp;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.data.models.Favorite;
import com.gabilheri.choresapp.data.models.User;

import static com.gabilheri.choresapp.data.ChoresContract.EventEntry;
import static com.gabilheri.choresapp.data.ChoresContract.UserEntry;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/4/15.
 */
public final class QueryUtils {

    private QueryUtils() {
    }

    public static User getAuthenticatedUserFromDB() {
        Context context = ChoresApp.instance().getApplicationContext();
        String username = PrefManager.with(context).getString(Const.USERNAME, Const.DEFAULT_USERNAME);
        return getUserFromDB(username);
    }

    public static User getUserFromDB(String username) {
        Context context = ChoresApp.instance().getApplicationContext();
        Cursor userCursor = context.getContentResolver().query(UserEntry.buildUserUri(username), null, null, null, null);
        return User.fromCursor(userCursor, true);
    }

    public static User getUserFromDB(Long id) {
        Context context = ChoresApp.instance().getApplicationContext();
        Cursor userCursor = context.getContentResolver().query(UserEntry.buildUserUri(id), null, null, null, null);
        return User.fromCursor(userCursor, true);
    }

    public static Favorite getFavoriteFromDB(Long uId){
        Context context = ChoresApp.instance().getApplicationContext();
        Cursor favCursor = context.getContentResolver().query(ChoresContract.FavoriteEntry.buildFavoritesForUser(uId), null, null, null, null);
        return Favorite.fromCursor(favCursor, true);
    }

    public static void saveEventToDB(Event event) {
        Context context = ChoresApp.instance().getApplicationContext();
        ContentValues values = Event.toContentValues(event);
        context.getContentResolver().insert(EventEntry.buildEventUri(), values);
    }

    public static Event getEventFromDB(Long eventId) {
        Context context = ChoresApp.instance().getApplicationContext();
        Cursor eventCursor = context.getContentResolver().query(EventEntry.buildEventUri(eventId), null, null, null, null);
        return Event.fromCursor(eventCursor, true);
    }

    public static SparseArrayCompat<User> getUsersForEvent(Long eventId) {
        Context context = ChoresApp.instance().getApplicationContext();
        Cursor usersCursor = context.getContentResolver().query(UserEntry.buildUsersForEvent(eventId), null, null, null, null);
        SparseArrayCompat<User> users = new SparseArrayCompat<>(3);
        int counter = 0;
        while(usersCursor.moveToNext() && counter < 2) {
            users.put(counter, User.fromCursor(usersCursor, false));
            counter++;
        }
        usersCursor.close();
        return users;
    }
}
