package com.gabilheri.choresapp.user_profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

import com.bumptech.glide.Glide;
import com.gabilheri.choresapp.BaseActivity;
import com.gabilheri.choresapp.R;

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

        String title = getIntent().getStringExtra(Intent.EXTRA_SHORTCUT_NAME);
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
