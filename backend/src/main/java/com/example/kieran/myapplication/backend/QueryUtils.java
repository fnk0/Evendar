package com.example.kieran.myapplication.backend;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import static com.example.kieran.myapplication.backend.OfyService.ofy;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/6/15.
 */
public final class QueryUtils {

    private QueryUtils() { }

    public static <T> T findByUsername(Class<T> type, String username) {
        return ofy().load().type(type).filter("username", username).first().now();
    }

    public static <T> T findByUserId(Class<T> type, Long userId) {
        return ofy().load().type(type).filter("userId", userId).first().now();
    }

    public static <T> T findRecordByDate(Class<T> type, String date) {
        return ofy().load().type(type).filter("date", date).first().now();
    }

    public static <T> T findRecordByUpdatedDate(Class<T> type, String date) {
        return ofy().load().type(type).filter("updatedAt", date).first().now();
    }

    public static <T> T findRecordByCreatedDate(Class<T> type, String date) {
        return ofy().load().type(type).filter("createdAt", date).first().now();
    }

    public static <T> T findRecord(Class<T> type, Long id) {
        return ofy().load().type(type).id(id).now();
    }

    public static <T> T getObject(Class<T> type, @Named("id") Long id) throws NotFoundException {
        T obj = findRecord(type, id);
        if(obj == null) {
            throw new NotFoundException("Could not find " + type.getCanonicalName() + " with id: " + id);
        }
        return obj;
    }

    public static <T> T insertObject(T object) {
        ofy().save().entity(object).now();
        return object;
    }

    public static <T> void deleteObject(Class<T> type, @Named("id") Long id) throws NotFoundException {
        T obj = findRecord(type, id);
        if(obj == null) {
            throw new NotFoundException("Could not find " + type.getSimpleName() + " with id: " + id);
        }
        ofy().delete().entity(obj).now();
    }

    public static <T> void deleteObjectByUsername(Class<T> type, @Named("username") String username) throws NotFoundException {
        T obj = findByUsername(type, username);
        if(obj == null) {
            throw new NotFoundException("Could not find " + type.getSimpleName() + " with username: " + username);
        }
        ofy().delete().entity(obj).now();
    }

    public static <T> CollectionResponse<T> listByUser(Class<T> type,
                                                       @Named("username") String username, @Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) {
        Query<T> query = ofy().load().type(type).filter("username", username);
        return listByQuery(query, cursorString, count);
    }

    public static <T> CollectionResponse<T> list(Class<T> type,
                                                 @Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) {
        Query<T> query = ofy().load().type(type);
        return listByQuery(query, cursorString, count);
    }

    public static <T> CollectionResponse<T> listByQuery(Query<T> query, @Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) {
        if (count != null) query.limit(count);
        if (cursorString != null && !cursorString.isEmpty()) {
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }
        List<T> records = new ArrayList<>();
        QueryResultIterator<T> iterator = query.iterator();
        int num = 0;
        while (iterator.hasNext()) {
            records.add(iterator.next());
            if (count != null) {
                num++;
                if (num == count) break;
            }
        }
        //Find the next cursor
        if (cursorString != null && !cursorString.isEmpty()) {
            Cursor cursor = iterator.getCursor();
            if (cursor != null) {
                cursorString = cursor.toWebSafeString();
            }
        }
        return CollectionResponse.<T>builder().setItems(records).setNextPageToken(cursorString).build();
    }
}
