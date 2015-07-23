package com.gabilheri.choresapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.gabilheri.choresapp.feed.FeedActivity;
import com.gabilheri.choresapp.friends_list.FriendListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

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

    @Bind(R.id.userPicture)
    CircleImageView userPicture;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        enableHamburgerMenu();
        setupDrawerContent();
    }

    private void setupDrawerContent() {
        ButterKnife.bind(navigationView);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Intent i = null;
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                i = getFeedActivity();
                                break;
                            case R.id.nav_friends:
                                i = getFriendsActivity();
                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        if(i != null) {
                            menuItem.setChecked(true);
                            startActivity(i);
                            finish();
                        }
                        return true;
                    }
                });

        Glide.with(this)
                .load("https://lh6.googleusercontent.com/-PLz-1wFMskM/U1VdxVwcXgI/AAAAAAAAMvw/JUU7tw4CWms/w1674-h1676-no/1397661337286.jpg")
                .centerCrop()
                .crossFade()
                .into(userPicture);
    }

    private Intent getFeedActivity() {
        if(this instanceof FeedActivity) {
           return null;
        }
        return new Intent(this, FeedActivity.class);
    }

    private Intent getFriendsActivity() {
        if(this instanceof FriendListActivity) {
            return null;
        }
        return new Intent(this, FriendListActivity.class);
    }

    protected void enableHamburgerMenu() {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
