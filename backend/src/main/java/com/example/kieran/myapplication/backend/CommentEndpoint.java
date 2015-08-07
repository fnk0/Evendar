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
        name = "commentApi",
        version = "v1",
        resource = "comment",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.kieran.example.com",
                ownerName = "backend.myapplication.kieran.example.com",
                packagePath = "")
)
public class CommentEndpoint {
    public CommentEndpoint(){}


    //inserts a new comment
    @ApiMethod(name = "insertComment")
    public Comment insertComment(Comment comment) throws ConflictException {
        if (comment.getId() != null) {
            if (QueryUtils.findRecord(Comment.class, comment.getId()) != null) {
                // We don't want a exception here
                // Instead we return null and in the client side
                // If the return of registering a user is null what we do
                // is fire the updateUser to keep our user updated in the server
                return null;
            }
        }

        ofy().save().entity(comment).now();
        return comment;
    }

    //removes a comment
    @ApiMethod(name = "removeComment")
    public void removeComment(@Named("id") Long id) throws NotFoundException {
        deleteObject(Comment.class, id);
    }


    @ApiMethod(name = "getComment")
    public Comment getComment(@Named("id") Long id) throws NotFoundException {
        return getObject(Comment.class, id);
    }


    //updates a comment
    @ApiMethod(name = "updateComment", path = "update", httpMethod = ApiMethod.HttpMethod.PUT)
    public Comment updateComment(Comment comment) throws NotFoundException {
        if (findRecord(Comment.class, comment.getId()) == null) {
            throw new NotFoundException("Comment Record does not exist!");
        }
        ofy().save().entity(comment).now();
        return comment;
    }



}
