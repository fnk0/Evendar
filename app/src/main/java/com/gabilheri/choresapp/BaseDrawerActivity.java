package com.gabilheri.choresapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.gabilheri.choresapp.feed.FeedActivity;
import com.gabilheri.choresapp.friends_list.FriendListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public abstract class BaseDrawerActivity extends BaseActivity {

    @Bind(R.id.drawer_layout)
    protected DrawerLayout mDrawerLayout;

    @Bind(R.id.nav_view)
    protected NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        enableHamburgerMenu();
        setupDrawerContent();
    }

    private void setupDrawerContent() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                startFeedActivity();
                                break;
                            case R.id.nav_friends:
                                startFriendsActivity();
                                break;
                        }

                        return true;
                    }
                });
    }

    private void startFeedActivity() {
        if(this instanceof FeedActivity) {
           return;
        }
        startActivity(new Intent(this, FeedActivity.class));
    }

    private void startFriendsActivity() {
        if(this instanceof FriendListActivity) {
            return;
        }
        startActivity(new Intent(this, FriendListActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_base_drawer;
    }
}
