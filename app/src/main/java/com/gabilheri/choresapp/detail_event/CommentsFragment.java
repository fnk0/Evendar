package com.gabilheri.choresapp.detail_event;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.gabilheri.choresapp.BaseCursorListFragment;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.CommentsAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/21/15.
 */
public class CommentsFragment extends BaseCursorListFragment implements ItemCallback {

    private static final int COMMENTS_LOADER = 0;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new CommentsAdapter(null, this);
        initCardsList(mAdapter);
    }

    @Override
    public void onItemClick(View view) {
        //TODO Handle what happens when a view is clicked on a comment
        switch (view.getId()) {
            case R.id.userName:
            case R.id.userPicture:
                // Goes to the user profile
                break;
            case R.id.favorite:
                // "Likes" this comment
                break;
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //TODO implement query
        return null;
    }

    @Override
    protected int[] getLoaders() {
        return new int[] {COMMENTS_LOADER};
    }
}

