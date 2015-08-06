package com.gabilheri.choresapp.detail_event;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.gabilheri.choresapp.BaseListFragment;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.CommentsAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.utils.Const;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/21/15.
 */
public class CommentsFragment extends BaseListFragment
        implements ItemCallback, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int COMMENTS_LOADER = 0;
    CommentsAdapter mAdapter;
    private int mPosition = ListView.INVALID_POSITION;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new CommentsAdapter(null, this);
        initCardsList(mAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(COMMENTS_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
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
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
        if (mPosition != ListView.INVALID_POSITION) {
            recyclerView.smoothScrollToPosition(mPosition);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(Const.SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }
}

