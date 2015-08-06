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
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
