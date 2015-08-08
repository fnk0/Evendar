package com.gabilheri.choresapp.data;

import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.data.models.User;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/21/15.
 */
public interface ChoresApi {

    // User section
    @POST(NetworkClient.USER_API + "/user")
    Observable<User> insertUser(@Body User user);

    @PUT(NetworkClient.USER_API + "/update")
    Observable<User> updateUser(@Body User user);

    @DELETE(NetworkClient.USER_API + "/user/{username}")
    Observable<User> deleteUser(@Path("username") String username);

    @DELETE(NetworkClient.USER_API + "/user/{id}")
    Observable<User> deleteUser(@Path("username") Long id);

    @GET("/{username}")
    Observable<User> getuser(@Path("username") String username);

    // Event section

    @POST(NetworkClient.EVENT_API + "/event")
    Observable<Event> insertEvent(@Body Event event);

    @PUT(NetworkClient.EVENT_API + "/event")
    Observable<Event> updateEvent(@Body Event event);
}
