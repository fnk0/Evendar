package com.gabilheri.choresapp.data;

import com.gabilheri.choresapp.data.models.Comment;
import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.data.models.FeedResponse;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.data.models.Friendship;

import java.util.List;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
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

    @POST(NetworkClient.FRIENDSHIP_API + "/friendship")
    Observable<Friendship> insertFriendship(@Body Friendship f);

    @PUT(NetworkClient.USER_API + "/update")
    Observable<User> updateUser(@Body User user);

    @DELETE(NetworkClient.USER_API + "/user/{username}")
    Observable<User> deleteUser(
            @Path("username") String username
    );

    @DELETE(NetworkClient.USER_API + "/user/{id}")
    Observable<Response> deleteUser(
            @Path("id") Long id
    );

    @GET("/{username}")
    Observable<Response> getuser(
            @Path("username") String username
    );

    // Events section

    // Returns all public events
    @GET(NetworkClient.EVENT_API + "/allEvents/{updatedAt}")
    Observable<List<Event>> getAllEvents(
            @Path("updatedAt") String updatedAt
    );

    @POST(NetworkClient.EVENT_API + "/allUserEvents")
    Observable<FeedResponse> getAllUserEvents(
            @Query("date") Long userId,
            @Query("id") String updatedAt
    );

    @POST(NetworkClient.EVENT_API + "/event")
    Observable<Event> insertEvent(@Body Event event);

    @PUT(NetworkClient.EVENT_API + "/update")
    Observable<Event> updateEvent(@Body Event event);

    @DELETE(NetworkClient.EVENT_API + "/event/{id}")
    Observable<Response> deleteEvent(
            @Path("id") Long eventId
    );

    // Comments Section

    /**
     * Gets all the comments for a specific event since last updated time
     * @param eventId
     *          The eventId
     * @param updatedAt
     *          Timestamp of last time this data has been updated
     * @return
     *          List with all the comments for the specific event
     */
    @POST(NetworkClient.COMMENT_API + "/allComments/{id}/{updatedAt}")
    Observable<List<Comment>> getAllCommentsForEvent(
            @Path("id") Long eventId,
            @Path("updatedAt") String updatedAt
    );

    @POST(NetworkClient.COMMENT_API + "/comment")
    Observable<Comment> insertComment(@Body Comment comment);

    @PUT(NetworkClient.COMMENT_API + "/update")
    Observable<Comment> updateComment(@Body Comment comment);

    @DELETE(NetworkClient.COMMENT_API + "/comment/{id}")
    Observable<Response> deleteComment(
            @Path("id") Long commentId
    );
}
