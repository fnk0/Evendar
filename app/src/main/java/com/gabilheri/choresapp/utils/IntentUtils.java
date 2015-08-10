package com.gabilheri.choresapp.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;

import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.user_profile.UserProfileActivity;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/9/15.
 */
public final class IntentUtils {

    private IntentUtils() { }

    public static void openUserProfile(Activity activity, String username, ImageView imageView) {
        Intent intent = new Intent(activity, UserProfileActivity.class);
        String picUrl = (String) imageView.getTag(R.id.userPicture);
        intent.putExtra(Const.USERNAME, username);
        intent.putExtra(Const.USER_PICTURE, picUrl);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, imageView, "profileImage");
            activity.startActivity(intent, options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

}
