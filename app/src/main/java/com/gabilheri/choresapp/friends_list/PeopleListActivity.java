package com.gabilheri.choresapp.friends_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.gabilheri.choresapp.BaseDrawerActivity;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.add_user.AddFriendActivity;
import com.gabilheri.choresapp.new_event.NewEventActivity;
import com.gabilheri.choresapp.utils.Const;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class PeopleListActivity extends BaseDrawerActivity {

    @Bind(R.id.fab)
    FloatingActionButton fab;

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
        mNavigationView.getMenu().getItem(2).setChecked(true);
    }

    @OnClick(R.id.fab)
    public void goToNewEventActivity( View view ) {
        startActivity(new Intent(PeopleListActivity.this, AddFriendActivity.class));
    }
}
