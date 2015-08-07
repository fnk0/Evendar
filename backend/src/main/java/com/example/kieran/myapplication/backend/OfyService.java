package com.example.kieran.myapplication.backend;

/**
 * Created by kieran on 8/5/15.
 */

import com.googlecode.objectify.Objectify;
        import com.googlecode.objectify.ObjectifyFactory;
        import com.googlecode.objectify.ObjectifyService;

public class OfyService {

    static {
        ObjectifyService.register(User.class);
        ObjectifyService.register(Event.class);
        ObjectifyService.register(Comment.class);
        ObjectifyService.register(Favorite.class);
        ObjectifyService.register(Friendship.class);
        ObjectifyService.register(RSVP.class);





    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
