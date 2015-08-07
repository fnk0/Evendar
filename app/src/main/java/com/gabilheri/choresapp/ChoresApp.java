package com.gabilheri.choresapp;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.gabilheri.choresapp.data.ChoresApi;
import com.gabilheri.choresapp.data.NetworkClient;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import net.danlew.android.joda.JodaTimeAndroid;

import io.fabric.sdk.android.Fabric;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/27/15.
 */
public class ChoresApp extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "whvjumpAlIDRZg7HbK0EzbfYp";
    private static final String TWITTER_SECRET = "5LozfFt9ejPT5QDlr7mnNb7og5uJJO01qHc6s6YnCehXciulfc";

    private static ChoresApp sInstance;

    private ChoresApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        FacebookSdk.sdkInitialize(this);
        JodaTimeAndroid.init(this);
        sInstance = this;
    }

    public static ChoresApp instance() {
        return sInstance;
    }

    public ChoresApi getApi() {
        if(api == null) {
            api = NetworkClient.getApi(this);
        }
        return api;
    }

}
