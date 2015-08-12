package com.gabilheri.choresapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.data.models.Comment;
import com.gabilheri.choresapp.data.models.User;
import com.gabilheri.choresapp.utils.QueryUtils;
import com.gabilheri.choresapp.utils.SocialUtils;
import com.gabilheri.choresapp.utils.TimeUtils;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/21/15.
 */
public class CommentsAdapter extends CursorRecyclerAdapter<CommentsAdapter.ViewHolder> {

    ItemCallback callback;
    int[] colorResources;
    int colorsLength;
    Random random = new Random();

    public CommentsAdapter(Cursor c, ItemCallback callback) {
        super(c);
        this.callback = callback;
    }

    void initArray(Context context) {
        colorResources = context.getResources().getIntArray(R.array.colors);
        colorsLength = colorResources.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(colorResources == null) {
            initArray(parent.getContext());
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_comment, parent, false);
        if(callback == null) {
            return new ViewHolder(view);
        } else {
            return new ViewHolder(view, callback);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
        Comment c = Comment.fromCursor(cursor, false);
        User user = QueryUtils.getUserFromDB(Long.parseLong(c.getUserId()));
        Glide.with(holder.itemView.getContext())
                .load(user.getPicUrl())
                .centerCrop()
                .crossFade()
                .into(holder.userPicture);

        holder.userName.setText(SocialUtils.getFirstLastName(user.getFullName()));
//        holder.favoritesCount.setText("" + c.getFavoritesCount());
        holder.comment.setText(c.getText());
        holder.date.setText(TimeUtils.formatBasicDate(Long.parseLong(c.getCreatedAt())));
        holder.bottomLine.setBackgroundColor(colorResources[random.nextInt(colorsLength)]);
        holder.userPicture.setTag(R.id.userPicture, user.getPicUrl());
        holder.itemView.setTag(R.id.commentId, c);
        holder.itemView.setTag(R.id.userName, user.getUsername());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.userPicture)
        public CircleImageView userPicture;

        @Bind(R.id.userName)
        public TextView userName;

        @Bind(R.id.favoritesCount)
        public TextView favoritesCount;

        @Bind(R.id.comment)
        public TextView comment;

        @Bind(R.id.createdAt)
        public TextView date;

        @Bind(R.id.bottomLine)
        public View bottomLine;

        @Bind(R.id.favorite)
        public ImageView favorite;

        private ItemCallback callback;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public ViewHolder(View itemView, ItemCallback callback) {
            super(itemView);
            this.callback = callback;
            ButterKnife.bind(this, itemView);
            userName.setOnClickListener(this);
            userPicture.setOnClickListener(this);
            favorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callback.onItemClick(v);
        }
    }
}
