package com.gabilheri.choresapp.feed;

import android.app.ActivityOptions;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.gabilheri.choresapp.BaseCursorListFragment;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.FeedAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.detail_event.DetailActivity;
import com.gabilheri.choresapp.user_profile.UserProfileActivity;
import com.gabilheri.choresapp.utils.Const;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class FeedFragment extends BaseCursorListFragment implements ItemCallback{

    private static final int FEED_LOADER = 0;

    String sortOrder = ChoresContract.EventEntry.COLUMN_DATE + " DESC";

    private boolean isWant = false;
    private String startDate = null;
    private String endDate = null;

    Bundle arguments = null;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        arguments = getArguments();

        if(arguments == null) {
            arguments = savedInstanceState;
        }

        if(arguments != null) {
            startDate = arguments.getString(Const.START_DATE);
            endDate = arguments.getString(Const.END_DATE);
            isWant = arguments.getBoolean(Const.BOOLEAN_IS_WANT);

            mAdapter = new FeedAdapter(null, this);
            initCardsList(mAdapter);
        }
    }

    @Override
    public void onItemClick(View view) {
        Intent intent = null;
        ActivityOptions options = null;
        switch (view.getId()) {
            case R.id.favorites:
                break;

            case R.id.userPicture:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    CircleImageView imgView = (CircleImageView) view.findViewById(R.id.userPicture);
                    options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), imgView, "profileImage");
                }

                intent = new Intent(getActivity(), UserProfileActivity.class);
                // Username should be enough to query for the right user on our content provider
                intent.putExtra(Const.USERNAME, view.getTag(R.id.userName).toString());
                intent.putExtra(Const.USER_PICTURE, view.getTag(R.id.userProfile).toString());
                break;

            case R.id.shares:
                break;

            case R.id.favorite:
                break;

            case R.id.details:
                intent = new Intent(getActivity(), DetailActivity.class);
                break;
        }

        if(intent != null) {
            if(options != null ) {
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // We don't need to worry about the ID here... because there's only 1 loader
        // in this fragment
        Uri queryUri = ChoresContract.EventEntry.buildEventUri(startDate, endDate, isWant);
        return new CursorLoader(getActivity(), queryUri, null, null, null, sortOrder);
    }

    @Override
    protected int[] getLoaders() {
        return new int[]{FEED_LOADER};
    }
}
