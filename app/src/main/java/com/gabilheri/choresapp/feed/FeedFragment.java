package com.gabilheri.choresapp.feed;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.gabilheri.choresapp.BaseListFragment;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.FeedAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.detail_event.DetailActivity;
import com.gabilheri.choresapp.utils.Const;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class FeedFragment extends BaseListFragment
        implements LoaderManager.LoaderCallbacks<Cursor>, ItemCallback{

    private static final int FEED_LOADER = 0;

    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    FeedAdapter adapter;

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

            isWant = getArguments().getBoolean(Const.BOOLEAN_IS_WANT);

            adapter = new FeedAdapter(null, this);
            initCardsList(adapter);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(FEED_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(View view) {
        switch (view.getId()) {
            case R.id.details:
                startActivity(new Intent(getActivity(), DetailActivity.class));
                break;
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
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(arguments);
    }
}
