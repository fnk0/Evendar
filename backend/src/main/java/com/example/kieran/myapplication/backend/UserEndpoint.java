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

//import static com.example.kieran.myapplication.backend.OfyService.ofy;

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


@Api(name = "userEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "", ownerName = "", packagePath = ""))
public class UserEndpoint {
    public UserEndpoint(){}


    //returns a collection of users
    @ApiMethod(name = "listUser")
    public CollectionResponse<User> listUser(@Nullable @Named("cursor") String cursorString,
        @Nullable @Named("count") Integer count) {

        Query<User> query = ofy().load().type(User.class);
        if(count != null){
            query.limit(count);
        }

        if(cursorString != null && cursorString != ""){
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }

        List<User> records = new ArrayList<User>();
        QueryResultIterator<User> iterator = query.iterator();
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
        return CollectionResponse.<User>builder().setItems(records).setNextPageToken(cursorString).build();

    }

    //inserts a new user
    @ApiMethod(name = "insertUser")
    public User insertUser(User user) throws ConflictException{
        if(user.getId() != null){
            if(findRecord(user.getId()) != null){
                throw new ConflictException("Object already exists!");
            }
        }

        ofy.save().entity(user).now();
        return user;
    }

    private User findRecord(Long id){
        return ofy().load().type(User.class).id(id).now();
    }


    //updates a user
    @ApiMethod(name = "updateUser")
    public User updateUser(User user) throws NotFoundException {
        if(findRecord(user.getId()) == null){
            throw new NotFoundException("User Record does not exist!");
        }
        ofy().save().entity(user).now();
        return user;
    }

    @ApiMethod(name = "removeUser")
    public void removeUser(@Named("id") Long id) throws NotFoundException {
        User record findRecord(id);
        if(record == null){
            throw new NotFoundException("User record does not exist!");
        }
        ofy().delete().entity(record).now();
    }























}
