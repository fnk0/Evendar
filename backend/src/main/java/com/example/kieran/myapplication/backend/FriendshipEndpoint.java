package com.example.kieran.myapplication.backend;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;
import static com.example.kieran.myapplication.backend.QueryUtils.deleteObject;
import static com.example.kieran.myapplication.backend.QueryUtils.*;



import static com.example.kieran.myapplication.backend.OfyService.ofy;
import static com.example.kieran.myapplication.backend.QueryUtils.findRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

/**
 * Created by kieran on 8/7/15.
 */



@Api(
        name = "friendshipApi",
        version = "v1",
        resource = "friendship",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.kieran.example.com",
                ownerName = "backend.myapplication.kieran.example.com",
                packagePath = "")
)
public class FriendshipEndpoint {
    public FriendshipEndpoint(){}

    //inserts a new friendship
    @ApiMethod(name = "insertFriendship")
    public Friendship insertFriendship(Friendship friendship) throws ConflictException {
        if (friendship.getId() != null) {
            if (QueryUtils.findRecord(Friendship.class, friendship.getId()) != null) {

                return null;
            }
        }

        ofy().save().entity(friendship).now();
        return friendship;
    }

    //removes a friendship
    @ApiMethod(name = "removeFriendship")
    public void removeFriendship(@Named("id") Long id) throws NotFoundException {
        deleteObject(Friendship.class, id);
    }


    @ApiMethod(name = "getFriendship")
    public Friendship getFriendship(@Named("id") Long id) throws NotFoundException {
        return getObject(Friendship.class, id);
    }


    //updates a favorite
    @ApiMethod(name = "updateFriendship", path = "update", httpMethod = ApiMethod.HttpMethod.PUT)
    public Friendship updateFriendship(Friendship friendship) throws NotFoundException {
        if (findRecord(Friendship.class, friendship.getId()) == null) {
            throw new NotFoundException("Friendship Record does not exist!");
        }
        ofy().save().entity(friendship).now();
        return friendship;
    }

    @ApiMethod(name = "getAllFriends", path = "getAllFriends")
    public CollectionResponse<Friendship> getAllFriends(@Named("id") Long userId) throws NotFoundException {
        if (findRecord(User.class, userId) == null){
            throw new NotFoundException("User record does not exist");
        }

        Query<Friendship> query = ofy().load().type(Friendship.class).filter(ChoresContract.FriendshipEntry.COLUMN_USER_ID1, userId).filter(" OR " + ChoresContract.FriendshipEntry.COLUMN_USER_ID2 + " = ", userId);

        return listByQuery(query, null, null);
    }

}
