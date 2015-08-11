package com.gabilheri.choresapp.data;

import android.os.Bundle;

import com.gabilheri.choresapp.BaseActivity;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.new_event.NewEventFragment;

import butterknife.ButterKnife;

/**
 * Created by kieran on 8/11/15.
 */
public class SeedActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("Create User");
        addFragmentToContainer(new NewEventFragment(), "New User Fragment");
        enableBackNav();
    }
}
