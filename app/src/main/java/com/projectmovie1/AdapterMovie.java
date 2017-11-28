package com.projectmovie1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectmovie1.Models.Result;
import com.projectmovie1.Utilities.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by cecy on 9/19/17.
 */

public class AdapterMovie extends BaseAdapter{
    private List<Result> movies;
    private Context context;
    public AdapterMovie(Context context, List<Result> movies) {
        this.movies = movies;
        this.context = context;
    }



    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Result getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, parent, false);
        }

        ImageView imagePosterMovie = (ImageView) view.findViewById(R.id.iv_movie_image);
        TextView titleMovie = (TextView) view.findViewById(R.id.tv_movie_name);

        final Result movie = getItem(position);
        Picasso.with(context).load(Utils.IMAGE_URL+movie.getPosterPath()).into(imagePosterMovie);
        titleMovie.setText(movie.getOriginalTitle());

        return view;
    }
}
