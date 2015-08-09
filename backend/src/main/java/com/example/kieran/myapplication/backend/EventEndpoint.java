package com.example.kieran.myapplication.backend;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;

import javax.inject.Named;

import static com.example.kieran.myapplication.backend.OfyService.ofy;
import static com.example.kieran.myapplication.backend.QueryUtils.deleteObject;
import static com.example.kieran.myapplication.backend.QueryUtils.findRecord;
import static com.example.kieran.myapplication.backend.QueryUtils.getObject;
import static com.example.kieran.myapplication.backend.QueryUtils.list;

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

    @ApiMethod(name = "listEvents", path = "allEvents")
    public CollectionResponse<Event> listEvents(@Named("updatedAt") String updatedAt,
                                                @Nullable @Named("cursor") String cursorString,
                                                @Nullable @Named("count") Integer count) {
        return list(Event.class, cursorString, count);
    }

    //TODO write code to return only events for a associated user.
    //Ex: events from his friends
    @ApiMethod(name = "listEventsForUsers", path = "allUserEvents")
    public CollectionResponse<Event> listAllEventsForUser(@Named("id") Long userId,
                                                          @Named("date") String updatedAt,
                                                          @Nullable @Named("cursor") String cursorString,
                                                          @Nullable @Named("count") Integer count) {
        return list(Event.class, cursorString, count);
    }

    //inserts a new event
    @ApiMethod(name = "insertEvent")
    public Event insertEvent(Event event) throws ConflictException {
        if (event.getId() != null) {
            if (QueryUtils.findRecord(Event.class, event.getId()) != null) {
                return null;
            }
        }

        ofy().save().entity(event).now();
        return event;
    }

    //removes an event
    @ApiMethod(name = "removeEvent")
    public void removeEvent(@Named("id") Long id) throws NotFoundException {
        deleteObject(Event.class, id);
    }



    //updates an event
    @ApiMethod(name = "updateEvent", path = "update", httpMethod = ApiMethod.HttpMethod.PUT)
    public Event updateEvent(Event event) throws NotFoundException {
        if (findRecord(Event.class, event.getId()) == null) {
            throw new NotFoundException("Event Record does not exist!");
        }
        ofy().save().entity(event).now();
        return event;
    }


    @ApiMethod(name = "getEvent")
    public Event getEvent(@Named("id") Long id) throws NotFoundException {
        return getObject(Event.class, id);
    }
}
