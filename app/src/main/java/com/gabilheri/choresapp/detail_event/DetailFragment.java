package com.gabilheri.choresapp.detail_event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gabilheri.choresapp.BaseFragment;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.friends_list.PeopleListActivity;
import com.gabilheri.choresapp.utils.Const;
import com.gabilheri.choresapp.utils.QueryUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class DetailFragment extends BaseFragment {

    @Bind(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    @Bind({R.id.friend1, R.id.friend2, R.id.friend3})
    List<CircleImageView> friends;

    @Bind(R.id.overFlowLayout)
    LinearLayout overflowLayout;

    @Bind(R.id.overflowFriends)
    TextView overflowFriends;

    @Bind(R.id.whereAddress)
    TextView whereAddress;

    @Bind(R.id.whoName)
    TextView whoName;


    Long eventId;

    Event event;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null) {
            eventId = getArguments().getLong(Const.EVENT_ID);
            SparseArrayCompat<User> users = QueryUtils.getUsersForEvent(eventId);
            int numUsers = users.size();
            int counter = 0;
            while(counter < numUsers && counter < 3) {
                CircleImageView imageView = friends.get(counter);
                imageView.setVisibility(View.VISIBLE);
                loadWithGlide(imageView, users.get(counter).getPicUrl());
                counter++;
            }

            if(numUsers > 3) {
                overflowLayout.setVisibility(View.VISIBLE);
                overflowFriends.setText(String.format("%d more", (numUsers - 3)));
            }

            event = QueryUtils.getEventFromDB(eventId);


        }
    }

    @OnClick(R.id.overflowFriends)
    public void navitateToWhosGoing() {
        Intent i = new Intent(getActivity(), PeopleListActivity.class);
        i.putExtra(Const.EVENT_ID, eventId);
        startActivity(i);
    }

    private void loadWithGlide(ImageView view, String url) {
        Glide.with(getActivity())
                .load(url)
                .fitCenter()
                .crossFade()
                .into(view);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.details_fragment;
    }
}
