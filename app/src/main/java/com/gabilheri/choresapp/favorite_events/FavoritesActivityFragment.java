package com.gabilheri.choresapp.favorite_events;

import android.app.ActivityOptions;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.choresapp.BaseCursorListFragment;
import com.gabilheri.choresapp.BaseListFragment;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.FeedAdapter;
import com.gabilheri.choresapp.adapters.FriendAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.detail_event.DetailActivity;
import com.gabilheri.choresapp.user_profile.UserProfileActivity;
import com.gabilheri.choresapp.utils.Const;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class FavoritesActivityFragment extends BaseCursorListFragment implements ItemCallback {

    private static final int FRIENDS_LOADER = 0;

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new FriendAdapter(null, this);
        initBaseList(mAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // TODO implement query
        return null;
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
    protected int[] getLoaders() {
        return new int[] {FRIENDS_LOADER};
    }
}
