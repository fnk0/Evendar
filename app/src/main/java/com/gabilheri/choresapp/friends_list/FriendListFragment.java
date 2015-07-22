package com.gabilheri.choresapp.friends_list;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gabilheri.choresapp.BaseListFragment;
import com.gabilheri.choresapp.MockUtils;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.FriendAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.models.User;
import com.gabilheri.choresapp.user_profile.UserProfileActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class FriendListFragment extends BaseListFragment {
    FriendAdapter adapter;

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            userList.add(MockUtils.getRandomUser());
        }

        adapter = new FriendAdapter(userList, new ItemCallback() {
            @Override
            public void onItemClick(View v) {
                CircleImageView imgView = (CircleImageView) v.findViewById(R.id.userPicture);
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                TextView userName = (TextView) v.findViewById(R.id.userName);
                intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, userName.getText().toString());
                intent.putExtra("userPicture", v.getTag().toString());

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), imgView, "profileImage");
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
//                Snackbar.make(v, v.getTag().toString(), Snackbar.LENGTH_LONG).show();
            }
        });

        initBaseList(adapter);
    }
}
