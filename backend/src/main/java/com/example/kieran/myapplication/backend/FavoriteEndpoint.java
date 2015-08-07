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
        name = "favoriteApi",
        version = "v1",
        resource = "favorite",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.kieran.example.com",
                ownerName = "backend.myapplication.kieran.example.com",
                packagePath = "")
)
public class FavoriteEndpoint {
    public FavoriteEndpoint(){}

    //inserts a new favorite
    @ApiMethod(name = "insertFavorite")
    public Favorite insertFavorite(Favorite favorite) throws ConflictException {
        if (favorite.getId() != null) {
            if (QueryUtils.findRecord(Favorite.class, favorite.getId()) != null) {
                // We don't want a exception here
                // Instead we return null and in the client side
                // If the return of registering a user is null what we do
                // is fire the updateUser to keep our user updated in the server
                return null;
            }
        }

        ofy().save().entity(favorite).now();
        return favorite;
    }

    //removes a favorite
    @ApiMethod(name = "removeFavorite")
    public void removeFavorite(@Named("id") Long id) throws NotFoundException {
        deleteObject(Favorite.class, id);
    }


    @ApiMethod(name = "getFavorite")
    public Favorite getFavorite(@Named("id") Long id) throws NotFoundException {
        return getObject(Favorite.class, id);
    }


    //updates a favorite
    @ApiMethod(name = "updateFavorite", path = "update", httpMethod = ApiMethod.HttpMethod.PUT)
    public Favorite updateFavorite(Favorite favorite) throws NotFoundException {
        if (findRecord(Favorite.class, favorite.getId()) == null) {
            throw new NotFoundException("Favorite Record does not exist!");
        }
        ofy().save().entity(favorite).now();
        return favorite;
    }



}
