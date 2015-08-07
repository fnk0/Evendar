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

import static com.example.kieran.myapplication.backend.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

/**
 * Created by kieran on 8/5/15.
 */

/*@TODO what do i use for owner domain/name?
fix the import for ofy
*/


@Api(
        name = "eventApi",
        version = "v1",
        resource = "event",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.kieran.example.com",
                ownerName = "backend.myapplication.kieran.example.com",
                packagePath = "")
)
public class EventEndpoint {
    public EventEndpoint(){}


    //inserts a new event
    @ApiMethod(name = "insertEvent")
    public Event insertEvent(Event event) throws ConflictException {
        if (event.getId() != null) {
            if (QueryUtils.findRecord(Event.class, event.getId()) != null) {
                // We don't want a exception here
                // Instead we return null and in the client side
                // If the return of registering a user is null what we do
                // is fire the updateUser to keep our user updated in the server
                return null;
            }
        }

        ofy().save().entity(event).now();
        return event;
    }




}
