package com.gabilheri.choresapp.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.gabilheri.choresapp.BaseDrawerActivity;
import com.gabilheri.choresapp.MockUtils;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.new_event.NewEventActivity;
import com.gabilheri.choresapp.sync.ChoresSyncAdapter;
import com.gabilheri.choresapp.utils.Const;
import com.gabilheri.choresapp.utils.TimeUtils;

import org.joda.time.LocalDateTime;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedActivity extends BaseDrawerActivity {

    private static final int MOCK_USERS = 0;
    private static final int MOCK_EVENTS = 1;
    private static final int MOCK_COMMENTS = 2;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Nullable
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.container)
    FrameLayout container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if(getIntent().getExtras() != null) {
            if(getIntent().getExtras().getBoolean(Const.IS_FAVORITES)) {
                container.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);
                setTitle(getString(R.string.favorites));
                Bundle b = new Bundle();
                b.putBoolean(Const.IS_FAVORITES, true);
                FeedFragment favFragment = new FeedFragment();
                favFragment.setArguments(b);
                addFragmentToContainer(favFragment, "Favorites");
                return;
            }
        }

        String today = TimeUtils.formatShortDate(LocalDateTime.now().toDate().getTime());
        setTitle(today);
        ChoresSyncAdapter.initializeSyncAdapter(this);
        FeedFragment wantsTo = new FeedFragment();
        wantsTo.setArguments(getArgumentsBundle(false));
        addFragmentToContainer(wantsTo, "feed");


//        MyFragmentAdapter adapter = new MyFragmentAdapter(getFragmentManager());
//        FeedFragment goingTo = new FeedFragment();
//        goingTo.setArguments(getArgumentsBundle(false));
//        adapter.addFragment(goingTo, getString(R.string.going_to));
//        adapter.addFragment(wantsTo, getString(R.string.wants_to));
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);

        mNavigationView.getMenu().getItem(0).setChecked(true);
    }

    private Bundle getArgumentsBundle(boolean isWant) {
        LocalDateTime now = LocalDateTime.now();
        // We get a month worth of data
        String start = String.valueOf(now.minusDays(15).toDate().getTime());
        String end = String.valueOf(now.plusDays(15).toDate().getTime());
        Bundle args = new Bundle();
        args.putString(Const.START_DATE, start);
        args.putString(Const.END_DATE, end);
        args.putBoolean(Const.BOOLEAN_IS_WANT, isWant);
        return args;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MOCK_USERS, 0, "Mock users");
        menu.add(0, MOCK_COMMENTS, 0, "Mock Comments");
        menu.add(0, MOCK_EVENTS, 0, "Mock Events");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case MOCK_USERS:
                MockUtils.insertMockUsers(this, 20);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.fab)
    public void goToNewEventActivity( View view ) {
        startActivity( new Intent(FeedActivity.this, NewEventActivity.class));
    }
}
