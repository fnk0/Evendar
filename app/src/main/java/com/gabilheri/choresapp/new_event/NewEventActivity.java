package com.gabilheri.choresapp.new_event;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.gabilheri.choresapp.BaseActivity;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.favorite_events.FavoritesActivityFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class NewEventActivity extends BaseActivity {
/*
    @Bind(R.id.enter_event_name)
    EditText enterEvent;*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle(getString(R.string.create_event));
        addFragmentToContainer(new NewEventFragment(), "New Event Fragment");
        enableBackNav();
    }

}
