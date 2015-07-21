package com.gabilheri.choresapp.feed;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.choresapp.MockUtils;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.FeedAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.detail_event.DetailActivity;
import com.gabilheri.choresapp.models.Feed;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class FeedFragment extends Fragment {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    FeedAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        ButterKnife.bind(this, v);
        return v;
    }


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
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1));
        recyclerView.setAdapter(adapter);
    }
}
