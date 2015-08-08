package com.gabilheri.choresapp.data;

import android.content.Context;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/21/15.
 */
public class NetworkClient {

    // Okhttp has some smart cache mecanisms to avoid doing the same network request twice
    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    // This variable is meant to be changed as pleased.
    private final static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.FULL;

    // This is the base URL that our API client will use
    public static final String API_VERSION = "v1";
    public static final String USER_API = "/userApi/" + NetworkClient.API_VERSION;
    public static final String EVENT_API = "/eventApi/" + NetworkClient.API_VERSION;
    private static final String API_ENDPOINT = "https://choresapp-1013.appspot.com/_ah/api";

    /**
     * The OkhttpClient is a fairly expensive object so we don't want to create instances
     * of this object all the time. That's why we keep this object as a Singleton keeping a reference
     * to the application context when initializing the cache directory
     *
     * @param context
     *       Context necessary to instantiate the cache directory
     * @return
     *       The OkhttpClient instance
     */
    static OkHttpClient getOkHttpClient(Context context) {
        OkHttpClient client = new OkHttpClient();
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(context.getApplicationContext().getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        client.setCache(cache);

        return client;
    }

    static Client getClient(OkHttpClient client) {
        return new OkClient(client);
    }

    /**
     * Our main interface to interact with our web server
     *
     * @param context
     *      The context from which this is created
     * @return
     *      The ChoresApi instance
     */
    public static ChoresApi getApi(Context context) {
        return new RestAdapter.Builder()
                .setEndpoint(API_ENDPOINT)
                .setLogLevel(LOG_LEVEL)
                .setClient(getClient(getOkHttpClient(context)))
                .build().create(ChoresApi.class);
    }
}
