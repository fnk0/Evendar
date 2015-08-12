package com.gabilheri.choresapp.add_user;

import android.content.ContentValues;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gabilheri.choresapp.BaseFragment;
import com.gabilheri.choresapp.ChoresApp;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.models.Friendship;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.utils.DialogUtils;
import com.gabilheri.choresapp.utils.QueryUtils;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddFriendActivityFragment extends BaseFragment {
    @Bind(R.id.enter_email_name)
    EditText emailText;
    static final String TAG = "HELLO";

    private User authenticatedUser;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authenticatedUser = QueryUtils.getAuthenticatedUserFromDB();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_add_friend;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater inflaterHere = this.getActivity().getMenuInflater();
        inflaterHere.inflate(R.menu.menu_add_friend_email, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Handler mHandler = new Handler();
        ChoresApp.instance().getApi().getuser(emailText.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new UserSubscrber());
        return false;
    }

    private void insertFriendShipToDb(Friendship f) {
        ContentValues values = Friendship.toContentValues(f); //TODO write methd to convert friendship to contentvale @see Event
        getActivity().getContentResolver().insert(ChoresContract.FriendshipEntry.CONTENT_URI, values);
        getActivity().finish();
    }

    private class UserSubscrber extends Subscriber<User> {
        @Override
        public void onCompleted() {
            unsubscribe();
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, e.getMessage());
        }

        @Override
        public void onNext(User user) {
            if (user != null) {
                Friendship newFriendship = new Friendship();
                newFriendship.setUserId1(authenticatedUser.getId());
                newFriendship.setUserId2(user.getId());
                ChoresApp.instance().getApi().insertFriendship(newFriendship)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new FriendShipSubscriber());
            } else {
                DialogUtils.showErrorDialog(getActivity(), "No Valid Email Entered", "Please enter a valid email address");
            }
        }
    }

    private class FriendShipSubscriber extends Subscriber<Friendship> {

        @Override
        public void onCompleted() {
            unsubscribe();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Friendship friendship) {
            if(friendship != null) {
                insertFriendShipToDb(friendship);
            }
        }
    }
}