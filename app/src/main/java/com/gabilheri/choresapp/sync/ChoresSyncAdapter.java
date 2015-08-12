package com.gabilheri.choresapp.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.gabilheri.choresapp.ChoresApp;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.data.models.FeedResponse;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.utils.Const;
import com.gabilheri.choresapp.utils.PrefManager;
import com.gabilheri.choresapp.utils.QueryUtils;

import org.joda.time.LocalDateTime;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/4/15.
 */
public class ChoresSyncAdapter extends AbstractThreadedSyncAdapter{

    private static final String LOG_TAG = "ChoresSyncAdapter";

    static final int SYNC_INTERVAL = 60 * 15;
    static final int SYNC_FLEXTIME = SYNC_INTERVAL / 3;

    public ChoresSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    public ChoresSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        User authenticatedUser = QueryUtils.getAuthenticatedUserFromDB();
        if(authenticatedUser != null) {
            String eventUpdatedAt = PrefManager.with(getContext())
                    .getString(Const.EVENT_UPDATED_AT, String.valueOf(LocalDateTime.now().minusDays(100).toDate().getTime()));
            ChoresApp.instance().getApi().getAllUserEvents(authenticatedUser.getId(), eventUpdatedAt)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(eventsSubscriber);
        }
    }

    Subscriber<FeedResponse> eventsSubscriber = new Subscriber<FeedResponse>() {
        @Override
        public void onCompleted() {
            unsubscribe();
        }

        @Override
        public void onError(Throwable e) {
            Log.e(LOG_TAG, "Error syncing events", e);
        }

        @Override
        public void onNext(FeedResponse response) {
            List<Event> events = response.getItems();
            LocalDateTime now = LocalDateTime.now();
            PrefManager.with(getContext()).save(Const.UPDATED_AT, String.valueOf(now.toDate().getTime()));
            int size = events.size();
            ContentValues[] cvArray = new ContentValues[size];

            for(int i = 0; i < size; i++) {
                cvArray[i] = Event.toContentValues(events.get(i));
            }
            getContext().getContentResolver().bulkInsert(ChoresContract.EventEntry.buildEventUri(), cvArray);
        }
    };

    /**
     * Helper method to schedule the sync adapter periodic execution
     */
    public static void configurePeriodicSync(Context context, int syncInterval, int flexTime) {
        Account account = getSyncAccount(context);
        String authority = context.getString(R.string.content_authority);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(syncInterval, flexTime).
                    setSyncAdapter(account, authority).
                    setExtras(new Bundle()).build();
            ContentResolver.requestSync(request);
        } else {
            ContentResolver.addPeriodicSync(account, authority, new Bundle(), syncInterval);
        }
    }

    /**
     * Helper method to have the sync adapter sync immediately
     *
     * @param context
     *         The context used to access the account service
     */
    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context), context.getString(R.string.content_authority), bundle);
    }

    /**
     * Helper method to get the fake account to be used with SyncAdapter, or make a new one
     * if the fake account doesn't exist yet.  If we make a new account, we call the
     * onAccountCreated method so we can initialize things.
     *
     * @param context
     *         The context used to access the account service
     * @return a fake account.
     */
    public static Account getSyncAccount(Context context) {
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // Create the account type and default account
        Account newAccount = new Account(context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        // If the password doesn't exist, the account doesn't exist
        if (null == accountManager.getPassword(newAccount)) {

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call ContentResolver.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */

            onAccountCreated(newAccount, context);
        }
        return newAccount;
    }

    static void onAccountCreated(Account newAccount, Context context) {
        /*
         * Since we've created an account
         */
        ChoresSyncAdapter.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);

        /*
         * Without calling setSyncAutomatically, our periodic sync will not be enabled.
         */
        ContentResolver.setSyncAutomatically(newAccount, context.getString(R.string.content_authority), true);

        /*
         * Finally, let's do a sync to get things started
         */
        syncImmediately(context);
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
    }
}
