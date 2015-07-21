package com.gabilheri.choresapp.detail_event;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;

import com.gabilheri.choresapp.BaseActivity;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.MyFragmentAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class DetailActivity extends BaseActivity implements OnMapReadyCallback {

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Nullable
    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Nullable
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ViewCompat.setElevation(collapsingToolbarLayout, 20);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (toolbar != null) {
                toolbar.setElevation(0f);
            }
            collapsingToolbarLayout.setElevation(0f);
        }

        enableBackNav();
        if(viewPager != null) {
            MyFragmentAdapter adapter = new MyFragmentAdapter(getFragmentManager());
            adapter.addFragment(new DetailFragment(), "Details");
            adapter.addFragment(new DetailFragment(), "Comments");
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(30.2702060, -97.6783);
        map.addMarker(new MarkerOptions().position(sydney).title("Some Location"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public int getLayoutResource() {
        return R.layout.event_detail;
    }
}
