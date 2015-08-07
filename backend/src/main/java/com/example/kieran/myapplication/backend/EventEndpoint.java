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


@Api(name = "eventEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "", ownerName = "", packagePath = ""))
public class EventEndpoint {
    public EventEndpoint(){}


    //returns a collection of events
    @ApiMethod(name = "listEvent")
    public CollectionResponse<Event> listEvent(@Nullable @Named("cursor") String cursorString,
                                             @Nullable @Named("count") Integer count) {

        Query<Event> query = ofy().load().type(Event.class);
        if(count != null){
            query.limit(count);
        }

        if(cursorString != null && cursorString != ""){
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }

        List<Event> records = new ArrayList<Event>();
        QueryResultIterator<Event> iterator = query.iterator();
        int num = 0;
        while(iterator.hasNext()){
            records.add(iterator.next());
            if(count != null){
                num++;
            }
            if(num == count){
                break;
            }
        }

        if(cursorString != null && cursorString != ""){
            Cursor cursor = iterator.getCursor();
            if(cursor != null){
                cursorString = cursor.toWebSafeString();
            }
        }
        return CollectionResponse.<Event>builder().setItems(records).setNextPageToken(cursorString).build();

    }

    //inserts a new event
    @ApiMethod(name = "insertEvent")
    public Event insertEvent(Event event) throws ConflictException{
        if(event.getId() != null){
            if(findRecord(event.getId()) != null){
                throw new ConflictException("Object already exists!");
            }
        }

        ofy().save().entity(event).now();
        return event;
    }

    private Event findRecord(Long id){
        return ofy().load().type(Event.class).id(id).now();
    }


    //updates a event
    @ApiMethod(name = "updateEvent")
    public Event updateEvent(Event event) throws NotFoundException {
        if(findRecord(event.getId()) == null){
            throw new NotFoundException("Event Record does not exist!");
        }
        ofy().save().entity(event).now();
        return event;
    }

    @ApiMethod(name = "removeEvent")
    public void removeEvent(@Named("id") Long id) throws NotFoundException {
        Event record = findRecord(id);
        if(record == null){
            throw new NotFoundException("Event record does not exist!");
        }
        ofy().delete().entity(record).now();
    }























}
