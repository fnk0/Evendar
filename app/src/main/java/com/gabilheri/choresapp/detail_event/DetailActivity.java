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
import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.utils.Const;
import com.gabilheri.choresapp.utils.QueryUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
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

    @Bind(R.id.mapview)
    MapView mapView;

    GoogleMap map;

    Event event;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewCompat.setElevation(collapsingToolbarLayout, 20);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (toolbar != null) {
                toolbar.setElevation(0f);
            }
            collapsingToolbarLayout.setElevation(0f);
        }

        Bundle extras = getIntent().getExtras();
        boolean isComment = false;
        long eventId = -1;
        if(extras != null) {
            isComment = extras.getBoolean(Const.IS_COMMENT);
            eventId = extras.getLong(Const.EVENT_ID);
        }

        if(eventId != -1) {
            event = QueryUtils.getEventFromDB(eventId);
        }

        mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(this);
        mapView.getMapAsync(this);

        enableBackNav();
        if(viewPager != null) {
            MyFragmentAdapter adapter = new MyFragmentAdapter(getFragmentManager());

            DetailFragment dFragment = new DetailFragment();
            CommentsFragment cFragment = new CommentsFragment();

            Bundle bundle = new Bundle();
            bundle.putLong(Const.EVENT_ID, eventId);

            dFragment.setArguments(bundle);
            cFragment.setArguments(bundle);

            adapter.addFragment(dFragment, getString(R.string.details));
            adapter.addFragment(cFragment, getString(R.string.comments));
            viewPager.setAdapter(adapter);
            if (tabLayout != null) {
                tabLayout.setupWithViewPager(viewPager);
            }

            if(isComment) {
                viewPager.setCurrentItem(1);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        // Add a marker in Sydney, Australia, and move the camera.

        String location = event.getLocation();

        LatLng latLng = null;

        if(location.contains("geo://")) {
            String[] coords = location.substring(6).split(",");
            latLng = new LatLng(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
        } else {
            //TODO get the latLng for the specific address
        }

        if(latLng != null) {
            map.addMarker(new MarkerOptions().position(latLng).title(event.getTitle()));
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.event_detail;
    }
}
