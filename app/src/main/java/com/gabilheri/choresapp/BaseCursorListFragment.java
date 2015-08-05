package com.gabilheri.choresapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.gabilheri.choresapp.adapters.CursorRecyclerAdapter;
import com.gabilheri.choresapp.utils.Const;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/5/15.
 */
public abstract class BaseCursorListFragment extends BaseListFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    protected CursorRecyclerAdapter mAdapter;
    protected int mPosition = ListView.INVALID_POSITION;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        int[] loaders = getLoaders();
        if(loaders != null) {
            for(Integer loader : loaders) {
                getActivity().getLoaderManager().initLoader(loader, null, this);
            }
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (mAdapter != null) {
            mAdapter.swapCursor(data);
            if (mPosition != ListView.INVALID_POSITION) {
                recyclerView.smoothScrollToPosition(mPosition);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if(mAdapter != null) {
            mAdapter.swapCursor(null);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(Const.SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    protected abstract int[] getLoaders();
}
