package com.gabilheri.choresapp.friends_list;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.choresapp.MockUtils;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.FriendAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.models.User;
import com.gabilheri.choresapp.ui.DividerItemDecorator;

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
public class FriendListFragment extends Fragment {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    FriendAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 300; i++) {
            userList.add(MockUtils.getRandomUser());
        }

        adapter = new FriendAdapter(userList, new ItemCallback() {
            @Override
            public void onItemClick(View v) {
                Snackbar.make(v, v.getTag().toString(), Snackbar.LENGTH_LONG).show();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1));
        recyclerView.addItemDecoration(new DividerItemDecorator(recyclerView.getContext(), null));
        recyclerView.setAdapter(adapter);
    }
}
