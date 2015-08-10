package com.gabilheri.choresapp.friends_list;

import android.os.Bundle;

import com.gabilheri.choresapp.BaseDrawerActivity;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.utils.Const;

import butterknife.ButterKnife;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class PeopleListActivity extends BaseDrawerActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        Long eventId = -1L;

        if (getIntent().getExtras() != null) {
            eventId = getIntent().getExtras().getLong(Const.EVENT_ID);
        }

        Bundle bundle = new Bundle();

        if(eventId != -1L) {
            setTitle(getString(R.string.whos_going));
            bundle.putLong(Const.EVENT_ID, eventId);
        } else {
            setTitle(getString(R.string.friends));
        }

        PeopleListFragment frag = new PeopleListFragment();
        frag.setArguments(bundle);

        addFragmentToContainer(frag, "Friends Fragment");
        navigationView.getMenu().getItem(2).setChecked(true);
    }
}
