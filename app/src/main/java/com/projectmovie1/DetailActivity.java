package com.projectmovie1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY_URL_POSTER = "poster_url";
    public static final String KEY_TITLE = "title";
    public static final String KEY_RELEASE_DATE = "date";
    public static final String KEY_VOTE_AVERAGE = "average";
    public static final String KEY_PLOT_SYNOPSIS = "synopsis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ivPoster = (ImageView) findViewById(R.id.iv_movie_poster);
        TextView tvTitle = (TextView) findViewById(R.id.tv_movie_title);
        TextView tvReleaseDate = (TextView) findViewById(R.id.tv_movie_release_date);
        TextView tvVoteAverage = (TextView) findViewById(R.id.tv_movie_vote_average);
        TextView tvPlotSynopsis = (TextView) findViewById(R.id.tv_movie_plot_synopsis);

        Intent intent = getIntent();
        if (null != intent) { //Null Checking
            String urlText= intent.getStringExtra(KEY_URL_POSTER);
            String titleText= intent.getStringExtra(KEY_TITLE);
            String releaseDateText= intent.getStringExtra(KEY_RELEASE_DATE);
            String averageText= intent.getStringExtra(KEY_VOTE_AVERAGE);
            String synopsisText= intent.getStringExtra(KEY_PLOT_SYNOPSIS);
            setTitle(titleText);
            Picasso.with(this).load(urlText).into(ivPoster);
            tvTitle.setText(titleText);
            tvReleaseDate.setText(releaseDateText);
            tvVoteAverage.setText(averageText);
            tvPlotSynopsis.setText(synopsisText);
        }


    }

}
