package com.example.kieran.myapplication.backend;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.googlecode.objectify.cmd.Query;


import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;

import static com.example.kieran.myapplication.backend.OfyService.ofy;
import static com.example.kieran.myapplication.backend.QueryUtils.deleteObject;
import static com.example.kieran.myapplication.backend.QueryUtils.findByUserId;
import static com.example.kieran.myapplication.backend.QueryUtils.findRecord;
import static com.example.kieran.myapplication.backend.QueryUtils.getEventsFromUser;
import static com.example.kieran.myapplication.backend.QueryUtils.getFriends;
import static com.example.kieran.myapplication.backend.QueryUtils.getObject;
import static com.example.kieran.myapplication.backend.QueryUtils.list;
import static com.example.kieran.myapplication.backend.QueryUtils.listByQuery;

/**
 * Created by kieran on 8/5/15.
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

    //returns the relevant event feed for a user (all the events created by either him/her or his/her friends
    @ApiMethod(name = "listEventsForUsers", path = "allUserEvents")
    public CollectionResponse<Event> listAllEventsForUser(@Named("id") Long userId,
                                                          @Named("date") String updatedAt,
                                                          @Nullable @Named("cursor") String cursorString,
                                                          @Nullable @Named("count") Integer count) {


        ArrayList<Event> feedForUser = new ArrayList<Event>();
        Collection<User> friends = getFriends(userId).getItems();
        for (User u : friends){
            Collection<Event> friendsEvents = getEventsFromUser(u.getId()).getItems();
            for (Event e : friendsEvents){
                feedForUser.add(e);
            }
        }

        return CollectionResponse.<Event> builder().setItems(feedForUser).build();
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

    @ApiMethod(name = "getEventsCreatedBy", path = "getEventsCreatedBy")
    public CollectionResponse<Event> getEventsCreatedBy(@Named("id") Long id) throws NotFoundException {
        if (findByUserId(User.class, id) == null){
            throw new NotFoundException("User record not found!");
        }

        Query<Event> query = ofy().load().type(Event.class).filter(ChoresContract.EventEntry.COLUMN_USER_ID, id);

        return listByQuery(query, null, null);
    }
}
