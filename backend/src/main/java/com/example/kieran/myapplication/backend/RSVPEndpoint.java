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
        name = "rsvpApi",
        version = "v1",
        resource = "rsvp",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.kieran.example.com",
                ownerName = "backend.myapplication.kieran.example.com",
                packagePath = "")
)
public class RSVPEndpoint {

    public RSVPEndpoint(){}

    //inserts a new RSVP
    @ApiMethod(name = "insertRSVP")
    public RSVP insertRSVP(RSVP rsvp) throws ConflictException {
        if (rsvp.getId() != null) {
            if (QueryUtils.findRecord(RSVP.class, rsvp.getId()) != null) {

                return null;
            }
        }

        ofy().save().entity(rsvp).now();
        return rsvp;
    }


    //removes an RSVP
    @ApiMethod(name = "removeRSVP")
    public void removeRSVP(@Named("id") Long id) throws NotFoundException {
        deleteObject(RSVP.class, id);
    }

    //returns an RSVP
    @ApiMethod(name = "getRSVP")
    public RSVP getRSVP(@Named("id") Long id) throws NotFoundException {
        return getObject(RSVP.class, id);
    }

    //updates an RSVP
    @ApiMethod(name = "updateRSVP", path = "update", httpMethod = ApiMethod.HttpMethod.PUT)
    public RSVP updateRSVP(RSVP rsvp) throws NotFoundException {
        if (findRecord(RSVP.class, rsvp.getId()) == null) {
            throw new NotFoundException("RSVP Record does not exist!");
        }
        ofy().save().entity(rsvp).now();
        return rsvp;
    }

    //gets all the RSVPs of a user
    @ApiMethod(name = "getRSVPsFor", path = "getRSVPsFor")
    public CollectionResponse<RSVP> getRSVPsFor(@Named("id") Long userId) throws NotFoundException{
        if(findByUserId(User.class, userId) == null){
            throw new NotFoundException("User record not found!");
        }

        Query<RSVP> query = ofy().load().type(RSVP.class).filter(ChoresContract.RSVPEntry.COLUMN_USER_ID, userId);
        return listByQuery(query, null, null);
    }



}
