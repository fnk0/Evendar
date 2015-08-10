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
import static com.example.kieran.myapplication.backend.QueryUtils.findByUsername;
import static com.example.kieran.myapplication.backend.QueryUtils.getObject;
import static com.example.kieran.myapplication.backend.QueryUtils.list;

/**
 * Created by kieran on 8/5/15.
 */

/*@TODO what do i use for owner domain/name?
Response: Package name
fix the import for ofy
*/

@Api(
        name = "userApi",
        version = "v1",
        resource = "user",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.kieran.example.com",
                ownerName = "backend.myapplication.kieran.example.com",
                packagePath = "")
)
public class UserEndpoint {

    //returns a collection of users
    @ApiMethod(name = "listUser", path = "allUsers")
    public CollectionResponse<User> listUsers(@Nullable @Named("cursor") String cursorString,
                                             @Nullable @Named("count") Integer count) {
        return list(User.class, cursorString, count);
    }

    //inserts a new user
    @ApiMethod(name = "insertUser")
    public User insertUser(User user) throws ConflictException {
        if (user.getUsername() != null) {
            if (QueryUtils.findByUsername(User.class, user.getUsername()) != null) {
                // We don't want a exception here
                // Instead we return null and in the client side
                // If the return of registering a user is null what we do
                // is fire the updateUser to keep our user updated in the server
                return null;
            }
        }

        ofy().save().entity(user).now();
        return user;
    }

    @ApiMethod(name = "getUser")
    public User getUser(@Named("id") Long id) throws NotFoundException {
        return getObject(User.class, id);
    }

    @ApiMethod(name = "getUserByUsername", path = "user")
    public User getUserByUsername(@Named("username") String username) {
        return findByUsername(User.class, username);
    }

    //updates a user
    @ApiMethod(name = "updateUser", path = "update", httpMethod = ApiMethod.HttpMethod.PUT)
    public User updateUser(User user) throws NotFoundException {
        User oldUser = findByUsername(User.class, user.getUsername());
        if (oldUser == null) {
            throw new NotFoundException("User Record does not exist!");
        }
        user.setId(oldUser.getId());
        ofy().save().entity(user).now();
        return user;
    }

    @ApiMethod(name = "removeUser")
    public void removeUser(@Named("id") Long id) throws NotFoundException {
       deleteObject(User.class, id);
    }


}
