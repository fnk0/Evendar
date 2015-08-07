package com.gabilheri.choresapp.sign_in;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gabilheri.choresapp.BaseActivity;
import com.gabilheri.choresapp.ChoresApp;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.feed.FeedActivity;
import com.gabilheri.choresapp.utils.Const;
import com.gabilheri.choresapp.utils.PrefManager;
import com.gabilheri.choresapp.utils.TimeUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/27/15.
 */
public class SignInActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String LOG_TAG = "SignInFragment";

    private static final int PROFILE_PIC_SIZE = 800;

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;

    @Bind(R.id.twitter_login_button)
    TwitterLoginButton twitterLoginButton;

    @Bind(R.id.facebook_login_button)
    LoginButton facebookLoginButton;

    CallbackManager mCallbackManager;

    GoogleApiClient mGoogleApiClient;

    private User mUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Sign In");

        mCallbackManager = CallbackManager.Factory.create();

        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                Twitter.getApiClient()
                        .getAccountService().verifyCredentials(true, true, new Callback<com.twitter.sdk.android.core.models.User>() {
                    @Override
                    public void success(Result<com.twitter.sdk.android.core.models.User> result) {
                        mUser = new User();
                        mUser.setUsername(result.data.screenName);
                        mUser.setTwitterUsername(String.valueOf(result.data.screenName));
                        mUser.setFullName(result.data.name);
                        mUser.setPicUrl(result.data.profileImageUrlHttps);
                        // If the user registers with Twitter we can't get a email unfortunately
                        // We have to ask the people from Twitter to white-list our app...
                        // and that takes a while!
                        registerUser();
                    }

                    @Override
                    public void failure(TwitterException e) {
                        //TODO show error to the user
                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                //TODO show error to the user
            }
        });

        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("public_profile");
        permissions.add("email");
        permissions.add("user_friends");
        facebookLoginButton.setReadPermissions(permissions);

        facebookLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Application code
                                try {
                                    Log.d(LOG_TAG, object.toString());
                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String profilePicture = "https://graph.facebook.com/v2.4/" + id + "/picture?height=800&type=square&width=800";
                                    mUser = new User();
                                    mUser.setEmail(email);
                                    mUser.setFullName(name);
                                    mUser.setFacebookUsername(id);
                                    mUser.setPicUrl(profilePicture);
                                    registerUser();
                                } catch (JSONException ex) {
                                    Log.e(LOG_TAG, "Error parsing JSON result", ex);
                                    //TODO show error to the user
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // Since they canceled we don't need to do anything here really
            }

            @Override
            public void onError(FacebookException e) {
                //TODO show error to the user
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != Activity.RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            mGoogleApiClient.connect();
        }
    }

    @OnClick(R.id.google_sign_in)
    public void signInWithGPlus() {
        if (mGoogleApiClient == null) {
            buildApiClient();
        }
        mShouldResolve = true;
        mGoogleApiClient.connect();
    }

    @OnClick(R.id.facebook_sign_in)
    public void signInWithFacebook() {
        facebookLoginButton.performClick();
    }

    @OnClick(R.id.twitter_sign_in)
    public void signInWithTwitter() {
        twitterLoginButton.performClick();
    }

    private void buildApiClient() {
        // Build GoogleApiClient with access to basic profile
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .addScope(Plus.SCOPE_PLUS_PROFILE)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mShouldResolve = false;
            Person person = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);

        if(person != null) {
            mUser = new User();
            String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

            if (email != null) {
                mUser.setEmail(email);
            }

            mUser.setGoogleUsername(person.getId());
            mUser.setFullName(person.getDisplayName());

            int g = person.getGender();

            // In case we want gender at some point
            String gender = "Other";
            if (g == 0) {
                gender = "Male";
            } else if (g == 1) {
                gender = "Female";
            }

            String picUrl = person.getImage().getUrl();

            mUser.setPicUrl(picUrl.substring(0, picUrl.length() - 2) + PROFILE_PIC_SIZE);
            registerUser();
        } else {
            //TODO show error to the user
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public void registerUser() {
        if(mUser.getEmail() != null) {
            mUser.setUsername(mUser.getEmail());
        }
        mUser.setDateRegistered(TimeUtils.getToday());

        ChoresApp.instance().getApi().insertUser(mUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new UserSubscriber());
    }

    public void updateUser() {
        ChoresApp.instance().getApi().updateUser(mUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new UserSubscriber());
    }

    void finishActivity(User user) {
        // saveUserToDB(user);
        PrefManager.with(getApplicationContext()).save(Const.USERNAME, user.getUsername());
        PrefManager.with(getApplicationContext()).save(Const.SIGNED_IN, true);
        goToFeedActivity();
        finish();
    }

    private void goToFeedActivity() {
        startActivity(new Intent(this, FeedActivity.class));
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Could not connect to Google Play Services.  The user needs to select an account,
        // grant permissions or resolve an error in order to sign in. Refer to the javadoc for
        // ConnectionResult to see possible error codes.
        Log.d(LOG_TAG, "onConnectionFailed:" + connectionResult);

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e(LOG_TAG, "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                // TODO show the user a error dialog
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null && !mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_sign_in;
    }

    private class UserSubscriber extends Subscriber<User>  {

        @Override
        public void onCompleted() {
            unsubscribe();
        }

        @Override
        public void onError(Throwable e) {
            //TODO show error tho the user
            Log.e(LOG_TAG, "Error registering user", e);
        }

        @Override
        public void onNext(User user) {
            // The servers returns null if the user already exists
            if(user != null) {
                finishActivity(user);
            } else {
                updateUser();
            }
        }
    }
}
