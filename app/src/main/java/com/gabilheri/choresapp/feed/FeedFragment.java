package com.gabilheri.choresapp.feed;

import android.app.ActivityOptions;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.gabilheri.choresapp.BaseCursorListFragment;
import com.gabilheri.choresapp.ChoresApp;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.FeedAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.detail_event.DetailActivity;
import com.gabilheri.choresapp.utils.Const;
import com.gabilheri.choresapp.utils.IntentUtils;
import com.gabilheri.choresapp.utils.QueryUtils;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class FeedFragment extends BaseCursorListFragment implements ItemCallback{

    private static final int FEED_LOADER = 0;
    private static final int FAVORITES_LOADER = 1;

    String sortOrder = ChoresContract.EventEntry.COLUMN_DATE + " DESC";

    private boolean isWant = false;
    private String startDate = null;
    private String endDate = null;

    Bundle arguments = null;
    boolean isFavorites = false;

    private User mCurrentUser;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() instanceof FeedActivity) {
            mCurrentUser = ((FeedActivity) getActivity()).getActiveUser();
        }

        arguments = getArguments();

        if(arguments == null) {
            arguments = savedInstanceState;
        }

        if(arguments != null) {

            isFavorites = arguments.getBoolean(Const.IS_FAVORITES);

            if(!isFavorites) {
                startDate = arguments.getString(Const.START_DATE);
                endDate = arguments.getString(Const.END_DATE);
                isWant = arguments.getBoolean(Const.BOOLEAN_IS_WANT);
                mAdapter = new FeedAdapter(null, this);
            } else {
                mAdapter = new FeedAdapter(null, this, true);
            }


            initCardsList(mAdapter);
        }
    }

    @Override
    public void onItemClick(View view) {
        Intent intent = null;
        ActivityOptions options = null;
        Event event = (Event) view.getTag(R.id.eventId);
        switch (view.getId()) {
            case R.id.favorites:
                event.setNumFavorites(event.getNumFavorites() + 1);
                break;

            case R.id.userPicture:
                CircleImageView imgView = (CircleImageView) view.findViewById(R.id.userPicture);
                String username = view.getTag(R.id.userName).toString();
                IntentUtils.openUserProfile(getActivity(), username, imgView);
                return; // We return because the openUserProfile already starts the intent

            case R.id.shares:
                event.setNumShares(event.getNumShares() + 1);
                break;

            case R.id.comments:
                intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(Const.IS_COMMENT, true);
                break;

            case R.id.details:
                intent = new Intent(getActivity(), DetailActivity.class);
                break;
        }

        if(intent != null) {
            intent.putExtra(Const.EVENT_ID, event.getId());
            startActivity(intent);
        } else {
            updateEvent(event);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri queryUri = null;
        switch (id) {
            case FEED_LOADER:
                queryUri = ChoresContract.EventEntry.buildEventUri(startDate, endDate, isWant);
                break;
            case FAVORITES_LOADER:
                if(mCurrentUser != null) {
                    queryUri = ChoresContract.FavoriteEntry.buildFavoritesForUser(mCurrentUser.getId());
                }
                break;
        }

        return new CursorLoader(getActivity(), queryUri, null, null, null, sortOrder);
    }

    @Override
    protected int[] getLoaders() {

        if(!isFavorites) {
            return new int[]{FEED_LOADER};
        } else {
            return new int[]{FAVORITES_LOADER};
        }
    }

    private void updateEvent(Event event) {
        ChoresApp.instance().getApi().updateEvent(event)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EventSubscriber());
    }

    private static class EventSubscriber extends Subscriber<Event> {

        @Override
        public void onCompleted() {
            unsubscribe();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Event event) {
            // When we save the event the cursor loader automatically updates the UI
            QueryUtils.saveEventToDB(event);
        }
    }
}
