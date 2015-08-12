package com.gabilheri.choresapp.add_user;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.choresapp.BaseFragment;
import com.gabilheri.choresapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddFriendActivityFragment extends BaseFragment {

    public AddFriendActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_friend, container, false);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_add_friend;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater inflaterHere = this.getActivity().getMenuInflater();
        inflaterHere.inflate(R.menu.menu_add_friend_email, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.getActivity().finish();
        return false;
    }
}
