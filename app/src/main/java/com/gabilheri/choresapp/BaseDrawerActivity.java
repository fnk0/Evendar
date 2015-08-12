package com.gabilheri.choresapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.feed.FeedActivity;
import com.gabilheri.choresapp.friends_list.PeopleListActivity;
import com.gabilheri.choresapp.sign_in.SignInActivity;
import com.gabilheri.choresapp.utils.Const;
import com.gabilheri.choresapp.utils.IntentUtils;
import com.gabilheri.choresapp.utils.PrefManager;
import com.gabilheri.choresapp.utils.QueryUtils;
import com.gabilheri.choresapp.utils.SocialUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    protected NavigationView mNavigationView;

    @Bind(R.id.userPicture)
    CircleImageView mUserPicture;

    @Bind(R.id.userName)
    TextView userName;

    @Bind(R.id.userLocation)
    TextView userLocation;

    protected User mActiveUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!PrefManager.with(this).getBoolean(Const.SIGNED_IN, false)) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        }

        ButterKnife.bind(this);
        enableHamburgerMenu();
        setupDrawerContent();
    }

    private void setupDrawerContent() {
        mActiveUser = QueryUtils.getAuthenticatedUserFromDB();
        ButterKnife.bind(mNavigationView);
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Intent i = null;
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                i = getFeedActivity();
                                break;
                            case R.id.nav_favorites:
                                i = getFavoritesActivity();
                                break;
                            case R.id.nav_friends:
                                i = getFriendsActivity();
                                break;
                            case R.id.sign_out:
                                i = signOut();
                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        if (i != null) {
                            menuItem.setChecked(true);
                            startActivity(i);
                            finish();
                        }
                        return true;
                    }
                });

        if(mActiveUser != null) {
            userName.setText(SocialUtils.getFirstLastName(mActiveUser.getFullName()));
            userLocation.setVisibility(View.GONE);
            mUserPicture.setTag(R.id.userPicture, mActiveUser.getPicUrl());
            Glide.with(this)
                    .load(mActiveUser.getPicUrl())
                    .centerCrop()
                    .crossFade()
                    .into(mUserPicture);
        }
    }

    @OnClick(R.id.userPicture)
    public void onUserPictureClick(View v) {
        IntentUtils.openUserProfile(this, mActiveUser.getUsername(), mUserPicture);
    }

    private Intent getFeedActivity() {
        return new Intent(this, FeedActivity.class);
    }

    private Intent getFavoritesActivity() {
        Intent i = new Intent(this, FeedActivity.class);
        i.putExtra(Const.IS_FAVORITES, true);
        return i;
    }

    private Intent getFriendsActivity() {
        if (this instanceof PeopleListActivity) {
            return null;
        }
        return new Intent(this, PeopleListActivity.class);
    }

    private Intent signOut() {


        PrefManager.with(this).remove(Const.USERNAME);
        PrefManager.with(this).save(Const.SIGNED_IN, false);
        return new Intent(this, SignInActivity.class);
    }

    protected void enableHamburgerMenu() {
        if (getSupportActionBar() != null) {
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

    public User getActiveUser() {
        return mActiveUser;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_base_drawer;
    }
}
