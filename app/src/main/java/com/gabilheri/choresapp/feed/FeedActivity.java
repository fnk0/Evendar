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

public class FeedActivity extends BaseDrawerActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getFragmentManager());
        adapter.addFragment(new FeedFragment(), "Going to");
        adapter.addFragment(new FeedFragment(), "Wants to");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }
}
