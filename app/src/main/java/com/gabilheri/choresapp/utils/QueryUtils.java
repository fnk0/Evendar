package com.gabilheri.choresapp.utils;

import android.content.Context;
import android.database.Cursor;

import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.models.User;

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

    public static User getUserFromDB(Context context, String username) {
        Cursor userCursor = context.getContentResolver().query(
                ChoresContract.UserEntry.buildUserUri(username), null, null, null, null);
        return User.fromCursor(userCursor, true);
    }
}
