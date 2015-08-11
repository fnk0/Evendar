package com.gabilheri.choresapp.favorite_events;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gabilheri.choresapp.BaseDrawerActivity;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.MyFragmentAdapter;
import com.gabilheri.choresapp.feed.FeedFragment;
import com.gabilheri.choresapp.utils.Const;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import org.joda.time.LocalDateTime;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class FavoritesActivity extends BaseDrawerActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("Favorite Posts");
        addFragmentToContainer(new FavoritesActivityFragment(), "Favorites Fragment");
        navigationView.getMenu().getItem(1).setChecked(true);
    }
}
