package com.gabilheri.choresapp.add_user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.gabilheri.choresapp.BaseActivity;
import com.gabilheri.choresapp.BaseFragment;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.new_event.NewEventFragment;

import butterknife.ButterKnife;

public class AddFriendActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle((getString(R.string.add_friend)));
        addFragmentToContainer(new AddFriendActivityFragment(), "Add add friend activity fragment");
        enableBackNav();
    }
}
