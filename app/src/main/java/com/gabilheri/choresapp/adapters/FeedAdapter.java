package com.gabilheri.choresapp.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.data.models.Event;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.utils.QueryUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.gabilheri.choresapp.data.ChoresContract.EventEntry;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class FeedAdapter extends CursorRecyclerAdapter<FeedAdapter.ViewHolder> {

    ItemCallback callback;

    public FeedAdapter(Cursor c, ItemCallback callback) {
        super(c);
        this.callback = callback;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor) {

        String username = cursor.getString(cursor.getColumnIndex(EventEntry.COLUMN_USER_ID));
        User user = QueryUtils.getUserFromDB(holder.itemView.getContext(), username);
        Glide.with(holder.itemView.getContext())
                .load(user.getPicUrl())
                .fitCenter()
                .crossFade()
                .into(holder.userPicture);

        holder.itemView.setTag(R.id.userName, username);
        holder.itemView.setTag(R.id.userProfile, user.getPicUrl());

        Event event = Event.fromCursor(cursor, false);

        holder.feedTitle.setText(event.getTitle());
        holder.favoritesCount.setText(String.valueOf(event.getNumFavorites()));
        holder.commentsCount.setText(String.valueOf(event.getNumComments()));
        holder.sharesCount.setText(String.valueOf(event.getNumShares()));
    }

    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_feed, parent, false);
        if(callback == null) {
            return new ViewHolder(view);
        } else {
            return new ViewHolder(view, callback);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ItemCallback callback;

        @Bind(R.id.date)
        public TextView date;

        @Bind(R.id.feedTitle)
        public TextView feedTitle;

        @Bind(R.id.comments)
        public ImageView comments;

        @Bind(R.id.commentsCount)
        public TextView commentsCount;

        @Bind(R.id.favorites)
        public ImageView favorites;

        @Bind(R.id.favoritesCount)
        public TextView favoritesCount;

        @Bind(R.id.shares)
        public ImageView shares;

        @Bind(R.id.sharesCount)
        public TextView sharesCount;

        @Bind(R.id.userPicture)
        public CircleImageView userPicture;

        @Bind(R.id.details)
        public TextView details;

        public ViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        public ViewHolder(View itemView, ItemCallback callback) {
            super(itemView);
            this.callback = callback;
            init(itemView);
        }

        void init(View itemView) {
            ButterKnife.bind(this, itemView);
            // Takes to the profile of the associated user
            userPicture.setOnClickListener(this);
            // Takes to detail and scrolls to comments
            comments.setOnClickListener(this);
            // Favorites this event
            favorites.setOnClickListener(this);
            // Shares this event
            shares.setOnClickListener(this);
            // Takes to the details page
            details.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callback.onItemClick(v);
        }
    }

}
