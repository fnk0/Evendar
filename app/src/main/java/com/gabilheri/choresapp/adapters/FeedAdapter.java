package com.gabilheri.choresapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gabilheri.choresapp.models.Feed;
import com.gabilheri.choresapp.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    List<Feed> feedList;

    public FeedAdapter(List<Feed> feedList) {
        this.feedList = feedList;
    }

    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedAdapter.ViewHolder holder, int position) {
        Feed feed = feedList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(feed.getUserPicUrl())
                .centerCrop()
                .crossFade()
                .into(holder.userPicture);
        holder.feedTitle.setText(feed.getTitle());
        holder.favoritesCount.setText(String.valueOf(feed.getFavoritesCount()));
        holder.commentsCount.setText(String.valueOf(feed.getCommentsCount()));
        holder.sharesCount.setText(String.valueOf(feed.getSharesCount()));
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

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
            ButterKnife.bind(this, itemView);
        }
    }

}
