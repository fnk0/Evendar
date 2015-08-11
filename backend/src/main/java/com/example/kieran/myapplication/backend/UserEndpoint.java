package com.example.kieran.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.sun.tools.corba.se.idl.constExpr.Not;
import com.googlecode.objectify.cmd.Query;


import java.util.List;

import javax.inject.Named;

import static com.example.kieran.myapplication.backend.OfyService.ofy;
import static com.example.kieran.myapplication.backend.QueryUtils.deleteObject;
import static com.example.kieran.myapplication.backend.QueryUtils.findByUserId;
import static com.example.kieran.myapplication.backend.QueryUtils.findByUsername;
import static com.example.kieran.myapplication.backend.QueryUtils.getObject;
import static com.example.kieran.myapplication.backend.QueryUtils.list;
import static com.example.kieran.myapplication.backend.QueryUtils.listByQuery;

/**
 * Created by kieran on 8/5/15.
 */


@Api(
        name = "userApi",
        version = "v1",
        resource = "user",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.kieran.example.com",
                ownerName = "backend.myapplication.kieran.example.com",
                packagePath = "")
)
public class UserEndpoint {

    //returns a collection of users
    @ApiMethod(name = "listUser", path = "allUsers")
    public CollectionResponse<User> listUsers(@Nullable @Named("cursor") String cursorString,
                                             @Nullable @Named("count") Integer count) {
        return list(User.class, cursorString, count);
    }

    //inserts a new user
    @ApiMethod(name = "insertUser")
    public User insertUser(User user) throws ConflictException {
        if (user.getUsername() != null) {
            if (QueryUtils.findByUsername(User.class, user.getUsername()) != null) {
                // We don't want a exception here
                // Instead we return null and in the client side
                // If the return of registering a user is null what we do
                // is fire the updateUser to keep our user updated in the server
                return null;
            }
        }

        ofy().save().entity(user).now();
        return user;
    }

    @ApiMethod(name = "getUser")
    public User getUser(@Named("id") Long id) throws NotFoundException {
        return getObject(User.class, id);
    }

    @ApiMethod(name = "getUserByUsername", path = "user")
    public User getUserByUsername(@Named("username") String username) {
        return findByUsername(User.class, username);
    }

    //updates a user
    @ApiMethod(name = "updateUser", path = "update", httpMethod = ApiMethod.HttpMethod.PUT)
    public User updateUser(User user) throws NotFoundException {
        User oldUser = findByUsername(User.class, user.getUsername());
        if (oldUser == null) {
            throw new NotFoundException("User Record does not exist!");
        }
        user.setId(oldUser.getId());
        ofy().save().entity(user).now();
        return user;
    }

    @ApiMethod(name = "removeUser")
    public void removeUser(@Named("id") Long id) throws NotFoundException {
       deleteObject(User.class, id);
    }

    @ApiMethod(name = "getEventFeed", path = "getEventsForUser")
    public List<Event> getEventFeed(User u) throws NotFoundException{
        User user = findByUsername(User.class, u.getUsername());
        if (user == null){
            throw new NotFoundException("User Record does not exist!");
        }

        return QueryUtils.getEventFeed(u.getId());
    }

    @ApiMethod(name = "getEventsAttending", path = "getEventsAttending")
    public CollectionResponse<Event> getEventsAttending(User u) throws NotFoundException{
        if(findByUserId(User.class, u.getId()) == null){
            throw new NotFoundException("User record does not exist");
        }

        //call on RSVP api and then get the events from that

        Query<Event> query = ofy().load().type(Event.class).filter(ChoresContract.RSVPEntry.COLUMN_USER_ID, u.getId());


        return listByQuery(query, null, null);

    }


}
