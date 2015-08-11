package com.gabilheri.choresapp.new_event;

import android.os.Bundle;

import com.gabilheri.choresapp.BaseActivity;
import com.gabilheri.choresapp.R;

import butterknife.ButterKnife;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class NewEventActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle(getString(R.string.create_event));
        addFragmentToContainer(new NewEventFragment(), "New Event Fragment");
        enableBackNav();
    }

}
