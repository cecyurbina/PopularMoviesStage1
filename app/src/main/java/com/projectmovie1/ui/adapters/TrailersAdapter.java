package com.projectmovie1.ui.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectmovie1.R;
import com.projectmovie1.data.model.videos.Result;
import com.projectmovie1.ui.view.TrailersView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cecy on 11/29/17.

 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.VideoViewHolder> {
    private final List<Result> trailers;
    private final TrailersView view;

    public TrailersAdapter(TrailersView view, List<Result> trailers){
        this.view = view;
        this.trailers = trailers;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false);
        return new VideoViewHolder(view, this.view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position){
        holder.bind(trailers.get(position));
    }

    @Override
    public int getItemCount(){
        return trailers.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_movie)
        ImageView ivPhoto;
        @BindView(R.id.tv_name)
        TextView tvTitle;

        final TrailersView view;
        Result trailer;

        public VideoViewHolder(View itemView, TrailersView view) {
            super(itemView);
            this.view = view;
            ButterKnife.bind(this, itemView);
        }

        public void bind(Result trailer){
            this.trailer = trailer;
            tvTitle.setText(trailer.getName());
        }

        @OnClick(R.id.fl_image)
        public void clickedUserName()
        {
            watchYoutubeVideo(itemView.getContext(), trailer.getKey());
        }
    }

    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
}

