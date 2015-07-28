package com.gabilheri.choresapp.feed;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import com.gabilheri.choresapp.BaseDrawerActivity;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.MyFragmentAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class FeedActivity extends BaseDrawerActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "JjDmkcjAqgjba10SvKRLFhEQB";
    private static final String TWITTER_SECRET = "HLgGNfZv2Z9YSMfceDpNFcbUAKjMSBTe7Oyg5aIbzGf251Tgnp";


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        ButterKnife.bind(this);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getFragmentManager());
        adapter.addFragment(new FeedFragment(), "Going to");
        adapter.addFragment(new FeedFragment(), "Wants to");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }
}
