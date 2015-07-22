package com.gabilheri.choresapp.detail_event;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gabilheri.choresapp.BaseFragment;
import com.gabilheri.choresapp.MockUtils;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
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

    @Bind(R.id.friend1)
    CircleImageView friend1;

    @Bind(R.id.friend2)
    CircleImageView friend2;

    @Bind(R.id.friend3)
    CircleImageView friend3;

    @Bind(R.id.overflowFriends)
    TextView overflowFriends;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<User> users = new ArrayList<>();
        int numUsers = new Random().nextInt(12) + 4;

        for(int i = 0; i < numUsers; i++) {
            users.add(MockUtils.getRandomUser());
        }

        if(numUsers > 0) {
            loadWithGlide(friend1, users.get(0).getUserPicture());
        }

        if(numUsers > 1) {
            loadWithGlide(friend2, users.get(1).getUserPicture());
        }

        if(numUsers > 2) {
            loadWithGlide(friend3, users.get(2).getUserPicture());
        }

        if(numUsers > 3) {
            overflowFriends.setText(String.format("%d more...", numUsers - 3));
        }
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
