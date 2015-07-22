package com.gabilheri.choresapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.models.Comment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    List<Comment> commentList;
    ItemCallback callback;
    Context context;
    int[] colorResources;
    int colorsLength;
    Random random = new Random();

    private static final SimpleDateFormat simpleDateFormat;

    static  {
        simpleDateFormat = new SimpleDateFormat("MMM, dd yyyy", Locale.US);
    }

    public CommentsAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public CommentsAdapter(List<Comment> commentList, ItemCallback callback) {
        this.commentList = commentList;
        this.callback = callback;
    }

    public CommentsAdapter(List<Comment> commentList, ItemCallback callback, Context context) {
        this.commentList = commentList;
        this.callback = callback;
        this.context = context;
        initArray();
    }

    void initArray() {
        colorResources = context.getResources().getIntArray(R.array.colors);
        colorsLength = colorResources.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_comment, parent, false);
        if(callback == null) {
            return new ViewHolder(view);
        } else {
            return new ViewHolder(view, callback);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment c = commentList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(c.getUserPicture())
                .centerCrop()
                .crossFade()
                .into(holder.userPicture);

        holder.userName.setText(c.getUserName());
        holder.favoritesCount.setText("" + c.getFavoritesCount());
        holder.comment.setText(c.getComment());
        holder.date.setText(simpleDateFormat.format(new Date(c.getDate())));
        if(context != null) {
            holder.bottomLine.setBackgroundColor(colorResources[random.nextInt(colorsLength)]);
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
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

        @Bind(R.id.date)
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
            favorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callback.onItemClick(v);
        }
    }
}
