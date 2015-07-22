package com.gabilheri.choresapp.friends_list;

import android.os.Bundle;

import com.gabilheri.choresapp.BaseDrawerActivity;

import butterknife.ButterKnife;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class FriendListActivity extends BaseDrawerActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("Friends");
        addFragmentToContainer(new FriendListFragment(), "Friends Fragment");
        navigationView.getMenu().getItem(2).setChecked(true);
    }
}
