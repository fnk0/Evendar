package com.gabilheri.choresapp.friends_list;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.gabilheri.choresapp.BaseListFragment;
import com.gabilheri.choresapp.MockUtils;
import com.gabilheri.choresapp.adapters.FriendAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class FriendListFragment extends BaseListFragment {
    FriendAdapter adapter;

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

        initBaseList(adapter);
    }
}
