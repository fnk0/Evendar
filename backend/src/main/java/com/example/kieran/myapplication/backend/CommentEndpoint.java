package com.example.kieran.myapplication.backend;



import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;

import javax.inject.Named;

import static com.example.kieran.myapplication.backend.OfyService.ofy;
import static com.example.kieran.myapplication.backend.QueryUtils.deleteObject;
import static com.example.kieran.myapplication.backend.QueryUtils.findRecord;
import static com.example.kieran.myapplication.backend.QueryUtils.getObject;

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
