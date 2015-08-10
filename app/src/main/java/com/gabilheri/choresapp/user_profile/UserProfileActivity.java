package com.gabilheri.choresapp.user_profile;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

import com.bumptech.glide.Glide;
import com.gabilheri.choresapp.BaseActivity;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.utils.Const;
import com.gabilheri.choresapp.utils.QueryUtils;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/22/15.
 */
public class UserProfileActivity extends BaseActivity {

    @Bind(R.id.userProfile)
    CircleImageView userProfile;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackNav();
        String username = getIntent().getStringExtra(Const.USERNAME);
        User user = QueryUtils.getUserFromDB(username);
        String title = user.getFullName();

        if(title != null) {
            collapsingToolbar.setTitle(title);
        }

        String url = getIntent().getStringExtra("userPicture");
        if(url != null) {
            Glide.with(this)
                    .load(url)
                    .centerCrop()
                    .crossFade()
                    .into(userProfile);
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.user_profile;
    }
}
