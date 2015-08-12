package com.gabilheri.choresapp.detail_event;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import android.net.Uri;


import com.gabilheri.choresapp.BaseCursorListFragment;
import com.gabilheri.choresapp.ChoresApp;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.adapters.CommentsAdapter;
import com.gabilheri.choresapp.adapters.ItemCallback;
import com.gabilheri.choresapp.data.ChoresContract;
import com.gabilheri.choresapp.data.models.Comment;
import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.utils.Const;
import com.gabilheri.choresapp.utils.IntentUtils;
import com.gabilheri.choresapp.utils.QueryUtils;

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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null) {
            Long eventId = getArguments().getLong(Const.EVENT_ID);
            event = QueryUtils.getEventFromDB(eventId);
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
        //TODO implement query
        Uri contentUri = ChoresContract.CommentEntry.CONTENT_URI;
        return new CursorLoader(getActivity(),
                contentUri,
                null,
                ( "" + ChoresContract.CommentEntry.TABLE_NAME + "." + ChoresContract.CommentEntry.COLUMN_EVENT_ID + " = ?"),
                new String[]{ChoresContract.CommentEntry.getIdFromUri(contentUri)},
                null

        );
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

    private class CommentSubscriber extends Subscriber<Comment> {
        @Override
        public void onCompleted() {
            unsubscribe();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Comment comment) {

        }
    }
}

