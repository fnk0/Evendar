package com.gabilheri.choresapp.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gabilheri.choresapp.BaseListFragment;
import com.gabilheri.choresapp.MockUtils;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.FeedAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.detail_event.DetailActivity;
import com.gabilheri.choresapp.models.Feed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class FeedFragment extends BaseListFragment {

    FeedAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Feed> feedList = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            feedList.add(MockUtils.getRandomFeed());
        }

        adapter = new FeedAdapter(feedList, new ItemCallback() {
            @Override
            public void onItemClick(View view) {
                switch (view.getId()) {
                    case R.id.details:
                        startActivity(new Intent(getActivity(), DetailActivity.class));
                        break;
                }
            }
        });

        initCardsList(adapter);
    }

}
