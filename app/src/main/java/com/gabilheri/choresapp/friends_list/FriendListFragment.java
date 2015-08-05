package com.gabilheri.choresapp.friends_list;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gabilheri.choresapp.BaseCursorListFragment;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.FriendAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
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
public class FriendListFragment extends BaseCursorListFragment implements ItemCallback {

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
    public void onItemClick(View v) {
        CircleImageView imgView = (CircleImageView) v.findViewById(R.id.userPicture);
        Intent intent = new Intent(getActivity(), UserProfileActivity.class);
        TextView userName = (TextView) v.findViewById(R.id.userName);
        intent.putExtra(Const.USERNAME, userName.getText().toString());
        intent.putExtra(Const.USER_PICTURE, v.getTag(R.id.userProfile).toString());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), imgView, "profileImage");
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    protected int[] getLoaders() {
        return new int[] {FRIENDS_LOADER};
    }
}
