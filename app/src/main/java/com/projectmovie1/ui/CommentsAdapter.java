package com.projectmovie1.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projectmovie1.R;
import com.projectmovie1.data.model.comments.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cecy on 11/29/17.

 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {
    private final List<Result> comments;
    private final CommentsView commentsView;

    public CommentsAdapter(CommentsView commentsView, List<Result> comments){
        this.commentsView = commentsView;
        this.comments = comments;
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentsViewHolder(view, commentsView);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position){
        holder.bind(comments.get(position));
    }

    @Override
    public int getItemCount(){
        return comments.size();
    }

    public static class CommentsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_comment)
        TextView tvComment;

        final CommentsView commentsView;

        public CommentsViewHolder(View itemView, CommentsView commentsView) {
            super(itemView);
            this.commentsView = commentsView;
            ButterKnife.bind(this, itemView);
        }

        public void bind(Result comment){
            tvAuthor.setText(comment.getAuthor());
            tvComment.setText(comment.getContent());
        }
    }
}

