package com.projectmovie1.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.projectmovie1.R;
import com.projectmovie1.data.DatabaseHelper;
import com.projectmovie1.data.model.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GeneralInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralInfoFragment extends Fragment {
    private static final String ARG_ID = "id";
    private static final String ARG_TITLE = "title";
    private static final String ARG_RELEASE_DATE = "release_date";
    private static final String ARG_VOTE_AVERAGE = "average";
    private static final String ARG_PLOT_SYNOPSIS = "synopsis";
    private static final String ARG_URL_POSTER = "url_poster";

    private int id;
    private String title;
    private String releaseDate;
    private String voteAverage;
    private String plotSynopsis;
    private String urlPoster;

    @BindView(R.id.tv_movie_title) TextView tvTitle;
    @BindView(R.id.tv_movie_release_date) TextView tvReleaseDate;
    @BindView(R.id.tv_movie_vote_average) TextView tvVoteAverage;
    @BindView(R.id.tv_movie_plot_synopsis) TextView tvPlotSynopsis;
    @BindView(R.id.ib_fav) ImageButton ibFavorite;


    public GeneralInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GeneralInfoFragment.
     */
    public static GeneralInfoFragment newInstance(String title,
                                                  String releaseDate,
                                                  String voteAverage,
                                                  String plotSynopsis,
                                                  String urlPoster,
                                                  int id) {
        GeneralInfoFragment fragment = new GeneralInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_RELEASE_DATE, releaseDate);
        args.putString(ARG_VOTE_AVERAGE, voteAverage);
        args.putString(ARG_PLOT_SYNOPSIS, plotSynopsis);
        args.putInt(ARG_ID, id);
        args.putString(ARG_URL_POSTER, urlPoster);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            releaseDate = getArguments().getString(ARG_RELEASE_DATE);
            voteAverage = getArguments().getString(ARG_VOTE_AVERAGE);
            plotSynopsis = getArguments().getString(ARG_PLOT_SYNOPSIS);
            urlPoster = getArguments().getString(ARG_URL_POSTER);
            id = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_general_info, container, false);
        ButterKnife.bind(this, view);
        tvTitle.setText(title);
        tvReleaseDate.setText(releaseDate);
        tvVoteAverage.setText(voteAverage);
        tvPlotSynopsis.setText(plotSynopsis);
        setFavourite();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.ib_fav)
    public void submit(View view) {
        DatabaseHelper helper = DatabaseHelper.getInstance(getContext());
        if (helper.isFavorite(id)){
            helper.removeFavorite(id);
            ibFavorite.setImageResource(R.drawable.ic_star_border_black_24dp);
        } else {
            Result result = new Result();
            result.setId(id);
            result.setTitle(title);
            result.setOverview(plotSynopsis);
            result.setPosterPath(urlPoster);
            result.setReleaseDate(releaseDate);
            result.setVoteAverage(Double.valueOf(voteAverage));
            helper.addFavorite(result);
            ibFavorite.setImageResource(R.drawable.ic_star_black_24dp);
        }
    }

    /**
     *
     */
    private void setFavourite(){
        DatabaseHelper helper = DatabaseHelper.getInstance(getContext());
        if (helper.isFavorite(id)){
           ibFavorite.setImageResource(R.drawable.ic_star_black_24dp);
        } else {
            ibFavorite.setImageResource(R.drawable.ic_star_border_black_24dp);
        }
    }
}
