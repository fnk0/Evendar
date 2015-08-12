package com.gabilheri.choresapp.add_user;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gabilheri.choresapp.BaseFragment;
import com.gabilheri.choresapp.ChoresApp;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.data.models.Friendship;
import com.gabilheri.choresapp.utils.DialogUtils;

import butterknife.Bind;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddFriendActivityFragment extends BaseFragment {
    @Bind(R.id.enter_email_name)
    EditText emailText;

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
        if( !emailText.getText().toString().isEmpty()) {
            Friendship newFriendship = new Friendship();

            ChoresApp.instance().getApi().insertFriendship(newFriendship);
        }
        else {
            DialogUtils.showErrorDialog(getActivity(), "No Email Entered", "Please enter an email address");
        }

        this.getActivity().finish();
        return false;

    }
}
