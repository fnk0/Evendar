package com.gabilheri.choresapp.friends_list;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gabilheri.choresapp.BaseCursorListFragment;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.FriendAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.utils.Const;
import com.gabilheri.choresapp.utils.IntentUtils;
import com.gabilheri.choresapp.utils.QueryUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class PeopleListFragment extends BaseCursorListFragment implements ItemCallback {

    private static final int FRIENDS_LOADER = 0;

    String sortOrder = ChoresContract.UserEntry.COLUMN_FULL_NAME + " ASC";

    Long eventId = -1L;

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null) {
            eventId = getArguments().getLong(Const.EVENT_ID);
        }

        mAdapter = new FriendAdapter(null, this);
        initBaseList(mAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        User user = QueryUtils.getAuthenticatedUserFromDB();
        Uri queryUri;

        if(eventId == -1L) {
            queryUri = ChoresContract.UserEntry.buildUserUri(user.getUsername());
        } else {
            queryUri = ChoresContract.UserEntry.buildUsersForEvent(eventId);
        }

        return new CursorLoader(getActivity(), queryUri, null, null, null, sortOrder);
    }

    @Override
    public void onItemClick(View v) {
        CircleImageView imgView = (CircleImageView) v.findViewById(R.id.userPicture);
        TextView userName = (TextView) v.findViewById(R.id.userName);
        IntentUtils.openUserProfile(getActivity(), userName.getText().toString(), imgView);
    }

    @Override
    protected int[] getLoaders() {
        return new int[] {FRIENDS_LOADER};
    }
}
