package com.gabilheri.choresapp.detail_event;

import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gabilheri.choresapp.BaseCursorListFragment;
import com.gabilheri.choresapp.ChoresApp;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.CommentsAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.models.Comment;
import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.utils.Const;
import com.gabilheri.choresapp.utils.IntentUtils;
import com.gabilheri.choresapp.utils.QueryUtils;

import org.joda.time.LocalDateTime;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/21/15.
 */
public class CommentsFragment extends BaseCursorListFragment implements ItemCallback {

    private static final int COMMENTS_LOADER = 0;

    Event event;

    @Bind(R.id.fab)
    FloatingActionButton comment;

    MaterialDialog mShareDialog;
    EditText messageText;

    User mAutheticatedUser;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null) {
            Long eventId = getArguments().getLong(Const.EVENT_ID);
            event = QueryUtils.getEventFromDB(eventId);
        }

        mAutheticatedUser = QueryUtils.getAuthenticatedUserFromDB();

        mShareDialog = new MaterialDialog.Builder(getActivity())
                .customView(R.layout.dialog_comment, false)
                .positiveColor(getResources().getColor(R.color.primary))
                .neutralColor(getResources().getColor(R.color.accent_color))
                .positiveText("Submit")

                .neutralText("Dismiss")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);

                        Comment c = new Comment();
                        c.setText(messageText.getText().toString());
                        LocalDateTime now = LocalDateTime.now();
                        String time = String.valueOf(now.toDate().getTime());
                        c.setCreatedAt(time);
                        c.setUpdatedAt(time);
                        c.setUserId(String.valueOf(mAutheticatedUser.getId()));
                        insertComment(c);
                    }

                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        super.onNeutral(dialog);
                        dialog.dismiss();
                    }
                })
                .build();

        if(mShareDialog.getCustomView() != null) {
            messageText = (EditText) mShareDialog.getCustomView().findViewById(R.id.image_caption);

        }

        mAdapter = new CommentsAdapter(null, this);
        initCardsList(mAdapter);
    }

    @Override
    public void onItemClick(View view) {
        Comment comment = (Comment) view.getTag(R.id.eventId);
        switch (view.getId()) {
            case R.id.userName:
            case R.id.userPicture:
                // Goes to the user profile
                CircleImageView imgView = (CircleImageView) view.findViewById(R.id.userPicture);
                String username = view.getTag(R.id.userName).toString();
                IntentUtils.openUserProfile(getActivity(), username, imgView);
                break;
            case R.id.favorite:
                // "Likes" this comment
                //TODO this sort of gonna crash, I believe!!
                updateComment(comment);
                break;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri contentUri = ChoresContract.CommentEntry.CONTENT_URI;
        return new CursorLoader(getActivity(),
                contentUri,
                null,
                null,
                null,
                null
        );
    }

    @OnClick(R.id.fab)
    public void comment(View v) {
        mShareDialog.show();
    }

    @Override
    protected int[] getLoaders() {
        return new int[] {COMMENTS_LOADER};
    }

    private void insertComment(Comment comment) {
        ChoresApp.instance().getApi().insertComment(comment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommentSubscriber());
    }

    private void updateComment(Comment comment) {
        ChoresApp.instance().getApi().updateComment(comment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommentSubscriber());
    }

    private class CommentListSubscriber extends Subscriber<List<Comment>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Comment> comments) {

        }
    }

    private class CommentSubscriber extends Subscriber<Comment> {
        @Override
        public void onCompleted() {
            unsubscribe();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("CommentFragment", "Error inserting comment", e);
        }

        @Override
        public void onNext(Comment comment) {
            if(comment != null) {
                ContentValues commentValues = Comment.toContentValues(comment);
                getActivity().getContentResolver().insert(ChoresContract.CommentEntry.CONTENT_URI, commentValues);
            }
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.comment_list_fragment;
    }
}

