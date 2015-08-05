package com.gabilheri.choresapp.data;

import com.gabilheri.choresapp.data.models.User;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/21/15.
 */
public interface ChoresApi {

    //TODO Still needs to put the right URL's for this API Calls...

    @POST("/")
    Observable<User> insertUser(@Body User user);

    @PUT("/")
    Observable<User> updateUser(@Body User user);

    @GET("/{username}")
    Observable<User> getuser(@Path("username") String username);
}
