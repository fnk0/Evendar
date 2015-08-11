package com.gabilheri.choresapp.favorite_events;

import android.os.Bundle;

import com.gabilheri.choresapp.BaseDrawerActivity;

import butterknife.ButterKnife;

public class FavoritesActivity extends BaseDrawerActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("Favorite Posts");
        addFragmentToContainer(new FavoritesActivityFragment(), "Favorites Fragment");
        mNavigationView.getMenu().getItem(1).setChecked(true);
    }
}
